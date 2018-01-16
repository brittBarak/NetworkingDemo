package com.playground.arch.britt.networkingdemo.data.model.local;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "venues")
public class VenueData {
    @Ignore
    public static final String FOURSQUARE = "FOURSQUARE";

//    @PrimaryKey(autoGenerate = true)
    @PrimaryKey
    @NonNull public String id;
    public String originId;
    public String name;
    public String photoUrl;
    public String source;


    public VenueData(String id, String name, String source) {
        this.originId = id;
        this.name = name;
        this.source = source;
        this.id = String.format("%s_%s", source, id);
    }

}
