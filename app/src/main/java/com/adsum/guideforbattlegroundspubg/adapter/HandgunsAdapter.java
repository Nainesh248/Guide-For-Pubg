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
import com.adsum.guideforbattlegroundspubg.model.ShotgunsResponse;

import java.util.List;

public class HandgunsAdapter extends RecyclerView.Adapter<HandgunsAdapter.MyViewHolder> {
    private List<HandgunsResponse> handgunsList;
    AdapterCallback adapterCallback;

    public HandgunsAdapter(List<HandgunsResponse> handgunsList, AdapterCallback adapterCallback1) {
        this.handgunsList = handgunsList;
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
        HandgunsResponse handgunsResponse = handgunsList.get(position);
        holder.weponname.setText(handgunsResponse.getWeponname());
        if (handgunsResponse.getWeponname().equalsIgnoreCase("Fliare Gun")){
            holder.next.setVisibility(View.GONE);
            holder.rl_weapon.setEnabled(false);

        }
        holder.damage.setText(handgunsResponse.getDamage());
        holder.handgunsimage.setImageResource(handgunsResponse.getHandgunimage());
        holder.rl_weapon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (adapterCallback != null)
                        adapterCallback.subcategoryclick(handgunsList, position);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return handgunsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView weponname, damage;
        public ImageView handgunsimage;
        public ImageView next;
        public RelativeLayout rl_weapon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            weponname = (TextView) itemView.findViewById(R.id.txt_weapon_name);
            damage = (TextView) itemView.findViewById(R.id.txt_weapon_damage);
            handgunsimage = (ImageView) itemView.findViewById(R.id.imageweapon);
            next = (ImageView) itemView.findViewById(R.id.img_next);
            rl_weapon = (RelativeLayout) itemView.findViewById(R.id.rl_weapon);

        }
    }

    public interface AdapterCallback {

        void subcategoryclick(List<HandgunsResponse> handgunsList, int position);
    }
}
