package com.adsum.guideforbattlegroundspubg.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adsum.guideforbattlegroundspubg.R;
import com.adsum.guideforbattlegroundspubg.model.AmmosResponse;
import com.adsum.guideforbattlegroundspubg.model.WeaponResponse;

import java.util.List;

public class WeaopnAdapter extends RecyclerView.Adapter<WeaopnAdapter.MyViewHolder> {
    private List<WeaponResponse> weaponList;
    AdapterCallback adapterCallback;
    public WeaopnAdapter(List<WeaponResponse> weaponList, AdapterCallback adapterCallback1) {
        this.weaponList = weaponList;
        this.adapterCallback = adapterCallback1;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        WeaponResponse weaponResponse= weaponList.get(position);

    }

    @Override
    public int getItemCount() {
        return weaponList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }
    public interface AdapterCallback {

        void subcategoryclick(List<AmmosResponse> ammosList, int position);
    }
}
