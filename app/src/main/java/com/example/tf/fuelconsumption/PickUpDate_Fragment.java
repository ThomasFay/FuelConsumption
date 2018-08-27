package com.example.tf.fuelconsumption;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

public class PickUpDate_Fragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int year = c.get(Calendar.YEAR);

        return new DatePickerDialog(getActivity(),(NewFillUp_Activity)getActivity(),year,month,day);

    }

    public void onDateSet(DatePicker view, int year, int month, int day){

    }
}
