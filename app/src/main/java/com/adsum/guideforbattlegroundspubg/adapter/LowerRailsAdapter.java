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
import com.adsum.guideforbattlegroundspubg.model.LowerRailsResponse;
import com.adsum.guideforbattlegroundspubg.model.MuzzlesResponse;

import java.util.List;

public class LowerRailsAdapter extends RecyclerView.Adapter<LowerRailsAdapter.MyViewHolder> {
    private List<LowerRailsResponse> lowerrailsList;
AdapterCallback adapterCallback;
    public LowerRailsAdapter(List<LowerRailsResponse> lowerrailsList,   AdapterCallback adapterCallback1) {
        this.lowerrailsList = lowerrailsList;
        this.adapterCallback = adapterCallback1;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lowerrails, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        LowerRailsResponse lowerRailsResponse= lowerrailsList.get(position);
        holder.lowername.setText(lowerRailsResponse.getLowerrname());
        holder.recol.setText(lowerRailsResponse.getDetail());
        holder.lowerrailsimage.setImageResource(lowerRailsResponse.getLowerrimage());
        holder.rl_lowerrails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(adapterCallback!=null)
                        adapterCallback.subcategoryclick(lowerrailsList,position);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return lowerrailsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView  lowername, recol;
        public ImageView lowerrailsimage;
        public RelativeLayout rl_lowerrails;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            lowername = (TextView) itemView.findViewById(R.id.txt_muzzle_name);
            recol = (TextView) itemView.findViewById(R.id.txt_weapon_recol);
            lowerrailsimage = (ImageView) itemView.findViewById(R.id.imageweapon);
            rl_lowerrails = (RelativeLayout) itemView.findViewById(R.id.rl_lowerrails);
        }
    }
    public interface AdapterCallback {


        void subcategoryclick(List<LowerRailsResponse> lowerrailsList, int position);
    }
}
