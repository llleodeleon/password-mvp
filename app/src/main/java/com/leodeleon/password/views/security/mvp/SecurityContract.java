package com.leodeleon.password.views.security.mvp;

import com.leodeleon.password.base.BasePresenter;
import com.leodeleon.password.base.BaseView;

public interface SecurityContract {
    interface View extends BaseView<Presenter> {
        void showWrongPasswordAlert();
        void showMainView();
    }

    interface Presenter extends BasePresenter<View> {
        void submitPassword(String password);
    }
}
