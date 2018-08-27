package com.example.tf.fuelconsumption;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.List;

public class Consumption_activity extends AppCompatActivity{
    public AppDataBase db;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_consumption);

        db = AppDataBase.getAppDatabase(getApplicationContext());


        FillUpsRepository fu_repo = new FillUpsRepository(getApplicationContext());

        List<FillUps> fu_list = fu_repo.getFillUpsList();
       // Log.d("TEST","Taille " + fu_list.size());

        if (fu_list == null){
            Log.d("TEST","NULL");
        } else {
            for (FillUps fu:fu_list){
                Log.d("TEST","test " + fu.km);
            }

        }



    }

    public void Back(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
