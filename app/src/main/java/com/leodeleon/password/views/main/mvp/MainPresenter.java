package com.leodeleon.password.views.main.mvp;

import android.support.annotation.Nullable;

import com.leodeleon.password.data.DataRepository;

import javax.inject.Inject;

public class MainPresenter implements MainContract.Presenter {
    private DataRepository mRepo;
    @Nullable
    private MainContract.View mView;

    @Inject
    MainPresenter(DataRepository repository) {
        mRepo = repository;
    }

    @Override
    public void takeView(MainContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {
        mView = null;
    }

    @Override
    public void checkSecureState() {
        if(mRepo.isInSecureMode() && mView != null){
            mView.revealFields();
        }
    }

    @Override
    public void savePassword(String password) {
        mRepo.savePassword(password);
        if(mRepo.isFirstRun()){
            mRepo.removeFirstRun();
        }
        if(mView != null){
            mView.clearFields();
            mView.showPasswordSaved();
        }
    }

    @Override
    public void enableSecureMode() {
        mRepo.setSecureMode();
    }

    @Override
    public void disableSecureMode() {
        mRepo.clearSecureMode();
        if(mView != null){
            mView.clearFields();
        }
    }
}
