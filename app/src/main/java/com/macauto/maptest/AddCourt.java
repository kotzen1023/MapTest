package com.macauto.maptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;


public class AddCourt extends AppCompatActivity {
    private static final String TAG = AddCourt.class.getName();

    private ImageView imageView;

    private Spinner courtTypeSpinner;
    private Spinner courtUsageSpinner;
    private Spinner lightSpinner;

    public ArrayAdapter<String> courtTypeAdapter;
    public ArrayAdapter<String> courtUsageAdapter;
    public ArrayAdapter<String> lightAdapter;

    private EditText editTextCourtName;
    private EditText editTextCourtNum;
    private EditText editTextCharge;
    private RatingBar ratingBarMaintenance;
    private RatingBar ratingBarTraffic;
    private RatingBar ratingBarParking;
    private Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_court);

        imageView = (ImageView) findViewById(R.id.imageViewCourt);
        editTextCourtName = (EditText) findViewById(R.id.courtName);
        courtTypeSpinner = (Spinner) findViewById(R.id.spinnerCourtType);
        courtUsageSpinner = (Spinner) findViewById(R.id.spinnerCourtUsage);
        lightSpinner = (Spinner) findViewById(R.id.spinnerLight);
        editTextCourtNum = (EditText) findViewById(R.id.courtNumber);
        editTextCharge = (EditText) findViewById(R.id.courtCharge);
        ratingBarMaintenance = (RatingBar) findViewById(R.id.ratingMaintain);
        ratingBarTraffic = (RatingBar) findViewById(R.id.ratingTraffic);
        ratingBarParking = (RatingBar) findViewById(R.id.ratingParking);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);

        String[] courtTypeList = {"Hard", "Grass", "Clay", "Hard, Grass", "Hard, Clay", "Grass, Clay", "All"};
        String[] courtUsageList = {"Public", "Private"};
        String[] lightList = {"Yes", "No"};

        courtTypeAdapter = new ArrayAdapter<>(AddCourt.this, R.layout.myspinner, courtTypeList);
        courtTypeSpinner.setAdapter(courtTypeAdapter);

        courtUsageAdapter = new ArrayAdapter<>(AddCourt.this, R.layout.myspinner, courtUsageList);
        courtUsageSpinner.setAdapter(courtUsageAdapter);

        lightAdapter = new ArrayAdapter<>(AddCourt.this, R.layout.myspinner, lightList);
        lightSpinner.setAdapter(lightAdapter);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
