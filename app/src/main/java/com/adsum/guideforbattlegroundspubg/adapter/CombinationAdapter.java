package com.adsum.guideforbattlegroundspubg.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adsum.guideforbattlegroundspubg.R;
import com.adsum.guideforbattlegroundspubg.model.CombinationResponse;
import com.adsum.guideforbattlegroundspubg.model.ScopesDetailResponse;

import java.util.List;

public class CombinationAdapter extends RecyclerView.Adapter<CombinationAdapter.MyViewHolder> {
    private List<CombinationResponse> combinationList;
    AdapterCallback adapterCallback;
    public CombinationAdapter(List<CombinationResponse> combinationList, AdapterCallback adapterCallback1) {
        this.combinationList = combinationList;
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
        CombinationResponse combinationResponse= combinationList.get(position);
        holder.combinationname.setText(combinationResponse.getCombinationname());
        holder.combinationimage.setImageResource(combinationResponse.getCombinationimage());
    }

    @Override
    public int getItemCount() {
        return combinationList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView  combinationname;
        public ImageView combinationimage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            combinationname = (TextView) itemView.findViewById(R.id.txt_itemname);
            combinationimage = (ImageView) itemView.findViewById(R.id.imgitem);

        }
    }
    public interface AdapterCallback {

    }
}
