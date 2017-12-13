package com.playground.arch.britt.networkingdemo.data;

import java.util.List;


public class VenuePhotosResponse {
    PhotosResponse photos;
    String id;

    public String getFirstUrl(){
        return (photos == null) ? null : photos.getFirstUrl();
    }

    public String getId() {
        return id;
    }


    public class PhotosResponse {
        List<Item> items;

        public List<Item> getItems() {
            return items;
        }

        public String getFirstUrl(){
            return (items == null) || items.size() < 1 ? null : items.get(0).getUrl();
        }
    }

    public class Item {
        String id;
        String prefix;
        String suffix;

        public String getUrl() {
            return String.format("%s%s%s", prefix, "original", suffix);
        }
    }

}
