package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StatesStatistics extends AppCompatActivity {
    private TextView statesConfirmed,statesDeaths,statesRecovered,statesActive,stateNameTV,lastUpdated;
    private String lastUpdatedData;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_states_statistics);

        statesConfirmed = findViewById(R.id.statesConfirmed);
        statesDeaths = findViewById(R.id.statesDeath);
        statesRecovered = findViewById(R.id.statesRecovered);
        statesActive = findViewById(R.id.statesActive);
        stateNameTV = findViewById(R.id.stateTV);
        lastUpdated = findViewById(R.id.statesUpdated);
        back = findViewById(R.id.statesBack);

            stateNameTV.setText(getIntent().getStringExtra("provinceState"));
            statesConfirmed.setText(getIntent().getStringExtra("confirmed"));
            statesDeaths.setText(getIntent().getStringExtra("deaths"));
            statesRecovered.setText(getIntent().getStringExtra("recovered"));
            statesActive.setText(getIntent().getStringExtra("active"));

            lastUpdatedData = getIntent().getStringExtra("lastUpdate");
            DateFormat format = new SimpleDateFormat("MMM dd, yyyy");
            long ms = Long.parseLong(lastUpdatedData);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(ms);
            lastUpdated.setText("Updated at "+ format.format(calendar.getTime()));

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent( StatesStatistics.this,statesActivity.class);
                    startActivity(i);
                    finish();
                }
            });
    }
}