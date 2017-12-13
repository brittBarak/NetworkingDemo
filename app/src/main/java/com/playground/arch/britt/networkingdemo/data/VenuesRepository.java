package com.playground.arch.britt.networkingdemo.data;

import android.arch.lifecycle.LiveData;

import com.playground.arch.britt.networkingdemo.network.AppApiClient;

public class VenuesRepository {
    static VenuesRepository instance;
    AppApiClient client = AppApiClient.get();

    public static VenuesRepository get() {
        if (instance == null) {
            instance = new VenuesRepository();
        }
        return instance;
    }

    public LiveData<VenuesResponse> getVenues() {
        return client.getVenues();
    }
}
