package com.leodeleon.password.di;


import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AndroidModule.class,
        AppModule.class,
        PresenterModule.class,
        DataModule.class
})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}