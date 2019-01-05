package com.leodeleon.password;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.leodeleon.password.data.DataRepository;
import com.leodeleon.password.views.main.MainFragment;
import com.leodeleon.password.views.security.SecurityFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    DataRepository mRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidInjection.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.content);
        if(currentFragment == null) {
            Fragment nextFragment;
            if(mRepo.isFirstRun()){
                nextFragment = new MainFragment();
            } else  {
                nextFragment = new SecurityFragment();
            }
            replaceFragment(nextFragment);
        } else if(currentFragment instanceof MainFragment && mRepo.isInSecureMode()){
                replaceFragment(new SecurityFragment());
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }
}
