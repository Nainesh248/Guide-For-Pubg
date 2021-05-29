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
import com.adsum.guideforbattlegroundspubg.model.AttechableWeaponResponse;
import com.adsum.guideforbattlegroundspubg.model.MagazinesDetailResponse;

import java.util.List;

public class AttechableWeaponAdapter extends RecyclerView.Adapter<AttechableWeaponAdapter.MyViewHolder> {
    private List<AttechableWeaponResponse> attechweaponList;
        AdapterCallback adapterCallback;

    public AttechableWeaponAdapter(List<AttechableWeaponResponse> attechweaponList, AdapterCallback adapterCallback1) {
        this.attechweaponList = attechweaponList;
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
        AttechableWeaponResponse attechableWeaponResponse= attechweaponList.get(position);
        holder.attechweaponname.setText(attechableWeaponResponse.getAttechweaponname());
        holder.damage.setText(attechableWeaponResponse.getDetail());
        holder.attechweaponimage.setImageResource(attechableWeaponResponse.getAttechweaponimage());
        holder.rl_weapon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(adapterCallback!=null)
                        adapterCallback.subcategoryclick(attechweaponList,position);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return attechweaponList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView attechweaponname,damage;
        public ImageView attechweaponimage;
        public RelativeLayout rl_weapon;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            attechweaponname = (TextView) itemView.findViewById(R.id.txt_weapon_name);
            damage = (TextView) itemView.findViewById(R.id.txt_weapon_damage);
            attechweaponimage = (ImageView) itemView.findViewById(R.id.imageweapon);
            rl_weapon = (RelativeLayout) itemView.findViewById(R.id.rl_weapon);

        }
    }

    public interface AdapterCallback {

        void subcategoryclick(List<AttechableWeaponResponse> attechweaponList, int position);
    }
}
