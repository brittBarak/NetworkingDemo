package com.playground.arch.britt.networkingdemo.presentation.VenuesList;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.playground.arch.britt.networkingdemo.R;
import com.playground.arch.britt.networkingdemo.network.utils.ImageDownloader;
import com.playground.arch.britt.networkingdemo.presentation.model.VenueViewModel;

public class VenueViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView textView;

    public VenueViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        textView = itemView.findViewById(R.id.textView);
    }

    public void bindViewHolder(VenueViewModel venue) {
        textView.setText(venue.getName());
        if (! TextUtils.isEmpty(venue.getImageUrl())) {
            ImageDownloader.download(imageView.getContext(), venue.getImageUrl(), imageView);
        }
    }
}
