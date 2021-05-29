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
import com.adsum.guideforbattlegroundspubg.model.StocksDetailResponse;
import com.adsum.guideforbattlegroundspubg.model.StocksResponse;

import java.util.List;

public class StocksDetailAdapter extends RecyclerView.Adapter<StocksDetailAdapter.MyViewHolder> {
    private List<StocksDetailResponse> stocksdetList;
    AdapterCallback adapterCallback;
    public StocksDetailAdapter(List<StocksDetailResponse> stocksdetList , AdapterCallback adapterCallback1) {
        this.stocksdetList = stocksdetList;
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
        StocksDetailResponse stocksDetailResponse= stocksdetList.get(position);
        holder.stockname.setText(stocksDetailResponse.getStocksname());
        holder.stockimage.setImageResource(stocksDetailResponse .getStocksimage());
    }

    @Override
    public int getItemCount() {
        return stocksdetList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView stockname;
        public ImageView stockimage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            stockname = (TextView) itemView.findViewById(R.id.txt_itemname);
            stockimage = (ImageView) itemView.findViewById(R.id.imgitem);
        }
    }
    public interface AdapterCallback {


    }
}
