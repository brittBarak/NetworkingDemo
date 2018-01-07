package com.playground.arch.britt.networkingdemo.data.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.playground.arch.britt.networkingdemo.AppExecutors;
import com.playground.arch.britt.networkingdemo.data.db.AppDatabase;
import com.playground.arch.britt.networkingdemo.data.db.VenueDao;
import com.playground.arch.britt.networkingdemo.data.model.Venue;
import com.playground.arch.britt.networkingdemo.data.model.VenuePhotosResponse;
import com.playground.arch.britt.networkingdemo.data.model.VenuesResponse;
import com.playground.arch.britt.networkingdemo.data.model.local.VenueLocal;
import com.playground.arch.britt.networkingdemo.network.AppApiClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VenuesRepository {
    static VenuesRepository instance;
    AppApiClient client = AppApiClient.get();
    VenueDao venueDao;

    public VenuesRepository(Context context) {
        venueDao = AppDatabase.getInMemoryDatabase(context.getApplicationContext()).venueDao();
    }

    public static VenuesRepository get(Context context) {
        if (instance == null) {
            instance = new VenuesRepository(context);
        }
        return instance;
    }


    public LiveData<List<VenueLocal>> getVenues() {
        refreshVenues();
        return venueDao.load();
    }

    private void refreshVenues() {
        AppExecutors.get().networkIO().execute(() -> {

            try {
                VenuesResponse venuesResponse = client.getVenues().execute().body();
                Venue venue;
                VenueLocal venueLocal;
                List<VenueLocal> list = new ArrayList<>();
                List<VenuesResponse.Item> items = venuesResponse.getGroups().get(0).getItems();
                for (int i = 0; i < items.size(); i++) {
                    venue = items.get(i).getVenue();
                    venueLocal = new VenueLocal(venue.getId(), venue.getName(), VenueLocal.FOURSQUARE);
                    list.add(venueLocal);
//                    if (shouldRefreshPhoto(item)) {
                    refreshPhoto(venueLocal);
//                    }
                }
                venueDao.save(list);

            } catch (IOException e) {
            }


        });
    }

    private void refreshPhoto(VenueLocal venue) {
        getVenuePhotos(venue);

    }


    public void getVenuePhotos(final VenueLocal venue) {
        AppExecutors.get().networkIO().execute(() -> {

            try {
                VenuePhotosResponse response = client.getVenuePhotos(venue.originId).execute().body();
                venue.photoUrl = response.getFirstUrl();
                venueDao.updatePhotoUrl(venue);

            } catch (IOException e) {
            }
        });
    }


}
