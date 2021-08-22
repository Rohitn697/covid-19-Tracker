package com.example.covid_19;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19.api.StatesData;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class statesAdapter extends RecyclerView.Adapter<statesAdapter.StatesViewHolder> {

    private Context context;
    private List<StatesData> list;
    public String stateConfirmedData,stateRecoveredData,stateActiveData,stateDeathsData,stateName,lastUpdated;

    public statesAdapter(Context context, List<StatesData> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public StatesViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.states_item_layout,parent,false);
        return new StatesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull statesAdapter.StatesViewHolder holder, int position) {
            StatesData data = list.get(position);
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(data.getConfirmed())));
            holder.state.setText(data.getProvinceState());
            holder.sno.setText(String.valueOf(position+1));


            holder.itemView.setOnClickListener(v->{
                stateConfirmedData = NumberFormat.getInstance().format(Integer.parseInt(data.getConfirmed()));
                stateRecoveredData = NumberFormat.getInstance().format(Integer.parseInt(data.getCases28Days()));
                stateActiveData = NumberFormat.getInstance().format(Integer.parseInt(data.getDeaths28Days())) ;
                stateDeathsData = NumberFormat.getInstance().format(Integer.parseInt(data.getDeaths()));
                stateName = data.getProvinceState();
                lastUpdated = data.getLastUpdate();


                Intent i = new Intent(context, StatesStatistics.class);
                i.putExtra("provinceState",stateName);
                i.putExtra("confirmed",stateConfirmedData);
                i.putExtra("deaths",stateDeathsData);
                i.putExtra("recovered",stateRecoveredData);
                i.putExtra("active",stateActiveData);
                i.putExtra("lastUpdate",lastUpdated);
                context.startActivity(i);
            });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class StatesViewHolder extends RecyclerView.ViewHolder {

        private TextView sno,state,cases;
        public StatesViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            sno = itemView.findViewById(R.id.serialNo);
            state=itemView.findViewById(R.id.stateName);
            cases = itemView.findViewById(R.id.casesCount);
        }
    }

    public void filterList(List<StatesData> filterList){
        list = filterList;
        notifyDataSetChanged();
    }
}
