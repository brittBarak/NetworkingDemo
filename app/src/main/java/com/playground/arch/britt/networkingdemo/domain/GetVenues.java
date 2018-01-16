package com.playground.arch.britt.networkingdemo.domain;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.content.Context;
import android.support.annotation.NonNull;

import com.playground.arch.britt.networkingdemo.data.model.local.VenueData;
import com.playground.arch.britt.networkingdemo.data.repository.VenuesRepository;
import com.playground.arch.britt.networkingdemo.presentation.model.VenueViewModel;

import java.util.ArrayList;
import java.util.List;

public class GetVenues {
    VenuesRepository repository;
    MutableLiveData<double[]> locationLiveD = new MutableLiveData<>();
    LiveData<List<VenueViewModel>> viewModelsLiveD;

    public GetVenues(Context context) {
        repository = VenuesRepository.get(context);

        LiveData<String> locationStrLiveD = Transformations.map(locationLiveD, newLocation ->
                (newLocation == null) ? null : String.format("%s,%1s", newLocation[0], newLocation[1]));

        LiveData<List<VenueData>> dataModelsLiveD = Transformations.switchMap(locationStrLiveD,
                newLocationStr -> (newLocationStr == null) ? null : repository.getVenues(newLocationStr));

        viewModelsLiveD = Transformations.map(dataModelsLiveD, newData -> createVenuesViewModel(newData));
    }


    private List<VenueViewModel> createVenuesViewModel(List<VenueData> list) {
        List<VenueViewModel> venuesVM = new ArrayList<>();
        VenueViewModel venueViewModel;
        for (VenueData item : list) {
            venueViewModel = createViewModel(item);
            venuesVM.add(venueViewModel);
        }
        return venuesVM;
    }


    @NonNull
    private VenueViewModel createViewModel(VenueData item) {
        return new VenueViewModel(item.originId, item.name, item.photoUrl);
    }


    public MutableLiveData<double[]> getLocationLiveD() {
        return locationLiveD;
    }

    public LiveData<List<VenueViewModel>> getViewModelsLiveD() {
        return viewModelsLiveD;
    }
}
