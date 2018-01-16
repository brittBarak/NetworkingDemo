package com.playground.arch.britt.networkingdemo.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.playground.arch.britt.networkingdemo.data.model.local.VenueData;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by britt on 12/27/17.
 */

@Dao
public interface VenueDao {
    @Insert(onConflict = REPLACE)
    void save(VenueData venue);

    @Insert(onConflict = REPLACE)
    void save(List<VenueData> venues);

    @Query("SELECT * FROM VenueData")
    LiveData<List<VenueData>> load();

    @Query("SELECT * FROM VenueData WHERE source = :source AND originId IN(:ids)")
    LiveData<List<VenueData>> loadByIds(String[] ids, String source);

    @Query("SELECT * FROM VenueData WHERE id = :venueId")
    LiveData<VenueData> loadVenue(String venueId);

    @Update(onConflict = REPLACE)
    void updatePhotoUrl(VenueData venue);

    @Query("DELETE FROM VenueData")
    void deleteAll();
}
