package com.example.customtablayoutdemo.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author : zhiwen.yang
 * date   : 2020/4/7
 * desc   :
 */
public abstract class BaseMVPActivity<T extends BaseContract.BasePresenter> extends AppCompatActivity implements BaseContract.BaseView {

    public Context mContext;
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        mContext = this;
        attachView();
        initData();
    }


    /**
     * 填充布局
     *
     * @return
     */
    public abstract int getLayoutResource();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 是否注册EventBus
     * @return
     */
    protected abstract boolean isRegisterEventBus();

    /**
     * 实例化presenter
     * @return presenter
     */
    protected abstract T initPresenter();

    /**
     * 贴上view
     */
    private void attachView() {
        if (mPresenter == null) {
            initPresenter();
        }
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    /**
     * 分离view
     */
    private void detachView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detachView();
        mContext = null;
        mPresenter = null;
    }

}
