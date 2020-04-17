package com.example.customtablayoutdemo.base;

/**
 * @author : zhiwen.yang
 * date   : 2020/4/7
 * desc   :
 */
public class BaseModel<P extends BasePresenter> implements BaseContract.BaseModel {
    protected P mPresenter;

    public BaseModel(P presenter) {
        mPresenter = presenter;
    }
}
