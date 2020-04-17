package com.example.customtablayoutdemo.paramimport;

import com.bin.david.form.data.column.Column;
import com.example.customtablayoutdemo.base.BaseContract;
import com.example.customtablayoutdemo.model.BaseStation;
import com.example.customtablayoutdemo.model.BaseStationDetail;
import com.example.customtablayoutdemo.model.MyBaseStationDetail;

import java.util.List;

/**
 * @author : zhiwen.yang
 * date   : 2020/4/7
 * desc   :
 */
public interface ParamImportContract {
    interface View extends BaseContract.BaseView {
        void updateRecyclerView(List<BaseStationDetail> stationDetails);

        void showProgressDialog();

        void dismissDialog();

        void updateSmartTable(List<MyBaseStationDetail> detailLTES, Column[] columns, int netType);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void parseBaseFile(String filePath, int mapType);

        void transferBaseList(List<BaseStation> baseStations);
    }

    interface Model extends BaseContract.BaseModel {
        void parseBaseFile(String filePath, int mapType);
    }


}
