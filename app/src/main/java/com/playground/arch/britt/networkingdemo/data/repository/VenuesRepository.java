package com.playground.arch.britt.networkingdemo.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.content.Context;

import com.playground.arch.britt.networkingdemo.AppExecutors;
import com.playground.arch.britt.networkingdemo.data.db.AppDatabase;
import com.playground.arch.britt.networkingdemo.data.db.VenueDao;
import com.playground.arch.britt.networkingdemo.data.model.Venue;
import com.playground.arch.britt.networkingdemo.data.model.VenuePhotosResponse;
import com.playground.arch.britt.networkingdemo.data.model.VenuesResponse;
import com.playground.arch.britt.networkingdemo.data.model.local.VenueData;
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

    public LiveData<List<VenueData>> getVenues(String location) {
        LiveData<String[]> venuesFromNet = fetchVenues(location);
        return Transformations.switchMap(venuesFromNet,
                ids -> venueDao.loadByIds(ids, VenueData.FOURSQUARE));

    }

    public LiveData<VenueData> getVenue(String venueId) {
        return venueDao.loadVenue(venueId);
    }

    private LiveData<String[]> fetchVenues(String location) {
        MutableLiveData<String[]> venueIds = new MutableLiveData<>();
        AppExecutors.get().networkIO().execute(() -> {

            try {
                Venue venue;
                VenueData venueLocal;
                List<VenueData> list = new ArrayList<>();
                VenuesResponse venuesResponse = client.getVenues(location).execute().body();
                List<VenuesResponse.Item> items = venuesResponse.getGroups().get(0).getItems();
                String[] ids = new String[items.size()];
                for (int i = 0; i < items.size(); i++) {
                    venue = items.get(i).getVenue();
                    ids[i] = venue.getId();
                    venueLocal = new VenueData(venue.getId(), venue.getName(), VenueData.FOURSQUARE);
                    list.add(venueLocal);
//                    if (shouldRefreshPhoto(item)) {
                    refreshPhoto(venueLocal);
//                    }
                }
                venueDao.save(list);

                venueIds.setValue(ids);

            } catch (IOException e) {
            }


        });
        return venueIds;
    }

    private void refreshPhoto(VenueData venue) {
        getVenuePhotos(venue);

    }


    public void getVenuePhotos(final VenueData venue) {
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
