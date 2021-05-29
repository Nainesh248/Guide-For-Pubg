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
import com.adsum.guideforbattlegroundspubg.model.MeleeResponse;
import com.adsum.guideforbattlegroundspubg.model.SmgResponse;
import com.adsum.guideforbattlegroundspubg.model.ThrowabilesResponse;

import java.util.List;

public class ThrowabilesAdapter extends RecyclerView.Adapter<ThrowabilesAdapter.MyViewHolder> {
    private List<ThrowabilesResponse> throwList;
    AdapterCallback adapterCallback;
    public ThrowabilesAdapter(List<ThrowabilesResponse> throwList, AdapterCallback adapterCallback1) {
        this.throwList = throwList;
        this.adapterCallback = adapterCallback1;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_throwables, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ThrowabilesResponse throwabilesResponse= throwList.get(position);
        holder.weponname.setText(throwabilesResponse.getWeponname());
        holder.damage.setText(throwabilesResponse.getDamage());
        holder.throwimage.setImageResource(throwabilesResponse.getThrowimage());
        holder.rl_weapon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (adapterCallback != null)
                        adapterCallback.subcategoryclick(throwList, position);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return throwList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView  weponname, damage;
        public ImageView throwimage;
        public RelativeLayout rl_weapon;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            weponname = (TextView) itemView.findViewById(R.id.txt_weapon_name);
            damage = (TextView) itemView.findViewById(R.id.txt_weapon_damage);
            throwimage = (ImageView) itemView.findViewById(R.id.imageweapon);
            rl_weapon = (RelativeLayout) itemView.findViewById(R.id.rl_weapon);

        }
    }
    public interface AdapterCallback {

        void subcategoryclick(List<ThrowabilesResponse> throwList, int position);
    }
}
