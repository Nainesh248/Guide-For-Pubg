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
import com.adsum.guideforbattlegroundspubg.model.MagazinesDetailResponse;
import com.adsum.guideforbattlegroundspubg.model.MagazinesResponse;

import java.util.List;

public class MagazinesDetailAdapter extends RecyclerView.Adapter<MagazinesDetailAdapter.MyViewHolder> {
    private List<MagazinesDetailResponse> magazinesdetList;
        AdapterCallback adapterCallback;

    public MagazinesDetailAdapter(List<MagazinesDetailResponse> magazinesdetList, AdapterCallback adapterCallback1) {
        this.magazinesdetList = magazinesdetList;
        this.adapterCallback = adapterCallback1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detail_small, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MagazinesDetailResponse magazinesDetailResponse = magazinesdetList.get(position);
        holder.magazinesname.setText(magazinesDetailResponse.getMagazinesname());
        holder.magazinesimage.setImageResource(magazinesDetailResponse.getMagazinesimage());
    }

    @Override
    public int getItemCount() {
        return magazinesdetList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView magazinesname;
        public ImageView magazinesimage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            magazinesname = (TextView) itemView.findViewById(R.id.txt_itemname);
            magazinesimage = (ImageView) itemView.findViewById(R.id.imgitem);

        }
    }

    public interface AdapterCallback {

    }
}
