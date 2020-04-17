package com.example.customtablayoutdemo.paramimport;

import android.content.Context;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.column.ColumnInfo;
import com.bin.david.form.data.format.draw.ImageResDrawFormat;
import com.bin.david.form.data.format.title.TitleImageDrawFormat;
import com.bin.david.form.data.table.TableData;
import com.bin.david.form.listener.OnColumnClickListener;
import com.bin.david.form.listener.OnColumnItemClickListener;
import com.bin.david.form.utils.DensityUtils;
import com.example.customtablayoutdemo.R;
import com.example.customtablayoutdemo.base.BasePresenter;
import com.example.customtablayoutdemo.model.BaseStation;
import com.example.customtablayoutdemo.model.BaseStationDetail;
import com.example.customtablayoutdemo.model.MyBaseStationDetail;
import com.example.customtablayoutdemo.model.StationDetailCDMA;
import com.example.customtablayoutdemo.model.StationDetailGSM;
import com.example.customtablayoutdemo.model.StationDetailLTE;
import com.example.customtablayoutdemo.model.StationDetailNR;
import com.example.customtablayoutdemo.model.StationDetailTDCDMA;
import com.example.customtablayoutdemo.model.StationDetailWCDMA;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhiwen.yang
 * date   : 2020/4/7
 * desc   :
 */
public class ParamImportPresenter extends BasePresenter<ParamImportContract.View, ParamImportModel> implements ParamImportContract.Presenter {

    @Override
    protected ParamImportModel getModel() {
        return new ParamImportModel(this);
    }

    @Override
    public void parseBaseFile(String filePath, int mapType) {
        mModel.parseBaseFile(filePath, mapType);
        mView.get().showProgressDialog();
    }

    @Override
    public void transferBaseList(List<BaseStation> baseStations) {
        if (baseStations != null && baseStations.size() > 0) {
            List<BaseStationDetail> stationDetails = new ArrayList<>();
            for (BaseStation baseStation : baseStations) {
                stationDetails.addAll(baseStation.details);
            }
            List<MyBaseStationDetail> details = new ArrayList<>();
            for (BaseStationDetail stationDetail : stationDetails) {
                switch (stationDetail.main.netType) {
                    case BaseStation.NETTYPE_LTE:
                        StationDetailLTE detailLTE = new StationDetailLTE(stationDetail.main.name, stationDetail.main.enodebId, stationDetail.sectorId, stationDetail.main.longitude, stationDetail.main.longitude,
                                stationDetail.pci, stationDetail.earfcn, stationDetail.azimuth, stationDetail.cellName, true, stationDetail.main.tac, stationDetail.cellId);
                        details.add(detailLTE);
                        break;
                    case BaseStation.NETTYPE_ENDC:
                        StationDetailNR detailNR = new StationDetailNR(stationDetail.main.name, stationDetail.main.siteId, stationDetail.main.enodebId, stationDetail.main.longitude, stationDetail.main.longitude,
                                stationDetail.pci, stationDetail.earfcn, stationDetail.earfcn, stationDetail.azimuth, stationDetail.cellName, true, stationDetail.main.tac, stationDetail.cellId);
                        details.add(detailNR);
                        break;
                    case BaseStation.NETTYPE_GSM:
                        StationDetailGSM detailGSM = new StationDetailGSM(stationDetail.main.name, stationDetail.main.longitude, stationDetail.main.latitude, stationDetail.azimuth, stationDetail.cellName,
                                stationDetail.bsic, stationDetail.bcch, stationDetail.lac, stationDetail.cellId, true);
                        details.add(detailGSM);
                        break;
                    case BaseStation.NETTYPE_WCDMA:
                        StationDetailWCDMA detailWCDMA = new StationDetailWCDMA(stationDetail.main.name, stationDetail.main.longitude, stationDetail.main.latitude, stationDetail.azimuth, stationDetail.cellName,
                                stationDetail.uarfcn, stationDetail.psc, stationDetail.lac, stationDetail.cellId, true);
                        details.add(detailWCDMA);
                        break;
                    case BaseStation.NETTYPE_TDSCDMA:
                        StationDetailTDCDMA detailTDCDMA = new StationDetailTDCDMA(stationDetail.main.name, stationDetail.main.longitude, stationDetail.main.latitude, stationDetail.azimuth, stationDetail.cellName,
                                stationDetail.uarfcn, stationDetail.cpi, stationDetail.lac, stationDetail.cellId, true);
                        details.add(detailTDCDMA);
                        break;
                    case BaseStation.NETTYPE_CDMA:
                        StationDetailCDMA detailCDMA = new StationDetailCDMA(stationDetail.main.name, stationDetail.main.longitude, stationDetail.main.latitude, stationDetail.azimuth, stationDetail.cellName,
                                stationDetail.frequency, stationDetail.pn, stationDetail.evFreq, stationDetail.evPn, stationDetail.bid, stationDetail.nid, stationDetail.sid, true);
                        details.add(detailCDMA);
                        break;
                    default:
                        break;
                }
            }
            mView.get().updateRecyclerView(stationDetails);
            mView.get().updateSmartTable(details, getColumn(stationDetails.get(0).main.netType), stationDetails.get(0).main.netType);
        }
        mView.get().dismissDialog();
    }

