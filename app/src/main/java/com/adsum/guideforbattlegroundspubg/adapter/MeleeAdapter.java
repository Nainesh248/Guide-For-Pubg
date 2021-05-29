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
import com.adsum.guideforbattlegroundspubg.model.HandgunsResponse;
import com.adsum.guideforbattlegroundspubg.model.MeleeResponse;
import com.adsum.guideforbattlegroundspubg.model.SmgResponse;

import java.util.List;

public class MeleeAdapter extends RecyclerView.Adapter<MeleeAdapter.MyViewHolder> {
    private List<MeleeResponse> meleeList;
    AdapterCallback adapterCallback;

    public MeleeAdapter(List<MeleeResponse> meleeList,AdapterCallback adapterCallback1) {
        this.meleeList = meleeList;
        this.adapterCallback = adapterCallback1;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_melee, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MeleeResponse meleeResponse= meleeList.get(position);
        holder.weponname.setText(meleeResponse.getWeponname());
        holder.damage.setText(meleeResponse.getDamage());
        holder.meleeimage.setImageResource(meleeResponse.getMeleeimage());
        holder.rl_weapon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (adapterCallback != null)
                        adapterCallback.subcategoryclick(meleeList, position);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return meleeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView  weponname, damage;
        public ImageView meleeimage;
        public RelativeLayout rl_weapon;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            weponname = (TextView) itemView.findViewById(R.id.txt_weapon_name);
            damage = (TextView) itemView.findViewById(R.id.txt_weapon_damage);
            meleeimage = (ImageView) itemView.findViewById(R.id.imageweapon);
            rl_weapon = (RelativeLayout) itemView.findViewById(R.id.rl_weapon);
        }
    }
    public interface AdapterCallback {

        void subcategoryclick(List<MeleeResponse> meleeList, int position);
    }
}
