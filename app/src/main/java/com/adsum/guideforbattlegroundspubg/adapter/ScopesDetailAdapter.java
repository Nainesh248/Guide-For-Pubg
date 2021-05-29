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
import com.adsum.guideforbattlegroundspubg.model.ScopesDetailResponse;
import com.adsum.guideforbattlegroundspubg.model.ScopesResponse;

import java.util.List;

public class ScopesDetailAdapter extends RecyclerView.Adapter<ScopesDetailAdapter.MyViewHolder> {
    private List<ScopesDetailResponse> scopesdetList;
    AdapterCallback adapterCallback;
    public ScopesDetailAdapter(List<ScopesDetailResponse> scopesdetList, AdapterCallback adapterCallback1) {
        this.scopesdetList = scopesdetList;
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
        ScopesDetailResponse scopesDetailResponse= scopesdetList.get(position);
        holder.scopesname.setText(scopesDetailResponse.getScopename());
        holder.scopesimage.setImageResource(scopesDetailResponse.getScopeimage());
    }

    @Override
    public int getItemCount() {
        return scopesdetList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView  scopesname;
        public ImageView scopesimage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            scopesname = (TextView) itemView.findViewById(R.id.txt_itemname);
            scopesimage = (ImageView) itemView.findViewById(R.id.imgitem);

        }
    }
    public interface AdapterCallback {

    }
}
