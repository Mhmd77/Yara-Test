package com.myapps.yara.utils;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.myapps.yara.service.database.MovieDao;
import com.myapps.yara.service.model.DetailMovie;

@Database(entities = {DetailMovie.class}, exportSchema = false, version = 1)

public abstract class AppDatabase extends RoomDatabase {
    public static AppDatabase INSTANCE;

    public abstract MovieDao movieDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user-database")
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
