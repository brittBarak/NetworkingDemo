package com.playground.arch.britt.networkingdemo.domain;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.playground.arch.britt.networkingdemo.ObserverWithIndex;
import com.playground.arch.britt.networkingdemo.data.VenuePhotosResponse;
import com.playground.arch.britt.networkingdemo.data.VenuesRepository;
import com.playground.arch.britt.networkingdemo.data.VenuesResponse;
import com.playground.arch.britt.networkingdemo.presentation.model.VenueViewModel;
import com.playground.arch.britt.networkingdemo.presentation.utils.MutableListLiveData;

import java.util.ArrayList;
import java.util.List;

public class GetVenues {
    VenuesRepository repository = VenuesRepository.get();
    GetVenuePhotos getVenuePhotos = new GetVenuePhotos();
    MutableListLiveData<VenueViewModel> viewModel = new MutableListLiveData();

    public MutableListLiveData<VenueViewModel> execute(final LifecycleOwner owner) {

        Observer<VenuesResponse> observer = data -> {
            final List<VenueViewModel> venuesVM = createVenuesViewModel(data);
            viewModel.setItems(venuesVM);

            getVenuesPhotos(venuesVM, owner);
        };

        repository.getVenues().observe(owner, observer);
        return viewModel;
    }

    private void getVenuesPhotos(final List<VenueViewModel> venuesVM, LifecycleOwner owner) {
        for (int i = 0; i < venuesVM.size(); i++) {

            getVenuePhotos.execute(venuesVM.get(i).getId())
                    .observe(owner, getVenuePhotosObserver(venuesVM, i));

        }
    }

    @NonNull
    private ObserverWithIndex<VenuePhotosResponse> getVenuePhotosObserver(final List<VenueViewModel> venuesVM, final int i) {
        final VenueViewModel finalVenueViewModel = venuesVM.get(i);
        return new ObserverWithIndex<VenuePhotosResponse>(i) {
            @Override
            public void onChanged(@Nullable VenuePhotosResponse venuePhotosResponse) {
                finalVenueViewModel.setImageUrl(venuePhotosResponse.getFirstUrl());
                viewModel.setItem(getIndex(), venuesVM.get(getIndex()));
            }
        };
    }

    @NonNull
    private List<VenueViewModel> createVenuesViewModel(VenuesResponse data) {
        List<VenueViewModel> venuesVM = new ArrayList<>();

        for (VenuesResponse.Group group : data.getGroups()) {
            for (int i = 0; i < group.getItems().size(); i++) {
                VenuesResponse.Item item = group.getItems().get(i);
                VenueViewModel venueViewModel = new VenueViewModel(item.getVenue().getId(), item.getVenue().getName());
                venuesVM.add(venueViewModel);
            }
        }
        return venuesVM;
    }

}
