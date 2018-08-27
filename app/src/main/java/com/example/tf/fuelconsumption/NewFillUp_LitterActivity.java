package com.example.tf.fuelconsumption;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;

public class NewFillUp_LitterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newfillup_litter);
        RadioGroup rg = findViewById(R.id.radioGroup2);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.Price_button){
                    Intent intent = new Intent(NewFillUp_LitterActivity.this,NewFillUp_Activity.class);
                    startActivity(intent);
                }

            }
        });



    }

     public void Back(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }

    public void validate(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
