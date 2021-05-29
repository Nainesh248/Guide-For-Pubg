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
import com.adsum.guideforbattlegroundspubg.model.ARResponse;
import com.adsum.guideforbattlegroundspubg.model.ShotgunsResponse;
import com.adsum.guideforbattlegroundspubg.model.SmgResponse;

import java.util.List;

public class ShotgunsAdapter extends RecyclerView.Adapter<ShotgunsAdapter.MyViewHolder> {
    private List<ShotgunsResponse> shotgunsList;
    AdapterCallback adapterCallback;
    public ShotgunsAdapter(List<ShotgunsResponse> shotgunsList, AdapterCallback adapterCallback1) {
        this.shotgunsList = shotgunsList;
        this.adapterCallback = adapterCallback1;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_weapon, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ShotgunsResponse shotgunsResponse= shotgunsList.get(position);
        holder.weponname.setText(shotgunsResponse.getWeponname());
        holder.damage.setText(shotgunsResponse.getDamage());
        holder.shotgunsimage.setImageResource(shotgunsResponse.getShotgunimage());
        holder.rl_weapon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(adapterCallback!=null)
                        adapterCallback.subcategoryclick(shotgunsList,position);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return shotgunsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView  weponname, damage;
        public ImageView shotgunsimage;
        public RelativeLayout rl_weapon;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            weponname = (TextView) itemView.findViewById(R.id.txt_weapon_name);
            damage = (TextView) itemView.findViewById(R.id.txt_weapon_damage);
            shotgunsimage = (ImageView) itemView.findViewById(R.id.imageweapon);
            rl_weapon = (RelativeLayout) itemView.findViewById(R.id.rl_weapon);

        }
    }
    public interface AdapterCallback {

        void subcategoryclick(List<ShotgunsResponse> shotgunsList, int position);
    }
}
