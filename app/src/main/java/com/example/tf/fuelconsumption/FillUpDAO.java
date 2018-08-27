package com.example.tf.fuelconsumption;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface FillUpDAO {
    @Insert
    public void insertFillUp(FillUps fillup);

    @Query("SELECT * from FillUps")
    public List<FillUps> getFillUps();
}
