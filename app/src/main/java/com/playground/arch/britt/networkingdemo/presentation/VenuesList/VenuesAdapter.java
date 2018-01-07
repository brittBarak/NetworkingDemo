package com.playground.arch.britt.networkingdemo.presentation.VenuesList;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.DiffCallback;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.playground.arch.britt.networkingdemo.R;
import com.playground.arch.britt.networkingdemo.presentation.model.VenueViewModel;

class VenuesAdapter extends ListAdapter<VenueViewModel, VenueViewHolder> {

    public VenuesAdapter() {
        super(DIFF_CALLBACK);
    }

    @Override
    public VenueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_place, null);
        return new VenueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VenueViewHolder holder, int position) {
        holder.bindViewHolder(getItem(position));
    }


    public static final DiffCallback<VenueViewModel> DIFF_CALLBACK = new DiffCallback<VenueViewModel>() {
        @Override
        public boolean areItemsTheSame(
                @NonNull VenueViewModel oldVenue, @NonNull VenueViewModel newVenue) {
            return oldVenue.getId().equals(newVenue.getId());
        }

        @Override
        public boolean areContentsTheSame(
                @NonNull VenueViewModel oldVenue, @NonNull VenueViewModel newVenue) {
            return oldVenue.equals(newVenue);
        }
    };
}
