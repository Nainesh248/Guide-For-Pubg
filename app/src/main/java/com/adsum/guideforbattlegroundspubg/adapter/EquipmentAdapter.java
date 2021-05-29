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
import com.adsum.guideforbattlegroundspubg.model.EquipmentResponse;

import java.util.List;

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.MyViewHolder> {
    private List<EquipmentResponse> equipmentList;
    AdapterCallback adapterCallback;
    public EquipmentAdapter(List<EquipmentResponse> equipmentList,AdapterCallback adapterCallback1) {
        this.equipmentList = equipmentList;
        this.adapterCallback = adapterCallback1;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_equipment, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        EquipmentResponse equipmentResponse= equipmentList.get(position);
        holder.weponname.setText(equipmentResponse.getWeponname());
        holder.damage.setText(equipmentResponse.getDamage());
        holder.durability.setText(equipmentResponse.getDurability());
        holder.capacity.setText(equipmentResponse.getCapacity());
        holder.equipmentimage.setImageResource(equipmentResponse.getEquipmentimage());
        holder.rl_equipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(adapterCallback!=null)
                        adapterCallback.subcategoryclick(equipmentList,position);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return equipmentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView  weponname, damage,capacity,durability;
        public ImageView equipmentimage;
        public RelativeLayout rl_equipment;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            weponname = (TextView) itemView.findViewById(R.id.txt_weapon_name);
            damage = (TextView) itemView.findViewById(R.id.txt_weapon_damage);
            capacity = (TextView) itemView.findViewById(R.id.txt_weapon_capacity);
            durability = (TextView) itemView.findViewById(R.id.txt_weapon_durability);
            equipmentimage = (ImageView) itemView.findViewById(R.id.imageweapon);
            rl_equipment = (RelativeLayout) itemView.findViewById(R.id.rl_equipment);

        }
    }
    public interface AdapterCallback {

        void subcategoryclick(List<EquipmentResponse> equipmentList, int position);
    }
}
