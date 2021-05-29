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
import com.adsum.guideforbattlegroundspubg.model.StocksResponse;

import java.util.List;

public class StocksAdapter extends RecyclerView.Adapter<StocksAdapter.MyViewHolder> {
    private List<StocksResponse> stocksList;
    AdapterCallback adapterCallback;
    public StocksAdapter(List<StocksResponse> stocksList ,AdapterCallback adapterCallback1) {
        this.stocksList = stocksList;
        this.adapterCallback = adapterCallback1;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_stocks, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        StocksResponse stocksResponse= stocksList.get(position);
        holder.stockname.setText(stocksResponse.getStocksname());
        holder.detail.setText(stocksResponse.getDetail());
        holder.stockimage.setImageResource(stocksResponse.getStocksimage());
        holder.rl_stocks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (adapterCallback != null)
                        adapterCallback.subcategoryclick(stocksList, position);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return stocksList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView stockname, detail;
        public ImageView stockimage;
        public RelativeLayout rl_stocks;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            stockname = (TextView) itemView.findViewById(R.id.txt_stocks_name);
            detail = (TextView) itemView.findViewById(R.id.txt_stocks_detail);
            stockimage = (ImageView) itemView.findViewById(R.id.imageweapon);
            rl_stocks = (RelativeLayout) itemView.findViewById(R.id.rl_stocks);
        }
    }
    public interface AdapterCallback {


        void subcategoryclick(List<StocksResponse> stocksList, int position);
    }
}
