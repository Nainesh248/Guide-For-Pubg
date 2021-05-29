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
import com.adsum.guideforbattlegroundspubg.model.DmrResponse;
import com.adsum.guideforbattlegroundspubg.model.LmgResponse;

import java.util.List;

public class LmgAdapter extends RecyclerView.Adapter<LmgAdapter.MyViewHolder> {
    private List<LmgResponse> lmgList;
   AdapterCallback adapterCallback;
    public LmgAdapter(List<LmgResponse> lmgList,AdapterCallback adapterCallback1) {
        this.lmgList = lmgList;
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
        LmgResponse lmgResponse= lmgList.get(position);
        holder.weponname.setText(lmgResponse.getWeponname());
        holder.damage.setText(lmgResponse.getDamage());
        holder.lmgimage.setImageResource(lmgResponse.getLmgimage());
        holder.rl_weapon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(adapterCallback!=null)
                        adapterCallback.subcategoryclick(lmgList,position);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return lmgList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView  weponname, damage;
        public ImageView lmgimage;
        public RelativeLayout rl_weapon;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            weponname = (TextView) itemView.findViewById(R.id.txt_weapon_name);
            damage = (TextView) itemView.findViewById(R.id.txt_weapon_damage);
            lmgimage = (ImageView) itemView.findViewById(R.id.imageweapon);
            rl_weapon = (RelativeLayout) itemView.findViewById(R.id.rl_weapon);

        }
    }
    public interface AdapterCallback {

        void subcategoryclick(List<LmgResponse> lmgList, int position);
    }
}