    private Column[] getColumn(int netType) {
        List<Column> columns = new ArrayList<>();
        Column[] columnArray = null;
        int size = DensityUtils.dp2px(((ParamImportFragment) mView.get()).getActivity(), 20); //指定图标大小
        switch (netType) {
            case BaseStation.NETTYPE_LTE:
                addLTEColumn(columns);
                break;
            case BaseStation.NETTYPE_ENDC:
                addNRColumn(columns);
                break;
            case BaseStation.NETTYPE_GSM:
                addGSMColumn(columns);
                break;
            case BaseStation.NETTYPE_WCDMA:
                addWCDMAColumn(columns);
                break;
            case BaseStation.NETTYPE_TDSCDMA:
                addTDSCDMAColumn(columns);
                break;
            case BaseStation.NETTYPE_CDMA:
                addCDMAColumn(columns);
                break;
            default:
                break;
        }


        final Column<Boolean> checkColumn = new Column<>("反选", "isCheck", new ImageResDrawFormat<Boolean>(size, size) {
            @Override
            protected Context getContext() {
                return ((ParamImportFragment) mView.get()).getActivity();
            }

            @Override
            protected int getResourceID(Boolean isCheck, String value, int position) {
                if (isCheck) {
                    return R.drawable.checked;
                }
                return R.drawable.unchecked;
            }
        });

        columns.add(checkColumn);
        columnArray = new Column[columns.size()];
        columns.toArray(columnArray);
        return columnArray;
    }

    private void addCDMAColumn(List<Column> columns) {
        Column<String> siteNameColumn = new Column<>("SiteName", "siteName");
//        siteNameColumn.setFixed(true);
        Column<String> longitudeColumn = new Column<>("Longitude", "longitude");
        Column<String> latitudeColumn = new Column<>("Latitude", "latitude");
        Column<String> aZimuthColumn = new Column<>("AZIMUTH", "azimuth");
        Column<String> cellNameColumn = new Column<>("CellName", "cellName");
        Column<String> frequencyColumn = new Column<>("Frequency", "frequency");
        Column<String> pnColumn = new Column<>("PN", "pn");
        Column<String> evFreqColumn = new Column<>("EV Freq", "evFreq");
        Column<String> evPnColumn = new Column<>("EV PN", "evPn");
        Column<String> bidColumn = new Column<>("BID", "bid");
        Column<String> nidColumn = new Column<>("NID", "nid");
        Column<String> sidColumn = new Column<>("SID", "sid");
        columns.add(siteNameColumn);
        columns.add(longitudeColumn);
        columns.add(latitudeColumn);
        columns.add(aZimuthColumn);
        columns.add(cellNameColumn);
        columns.add(frequencyColumn);
        columns.add(pnColumn);
        columns.add(evFreqColumn);
        columns.add(evPnColumn);
        columns.add(bidColumn);
        columns.add(nidColumn);
        columns.add(sidColumn);

    }

    private void addTDSCDMAColumn(List<Column> columns) {
        Column<String> siteNameColumn = new Column<>("SiteName", "siteName");
//        siteNameColumn.setFixed(true);
        Column<String> longitudeColumn = new Column<>("Longitude", "longitude");
        Column<String> latitudeColumn = new Column<>("Latitude", "latitude");
        Column<String> aZimuthColumn = new Column<>("AZIMUTH", "azimuth");
        Column<String> cellNameColumn = new Column<>("CellName", "cellName");
        Column<String> cellIdColumn = new Column<>("CELLID", "cellId");
        Column<String> uarfcnColumn = new Column<>("UARFCN", "uarfcn");
        Column<String> cpiColumn = new Column<>("CPI", "cpi");
        Column<String> lacColumn = new Column<>("LAC", "lac");
        columns.add(siteNameColumn);
        columns.add(longitudeColumn);
        columns.add(latitudeColumn);
        columns.add(aZimuthColumn);
        columns.add(cellNameColumn);
        columns.add(cellIdColumn);
        columns.add(uarfcnColumn);
        columns.add(cpiColumn);
        columns.add(lacColumn);
    }

