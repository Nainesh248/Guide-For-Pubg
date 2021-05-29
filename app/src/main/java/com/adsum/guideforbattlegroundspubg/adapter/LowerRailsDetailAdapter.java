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
import com.adsum.guideforbattlegroundspubg.model.LowerRailsDetailsResponse;
import com.adsum.guideforbattlegroundspubg.model.LowerRailsResponse;

import java.util.List;

public class LowerRailsDetailAdapter extends RecyclerView.Adapter<LowerRailsDetailAdapter.MyViewHolder> {
    private List<LowerRailsDetailsResponse> lowerrailsdetList;
AdapterCallback adapterCallback;
    public LowerRailsDetailAdapter(List<LowerRailsDetailsResponse> lowerrailsdetList, AdapterCallback adapterCallback1) {
        this.lowerrailsdetList = lowerrailsdetList;
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
        LowerRailsDetailsResponse lowerRailsResponse= lowerrailsdetList.get(position);
        holder.lowername.setText(lowerRailsResponse.getLowerrname());
        holder.lowerrailsimage.setImageResource(lowerRailsResponse.getLowerrimage());
    }

    @Override
    public int getItemCount() {
        return lowerrailsdetList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView lowername;
        public ImageView lowerrailsimage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            lowername = (TextView) itemView.findViewById(R.id.txt_itemname);
            lowerrailsimage = (ImageView) itemView.findViewById(R.id.imgitem);

        }
    }
    public interface AdapterCallback {

    }
}
