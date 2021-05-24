package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covid_19.api.ApiUtilities;
import com.example.covid_19.api.CountriesData;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatisticActivity extends AppCompatActivity {
    ImageView back;
    private TextView affected;
    private TextView deaths;
    private TextView recovered;
    private TextView critical;
    private TextView active;
    Button myCountry,Global;
    private TextView today,allTime,updatedData;
    private List<CountriesData> list;

    private ProgressDialog dialog;

    public int  confirmVal,activeVal,deathsVal,recoveredVal,criticalVal,todayRecoveredVal,todayDeathsVal,todayCasesVal;
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_statistic);



        list = new ArrayList<>();

        init();

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();

        ApiUtilities.getApiInterface().getCountriesData()
                .enqueue(new Callback<List<CountriesData>>() {
                    @Override
                    public void onResponse(Call<List<CountriesData>> call, Response<List<CountriesData>> response) {
                        list.addAll(response.body());
                        String passedArg = getIntent().getExtras().getString("CountryName");
                        myCountry.setText(passedArg);
                        dialog.dismiss();
                        for(int i=0;i<list.size();i++){
                            if(list.get(i).getCountry().equals(passedArg)){
                                 confirmVal = Integer.parseInt(list.get(i).getCases());
                                 activeVal = Integer.parseInt(list.get(i).getActive());
                                 recoveredVal = Integer.parseInt(list.get(i).getRecovered());
                                 deathsVal = Integer.parseInt(list.get(i).getDeaths());
                                 criticalVal = Integer.parseInt(list.get(i).getCritical());
                                 todayRecoveredVal= Integer.parseInt(list.get(i).getTodayRecovered());
                                 todayCasesVal= Integer.parseInt(list.get(i).getTodayCases());
                                 todayDeathsVal= Integer.parseInt(list.get(i).getTodayDeaths());

                                affected.setText(NumberFormat.getInstance().format(confirmVal));
                                deaths.setText(NumberFormat.getInstance().format(deathsVal));
                                recovered.setText(NumberFormat.getInstance().format(recoveredVal));
                                critical.setText(NumberFormat.getInstance().format(criticalVal));
                                active.setText(NumberFormat.getInstance().format(activeVal));

                                setText(list.get(i).getUpdated());

                                pieChart.addPieSlice(new PieModel("Affected",confirmVal, Color.parseColor("#FED70E")));
                                pieChart.addPieSlice(new PieModel("Deaths", deathsVal, Color.parseColor("#ff5959")));
                                pieChart.addPieSlice(new PieModel("Recovered", recoveredVal, Color.parseColor("#43d97b")));
                                pieChart.addPieSlice(new PieModel("Active", activeVal, Color.parseColor("#56B7F1")));
                                pieChart.addPieSlice(new PieModel("Critical", criticalVal, Color.parseColor("#9059ff")));

                                pieChart.startAnimation();

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CountriesData>> call, Throwable t) {
                        Toast.makeText(StatisticActivity.this, "Error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        allTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                today.setTextColor(Color.rgb(178,175,175));
                allTime.setTextColor(Color.rgb(255,255,255));
                affected.setText(NumberFormat.getInstance().format(confirmVal));
                deaths.setText(NumberFormat.getInstance().format(deathsVal));
                recovered.setText(NumberFormat.getInstance().format(recoveredVal));
                critical.setText(NumberFormat.getInstance().format(criticalVal));
                active.setText(NumberFormat.getInstance().format(activeVal));

            }
        });


        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                today.setTextColor(Color.rgb(255,255,255));
                allTime.setTextColor(Color.rgb(178,175,175));
                affected.setText(NumberFormat.getInstance().format(todayCasesVal));
                deaths.setText(NumberFormat.getInstance().format(todayDeathsVal));
                recovered.setText(NumberFormat.getInstance().format(todayRecoveredVal));
                critical.setText(NumberFormat.getInstance().format(criticalVal));
                active.setText(NumberFormat.getInstance().format(activeVal));

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StatisticActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        GlobalOnClick();
    }

    private void setText(String updated) {
        DateFormat format = new SimpleDateFormat("MMM dd, yyyy");
        long ms = Long.parseLong(updated);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(ms);
        updatedData.setText("Updated at "+ format.format(calendar.getTime()));
    }

    private void init() {
        back = findViewById(R.id.backButton);
        affected = findViewById(R.id.affectedData);
        deaths = findViewById(R.id.deathData);
        recovered = findViewById(R.id.recoveredData);
        critical = findViewById(R.id.criticalData);
        active = findViewById(R.id.activeData);
        updatedData = findViewById(R.id.updated);

        myCountry = findViewById(R.id.Mycountry);
        Global = findViewById(R.id.global);

        today = findViewById(R.id.today);
        allTime = findViewById(R.id.total);
        pieChart = (PieChart) findViewById(R.id.piechart);
    }


    private void GlobalOnClick(){
        Global.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent i = new Intent(StatisticActivity.this,statesActivity.class);
            startActivity(i);

            }
        });
    }



}