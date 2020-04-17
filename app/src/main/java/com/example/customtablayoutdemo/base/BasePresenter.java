package com.example.customtablayoutdemo.base;

import java.lang.ref.WeakReference;

/**
 * @author : zhiwen.yang
 * date   : 2020/4/7
 * desc   :
 */
public abstract class BasePresenter<V extends BaseContract.BaseView, M extends BaseModel> implements BaseContract.BasePresenter<V> {
    protected WeakReference<V> mView;
    protected M mModel;

    public BasePresenter() {
        mModel = getModel();
    }

    protected abstract M getModel();

    @Override
    public void attachView(V view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }
}
