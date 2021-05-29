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
import com.adsum.guideforbattlegroundspubg.model.EquipmentResponse;

import java.util.List;

public class ConsumablesAdapter extends RecyclerView.Adapter<ConsumablesAdapter.MyViewHolder> {
    private List<ConsumablesResponse> consumablesList;
   AdapterCallback adapterCallback;
    public ConsumablesAdapter(List<ConsumablesResponse> consumablesList,AdapterCallback adapterCallback1) {
        this.consumablesList = consumablesList;
        this.adapterCallback = adapterCallback1;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_consumables, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ConsumablesResponse consumablesResponse= consumablesList.get(position);
        holder.weponname.setText(consumablesResponse.getWeponname());
        holder.increase.setText(consumablesResponse.getIncrease());
        holder.timetaken.setText(consumablesResponse.getTimetaken());
        holder.capacity.setText(consumablesResponse.getCapacity());
        holder.consumablesimage.setImageResource(consumablesResponse.getConsumablesimage());
        holder.rl_consumables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(adapterCallback!=null)
                        adapterCallback.subcategoryclick(consumablesList,position);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return consumablesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView  weponname, increase,timetaken,capacity;
        public ImageView consumablesimage;
        public RelativeLayout rl_consumables;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            weponname = (TextView) itemView.findViewById(R.id.txt_weapon_name);
            increase = (TextView) itemView.findViewById(R.id.txt_weapon_increase);
            capacity = (TextView) itemView.findViewById(R.id.txt_weapon_capacity);
            timetaken = (TextView) itemView.findViewById(R.id.txt_weapon_timetaken);
            consumablesimage = (ImageView) itemView.findViewById(R.id.imageweapon);
            rl_consumables = (RelativeLayout) itemView.findViewById(R.id.rl_consumables);

        }
    }
    public interface AdapterCallback {


        void subcategoryclick(List<ConsumablesResponse> consumablesList, int position);
    }
}
