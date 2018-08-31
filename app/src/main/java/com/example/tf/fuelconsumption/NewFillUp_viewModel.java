package com.example.tf.fuelconsumption;

import android.arch.lifecycle.ViewModel;
import android.icu.util.Calendar;

public class NewFillUp_viewModel extends ViewModel{
    private int selected_year;
    private int selected_day;
    private int selected_month;
    private boolean isSet = false;

    public void setDate(int day,int month,int year){
        this.selected_day = day;
        this.selected_month = month;
        this.selected_year = year;
        this.isSet = true;
    }

    public int getSelected_year(){
        return selected_year;
    }

    public int getSelected_day(){
        return selected_day;
    }

    public int getSelected_month() {
        return selected_month;
    }

    public boolean get_isSet(){
        return isSet;
    }
}
