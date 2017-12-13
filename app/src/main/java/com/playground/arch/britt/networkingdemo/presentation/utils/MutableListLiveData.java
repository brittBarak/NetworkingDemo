package com.playground.arch.britt.networkingdemo.presentation.utils;

import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;


public class MutableListLiveData<T> extends MutableLiveData<MutableListLiveData.ListInfo<T>> {

    public MutableListLiveData() {
        setValue(new ListInfo<T>());
    }

    public void setItems(List<T> newItems) {
        getValue().indexChanged = -1;
        getValue().items = newItems;
        setValue(getValue());

    }

    public void setItem(int index, T item) {
        getValue().indexChanged = index;
        getValue().items.set(index, item);
        setValue(getValue());


    }

    public List<T> getItems() {
        return getValue().items;
    }

    public int getIndexChanged() {
        return getValue().indexChanged;
    }


    public static class ListInfo<T> {
        private int indexChanged = -1;
        private List<T> items = new ArrayList<>();

        public int getIndexChanged() {
            return indexChanged;
        }

        public List<T> getItems() {
            return items;
        }
    }
}
