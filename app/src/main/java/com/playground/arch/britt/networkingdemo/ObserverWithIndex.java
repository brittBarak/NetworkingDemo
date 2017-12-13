package com.playground.arch.britt.networkingdemo;

import android.arch.lifecycle.Observer;


public abstract class ObserverWithIndex<T> implements Observer<T>{
    private int index;

    public ObserverWithIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
