package com.leodeleon.password.di;

import com.leodeleon.password.MainActivity;
import com.leodeleon.password.views.main.MainFragment;
import com.leodeleon.password.views.security.SecurityFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AndroidModule {
    @ContributesAndroidInjector
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector
    abstract MainFragment mainFragment();

    @ContributesAndroidInjector
    abstract SecurityFragment securityFragment();
}
