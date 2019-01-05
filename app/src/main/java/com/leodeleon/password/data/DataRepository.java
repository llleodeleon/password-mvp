package com.leodeleon.password.data;

import android.content.SharedPreferences;

public class DataRepository {
    private static final String PASSWORD  = "PASSWORD";
    private static final String IN_SECURE_MODE = "IN_SECURE_MODE";
    private SharedPreferences mSharedPrefs;
    private String mPassword;
    private Boolean mIsFirstRun = true;

    public DataRepository(SharedPreferences preferences) {
        mSharedPrefs = preferences;
        mPassword = preferences.getString(PASSWORD, "");
    }

    public Boolean isValidPassword(String password) {
        return !mPassword.isEmpty() && mPassword.equals(password);
    }

    public void savePassword(String password) {
        mPassword = password;
        saveString(PASSWORD, password);
    }

    public Boolean isFirstRun() {
        return mIsFirstRun;
    }

    public Boolean isInSecureMode() {
        return mSharedPrefs.getBoolean(IN_SECURE_MODE,false);
    }

    public void removeFirstRun() {
        mIsFirstRun = false;
    }

    public void setSecureMode(){
        saveBoolean(IN_SECURE_MODE, true);
    }

    public void clearSecureMode(){
        clear(PASSWORD);
        clear(IN_SECURE_MODE);
    }

    private void saveString(String key, String value){
        mSharedPrefs.edit().putString(key,value).apply();
    }

    private void saveBoolean(String key, Boolean value){
        mSharedPrefs.edit().putBoolean(key, value).apply();
    }

    private void clear(String key){
        mSharedPrefs.edit().remove(key).apply();
    }

}
