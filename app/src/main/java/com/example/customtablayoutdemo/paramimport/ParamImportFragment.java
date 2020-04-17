package com.example.customtablayoutdemo.paramimport;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.column.ColumnInfo;
import com.bin.david.form.data.format.title.TitleImageDrawFormat;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.data.table.TableData;
import com.bin.david.form.listener.OnColumnClickListener;
import com.bin.david.form.listener.OnColumnItemClickListener;
import com.bin.david.form.utils.DensityUtils;
import com.example.customtablayoutdemo.R;
import com.example.customtablayoutdemo.base.LazyloadBaseFragment;
import com.example.customtablayoutdemo.model.BaseStation;
import com.example.customtablayoutdemo.model.BaseStationDetail;
import com.example.customtablayoutdemo.model.MyBaseStationDetail;

import java.util.List;

/**
 * @author : zhiwen.yang
 * date   : 2020/4/7
 * desc   :
 */
public class ParamImportFragment extends LazyloadBaseFragment<ParamImportPresenter> implements ParamImportContract.View {
    LinearLayout llHeader;
    RecyclerView rvParam;
    TextView tvFileName;
    CheckBox cbSelected;
    SmartTable table;

    private ProgressDialog progressDialog;

    private ParamImportAdapter adapter;
    private int selectCount;
    private List<BaseStationDetail> stationDetails;


    public static Fragment getInstance(String filePath, int mapType) {
        Bundle bundle = new Bundle();
        bundle.putString("filePath", filePath);
        bundle.putInt("mapType", mapType);
        ParamImportFragment fragment = new ParamImportFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_param_import;
    }

    @Override
    protected void initView(View rootView) {
        llHeader = rootView.findViewById(R.id.ll_Header);
        rvParam = rootView.findViewById(R.id.rv_Param);
        tvFileName = rootView.findViewById(R.id.tv_fileName);
        cbSelected = rootView.findViewById(R.id.cb_Selected);
        table = rootView.findViewById(R.id.table);

        adapter = new ParamImportAdapter();
        //添加Android自带的分割线
//        rvParam.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rvParam.setLayoutManager(new LinearLayoutManager(getContext()));
        rvParam.setAdapter(adapter);
        initListener();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initListener() {
        adapter.setOnChangeSelectedListener(new ParamImportAdapter.OnChangeSelectedListener() {
            @Override
            public void onChangeSelected(boolean isSelectedAll) {
                if (isSelectedAll) {
                    cbSelected.setText("全选");
                    cbSelected.setChecked(false);
                } else {
                    cbSelected.setText("反选");
                    cbSelected.setChecked(true);
                }
            }
        });
        cbSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //必须要有数据才能进行全选反选操作
                if (adapter != null && adapter.getItemCount() > 0) {
                    //反选
                    if (cbSelected.getText().toString().equals("反选")) {
                        adapter.selectedAll(false);
                        cbSelected.setText("全选");
                        cbSelected.setChecked(false);
                    } else {
                        //全选
                        adapter.selectedAll(true);
                        cbSelected.setText("反选");
                        cbSelected.setChecked(true);
                    }
                }
            }
        });
    }

    @Override
    protected ParamImportPresenter initPresenter() {
        return new ParamImportPresenter();
    }

    @Override
    public void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        assert getArguments() != null;
        String filePath = getArguments().getString("filePath", "Unknown");
        int mapType = getArguments().getInt("mapType");
        tvFileName.setText(filePath.substring(filePath.lastIndexOf("/") + 1));
        mPresenter.parseBaseFile(filePath, mapType);
    }

    /**
     * 刷新界面
     *
     * @param stationDetails 数据源
     */
    @Override
    public void updateRecyclerView(final List<BaseStationDetail> stationDetails) {
        this.stationDetails = stationDetails;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                if (adapter != null) {
//                    llHeader.setVisibility(View.VISIBLE);
//                    tvFileName.setVisibility(View.VISIBLE);
//                    adapter.setBaseStationDetail(stationDetails);
//                    adapter.notifyDataSetChanged();
//                }
            }
        });
    }

    @Override
    public void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("导入");
            progressDialog.setMessage("提示。。。");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);
        }
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void dismissDialog() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });

    }

    @Override
    public void updateSmartTable(List<MyBaseStationDetail> detailLTES, Column[] columns, int netType) {
//        table.setData(detailLTES);
        table.getConfig().setContentStyle(new FontStyle(40, Color.GRAY));

        int size = DensityUtils.dp2px(getActivity(), 20); //指定图标大小
        final Column checkColumn = columns[columns.length - 1];
        checkColumn.setOnColumnItemClickListener(new OnColumnItemClickListener<Boolean>() {
            @Override
            public void onClick(Column<Boolean> column, String value, Boolean aBoolean, int position) {
                column.getDatas().set(position, !aBoolean);
                if (!aBoolean) {
                    selectCount++;
                } else {
                    selectCount--;
                }
                if (selectCount > column.getDatas().size()) {
                    selectCount = column.getDatas().size();
                    column.setColumnName("反选");
                }
                if (selectCount < 0) {
                    selectCount = 0;
                    column.setColumnName("全选");
                }
                if (selectCount == column.getDatas().size()) { //设置tv成反选
                    column.setColumnName("反选");
                } else if (selectCount == 0) {  //设置tv为全选
                    column.setColumnName("全选");
                }
                table.invalidate();
            }
        });

        final TableData<MyBaseStationDetail> tableData = new TableData<>("LTE", detailLTES, columns);
        table.setOnColumnClickListener(new OnColumnClickListener() {

            @Override
            public void onClick(ColumnInfo columnInfo) {
                if (!columnInfo.column.isParent()) {
                    if (columnInfo.column == checkColumn) {
                        if ("反选".equals(checkColumn.getColumnName())) {
                            checkColumn.setColumnName("全选");
                            for (int i = 0; i < checkColumn.getDatas().size(); i++) {
                                checkColumn.getDatas().set(i, false);
                            }
                            selectCount = 0;

                        } else {
                            checkColumn.setColumnName("反选");
                            for (int i = 0; i < checkColumn.getDatas().size(); i++) {
                                checkColumn.getDatas().set(i, true);
                            }
                            selectCount = checkColumn.getDatas().size();
                        }
                        table.invalidate();
                    }
                }
            }
        });
        tableData.setTitleDrawFormat(new TitleImageDrawFormat(size, size, TitleImageDrawFormat.LEFT, 10) {
            @Override
            protected Context getContext() {
                return getActivity();
            }

            @Override
            protected int getResourceID(Column column) {
                if (!column.isParent()) {
                    if (column == checkColumn) {
                        column.setTitleAlign(Paint.Align.RIGHT);
                        if ( "反选".equals(checkColumn.getColumnName())) {
                            return R.drawable.checked;
                        } else {
                            return R.drawable.unchecked;
                        }
                    }
                }
                return 0;
            }
        });
        //Y序号列
        table.getConfig().setFixedYSequence(true);
        //X序号列
        table.getConfig().setFixedXSequence(true);
        //列标题
        table.getConfig().setFixedCountRow(true);
        //统计行
        table.getConfig().setFixedTitle(true);

        table.setTableData(tableData);
        selectCount = checkColumn.getDatas().size();

    }

    List<BaseStation> getBaseStations() {
        List<MyBaseStationDetail> t = table.getTableData().getT();

        return null;
    }
}