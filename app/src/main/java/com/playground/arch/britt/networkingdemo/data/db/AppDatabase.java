package com.playground.arch.britt.networkingdemo.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.playground.arch.britt.networkingdemo.data.model.local.VenueData;


@Database(entities = {VenueData.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{
    private static AppDatabase instance;

    public abstract VenueDao venueDao();

    public static AppDatabase getInMemoryDatabase(Context appContext) {
        if (instance == null) {
            instance = Room.inMemoryDatabaseBuilder(
                    appContext, AppDatabase.class).build();
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }


}
