package com.playground.arch.britt.networkingdemo.network;

import com.playground.arch.britt.networkingdemo.data.model.VenuePhotosResponse;
import com.playground.arch.britt.networkingdemo.data.model.VenuesResponse;

import retrofit2.Call;

public class AppApiClient {
    static AppApiClient instance = new AppApiClient();

    public static AppApiClient get() {
        return instance;
    }

    public Call<VenuesResponse> getVenues() {
//        final MutableLiveData<VenuesResponse> data = new MutableLiveData<>();
        String location = "32.070080,34.794145";
        return ServiceGenerator.getFoursquareService()
                .getVenues(location);

//                .enqueue(new Callback<VenuesResponse>() {
//            @Override
//            public void onResponse(Call<VenuesResponse> call, Response<VenuesResponse> response) {
//                data.setValue(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<VenuesResponse> call, Throwable t) {
//                //                handle failure
//            }
//        });
//        return data;
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
