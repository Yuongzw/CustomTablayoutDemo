package com.example.customtablayoutdemo.base;

/**
 * @author : zhiwen.yang
 * date   : 2020/4/7
 * desc   :
 */
public interface BaseContract {
    interface BasePresenter<T extends BaseView> {

        void attachView(T view);

        void detachView();
    }

    interface BaseModel {

    }

    interface BaseView {

    }
}
