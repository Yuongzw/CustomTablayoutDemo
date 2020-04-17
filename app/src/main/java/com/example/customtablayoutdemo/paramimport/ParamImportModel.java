package com.example.customtablayoutdemo.paramimport;

import com.example.customtablayoutdemo.base.BaseModel;
import com.example.customtablayoutdemo.model.BaseDataParser;
import com.example.customtablayoutdemo.model.BaseStation;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : zhiwen.yang
 * date   : 2020/4/7
 * desc   :
 */
public class ParamImportModel extends BaseModel<ParamImportPresenter> implements ParamImportContract.Model {
    /**
     * 基站文件解析类
     */
    private BaseDataParser baseDataParser = new BaseDataParser();

    ParamImportModel(ParamImportPresenter importPresenter) {
        super(importPresenter);
    }

    @Override
    public void parseBaseFile(final String filePath, final int mapType) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<BaseStation> baseList;
                baseList = baseDataParser.parse(filePath, null);
                if (mapType == BaseStation.MAPTYPE_OUTDOOR && baseList != null && baseList.size() > 0) {
                    for (BaseStation base : baseList) {
                        if (!correctLonAndLat(base)) {
                            continue;
                        }
                    }
                    mPresenter.transferBaseList(baseList);
                }
                mPresenter.transferBaseList(null);
            }
        }).start();
    }


    /**
     * 纠偏位置信息处理<BR>
     *
     * @param baseStation
     *          基站对象
     * @return
     */
    private boolean correctLonAndLat(BaseStation baseStation) {
        try {
            // 纠偏google坐标
            baseStation.googleLatitude = new BigDecimal(baseStation.latitude).setScale(8, BigDecimal.ROUND_HALF_DOWN).doubleValue();
            baseStation.googleLongitude = new BigDecimal( baseStation.longitude).setScale(8, BigDecimal.ROUND_HALF_DOWN).doubleValue();
            baseStation.baiduLatitude = new BigDecimal(baseStation.latitude).setScale(8, BigDecimal.ROUND_HALF_DOWN).doubleValue();
            baseStation.baiduLongitude = new BigDecimal(baseStation.longitude).setScale(8, BigDecimal.ROUND_HALF_DOWN).doubleValue();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
