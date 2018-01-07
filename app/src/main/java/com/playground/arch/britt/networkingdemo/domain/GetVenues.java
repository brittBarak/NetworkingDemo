package com.playground.arch.britt.networkingdemo.domain;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.content.Context;
import android.support.annotation.NonNull;

import com.playground.arch.britt.networkingdemo.data.model.local.VenueLocal;
import com.playground.arch.britt.networkingdemo.data.repository.VenuesRepository;
import com.playground.arch.britt.networkingdemo.presentation.model.VenueViewModel;

import java.util.ArrayList;
import java.util.List;

public class GetVenues {
    VenuesRepository repository;
    LiveData<List<VenueViewModel>> viewModels;

    public GetVenues(Context context) {
        repository = VenuesRepository.get(context);
        LiveData<List<VenueLocal>> data = repository.getVenues();
        viewModels = Transformations.switchMap(data, this::createVenuesViewModel);
    }


    private LiveData<List<VenueViewModel>> createVenuesViewModel(List<VenueLocal> list) {
        MutableLiveData<List<VenueViewModel>> result = new MutableLiveData<>();
        List<VenueViewModel> venuesVM = new ArrayList<>();
        VenueViewModel venueViewModel;
        for (VenueLocal item : list) {
            venueViewModel = createViewModel(item);
            venuesVM.add(venueViewModel);
        }

        result.setValue(venuesVM);
        return result;
    }

    @NonNull
    private VenueViewModel createViewModel(VenueLocal item) {
        return new VenueViewModel(item.originId, item.name, item.photoUrl);
    }


    public LiveData<List<VenueViewModel>> getLiveDataForView() {
        return viewModels;
    }
}
