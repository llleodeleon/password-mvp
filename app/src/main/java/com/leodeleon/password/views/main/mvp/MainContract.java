package com.leodeleon.password.views.main.mvp;

import com.leodeleon.password.base.BasePresenter;
import com.leodeleon.password.base.BaseView;

public interface MainContract {
    interface View extends BaseView<Presenter> {
        void showPasswordSaved();
        void revealFields();
        void clearFields();
    }

    interface Presenter extends BasePresenter<View> {
        void savePassword(String password);
        void enableSecureMode();
        void disableSecureMode();
        void checkSecureState();
    }
}
