package com.playground.arch.britt.networkingdemo.presentation.VenuesList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.playground.arch.britt.networkingdemo.R;
import com.playground.arch.britt.networkingdemo.presentation.model.VenueViewModel;

import java.util.List;

class VenuesAdapter extends RecyclerView.Adapter<VenueViewHolder> {

    private List<VenueViewModel> venues;

    public VenuesAdapter(List<VenueViewModel> venues) {
        this.venues = venues;
    }

    @Override
    public VenueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_place, null);
        return new VenueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VenueViewHolder holder, int position) {
        holder.bindViewHolder(venues.get(position));
    }

    @Override
    public int getItemCount() {
        return venues == null ? 0 : venues.size();
    }

    public void setItems(List<VenueViewModel> newVenues) {
        this.venues = newVenues;
        notifyDataSetChanged();
    }

    public void setItem(int index, List<VenueViewModel> venues) {
        this.venues = venues;
        notifyItemChanged(index);
    }
}
