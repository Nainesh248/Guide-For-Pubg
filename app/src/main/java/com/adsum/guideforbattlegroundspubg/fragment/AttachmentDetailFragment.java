package com.adsum.guideforbattlegroundspubg.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adsum.guideforbattlegroundspubg.R;
import com.adsum.guideforbattlegroundspubg.activity.DashBoard;
import com.adsum.guideforbattlegroundspubg.adapter.AttechableWeaponAdapter;
import com.adsum.guideforbattlegroundspubg.adapter.EquipmentAdapter;
import com.adsum.guideforbattlegroundspubg.config.Constants;
import com.adsum.guideforbattlegroundspubg.model.AttechableWeaponResponse;
import com.adsum.guideforbattlegroundspubg.model.EquipmentResponse;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AttachmentDetailFragment extends Fragment {

    View rootView;
    String attechname;
    int attechimage;
    ImageView attechmentimage;
    TextView attechmentname;
    TextView detail,recoil1,recoil2,recoil3;

    private RecyclerView attechweapon_recycler;
    private AttechableWeaponAdapter attechableWeaponAdapter;
    private List<AttechableWeaponResponse> attechableWeaponResponses = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.activity_attachment_detail_fragment, container, false);
        attechmentimage = (ImageView) rootView.findViewById(R.id.gunimage);
        attechmentname = (TextView) rootView.findViewById(R.id.txt_attechmentname);

        attechweapon_recycler = (RecyclerView) rootView.findViewById(R.id.attechweapon_recycler);

        detail = (TextView) rootView.findViewById(R.id.txt_detail);
        recoil1 = (TextView) rootView.findViewById(R.id.txt_recoil1);
        recoil2 = (TextView) rootView.findViewById(R.id.txt_recoil2);
        recoil3 = (TextView) rootView.findViewById(R.id.txt_recoil3);
                MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        List<String> testDeviceIds = Arrays.asList("552DE1A067B9147A504354A81D082946");
        RequestConfiguration configuration =
                new RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build();
        AdSettings.addTestDevice("4a203f45-6037-4093-9775-54d7250fda28");

        AudienceNetworkAds.initialize(getActivity());
        init();
        return rootView;

    }

    private void init() {
        try {
            if (getArguments() != null && getArguments().containsKey(Constants.attechmentname)) {
                attechname = getArguments().getString(Constants.attechmentname);
                attechimage = getArguments().getInt(Constants.attechmentimage);
                attechmentname.setText(attechname);
                attechmentimage.setImageResource(attechimage);
                attechmentdata();
                attechweapondata();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void attechweapondata() {
        try {
            attechableWeaponAdapter = new AttechableWeaponAdapter(attechableWeaponResponses, new AttechableWeaponAdapter.AdapterCallback() {

                @Override
                public void subcategoryclick(List<AttechableWeaponResponse> attechweaponList, int position) {
                    DetailFragment detailFragment = new DetailFragment();
                    Bundle bundle = new Bundle();
                    //  bundle.putString(Constants.catgegoryname,txt_attechment.toString());
                    bundle.putString(Constants.weaponname,attechweaponList.get(position).getAttechweaponname());
                    bundle.putInt(Constants.weaponimage,attechweaponList.get(position).getAttechweaponimage());

                    detailFragment.setArguments(bundle);
                    ((DashBoard)getActivity()).addFragment(detailFragment);
                }
            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            attechweapon_recycler.setLayoutManager(mLayoutManager);
            attechweapon_recycler.setItemAnimator(new DefaultItemAnimator());
            attechweapon_recycler.setAdapter(attechableWeaponAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void attechmentdata() {
        try {
                if (attechname.equalsIgnoreCase("Chcoke (SG)")){

                AttechableWeaponResponse
                        attechweapon= new AttechableWeaponResponse(getString(R.string.s686), "DAMAGE: 216",R.drawable.ic_s686);
                attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.s1897), "DAMAGE: 216",R.drawable.ic_s1897);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.sawedoff), "DAMAGE: 160",R.drawable.ic_sawed);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();

                detail.setText("Reduces pellet spread by 25%");
                recoil1.setVisibility(View.INVISIBLE);
                recoil2.setText("-25% Shot sread");
                recoil3.setVisibility(View.INVISIBLE);

            }
            else if(attechname.equalsIgnoreCase("Compensator (AR,DMR,S12K)")){

                AttechableWeaponResponse
                        attechweapon= new AttechableWeaponResponse(getString(R.string.groza), "DAMAGE: 49",R.drawable.ic_groza);
                attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.akm), "DAMAGE: 49",R.drawable.ic_akm);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mk47), "DAMAGE: 49",R.drawable.ic_mk47);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mm762), "DAMAGE: 47",R.drawable.ic_m762);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m416), "DAMAGE: 43",R.drawable.ic_m416);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.scarl), "DAMAGE: 43",R.drawable.ic_scarl);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.auga3), "DAMAGE: 43",R.drawable.ic_aug);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.qbz95), "DAMAGE: 43",R.drawable.ic_qbz);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m16a4), "DAMAGE: 43",R.drawable.ic_m16a4);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();

                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setText("-25% recoil pattern");
                recoil2.setText("-10% horizontal recoil");
                recoil3.setText("-15% verticle recoil");
            }
            else if(attechname.equalsIgnoreCase("Compensator (DMR,SR)")){

                AttechableWeaponResponse
                        attechweapon= new AttechableWeaponResponse(getString(R.string.awm), "DAMAGE: 132",R.drawable.ic_awm);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m24), "DAMAGE: 82",R.drawable.ic_m24);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.kar98k), "DAMAGE: 72",R.drawable.ic_kar98);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mk14), "DAMAGE: 61",R.drawable.ic_mk14);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.slr), "DAMAGE: 58",R.drawable.ic_slr);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.sks), "DAMAGE: 53",R.drawable.ic_sks);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.qbu), "DAMAGE: 48",R.drawable.ic_qbu);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mini14), "DAMAGE: 46",R.drawable.ic_mini14);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();

                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setText("-25% recoil pattern");
                recoil2.setText("-20% horizontal recoil");
                recoil3.setText("-20% verticle recoil");
            }
            else if(attechname.equalsIgnoreCase("Compensator (SMG)")){

                AttechableWeaponResponse
                        attechweapon= new AttechableWeaponResponse(getString(R.string.ump9), "DAMAGE: 39",R.drawable.ic_ump45);
                attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.vector), "DAMAGE: 34",R.drawable.ic_vector);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.microuzi), "DAMAGE: 26",R.drawable.ic_uzi);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();

                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setText("-25% recoil pattern");
                recoil2.setText("-20% horizontal recoil");
                recoil3.setText("-25% verticle recoil");
            }
            else if(attechname.equalsIgnoreCase("Duckbill (SG)")){
                AttechableWeaponResponse
                        attechweapon= new AttechableWeaponResponse(getString(R.string.tommy), "DAMAGE: 40",R.drawable.ic_thomson);
                attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.pp19), "DAMAGE: 35",R.drawable.ic_bizon);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setText("+shotgun pellet verticle spread tightness");
                recoil2.setText("-shotgun pellet horizontal");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("Flash Hider (AR,DMR,S12K)")){
                AttechableWeaponResponse
                        attechweapon= new AttechableWeaponResponse(getString(R.string.slr), "DAMAGE: 58",R.drawable.ic_slr);
                attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.sks), "DAMAGE: 53",R.drawable.ic_sks);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.akm), "DAMAGE: 49",R.drawable.ic_akm);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mk47), "DAMAGE: 49",R.drawable.ic_mk47);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.berylm762), "DAMAGE: 47",R.drawable.ic_m762);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m416), "DAMAGE: 43",R.drawable.ic_m416);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.scarl), "DAMAGE: 43",R.drawable.ic_scarl);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.qbz95), "DAMAGE: 43",R.drawable.ic_qbz);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m16a4), "DAMAGE: 43",R.drawable.ic_m16a4);
                    attechableWeaponResponses.add(attechweapon);


                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setText("-10% recoil pattern");
                recoil2.setText("-10% horizontal recoil");
                recoil3.setText("-10% verticle recoil");
            }
            else if(attechname.equalsIgnoreCase("Flash Hider (DMR,SR)")){
                    AttechableWeaponResponse
                            attechweapon= new AttechableWeaponResponse(getString(R.string.awm), "DAMAGE: 132",R.drawable.ic_awm);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m24), "DAMAGE: 82",R.drawable.ic_m24);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.kar98k), "DAMAGE: 72",R.drawable.ic_kar98);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mk14), "DAMAGE: 61",R.drawable.ic_mk14);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.slr), "DAMAGE: 58",R.drawable.ic_slr);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.sks), "DAMAGE: 53",R.drawable.ic_sks);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.qbu), "DAMAGE: 48",R.drawable.ic_qbu);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mini14), "DAMAGE: 46",R.drawable.ic_mini14);
                    attechableWeaponResponses.add(attechweapon);
                    attechableWeaponAdapter.notifyDataSetChanged();

                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setText("-10% recoil pattern");
                recoil2.setText("-10% horizontal recoil");
                recoil3.setText("-10% verticle recoil");
            }
            else if(attechname.equalsIgnoreCase("Flash Hider (SMG)")){
                    AttechableWeaponResponse
                            attechweapon= new AttechableWeaponResponse(getString(R.string.s686), "DAMAGE: 216",R.drawable.ic_s686);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.s1897), "DAMAGE: 216",R.drawable.ic_s1897);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.sawedoff), "DAMAGE: 160",R.drawable.ic_sawed);
                    attechableWeaponResponses.add(attechweapon);
                    attechableWeaponAdapter.notifyDataSetChanged();

                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setText("-10% recoil pattern");
                recoil2.setText("-10% horizontal recoil");
                recoil3.setText("-10% verticle recoil");
            }
            else if(attechname.equalsIgnoreCase("Suppressor (AR,DMR,S12K)")){
                    AttechableWeaponResponse


                    attechweapon= new AttechableWeaponResponse(getString(R.string.mk14), "DAMAGE: 61",R.drawable.ic_mk14);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.slr), "DAMAGE: 58",R.drawable.ic_slr);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.sks), "DAMAGE: 53",R.drawable.ic_sks);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.qbu), "DAMAGE: 48",R.drawable.ic_qbu);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mini14), "DAMAGE: 46",R.drawable.ic_mini14);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.akm), "DAMAGE: 49",R.drawable.ic_akm);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mk47), "DAMAGE: 49",R.drawable.ic_mk47);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.berylm762), "DAMAGE: 47",R.drawable.ic_m762);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m416), "DAMAGE: 43",R.drawable.ic_m416);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.scarl), "DAMAGE: 43",R.drawable.ic_scarl);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.qbz95), "DAMAGE: 43",R.drawable.ic_qbz);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m16a4), "DAMAGE: 43",R.drawable.ic_m16a4);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.s12k), "DAMAGE: 198",R.drawable.ic_s12k);
                    attechableWeaponResponses.add(attechweapon);
                    attechableWeaponAdapter.notifyDataSetChanged();

                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setVisibility(View.INVISIBLE);
                recoil2.setText("-5% deviation");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("Suppressor (DMR,SR)")){
                AttechableWeaponResponse
                        attechweapon= new AttechableWeaponResponse(getString(R.string.awm), "DAMAGE: 132",R.drawable.ic_awm);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m24), "DAMAGE: 82",R.drawable.ic_m24);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.kar98k), "DAMAGE: 72",R.drawable.ic_kar98);
                    attechableWeaponResponses.add(attechweapon);
                        attechweapon= new AttechableWeaponResponse(getString(R.string.mk14), "DAMAGE: 61",R.drawable.ic_mk14);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.slr), "DAMAGE: 58",R.drawable.ic_slr);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.sks), "DAMAGE: 53",R.drawable.ic_sks);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.qbu), "DAMAGE: 48",R.drawable.ic_qbu);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mini14), "DAMAGE: 46",R.drawable.ic_mini14);
                    attechableWeaponResponses.add(attechweapon);


                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setVisibility(View.INVISIBLE);
                recoil2.setText("-5% deviation");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("Suppressor (Handguns)")){
                AttechableWeaponResponse
                        attechweapon= new AttechableWeaponResponse(getString(R.string.r1895), "DAMAGE: 55",R.drawable.ic_r1895);
                attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.p1911), "DAMAGE: 41",R.drawable.ic_p1911);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.p92), "DAMAGE: 35",R.drawable.ic_p92);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.p18c), "DAMAGE: 23",R.drawable.ic_p18c);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.skorpion), "DAMAGE: 22",R.drawable.ic_skorpion);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setVisibility(View.INVISIBLE);
                recoil2.setText("-5% deviation");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("Suppressor (SMG)")){
                AttechableWeaponResponse
                        attechweapon= new AttechableWeaponResponse(getString(R.string.tommy), "DAMAGE: 40",R.drawable.ic_thomson);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.vector), "DAMAGE: 34",R.drawable.ic_vector);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.ump9), "DAMAGE: 39",R.drawable.ic_ump45);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.microuzi), "DAMAGE: 26",R.drawable.ic_uzi);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setVisibility(View.INVISIBLE);
                recoil2.setText("-5% deviation");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("Angled Foregrip (AR, DMR, SMG)")){
                AttechableWeaponResponse
                        attechweapon= new AttechableWeaponResponse(getString(R.string.mk47), "DAMAGE: 49",R.drawable.ic_mk47);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.berylm762), "DAMAGE: 47",R.drawable.ic_m762);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m416), "DAMAGE: 43",R.drawable.ic_m416);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.scarl), "DAMAGE: 43",R.drawable.ic_scarl);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.sks), "DAMAGE: 53",R.drawable.ic_sks);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.auga3), "DAMAGE: 43",R.drawable.ic_aug);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.ump9), "DAMAGE: 39",R.drawable.ic_ump45);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setText("-20% recoil pattern");
                recoil2.setText("-20% horizontal recoil");
                recoil3.setText("-10% faster ads");
            }
            else if(attechname.equalsIgnoreCase("Half Grip")){
                AttechableWeaponResponse
                        attechweapon= new AttechableWeaponResponse(getString(R.string.auga3), "DAMAGE: 43",R.drawable.ic_aug);
                    attechableWeaponResponses.add(attechweapon);
                        attechweapon= new AttechableWeaponResponse(getString(R.string.vector), "DAMAGE: 34",R.drawable.ic_vector);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.ump9), "DAMAGE: 39",R.drawable.ic_ump45);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.skorpion), "DAMAGE: 22",R.drawable.ic_skorpion);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m416), "DAMAGE: 43",R.drawable.ic_m416);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.scarl), "DAMAGE: 43",R.drawable.ic_scarl);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.qbz95), "DAMAGE: 43",R.drawable.ic_qbz);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.sks), "DAMAGE: 53",R.drawable.ic_sks);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setText("+recoil control");
                recoil2.setText("+recoil recovery");
                recoil3.setText("-weapon steadiness");
            }
            else if(attechname.equalsIgnoreCase("Quiver for Crossbow")){
                AttechableWeaponResponse
                        attechweapon= new AttechableWeaponResponse(getString(R.string.crossbow), "DAMAGE: 105",R.drawable.ic_crossbow);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setVisibility(View.INVISIBLE);
                recoil2.setText("-30% reload duration");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("Vertical Foregrip (AR, SMG DMR)")){
                AttechableWeaponResponse

                        attechweapon= new AttechableWeaponResponse(getString(R.string.mk47), "DAMAGE: 49",R.drawable.ic_mk47);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.berylm762), "DAMAGE: 47",R.drawable.ic_m762);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m416), "DAMAGE: 43",R.drawable.ic_m416);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.scarl), "DAMAGE: 43",R.drawable.ic_scarl);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.sks), "DAMAGE: 53",R.drawable.ic_sks);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.auga3), "DAMAGE: 43",R.drawable.ic_aug);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.ump9), "DAMAGE: 39",R.drawable.ic_ump45);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.tommy), "DAMAGE: 40",R.drawable.ic_thomson);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.qbz95), "DAMAGE: 43",R.drawable.ic_qbz);
                    attechableWeaponResponses.add(attechweapon);

                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setText("-20% recoil pattern");
                recoil2.setText("-15% verticle recoil");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("Thumb Grip")){
                AttechableWeaponResponse
                        attechweapon= new AttechableWeaponResponse(getString(R.string.m416), "DAMAGE: 43",R.drawable.ic_m416);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.scarl), "DAMAGE: 43",R.drawable.ic_scarl);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.sks), "DAMAGE: 53",R.drawable.ic_sks);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.auga3), "DAMAGE: 43",R.drawable.ic_aug);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.ump9), "DAMAGE: 39",R.drawable.ic_ump45);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.vector), "DAMAGE: 34",R.drawable.ic_vector);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.qbz95), "DAMAGE: 43",R.drawable.ic_qbz);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.skorpion), "DAMAGE: 22",R.drawable.ic_skorpion);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setText("+ads speed");
                recoil2.setText("+verticle recoil control");
                recoil3.setText("+weapon steadiness");
            }
            else if(attechname.equalsIgnoreCase("Light Grip")){
                AttechableWeaponResponse
                        attechweapon= new AttechableWeaponResponse(getString(R.string.m416), "DAMAGE: 43",R.drawable.ic_m416);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.scarl), "DAMAGE: 43",R.drawable.ic_scarl);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.sks), "DAMAGE: 53",R.drawable.ic_sks);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.auga3), "DAMAGE: 43",R.drawable.ic_aug);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.ump9), "DAMAGE: 39",R.drawable.ic_ump45);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.qbz95), "DAMAGE: 43",R.drawable.ic_qbz);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.skorpion), "DAMAGE: 22",R.drawable.ic_skorpion);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setText("+weapon stability after shot");
                recoil2.setText("+weapon steadiness");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("8x CQBSS Scope")){
                AttechableWeaponResponse
                        attechweapon= new AttechableWeaponResponse(getString(R.string.awm), "DAMAGE: 132",R.drawable.ic_awm);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m24), "DAMAGE: 82",R.drawable.ic_m24);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.kar98k), "DAMAGE: 72",R.drawable.ic_kar98);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mk14), "DAMAGE: 61",R.drawable.ic_mk14);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.slr), "DAMAGE: 58",R.drawable.ic_slr);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.sks), "DAMAGE: 53",R.drawable.ic_sks);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.qbu), "DAMAGE: 48",R.drawable.ic_qbu);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mini14), "DAMAGE: 46",R.drawable.ic_mini14);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setVisibility(View.INVISIBLE);
                recoil2.setText("-20% ads speed");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("6x Scope")){
                AttechableWeaponResponse
                        attechweapon= new AttechableWeaponResponse(getString(R.string.awm), "DAMAGE: 132",R.drawable.ic_awm);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m24), "DAMAGE: 82",R.drawable.ic_m24);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.kar98k), "DAMAGE: 72",R.drawable.ic_kar98);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mk14), "DAMAGE: 61",R.drawable.ic_mk14);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.slr), "DAMAGE: 58",R.drawable.ic_slr);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.sks), "DAMAGE: 53",R.drawable.ic_sks);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.qbu), "DAMAGE: 48",R.drawable.ic_qbu);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mini14), "DAMAGE: 46",R.drawable.ic_mini14);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setVisibility(View.INVISIBLE);
                recoil2.setText("-20% ads speed");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("4x ACOG Scope")){
                AttechableWeaponResponse
                        attechweapon= new AttechableWeaponResponse(getString(R.string.awm), "DAMAGE: 132",R.drawable.ic_awm);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m24), "DAMAGE: 82",R.drawable.ic_m24);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.kar98k), "DAMAGE: 72",R.drawable.ic_kar98);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mk14), "DAMAGE: 61",R.drawable.ic_mk14);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.slr), "DAMAGE: 58",R.drawable.ic_slr);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.sks), "DAMAGE: 53",R.drawable.ic_sks);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.qbu), "DAMAGE: 48",R.drawable.ic_qbu);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mini14), "DAMAGE: 46",R.drawable.ic_mini14);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m249), "DAMAGE: 45",R.drawable.ic_m249);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.dp28), "DAMAGE: 51",R.drawable.ic_dp28);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.groza), "DAMAGE: 49",R.drawable.ic_groza);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.akm), "DAMAGE: 49",R.drawable.ic_akm);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mk47), "DAMAGE: 49",R.drawable.ic_mk47);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mm762), "DAMAGE: 47",R.drawable.ic_m762);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m416), "DAMAGE: 43",R.drawable.ic_m416);
                    attechableWeaponResponses.add(attechweapon);

                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setVisibility(View.INVISIBLE);
                recoil2.setText("-4.21x Magnification");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("3x Backlit Scope")){
                AttechableWeaponResponse
                        attechweapon= new AttechableWeaponResponse(getString(R.string.awm), "DAMAGE: 132",R.drawable.ic_awm);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m24), "DAMAGE: 82",R.drawable.ic_m24);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.kar98k), "DAMAGE: 72",R.drawable.ic_kar98);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mk14), "DAMAGE: 61",R.drawable.ic_mk14);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.slr), "DAMAGE: 58",R.drawable.ic_slr);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.sks), "DAMAGE: 53",R.drawable.ic_sks);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.qbu), "DAMAGE: 48",R.drawable.ic_qbu);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mini14), "DAMAGE: 46",R.drawable.ic_mini14);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m249), "DAMAGE: 45",R.drawable.ic_m249);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.dp28), "DAMAGE: 51",R.drawable.ic_dp28);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.groza), "DAMAGE: 49",R.drawable.ic_groza);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.akm), "DAMAGE: 49",R.drawable.ic_akm);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mk47), "DAMAGE: 49",R.drawable.ic_mk47);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mm762), "DAMAGE: 47",R.drawable.ic_m762);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m416), "DAMAGE: 43",R.drawable.ic_m416);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setVisibility(View.INVISIBLE);
                recoil2.setText("-10% ads speed");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("2x Aimpoint Scope")){
                AttechableWeaponResponse
                        attechweapon= new AttechableWeaponResponse(getString(R.string.awm), "DAMAGE: 132",R.drawable.ic_awm);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m24), "DAMAGE: 82",R.drawable.ic_m24);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.kar98k), "DAMAGE: 72",R.drawable.ic_kar98);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mk14), "DAMAGE: 61",R.drawable.ic_mk14);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.slr), "DAMAGE: 58",R.drawable.ic_slr);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.sks), "DAMAGE: 53",R.drawable.ic_sks);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.qbu), "DAMAGE: 48",R.drawable.ic_qbu);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mini14), "DAMAGE: 46",R.drawable.ic_mini14);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m249), "DAMAGE: 45",R.drawable.ic_m249);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.dp28), "DAMAGE: 51",R.drawable.ic_dp28);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.groza), "DAMAGE: 49",R.drawable.ic_groza);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.akm), "DAMAGE: 49",R.drawable.ic_akm);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mk47), "DAMAGE: 49",R.drawable.ic_mk47);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mm762), "DAMAGE: 47",R.drawable.ic_m762);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m416), "DAMAGE: 43",R.drawable.ic_m416);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setVisibility(View.INVISIBLE);
                recoil2.setText("-10% ads speed");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("Red Dot Sight")){
                AttechableWeaponResponse         attechweapon= new AttechableWeaponResponse(getString(R.string.awm), "DAMAGE: 132",R.drawable.ic_awm);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m24), "DAMAGE: 82",R.drawable.ic_m24);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.kar98k), "DAMAGE: 72",R.drawable.ic_kar98);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mk14), "DAMAGE: 61",R.drawable.ic_mk14);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.slr), "DAMAGE: 58",R.drawable.ic_slr);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.sks), "DAMAGE: 53",R.drawable.ic_sks);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.qbu), "DAMAGE: 48",R.drawable.ic_qbu);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mini14), "DAMAGE: 46",R.drawable.ic_mini14);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m249), "DAMAGE: 45",R.drawable.ic_m249);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.dp28), "DAMAGE: 51",R.drawable.ic_dp28);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.groza), "DAMAGE: 49",R.drawable.ic_groza);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.akm), "DAMAGE: 49",R.drawable.ic_akm);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mk47), "DAMAGE: 49",R.drawable.ic_mk47);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mm762), "DAMAGE: 47",R.drawable.ic_m762);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m416), "DAMAGE: 43",R.drawable.ic_m416);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setVisibility(View.INVISIBLE);
                recoil2.setText("-20% faster ads speed");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("Holographic Sight")){
                AttechableWeaponResponse         attechweapon= new AttechableWeaponResponse(getString(R.string.awm), "DAMAGE: 132",R.drawable.ic_awm);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m24), "DAMAGE: 82",R.drawable.ic_m24);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.kar98k), "DAMAGE: 72",R.drawable.ic_kar98);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mk14), "DAMAGE: 61",R.drawable.ic_mk14);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.slr), "DAMAGE: 58",R.drawable.ic_slr);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.sks), "DAMAGE: 53",R.drawable.ic_sks);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.qbu), "DAMAGE: 48",R.drawable.ic_qbu);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mini14), "DAMAGE: 46",R.drawable.ic_mini14);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m249), "DAMAGE: 45",R.drawable.ic_m249);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.dp28), "DAMAGE: 51",R.drawable.ic_dp28);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.groza), "DAMAGE: 49",R.drawable.ic_groza);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.akm), "DAMAGE: 49",R.drawable.ic_akm);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mk47), "DAMAGE: 49",R.drawable.ic_mk47);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mm762), "DAMAGE: 47",R.drawable.ic_m762);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m416), "DAMAGE: 43",R.drawable.ic_m416);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setVisibility(View.INVISIBLE);
                recoil2.setText("-20% faster ads speed");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("Quickdraw Mag (AR, DMR, S12K)")){
                AttechableWeaponResponse
                        attechweapon= new AttechableWeaponResponse(getString(R.string.mk14), "DAMAGE: 61",R.drawable.ic_mk14);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.slr), "DAMAGE: 58",R.drawable.ic_slr);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.sks), "DAMAGE: 53",R.drawable.ic_sks);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.qbu), "DAMAGE: 48",R.drawable.ic_qbu);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mini14), "DAMAGE: 46",R.drawable.ic_mini14);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.groza), "DAMAGE: 49",R.drawable.ic_groza);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.akm), "DAMAGE: 49",R.drawable.ic_akm);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mk47), "DAMAGE: 49",R.drawable.ic_mk47);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mm762), "DAMAGE: 47",R.drawable.ic_m762);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m416), "DAMAGE: 43",R.drawable.ic_m416);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.scarl), "DAMAGE: 43",R.drawable.ic_scarl);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.auga3), "DAMAGE: 43",R.drawable.ic_aug);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.qbz95), "DAMAGE: 43",R.drawable.ic_qbz);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.s12k), "DAMAGE: 198",R.drawable.ic_s12k);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m16a4), "DAMAGE: 43",R.drawable.ic_m16a4);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setVisibility(View.INVISIBLE);
                recoil2.setText("-30% reload duration");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("Quickdraw Mag (DMR, SR)")){
                    AttechableWeaponResponse
                            attechweapon= new AttechableWeaponResponse(getString(R.string.awm), "DAMAGE: 132",R.drawable.ic_awm);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m24), "DAMAGE: 82",R.drawable.ic_m24);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mk14), "DAMAGE: 61",R.drawable.ic_mk14);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.slr), "DAMAGE: 58",R.drawable.ic_slr);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.sks), "DAMAGE: 53",R.drawable.ic_sks);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.qbu), "DAMAGE: 48",R.drawable.ic_qbu);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mini14), "DAMAGE: 46",R.drawable.ic_mini14);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.vss), "DAMAGE: 41",R.drawable.ic_vss);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setVisibility(View.INVISIBLE);
                recoil2.setText("-30% reload duration");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("Quickdraw Mag (Handgun)")){
                    AttechableWeaponResponse
                            attechweapon= new AttechableWeaponResponse(getString(R.string.p1911), "DAMAGE: 41",R.drawable.ic_p1911);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.p92), "DAMAGE: 35",R.drawable.ic_p92);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.p18c), "DAMAGE: 23",R.drawable.ic_p18c);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setVisibility(View.INVISIBLE);
                recoil2.setText("-30% reload duration");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("Quickdraw Mag (SMG)")){
                AttechableWeaponResponse attechweapon= new AttechableWeaponResponse(getString(R.string.s12k), "DAMAGE: 198",R.drawable.ic_s12k);
                attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.ump9), "DAMAGE: 39",R.drawable.ic_ump45);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.vector), "DAMAGE: 34",R.drawable.ic_vector);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.microuzi), "DAMAGE: 26",R.drawable.ic_uzi);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setVisibility(View.INVISIBLE);
                recoil2.setText("-30% reload duration");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("Quickdraw Mag (VSS)")){
                    AttechableWeaponResponse attechweapon= new AttechableWeaponResponse(getString(R.string.vss), "DAMAGE: 41",R.drawable.ic_vss);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setVisibility(View.INVISIBLE);
                recoil2.setText("-reload duration");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("Extended Quickdraw Mag (AR, DMR, S12K)")){
                AttechableWeaponResponse
                        attechweapon= new AttechableWeaponResponse(getString(R.string.mk14), "DAMAGE: 61",R.drawable.ic_mk14);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.slr), "DAMAGE: 58",R.drawable.ic_slr);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.sks), "DAMAGE: 53",R.drawable.ic_sks);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.qbu), "DAMAGE: 48",R.drawable.ic_qbu);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mini14), "DAMAGE: 46",R.drawable.ic_mini14);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.groza), "DAMAGE: 49",R.drawable.ic_groza);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.akm), "DAMAGE: 49",R.drawable.ic_akm);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mk47), "DAMAGE: 49",R.drawable.ic_mk47);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mm762), "DAMAGE: 47",R.drawable.ic_m762);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m416), "DAMAGE: 43",R.drawable.ic_m416);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.scarl), "DAMAGE: 43",R.drawable.ic_scarl);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.auga3), "DAMAGE: 43",R.drawable.ic_aug);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.qbz95), "DAMAGE: 43",R.drawable.ic_qbz);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m16a4), "DAMAGE: 43",R.drawable.ic_m16a4);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.s12k), "DAMAGE: 198",R.drawable.ic_s12k);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setText("-30% reload duration");
                recoil2.setText("+magazin capacity");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("Extended Quickdraw Mag (DMR, SR)")){
                AttechableWeaponResponse
                        attechweapon= new AttechableWeaponResponse(getString(R.string.awm), "DAMAGE: 132",R.drawable.ic_awm);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m24), "DAMAGE: 82",R.drawable.ic_m24);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mk14), "DAMAGE: 61",R.drawable.ic_mk14);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.slr), "DAMAGE: 58",R.drawable.ic_slr);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.sks), "DAMAGE: 53",R.drawable.ic_sks);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.qbu), "DAMAGE: 48",R.drawable.ic_qbu);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mini14), "DAMAGE: 46",R.drawable.ic_mini14);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.vss), "DAMAGE: 41",R.drawable.ic_vss);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setText("-30% reload duration");
                recoil2.setText("+magazin capacity");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("Extended Quickdraw Mag (Handgun)")){
                AttechableWeaponResponse attechweapon= new AttechableWeaponResponse(getString(R.string.p1911), "DAMAGE: 41",R.drawable.ic_p1911);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.p92), "DAMAGE: 35",R.drawable.ic_p92);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.p18c), "DAMAGE: 23",R.drawable.ic_p18c);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setText("-30% reload duration");
                recoil2.setText("+magazin capacity");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("Extended Quickdraw Mag (SMG)")){
                AttechableWeaponResponse attechweapon= new AttechableWeaponResponse(getString(R.string.tommy), "DAMAGE: 40",R.drawable.ic_thomson);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.vector), "DAMAGE: 34",R.drawable.ic_vector);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.ump9), "DAMAGE: 39",R.drawable.ic_ump45);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.microuzi), "DAMAGE: 26",R.drawable.ic_uzi);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setText("-30% reload duration");
                recoil2.setText("+magazin capacity");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("Extended Mag (SR, DMR, S12K)")){
                AttechableWeaponResponse
                        attechweapon= new AttechableWeaponResponse(getString(R.string.mk14), "DAMAGE: 61",R.drawable.ic_mk14);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.slr), "DAMAGE: 58",R.drawable.ic_slr);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.sks), "DAMAGE: 53",R.drawable.ic_sks);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.qbu), "DAMAGE: 48",R.drawable.ic_qbu);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mini14), "DAMAGE: 46",R.drawable.ic_mini14);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.groza), "DAMAGE: 49",R.drawable.ic_groza);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.akm), "DAMAGE: 49",R.drawable.ic_akm);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mk47), "DAMAGE: 49",R.drawable.ic_mk47);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mm762), "DAMAGE: 47",R.drawable.ic_m762);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m416), "DAMAGE: 43",R.drawable.ic_m416);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.scarl), "DAMAGE: 43",R.drawable.ic_scarl);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.auga3), "DAMAGE: 43",R.drawable.ic_aug);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.qbz95), "DAMAGE: 43",R.drawable.ic_qbz);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m16a4), "DAMAGE: 43",R.drawable.ic_m16a4);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.s12k), "DAMAGE: 198",R.drawable.ic_s12k);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setVisibility(View.INVISIBLE);
                recoil2.setText("+magazin capacity");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("Extended Mag (DMR, SR)")){
                AttechableWeaponResponse
                        attechweapon= new AttechableWeaponResponse(getString(R.string.awm), "DAMAGE: 132",R.drawable.ic_awm);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.m24), "DAMAGE: 82",R.drawable.ic_m24);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mk14), "DAMAGE: 61",R.drawable.ic_mk14);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.slr), "DAMAGE: 58",R.drawable.ic_slr);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.sks), "DAMAGE: 53",R.drawable.ic_sks);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.qbu), "DAMAGE: 48",R.drawable.ic_qbu);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.mini14), "DAMAGE: 46",R.drawable.ic_mini14);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.vss), "DAMAGE: 41",R.drawable.ic_vss);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setVisibility(View.INVISIBLE);
                recoil2.setText("+magazin capacity");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("Extended Mag (VSS)")){
                AttechableWeaponResponse attechweapon= new AttechableWeaponResponse(getString(R.string.vss), "DAMAGE: 41",R.drawable.ic_vss);
                attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setVisibility(View.INVISIBLE);
                recoil2.setText("+magazin capacity");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("Extended Mag (SMG)")){
                AttechableWeaponResponse attechweapon= new AttechableWeaponResponse(getString(R.string.tommy), "DAMAGE: 40",R.drawable.ic_thomson);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.vector), "DAMAGE: 34",R.drawable.ic_vector);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.ump9), "DAMAGE: 39",R.drawable.ic_ump45);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.microuzi), "DAMAGE: 26",R.drawable.ic_uzi);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setVisibility(View.INVISIBLE);
                recoil2.setText("+magazin capacity");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("Extended Mag (Handgun)")){
                AttechableWeaponResponse
                        attechweapon= new AttechableWeaponResponse(getString(R.string.p1911), "DAMAGE: 41",R.drawable.ic_p1911);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.p92), "DAMAGE: 35",R.drawable.ic_p92);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.p18c), "DAMAGE: 23",R.drawable.ic_p18c);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setVisibility(View.INVISIBLE);
                recoil2.setText("+magazin capacity");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("Bullet Loops for S1897, S686")){
                AttechableWeaponResponse attechweapon= new AttechableWeaponResponse(getString(R.string.s1897), "DAMAGE: 216",R.drawable.ic_s1897);
                attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.s686), "DAMAGE: 216",R.drawable.ic_s686);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setVisibility(View.INVISIBLE);
                recoil2.setText("-30% reload duration");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("Bullet Loops for Snipers")){
                AttechableWeaponResponse
                        attechweapon= new AttechableWeaponResponse(getString(R.string.kar98k), "DAMAGE: 72",R.drawable.ic_kar98);
                    attechableWeaponResponses.add(attechweapon);
                        attechweapon= new AttechableWeaponResponse(getString(R.string.win94), "DAMAGE: 66",R.drawable.ic_win94);
                attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setText("-30% reload duration");
                recoil2.setText("-50% spread base");
                recoil3.setVisibility(View.INVISIBLE);
            }
            else if(attechname.equalsIgnoreCase("Cheek Pad (DMR,SR)")){
                AttechableWeaponResponse
                        attechweapon= new AttechableWeaponResponse(getString(R.string.s1897), "DAMAGE: 216",R.drawable.ic_s1897);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.s686), "DAMAGE: 216",R.drawable.ic_s686);
                    attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.sawedoff), "DAMAGE: 160",R.drawable.ic_sawed);
                    attechableWeaponResponses.add(attechweapon);

                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setText("-20% verticle recoil");
                recoil2.setText("-20% animation kick");
                recoil3.setText("-15% sway");
            }
            else if(attechname.equalsIgnoreCase("Stock for Micro UZI")){
                AttechableWeaponResponse attechweapon= new AttechableWeaponResponse(getString(R.string.microuzi), "DAMAGE: 26",R.drawable.ic_uzi);
                attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setText("-20% recoil pettern");
                recoil2.setText("-20% verticle recoil");
                recoil3.setText("-15% recoil recovery");
            }
            else if(attechname.equalsIgnoreCase("Tactical Stock for M416, Vector")){
                AttechableWeaponResponse attechweapon= new AttechableWeaponResponse(getString(R.string.m416), "DAMAGE: 43",R.drawable.ic_m416);
                attechableWeaponResponses.add(attechweapon);
                    attechweapon= new AttechableWeaponResponse(getString(R.string.vector), "DAMAGE: 34",R.drawable.ic_vector);
                    attechableWeaponResponses.add(attechweapon);
                attechableWeaponAdapter.notifyDataSetChanged();
                detail.setText("Reduces horizontal and verticle recoil");
                recoil1.setText("-20% recoil pettern");
                recoil2.setText("-15% recoil recovery");
                recoil3.setText("-10%    animation kick");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}