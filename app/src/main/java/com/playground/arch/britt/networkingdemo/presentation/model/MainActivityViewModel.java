package com.playground.arch.britt.networkingdemo.presentation.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.playground.arch.britt.networkingdemo.domain.GetVenues;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    public LiveData<List<VenueViewModel>> venues;
    public String title;
    public MutableLiveData<double[]> location;


    public MainActivityViewModel(GetVenues getVenues, String title) {
        this.title = title;

        this.location = getVenues.getLocationLiveD();
        this.location.setValue(new double[]{32.070080, 34.794145});
        this.venues = getVenues.getViewModelsLiveD();

    }
}
