package com.example.tf.fuelconsumption;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.tf.fuelconsumption.FillUps;

@Database(entities={FillUps.class},version = 2)
public abstract class AppDataBase extends RoomDatabase{
    private static AppDataBase INSTANCE;

    public abstract FillUpDAO fpdao();

    public static AppDataBase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "fillup")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
