package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.covid_19.api.StatesData;
import com.example.covid_19.api.stateApiUtility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class statesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<StatesData> list;
    private ProgressDialog dialog;
    private EditText searchBar;
    private statesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_states);

        recyclerView = findViewById(R.id.states);
        searchBar = findViewById(R.id.searchBar);
        list = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new statesAdapter(this,list);
        recyclerView.setAdapter(adapter);


        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();

       stateApiUtility.getStateApiInterface().getStatesData().enqueue(new Callback<List<StatesData>>() {
            @Override
            public void onResponse(Call<List<StatesData>> call, Response<List<StatesData>> response) {
                list.addAll(response.body());
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<StatesData>> call, Throwable t) {
            dialog.dismiss();
                Toast.makeText(statesActivity.this, "Error: "+ t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
            filter(editable.toString());
            }
        });

    }

    private void filter(String text) {
        List<StatesData> filterList = new ArrayList<>();
        for(StatesData items: list){
            if(items.getProvinceState().toLowerCase().contains(text.toLowerCase())){
                filterList.add(items);
            }
        }
        adapter.filterList(filterList);
    }
}