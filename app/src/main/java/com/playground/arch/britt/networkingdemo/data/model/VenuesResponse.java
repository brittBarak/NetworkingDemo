package com.playground.arch.britt.networkingdemo.data.model;

import java.util.List;

public class VenuesResponse {
    private List<Group> groups;

    public List<Group> getGroups() {
        return groups;
    }

    public static class Group {
        List<Item> items;

        public List<Item> getItems() {
            return items;
        }
    }

    public static class Item {
        Venue venue;

        public Venue getVenue() {
            return venue;
        }
    }
}
