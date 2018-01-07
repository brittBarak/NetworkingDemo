package com.playground.arch.britt.networkingdemo.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.playground.arch.britt.networkingdemo.data.model.local.VenueLocal;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by britt on 12/27/17.
 */

@Dao
public interface VenueDao {
    @Insert(onConflict = REPLACE)
    void save(VenueLocal venue);

    @Insert(onConflict = REPLACE)
    void save(List<VenueLocal> venues);

    @Query("SELECT * FROM venues")
    LiveData<List<VenueLocal>> load();

    @Query("SELECT * FROM venues WHERE id = :venueId")
    LiveData<VenueLocal> load(String venueId);

    @Update(onConflict = REPLACE)
    void updatePhotoUrl(VenueLocal venue);

    @Query("DELETE FROM venues")
    void deleteAll();
}
