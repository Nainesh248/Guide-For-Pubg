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
import com.adsum.guideforbattlegroundspubg.model.LmgResponse;
import com.adsum.guideforbattlegroundspubg.model.SmgResponse;

import java.util.List;

public class SmgAdapter extends RecyclerView.Adapter<SmgAdapter.MyViewHolder> {
    private List<SmgResponse> smgList;
    AdapterCallback adapterCallback;
    public SmgAdapter(List<SmgResponse> smgList, AdapterCallback adapterCallback1) {
        this.smgList = smgList;
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
        SmgResponse smgResponse= smgList.get(position);
        holder.weponname.setText(smgResponse.getWeponname());
        holder.damage.setText(smgResponse.getDamage());
        holder.smgimage.setImageResource(smgResponse.getSmgimage());
        holder.rl_weapon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(adapterCallback!=null)
                        adapterCallback.subcategoryclick(smgList,position);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return smgList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView  weponname, damage;
        public ImageView smgimage;
        public RelativeLayout rl_weapon;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            weponname = (TextView) itemView.findViewById(R.id.txt_weapon_name);
            damage = (TextView) itemView.findViewById(R.id.txt_weapon_damage);
            smgimage = (ImageView) itemView.findViewById(R.id.imageweapon);
            rl_weapon = (RelativeLayout) itemView.findViewById(R.id.rl_weapon);
        }
    }
    public interface AdapterCallback {

        void subcategoryclick(List<SmgResponse> smgList, int position);
    }
}
