package com.example.tf.fuelconsumption;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private AppDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        db = AppDataBase.getAppDatabase(getApplicationContext());
    }

    public void newFillUp(View view){
        Intent intent = new Intent(this,NewFillUp_Activity.class);
        startActivity(intent);

    }

    public void consumtion(View view){
        Intent intent = new Intent(this,Consumption_activity.class);
        startActivity(intent);
    }
}
