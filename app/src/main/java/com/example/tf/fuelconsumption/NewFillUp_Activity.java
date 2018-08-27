package com.example.tf.fuelconsumption;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import static java.util.Calendar.MONTH;

public class NewFillUp_Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    public AppDataBase db;

    private int selected_year;
    private int selected_day;
    private int selected_month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newfillup);
        RadioGroup rg = findViewById(R.id.radioGroup2);
        final RadioButton rb_price = findViewById(R.id.Price_button);
        final RadioButton rb_litter = findViewById(R.id.Litter_button);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                //if (checkedId == R.id.Litter_button){
                //    Intent intent = new Intent(NewFillUp_Activity.this,NewFillUp_LitterActivity.class);
                //    startActivity(intent);
                //}
                TextView pl_textview = findViewById(R.id.price_litter_textview);
                EditText pl_edittext = findViewById(R.id.price_litter_edittext);
                if (rb_litter.isChecked()) {
                    pl_textview.setText("Nombre de litres");
                    pl_edittext.setHint("Nombre de litres");
                }
                if (rb_price.isChecked()) {
                    pl_textview.setText("Prix du plein");
                    pl_edittext.setHint("Prix");
                }

            }
        });

        Calendar c = Calendar.getInstance();
        selected_day = c.get(Calendar.DAY_OF_MONTH);
        selected_month = c.get(Calendar.MONTH) + 1;
        selected_year = c.get(Calendar.YEAR);
        Button date_picker = findViewById(R.id.date_picker);
        date_picker.setText(formatDateText());

        db = AppDataBase.getAppDatabase(getApplicationContext());
    }

    private String formatDateText() {
        String month_string = new String();
        switch (selected_month) {
            case 1:
                month_string = "Janv";
                break;
            case 2:
                month_string = "Fév";
                break;
            case 3:
                month_string = "Mars";
                break;
            case 4:
                month_string = "Avr";
                break;
            case 5:
                month_string = "Mai";
                break;
            case 6:
                month_string = "Juin";
                break;
            case 7:
                month_string = "Juil";
                break;
            case 8:
                month_string = "Aou";
                break;
            case 9:
                month_string = "Sept";
                break;
            case 10:
                month_string = "Oct";
                break;
            case 11:
                month_string = "Nov";
                break;
            case 12:
                month_string = "Déc";
                break;
        }
        String date_text = selected_day + " " + month_string + " " + selected_year;
        return date_text;

    }

    public void Back(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    private FillUps getReadyForInsertion(int km, float unit_price, float litter, boolean full, int year, int month, int day) {
        FillUps fu = new FillUps();
        fu.km = km;
        fu.unit_price = unit_price;
        fu.liter = litter;
        fu.full = full;
        fu.year = year;
        fu.month = month;
        fu.day = day;
        Log.d("LITTER", "The litter value is " + fu.km);

        return fu;
    }

    public void validate(View view) {
        boolean error_formular = false;

        RadioButton rb_littre = findViewById(R.id.Litter_button);
        boolean isPrice = true;

        if (rb_littre.isChecked()) {
            isPrice = false;
        }

        String error_message = "Veuillez Remplir les champs suivants\n";

        // Parsing the value of the formular
        EditText et_km = findViewById(R.id.km_edittext);
        int km;
        if (TextUtils.isEmpty(et_km.getText())) {
            error_formular = true;
            error_message += " - Kilomètres\n";
            Log.d("KM", "KM is empty");
            km = 0;
        } else {
            km = Integer.valueOf(et_km.getText().toString());
            Log.d("PRICE", "Price value is " + km);
        }

        EditText et_unitprice = findViewById(R.id.unitprice_edittext);
        float unitprice;
        if (TextUtils.isEmpty(et_unitprice.getText())) {
            error_formular = true;
            error_message += " - Prix au Litre\n";
            Log.d("UNIT_PRICE", "Unit Price is empty");
            unitprice = 1;
        } else {
            unitprice = Float.valueOf(et_unitprice.getText().toString());
            Log.d("PRICE", "Price value is " + unitprice);
        }

        EditText et_price_litter = findViewById(R.id.price_litter_edittext);
        float price_litter;
        if (TextUtils.isEmpty(et_price_litter.getText())) {
            error_formular = true;
            if (isPrice) {
                error_message += " - Prix";
            } else {
                error_message += " - Nombre de litres";
            }
            Log.d("PRICE", "Price is empty");
            price_litter = 0;
        } else {
            price_litter = Float.valueOf(et_price_litter.getText().toString());
            Log.d("PRICE", "Price value is " + price_litter);
        }

        CheckBox full_chkbx = findViewById(R.id.full_checkbox);
        boolean full = full_chkbx.isChecked();

        float litter;
        if (isPrice) {
            litter = price_litter / unitprice;
        } else {
            litter = price_litter;
        }

        final FillUps fp = getReadyForInsertion(km, unitprice, litter, full,selected_year,selected_month,selected_day);

        if (error_formular) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
            builder.setMessage(error_message).setTitle("Erreur").setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    return;
                }
            }).show();
            Log.d("ERROR", "A Value was not correctly set");
        } else {
            Thread t = new Thread() {
                @Override
                public void run() {
                    db.fpdao().insertFillUp(fp);
                }

                ;
            };
            Log.d("OK", "Value Correctly Inserted (Hopfully)");
            t.start();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }


    }

    public void selectDate(View v) {
        PickUpDate_Fragment test = new PickUpDate_Fragment();
        test.show(getSupportFragmentManager(), "test");


    }

    @Override
    public void onDateSet(DatePicker view,int year, int month, int day){

        selected_year = year;
        selected_day = day;
        selected_month = month + 1;
        Button datePicker = findViewById(R.id.date_picker);
        datePicker.setText(formatDateText());
    }
}
