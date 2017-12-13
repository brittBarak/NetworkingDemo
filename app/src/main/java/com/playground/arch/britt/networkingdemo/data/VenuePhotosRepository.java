package com.playground.arch.britt.networkingdemo.data;

import android.arch.lifecycle.LiveData;

import com.playground.arch.britt.networkingdemo.network.AppApiClient;

public class VenuePhotosRepository {
    private static VenuePhotosRepository instance;
    private AppApiClient client = AppApiClient.get();

    public static VenuePhotosRepository get() {
        if (instance == null) {
            instance = new VenuePhotosRepository();
        }
        return instance;
    }

    public LiveData<VenuePhotosResponse> getVenuePhotos(final String venueId) {
        return client.getVenuePhotos(venueId);
    }

}
