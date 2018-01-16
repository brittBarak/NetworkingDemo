package com.playground.arch.britt.networkingdemo.network;

import com.playground.arch.britt.networkingdemo.data.model.VenuePhotosResponse;
import com.playground.arch.britt.networkingdemo.data.model.VenuesResponse;

import retrofit2.Call;

public class AppApiClient {
    static AppApiClient instance = new AppApiClient();

    public static AppApiClient get() {
        return instance;
    }

    public Call<VenuesResponse> getVenues(String location) {
        return ServiceGenerator.getFoursquareService()
                .getVenues(location);
    }


    public Call<VenuePhotosResponse> getVenuePhotos(String venueId) {
//        final MutableLiveData<VenuePhotosResponse> data = new MutableLiveData<>();

        Call<VenuePhotosResponse> call = ServiceGenerator.getFoursquareService().getVenuePhotos(venueId);

//        call.enqueue(new Callback<VenuePhotosResponse>() {
//            @Override
//            public void onResponse(Call<VenuePhotosResponse> call, Response<VenuePhotosResponse> response) {
//                data.setValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<VenuePhotosResponse> call, Throwable t) {
////                handle failure
//            }
//        });
//        return data;

        return call;
    }
}
