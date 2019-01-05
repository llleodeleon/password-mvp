package com.leodeleon.password.views.security.mvp;

import android.support.annotation.Nullable;

import com.leodeleon.password.data.DataRepository;

import javax.inject.Inject;

public class SecurityPresenter implements SecurityContract.Presenter {

    private final DataRepository mRepo;

    @Nullable
    private SecurityContract.View mView;

    @Inject
    SecurityPresenter(DataRepository repository) {
        mRepo = repository;
    }

    @Override
    public void takeView(SecurityContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }

    @Override
    public void submitPassword(String password) {
        boolean valid = mRepo.isValidPassword(password);
        if(mView!=null){
            if(valid){
                mView.showMainView();
            } else {
                mView.showWrongPasswordAlert();
            }
        }
    }
}
