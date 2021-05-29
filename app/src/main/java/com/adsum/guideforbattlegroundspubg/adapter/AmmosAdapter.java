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
import com.adsum.guideforbattlegroundspubg.model.AmmosResponse;

import java.util.List;

public class AmmosAdapter extends RecyclerView.Adapter<AmmosAdapter.MyViewHolder> {
    private List<AmmosResponse> ammosList;
    AdapterCallback adapterCallback;
    public AmmosAdapter(List<AmmosResponse> ammosList,AdapterCallback adapterCallback1) {
        this.ammosList = ammosList;
        this.adapterCallback = adapterCallback1;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ammos, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AmmosResponse ammosResponse= ammosList.get(position);
        holder.weponname.setText(ammosResponse.getWeponname());
        holder.damage.setText(ammosResponse.getDetail());
        holder.ammosimage.setImageResource(ammosResponse.getAmmosimage());
        holder.rl_ammos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(adapterCallback!=null)
                        adapterCallback.subcategoryclick(ammosList,position);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return ammosList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView  weponname, damage;
        public ImageView ammosimage;
        public RelativeLayout rl_ammos;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            weponname = (TextView) itemView.findViewById(R.id.txt_weapon_name);
            damage = (TextView) itemView.findViewById(R.id.txt_weapon_damage);
            ammosimage = (ImageView) itemView.findViewById(R.id.imageweapon);
            rl_ammos = (RelativeLayout) itemView.findViewById(R.id.rl_ammos);

        }
    }
    public interface AdapterCallback {

        void subcategoryclick(List<AmmosResponse> ammosList, int position);
    }
}
