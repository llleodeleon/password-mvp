package com.leodeleon.password.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.leodeleon.password.data.DataRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class DataModule {
    @Provides
    SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    DataRepository securityRepository(SharedPreferences preferences){
        return new DataRepository(preferences);
    }
}
