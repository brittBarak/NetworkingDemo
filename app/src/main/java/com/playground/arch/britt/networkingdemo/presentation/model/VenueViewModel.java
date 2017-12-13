package com.playground.arch.britt.networkingdemo.presentation.model;

public class VenueViewModel {
    String id;
    String name;
    String imageUrl;

    public VenueViewModel(String id, String name) {
        this.id = id;
        this.name = name;
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

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
