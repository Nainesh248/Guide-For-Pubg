package com.adsum.guideforbattlegroundspubg.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adsum.guideforbattlegroundspubg.R;
import com.adsum.guideforbattlegroundspubg.model.AmmosResponse;
import com.adsum.guideforbattlegroundspubg.model.ConsumablesResponse;
import com.adsum.guideforbattlegroundspubg.model.VehiclesResponse;

import java.util.List;

public class VehiclesAdapter extends RecyclerView.Adapter<VehiclesAdapter.MyViewHolder> {
    private List<VehiclesResponse> vehiclesList;
    AdapterCallback adapterCallback;

    public VehiclesAdapter(List<VehiclesResponse> vehiclesList, AdapterCallback adapterCallback1) {
        this.vehiclesList = vehiclesList;
        this.adapterCallback = adapterCallback1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_vehicles, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        VehiclesResponse vehiclesResponse = vehiclesList.get(position);
        holder.weponname.setText(vehiclesResponse.getWeponname());
        holder.health.setText(vehiclesResponse.getHealth());
        holder.topspeed.setText(vehiclesResponse.getTopspeed());
        holder.vehiclesimage.setImageResource(vehiclesResponse.getVehiclesimage());
        holder.rl_vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(adapterCallback!=null)
                        adapterCallback.subcategoryclick(vehiclesList,position);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return vehiclesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView weponname, health, topspeed;
        public ImageView vehiclesimage;
        public RelativeLayout rl_vehicle;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            weponname = (TextView) itemView.findViewById(R.id.txt_weapon_name);
            health = (TextView) itemView.findViewById(R.id.txt_weapon_health);
            topspeed = (TextView) itemView.findViewById(R.id.txt_weapon_topspeed);
            vehiclesimage = (ImageView) itemView.findViewById(R.id.imageweapon);
            rl_vehicle = (RelativeLayout) itemView.findViewById(R.id.rl_vehicle);

        }
    }

    public interface AdapterCallback {

        void subcategoryclick(List<VehiclesResponse> vehiclesList, int position);
    }
}
