package com.leodeleon.password.di;

import com.leodeleon.password.views.main.mvp.MainContract;
import com.leodeleon.password.views.main.mvp.MainPresenter;
import com.leodeleon.password.views.security.mvp.SecurityContract;
import com.leodeleon.password.views.security.mvp.SecurityPresenter;

import dagger.Binds;
import dagger.Module;

@Module
abstract class PresenterModule {
    @Binds abstract MainContract.Presenter mainPresenter(MainPresenter presenter);
    @Binds abstract SecurityContract.Presenter securityPresneter(SecurityPresenter presenter);
}
