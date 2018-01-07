package com.playground.arch.britt.networkingdemo.presentation.model;

import android.arch.lifecycle.ViewModel;

import java.util.Objects;

public class VenueViewModel extends ViewModel {
    String id;
    String name;
    String imageUrl;
//    LiveData<String> imageUrl;

    public VenueViewModel(String id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

//    public LiveData<String> getImageUrl() {
//        return imageUrl;
//    }

//    public void setImageUrl(String imageUrl) {
//        this.imageUrl.setValue(imageUrl);
//    }

//    public void setImageUrl(LiveData<String> imageUrl) {
//        this.imageUrl = imageUrl;
//    }


    public boolean equals(VenueViewModel other) {
        if (other == null) return false;
        return (Objects.equals(this.id, other.id))
                && (Objects.equals(this.name, other.name))
                && (Objects.equals(this.imageUrl, other.imageUrl));
    }

}
