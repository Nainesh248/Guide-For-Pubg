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
import com.adsum.guideforbattlegroundspubg.model.MagazinesResponse;
import com.adsum.guideforbattlegroundspubg.model.ScopesResponse;

import java.util.List;

public class MagazinesAdapter extends RecyclerView.Adapter<MagazinesAdapter.MyViewHolder> {
    private List<MagazinesResponse> magazinesList;
        AdapterCallback adapterCallback;

    public MagazinesAdapter(List<MagazinesResponse> magazinesList, AdapterCallback adapterCallback1) {
        this.magazinesList = magazinesList;
        this.adapterCallback = adapterCallback1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_magazines, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MagazinesResponse magazinesResponse = magazinesList.get(position);
        holder.magazinesname.setText(magazinesResponse.getMagazinesname());
        holder.detail.setText(magazinesResponse.getDetail());
        holder.magazinesimage.setImageResource(magazinesResponse.getMagazinesimage());
        holder.rl_magazines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (adapterCallback != null)
                        adapterCallback.subcategoryclick(magazinesList, position);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return magazinesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView magazinesname, detail;
        public ImageView magazinesimage;
        public RelativeLayout rl_magazines;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            magazinesname = (TextView) itemView.findViewById(R.id.txt_magazines_name);
            detail = (TextView) itemView.findViewById(R.id.txt_magazines_reload);
            magazinesimage = (ImageView) itemView.findViewById(R.id.imageweapon);
            rl_magazines = (RelativeLayout) itemView.findViewById(R.id.rl_magazines);

        }
    }

    public interface AdapterCallback {

        void subcategoryclick(List<MagazinesResponse> magazinesList, int position);
    }
}
