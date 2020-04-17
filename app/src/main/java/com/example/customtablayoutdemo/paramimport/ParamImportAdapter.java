package com.example.customtablayoutdemo.paramimport;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customtablayoutdemo.R;
import com.example.customtablayoutdemo.model.BaseStation;
import com.example.customtablayoutdemo.model.BaseStationDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : zhiwen.yang
 * date   : 2020/4/7
 * desc   :
 */
public class ParamImportAdapter extends RecyclerView.Adapter<ParamImportAdapter.MyViewHolder> {

    /**
     * 数据源
     */
    private List<BaseStationDetail> baseStationDetails;

    /**
     * 选择的个数
     */
    private int selectCount;

    private OnChangeSelectedListener onChangeSelectedListener;

    private Map<Integer, Boolean> checkStatus;//用来记录所有checkbox的状态


    @SuppressLint("UseSparseArrays")
    ParamImportAdapter() {
        checkStatus = new HashMap<>();
    }

    public ParamImportAdapter(List<BaseStationDetail> baseStationDetails) {
        this.baseStationDetails = baseStationDetails;
    }

    void setBaseStationDetail(List<BaseStationDetail> baseStationDetails) {
        this.baseStationDetails = baseStationDetails;
        for (int i = 0; i < baseStationDetails.size(); i++) {
            checkStatus.put(i, true);//默认为全选
        }
    }

    void setOnChangeSelectedListener(OnChangeSelectedListener onChangeSelectedListener) {
        this.onChangeSelectedListener = onChangeSelectedListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //绑定子视图
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.param_import_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.tvSiteName.setText(baseStationDetails.get(i).main.name);
        myViewHolder.tvCellName.setText(baseStationDetails.get(i).cellName);

        myViewHolder.ItemCheck.setImageResource(checkStatus.get(i) ? R.drawable.btn_check_on : R.drawable.btn_check_off);
        myViewHolder.llSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkStatus.put(i, (!checkStatus.get(i)));
                if (checkStatus.get(i)) {
                    selectCount++;
                } else {
                    selectCount--;
                }
                notifyItemChanged(i);
                if (selectCount > getItemCount()) {
                    selectCount = getItemCount();
                }
                if (selectCount < 0) {
                    selectCount = 0;
                }
                if (onChangeSelectedListener != null) {
                    if (selectCount == getItemCount()) { //设置tv成反选
                        onChangeSelectedListener.onChangeSelected(false);
                    } else if (selectCount == 0) {  //设置tv为全选
                        onChangeSelectedListener.onChangeSelected(true);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return baseStationDetails != null ? baseStationDetails.size() : 0;
    }

    List<BaseStation> getSelectedBaseStations() {
        List<BaseStation> baseStations = new ArrayList<>();
        String preSiteName = "";//前一个的SiteName
        for (int i = 0; i < baseStationDetails.size(); i++) {
            if (checkStatus.get(i)) {
                //表明已经有这个BaseStation了
                if (preSiteName.equals(baseStationDetails.get(i).main.name)) {
                    for (int j = 0; j < baseStations.size(); j++) {
                        if (baseStations.get(j).name.equals(preSiteName)) {
                            baseStations.get(j).details.add(baseStationDetails.get(i));
                        }
                    }
                } else {//没有这个BaseStation，新建一个
                    BaseStation baseStation = new BaseStation();
                    baseStation.id = baseStationDetails.get(i).main.id;
                    baseStation.mainId = baseStationDetails.get(i).main.mainId;
                    baseStation.longitude = baseStationDetails.get(i).main.longitude;
                    baseStation.latitude = baseStationDetails.get(i).main.latitude;
                    baseStation.baiduLongitude = baseStationDetails.get(i).main.baiduLongitude;
                    baseStation.baiduLatitude = baseStationDetails.get(i).main.baiduLatitude;
                    baseStation.googleLongitude = baseStationDetails.get(i).main.googleLongitude;
                    baseStation.googleLatitude = baseStationDetails.get(i).main.googleLatitude;
                    baseStation.name = baseStationDetails.get(i).main.name;
                    baseStation.enodebId = baseStationDetails.get(i).main.enodebId;
                    baseStation.netType = baseStationDetails.get(i).main.netType;
                    baseStation.detailIndex = baseStationDetails.get(i).main.detailIndex;
                    baseStation.mapType = baseStationDetails.get(i).main.mapType;
                    baseStation.details.add(baseStationDetails.get(i));
                    baseStations.add(baseStation);
                }
                preSiteName = baseStationDetails.get(i).main.name;
            }
        }
        return baseStations;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSiteName;
        private TextView tvCellName;
        private ImageButton ItemCheck;
        private LinearLayout llSelected;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSiteName = itemView.findViewById(R.id.tv_siteName);
            tvCellName = itemView.findViewById(R.id.tv_cellName);
            ItemCheck = itemView.findViewById(R.id.ItemCheck);
            llSelected = itemView.findViewById(R.id.ll_selected);
        }
    }

    /**
     * 全选或反选
     *
     * @param isSelectedAll 是否全选
     */
    void selectedAll(boolean isSelectedAll) {
        for (int i = 0; i < getItemCount(); i++) {
            checkStatus.put(i, isSelectedAll);
        }
        if (isSelectedAll) {
            selectCount = getItemCount();
        } else {
            selectCount = 0;
        }
        notifyDataSetChanged();
    }

    interface OnChangeSelectedListener {
        void onChangeSelected(boolean isSelectedAll);
    }
}
