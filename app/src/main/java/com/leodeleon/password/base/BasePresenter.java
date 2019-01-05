package com.leodeleon.password.base;

public interface BasePresenter<T> {
    void takeView(T view);
    void dropView();
}
