package com.adsum.guideforbattlegroundspubg.adapter;

import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adsum.guideforbattlegroundspubg.R;
import com.adsum.guideforbattlegroundspubg.model.SniperResponse;

import java.util.List;

public class SniperAdapter extends RecyclerView.Adapter<SniperAdapter.MyViewHolder> {
    private List<SniperResponse> sniperList;
    AdapterCallback adapterCallback;
    public SniperAdapter(List<SniperResponse> sniperList,AdapterCallback adapterCallback1) {
        this.sniperList = sniperList;
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
        SniperResponse sniperResponse= sniperList.get(position);
        holder.weponname.setText(sniperResponse.getWeponname());
        holder.damage.setText(sniperResponse.getDamage());
        holder.sniperimage.setImageResource(sniperResponse.getSniperimage());
        holder.rl_weapon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(adapterCallback!=null)
                        adapterCallback.subcategoryclick(sniperList,position);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return sniperList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView  weponname, damage;
        public ImageView sniperimage;
        public RelativeLayout rl_weapon;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            weponname = (TextView) itemView.findViewById(R.id.txt_weapon_name);
            damage = (TextView) itemView.findViewById(R.id.txt_weapon_damage);
            sniperimage = (ImageView) itemView.findViewById(R.id.imageweapon);
            rl_weapon = (RelativeLayout) itemView.findViewById(R.id.rl_weapon);

        }
    }
    public interface AdapterCallback {

        void subcategoryclick(List<SniperResponse> sniperList, int position);
    }
}
