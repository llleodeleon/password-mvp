package com.leodeleon.password.di;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

@Module
abstract class AppModule {
    @Binds
    abstract Context bindContext(Application application);
}
