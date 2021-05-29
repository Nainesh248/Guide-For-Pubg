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
import com.adsum.guideforbattlegroundspubg.model.MuzzlesDetailResponse;
import com.adsum.guideforbattlegroundspubg.model.MuzzlesResponse;

import java.util.List;

public class MuzzlesDetailAdapter extends RecyclerView.Adapter<MuzzlesDetailAdapter.MyViewHolder> {
    private List<MuzzlesDetailResponse> muzzlesdetList;
    AdapterCallback adapterCallback;
    public MuzzlesDetailAdapter(List<MuzzlesDetailResponse> muzzlesdetList, AdapterCallback adapterCallback1) {
        this.muzzlesdetList = muzzlesdetList;
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
        MuzzlesDetailResponse muzzlesDetailResponse= muzzlesdetList.get(position);
        holder.muzzlesname.setText(muzzlesDetailResponse.getMuzzlename());
        holder.muzzlesimage.setImageResource(muzzlesDetailResponse.getMuzzlesimage());
    }

    @Override
    public int getItemCount() {
        return muzzlesdetList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView  muzzlesname;
        public ImageView muzzlesimage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            muzzlesname = (TextView) itemView.findViewById(R.id.txt_itemname);
            muzzlesimage = (ImageView) itemView.findViewById(R.id.imgitem);


        }
    }
    public interface AdapterCallback {

    }
}
