package com.example.tf.fuelconsumption;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class FillUps {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int km;
    public float unit_price;
    public float liter;
    public boolean full;
    public int year;
    public int month;
    public int day;

}
