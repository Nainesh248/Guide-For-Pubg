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
import com.adsum.guideforbattlegroundspubg.model.MuzzlesResponse;
import com.adsum.guideforbattlegroundspubg.model.SniperResponse;

import java.util.List;

public class MuzzlesAdapter extends RecyclerView.Adapter<MuzzlesAdapter.MyViewHolder> {
    private List<MuzzlesResponse> muzzlesList;
    AdapterCallback adapterCallback;
    public MuzzlesAdapter(List<MuzzlesResponse> muzzlesList, AdapterCallback adapterCallback1) {
        this.muzzlesList = muzzlesList;
        this.adapterCallback = adapterCallback1;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_muzzlesmode, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MuzzlesResponse muzzlesResponse= muzzlesList.get(position);
        holder.weponname.setText(muzzlesResponse.getMuzzlename());
        holder.recol.setText(muzzlesResponse.getDetail());
        holder.ammosimage.setImageResource(muzzlesResponse.getMuzzlesimage());
        holder.rl_muzzle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(adapterCallback!=null)
                        adapterCallback.subcategoryclick(muzzlesList,position);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return muzzlesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView  weponname, recol;
        public ImageView ammosimage;
        public RelativeLayout rl_muzzle;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            weponname = (TextView) itemView.findViewById(R.id.txt_muzzle_name);
            recol = (TextView) itemView.findViewById(R.id.txt_weapon_recol);
            ammosimage = (ImageView) itemView.findViewById(R.id.imageweapon);
            rl_muzzle = (RelativeLayout) itemView.findViewById(R.id.rl_muzzle);

        }
    }
    public interface AdapterCallback {

        void subcategoryclick(List<MuzzlesResponse> muzzlesList, int position);
    }
}
