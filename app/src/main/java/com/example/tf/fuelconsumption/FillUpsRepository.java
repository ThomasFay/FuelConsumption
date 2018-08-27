package com.example.tf.fuelconsumption;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public class FillUpsRepository {
    private FillUpDAO fillups_dao;
    private List<FillUps> fillUpsList;

    FillUpsRepository(Context application){
        final Context app = application;
        new AsyncTask<Void,Void,List<FillUps>>() {
            @Override
            protected List<FillUps> doInBackground(Void... voids) {
                AppDataBase db = AppDataBase.getAppDatabase(app);
                fillups_dao = db.fpdao();
                fillUpsList = fillups_dao.getFillUps();
                if (fillUpsList == null){
                    Log.d("NULL2","NULL");
                } else
                    Log.d("NOTNULL2","NOTNULL");
                return null;
            }
        }.execute();
        //AppDataBase db = AppDataBase.getAppDatabase(application);
        //fillups_dao = db.fpdao();
        //fillUpsList = fillups_dao.getFillUps();
        if (fillUpsList == null){
            Log.d("NULL","NULL");
        } else {
            Log.d("NOTNULL","NOTNULL");
        }
    }

    public List<FillUps> getFillUpsList() {
        return fillUpsList;
    }
}
