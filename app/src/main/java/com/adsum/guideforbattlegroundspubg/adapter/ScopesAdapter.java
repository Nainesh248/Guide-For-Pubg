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
import com.adsum.guideforbattlegroundspubg.model.ScopesResponse;

import java.util.List;

public class ScopesAdapter extends RecyclerView.Adapter<ScopesAdapter.MyViewHolder> {
    private List<ScopesResponse> scopesList;
    AdapterCallback adapterCallback;
    public ScopesAdapter(List<ScopesResponse> scopesList, AdapterCallback adapterCallback1) {
        this.scopesList = scopesList;
        this.adapterCallback = adapterCallback1;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_scopes, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ScopesResponse scopesResponse= scopesList.get(position);
        holder.scopesname.setText(scopesResponse.getScopename());
        holder.detail.setText(scopesResponse.getDetail());
        holder.scopesimage.setImageResource(scopesResponse.getScopeimage());
        holder.rl_scope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(adapterCallback!=null)
                        adapterCallback.subcategoryclick(scopesList,position);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return scopesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView  scopesname, detail;
        public ImageView scopesimage;
        public RelativeLayout rl_scope;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            scopesname = (TextView) itemView.findViewById(R.id.txt_scopes_name);
            detail = (TextView) itemView.findViewById(R.id.txt_scopes_detail);
            scopesimage = (ImageView) itemView.findViewById(R.id.imageweapon);
            rl_scope = (RelativeLayout) itemView.findViewById(R.id.rl_scope);

        }
    }
    public interface AdapterCallback {

        void subcategoryclick(List<ScopesResponse> scopesList, int position);
    }
}
