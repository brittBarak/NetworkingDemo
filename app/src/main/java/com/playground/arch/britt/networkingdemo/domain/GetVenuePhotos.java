package com.playground.arch.britt.networkingdemo.domain;

import android.arch.lifecycle.LiveData;

import com.playground.arch.britt.networkingdemo.data.repository.VenuePhotosRepository;
import com.playground.arch.britt.networkingdemo.data.model.VenuePhotosResponse;


public class GetVenuePhotos {
    VenuePhotosRepository repository = VenuePhotosRepository.get();


    public LiveData<VenuePhotosResponse> execute(String venueId) {
        return repository.getVenuePhotos(venueId);

    }

}