    private void addWCDMAColumn(List<Column> columns) {
        Column<String> siteNameColumn = new Column<>("SiteName", "siteName");
//        siteNameColumn.setFixed(true);
        Column<String> longitudeColumn = new Column<>("Longitude", "longitude");
        Column<String> latitudeColumn = new Column<>("Latitude", "latitude");
        Column<String> aZimuthColumn = new Column<>("AZIMUTH", "azimuth");
        Column<String> cellNameColumn = new Column<>("CellName", "cellName");
        Column<String> cellIdColumn = new Column<>("CELLID", "cellId");
        Column<String> uarfcnColumn = new Column<>("UARFCN", "uarfcn");
        Column<String> pscColumn = new Column<>("PSC", "psc");
        Column<String> lacColumn = new Column<>("LAC", "lac");
        columns.add(siteNameColumn);
        columns.add(longitudeColumn);
        columns.add(latitudeColumn);
        columns.add(aZimuthColumn);
        columns.add(cellNameColumn);
        columns.add(cellIdColumn);
        columns.add(uarfcnColumn);
        columns.add(pscColumn);
        columns.add(lacColumn);
    }

    private void addGSMColumn(List<Column> columns) {
        Column<String> siteNameColumn = new Column<>("SiteName", "siteName");
//        siteNameColumn.setFixed(true);
        Column<String> longitudeColumn = new Column<>("Longitude", "longitude");
        Column<String> latitudeColumn = new Column<>("Latitude", "latitude");
        Column<String> aZimuthColumn = new Column<>("AZIMUTH", "azimuth");
        Column<String> cellNameColumn = new Column<>("CellName", "cellName");
        Column<String> cellIdColumn = new Column<>("CELLID", "cellId");
        Column<String> bisicColumn = new Column<>("BSIC", "basic");
        Column<String> bcchColumn = new Column<>("BCCH", "bcch");
        Column<String> lacColumn = new Column<>("LAC", "lac");
        columns.add(siteNameColumn);
        columns.add(longitudeColumn);
        columns.add(latitudeColumn);
        columns.add(aZimuthColumn);
        columns.add(cellNameColumn);
        columns.add(cellIdColumn);
        columns.add(bisicColumn);
        columns.add(bcchColumn);
        columns.add(lacColumn);
    }

    private void addNRColumn(List<Column> columns) {
        Column<String> siteNameColumn = new Column<>("SiteName", "siteName");
//        siteNameColumn.setFixed(true);
        Column<String> siteIdColumn = new Column<>("SiteId", "siteId");
        Column<String> longitudeColumn = new Column<>("Longitude", "longitude");
        Column<String> latitudeColumn = new Column<>("Latitude", "latitude");
        Column<String> pciColumn = new Column<>("PCI", "pci");
        Column<String> aZimuthColumn = new Column<>("AZIMUTH", "azimuth");
        Column<String> cellNameColumn = new Column<>("CellName", "cellName");
        Column<String> eNodeBIDColumn = new Column<>("gNB ID", "eNodeBID");
        Column<String> cellIdColumn = new Column<>("CELLID", "cellId");
        Column<String> tacColumn = new Column<>("TAC", "tac");
        Column<String> ssbArfcnColumn = new Column<>("SSB ARFCN", "ssbArfcn");
        Column<String> centerArfcnColumn = new Column<>("SSB ARFCN", "centerArfcn");
        columns.add(siteNameColumn);
        columns.add(siteIdColumn);
        columns.add(longitudeColumn);
        columns.add(latitudeColumn);
        columns.add(pciColumn);
        columns.add(aZimuthColumn);
        columns.add(eNodeBIDColumn);
        columns.add(ssbArfcnColumn);
        columns.add(centerArfcnColumn);
        columns.add(cellNameColumn);
        columns.add(cellIdColumn);
        columns.add(tacColumn);
    }

    private void addLTEColumn(List<Column> columns) {
        Column<String> siteNameColumn = new Column<>("SiteName", "siteName");
//        siteNameColumn.setFixed(true);
        Column<String> longitudeColumn = new Column<>("Longitude", "longitude");
        Column<String> latitudeColumn = new Column<>("Latitude", "latitude");
        Column<String> pciColumn = new Column<>("PCI", "pci");
        Column<String> aZimuthColumn = new Column<>("AZIMUTH", "azimuth");
        Column<String> cellNameColumn = new Column<>("CellName", "cellName");
        Column<String> eNodeBIDColumn = new Column<>("ENodeBID", "eNodeBID");
        Column<String> sectorIDColumn = new Column<>("SectorID", "sectorID");
        Column<String> earfcnColumn = new Column<>("EARFCN", "earfcn");
        Column<String> cellIdColumn = new Column<>("CELLID", "cellId");
        Column<String> tacColumn = new Column<>("TAC", "tac");
        columns.add(siteNameColumn);
        columns.add(longitudeColumn);
        columns.add(latitudeColumn);
        columns.add(pciColumn);
        columns.add(aZimuthColumn);
        columns.add(cellNameColumn);
        columns.add(eNodeBIDColumn);
        columns.add(sectorIDColumn);
        columns.add(earfcnColumn);
        columns.add(cellIdColumn);
        columns.add(tacColumn);
    }
}
