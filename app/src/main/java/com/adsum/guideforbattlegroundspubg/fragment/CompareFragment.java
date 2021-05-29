package com.adsum.guideforbattlegroundspubg.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.adsum.guideforbattlegroundspubg.R;
import com.adsum.guideforbattlegroundspubg.SplashActivity;
import com.adsum.guideforbattlegroundspubg.config.CommonFunctions;
import com.adsum.guideforbattlegroundspubg.config.Constants;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import java.util.Arrays;
import java.util.List;

public class CompareFragment extends Fragment {
    View rootView;
    String google_banner, google_interitial, google_rewarded, google_native;
    String fb_banner, fb_interitial, fb_rewarded, fb_native;
    Button watch;
    Spinner spinner1, spinner2;
    RelativeLayout rlwatch, rlmain;
    private RewardedAd rewardedAd;
    RewardedVideoAd fbrewardedVideoAd;
    private String status;
    String[] values =
            {"AWM", "M24", "Kar98k", "Win94", "MK14", "SLR", "SKS", "QBU", "Mini14", "VSS", "M249", "DP-28", "Groza", "AKM", "Mk47 Mutant", "Beryl M762", "M416", "SCAR-L", "Aug A3", "QBZ95", "M16A4", "Tommy Gun", "Ump9", "PP-19 Bizon", "Vector", "MP5K", "Micro uzi", "S12K", "S686", "S1897", "Sawed-off", "Cross-bow", "Skorpion", "P18C", "P92", "P1911", "R1895", "R45"};
    ImageView imgweapon1,imgweapon2,imgammo1,imgammo2,normalsound1,normalsound2,suppresersound1,suppresersound2;
    TextView type1,type2,damage1,damage2,bullet1,bullet2,time1,time2,normalreload1,normalreload2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.activity_compare_fragment, container, false);
        status = CommonFunctions.getPreference(getActivity(), Constants._true, "");
        watch = (Button) rootView.findViewById(R.id.btn_watch_video);
        rlwatch = (RelativeLayout) rootView.findViewById(R.id.rl_video);
        rlmain = (RelativeLayout) rootView.findViewById(R.id.rl_main);
        spinner1 = (Spinner) rootView.findViewById(R.id.spinner1);

        spinner2 = (Spinner) rootView.findViewById(R.id.spinner2);
        if (status.equalsIgnoreCase("true")) {
            rlmain.setVisibility(View.VISIBLE);
            rlwatch.setVisibility(View.GONE);
        } else {
            rlmain.setVisibility(View.GONE);
            rlwatch.setVisibility(View.VISIBLE);
        }

        imgweapon1 = (ImageView) rootView.findViewById(R.id.imgweapon1);
        imgweapon2 = (ImageView) rootView.findViewById(R.id.imgweapon2);


        imgammo1 = (ImageView) rootView.findViewById(R.id.imgammo1);
        imgammo2 = (ImageView) rootView.findViewById(R.id.imgammo2);

        type1 = (TextView) rootView.findViewById(R.id.type1);
        type2 = (TextView) rootView.findViewById(R.id.type2);

        damage1 = (TextView) rootView.findViewById(R.id.damage1);
        damage2 = (TextView) rootView.findViewById(R.id.damage2);

        bullet1 = (TextView) rootView.findViewById(R.id.bullet1);
        bullet2 = (TextView) rootView.findViewById(R.id.bullet2);


        time1 = (TextView) rootView.findViewById(R.id.time1);
        time2 = (TextView) rootView.findViewById(R.id.time2);

        normalreload1 = (TextView) rootView.findViewById(R.id.normalreload1);
        normalreload2 = (TextView) rootView.findViewById(R.id.normalreload2);

        normalsound1 = (ImageView) rootView.findViewById(R.id.normalsound1);
        normalsound2 = (ImageView) rootView.findViewById(R.id.normalsound2);


        suppresersound1 = (ImageView) rootView.findViewById(R.id.suppresersound1);
        suppresersound2 = (ImageView) rootView.findViewById(R.id.suppresersound2);

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

        google_banner = SplashActivity.appConfigModel.GOOGLE_BANNER_ID;
        google_interitial = SplashActivity.appConfigModel.GOOGLE_INTERITIAL_ID;
        google_rewarded = SplashActivity.appConfigModel.GOOGLE_REWARDED_ID;
        fb_banner = SplashActivity.appConfigModel.FACEBOOK_BANNER_ID;
        fb_interitial = SplashActivity.appConfigModel.FACEBOOK_INTERITIAL_ID;
        fb_rewarded = SplashActivity.appConfigModel.FACEBOOK_REWARDED_ID;
        fb_native = SplashActivity.appConfigModel.FACEBOOK_NATIVEAD_ID;
        google_native = SplashActivity.appConfigModel.GOOGLE_NATIVEAD_ID;
        if (SplashActivity.appConfigModel.IS_GOOGLE.equalsIgnoreCase(Constants.VAL_TRUE)) {

            load_RewardedVideoAd_admob();
        } else {
            loadRewardedVideoAd_fb();
        }
        init();
        return rootView;

    }

    private void init() {
        try {
            watch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (SplashActivity.appConfigModel.IS_GOOGLE.equalsIgnoreCase(Constants.VAL_TRUE)) {
                        googRewardedVideoAdleadshow();
                    } else {
                        facebookRewardedVideoAdadshow();
                    }
                }
            });
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            spinner1.setAdapter(adapter);
            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(position==0)
                    {
                       // AWM
                        imgweapon1.setImageResource(R.drawable.ic_awm);
                        imgammo1.setImageResource(R.drawable.ic_300_magnum);
                        type1.setText(" Sniper");
                        damage1.setText("132");
                        bullet1.setText("945 m/s");
                        time1.setText("1.85 s");
                        normalreload1.setText("4.2 second");

                    }
                    else if(position==1)
                    {
                       // M24
                        imgweapon1.setImageResource(R.drawable.ic_m24);
                        imgammo1.setImageResource(R.drawable.ic_7_62_mm);
                        type1.setText("Sniper");
                        damage1.setText("82");
                        bullet1.setText("790 m/s");
                        time1.setText("1.8s");
                        normalreload1.setText("4.3 second");

                    } else if(position==2)
                    {
                       // Kar98k
                        imgweapon1.setImageResource(R.drawable.ic_kar98);
                        imgammo1.setImageResource(R.drawable.ic_7_62_mm);
                        type1.setText("Sniper");
                        damage1.setText("72");
                        bullet1.setText("760 m/s");
                        time1.setText("1.9s");
                        normalreload1.setText("4 second");

                    }
                    else if(position==3)
                    {
                        // Win94
                        imgweapon1.setImageResource(R.drawable.ic_win94);
                        imgammo1.setImageResource(R.drawable.ic_45_acp);
                        type1.setText("Sniper");
                        damage1.setText("66");
                        bullet1.setText("760 m/s");
                        time1.setText("-");
                        normalreload1.setText("-");
                    }
                    else if(position==4)
                    {
                        // MK14
                        imgweapon1.setImageResource(R.drawable.ic_mk14);
                        imgammo1.setImageResource(R.drawable.ic_7_62_mm);
                        type1.setText("DMR");
                        damage1.setText("61");
                        bullet1.setText("853 m/s");
                        time1.setText("0.09s");
                        normalreload1.setText("3.683 second");
                    }
                    else if(position==5)
                    {
                        // SLR
                        imgweapon1.setImageResource(R.drawable.ic_slr);
                        imgammo1.setImageResource(R.drawable.ic_7_62_mm);
                        type1.setText("DMR");
                        damage1.setText("58");
                        bullet1.setText("840 m/s");
                        time1.setText("0.10s");
                        normalreload1.setText("3.683 second");
                    }
                    else if(position==6)
                    {
                        // SKS
                        imgweapon1.setImageResource(R.drawable.ic_sks);
                        imgammo1.setImageResource(R.drawable.ic_7_62_mm);
                        type1.setText("DMR");
                        damage1.setText("53");
                        bullet1.setText("800 m/s");
                        time1.setText("0.09s");
                        normalreload1.setText("4.550 second");
                    }
                    else if(position==7)
                    {
                        // QBU
                        imgweapon1.setImageResource(R.drawable.ic_qbu);
                        imgammo1.setImageResource(R.drawable.ic_5_56_mm);
                        type1.setText("DMR");
                        damage1.setText("48");
                        bullet1.setText("954 m/s");
                        time1.setText("0.09s");
                        normalreload1.setText("3 second");
                    }
                    else if(position==8)
                    {
                        // Mini14
                        imgweapon1.setImageResource(R.drawable.ic_mini14);
                        imgammo1.setImageResource(R.drawable.ic_5_56_mm);
                        type1.setText("DMR");
                        damage1.setText("46");
                        bullet1.setText("990 m/s");
                        time1.setText("0.133s");
                        normalreload1.setText("3.6 second");
                    }
                    else if(position==9)
                    {
                        // VSS
                        imgweapon1.setImageResource(R.drawable.ic_vss);
                        imgammo1.setImageResource(R.drawable.ic_9_mm);
                        type1.setText("DMR");
                        damage1.setText("41");
                        bullet1.setText("330 m/s");
                        time1.setText("0.086s");
                        normalreload1.setText("2.9 second");
                    }
                    else if(position==10)
                    {
                        // M249
                        imgweapon1.setImageResource(R.drawable.ic_m249);
                        imgammo1.setImageResource(R.drawable.ic_5_56_mm);
                        type1.setText("LMG");
                        damage1.setText("45");
                        bullet1.setText("915 m/s");
                        time1.setText("0.075s");
                        normalreload1.setText("7.1 second");
                    }
                    else if(position==11)
                    {
                        // DP-28
                        imgweapon1.setImageResource(R.drawable.ic_dp28);
                        imgammo1.setImageResource(R.drawable.ic_7_62_mm);
                        type1.setText("LMG");
                        damage1.setText("51");
                        bullet1.setText("715 m/s");
                        time1.setText("0.19s");
                        normalreload1.setText("4.5 second");
                    }
                    else if(position==12)
                    {
                        // Groza
                        imgweapon1.setImageResource(R.drawable.ic_groza);
                        imgammo1.setImageResource(R.drawable.ic_7_62_mm);
                        type1.setText("AR");
                        damage1.setText("49");
                        bullet1.setText("715 m/s");
                        time1.setText("0.080s");
                        normalreload1.setText("3 second");
                    }
                    else if(position==13)
                    {
                        // AKM
                        imgweapon1.setImageResource(R.drawable.ic_akm);
                        imgammo1.setImageResource(R.drawable.ic_7_62_mm);
                        type1.setText("AR");
                        damage1.setText("49");
                        bullet1.setText("715 m/s");
                        time1.setText("0.1s");
                        normalreload1.setText("2.9 second");
                    }
                    else if(position==14)
                    {
                        // Mk47 Mutant
                        imgweapon1.setImageResource(R.drawable.ic_mk47);
                        imgammo1.setImageResource(R.drawable.ic_7_62_mm);
                        type1.setText("AR");
                        damage1.setText("49");
                        bullet1.setText("780 m/s");
                        time1.setText("8.0867s");
                        normalreload1.setText("2.2 second");
                    }
                    else if(position==15)
                    {
                        // Beryl M762
                        imgweapon1.setImageResource(R.drawable.ic_m762);
                        imgammo1.setImageResource(R.drawable.ic_7_62_mm);
                        type1.setText("AR");
                        damage1.setText("47");
                        bullet1.setText("715 m/s");
                        time1.setText("0.0s");
                        normalreload1.setText("2.95 second");
                    }
                    else if(position==16)
                    {
                        // M416
                        imgweapon1.setImageResource(R.drawable.ic_m416);
                        imgammo1.setImageResource(R.drawable.ic_5_56_mm);
                        type1.setText("AR");
                        damage1.setText("43");
                        bullet1.setText("880 m/s");
                        time1.setText("0.867s");
                        normalreload1.setText("2.1 second");
                    }
                    else if(position==17)
                    {
                        // SCAR-L
                        imgweapon1.setImageResource(R.drawable.ic_scarl);
                        imgammo1.setImageResource(R.drawable.ic_5_56_mm);
                        type1.setText("AR");
                        damage1.setText("43");
                        bullet1.setText("870 m/s");
                        time1.setText("0.096s");
                        normalreload1.setText("2.2 second");
                    }
                    else if(position==18)
                    {
                        // Aug A3
                        imgweapon1.setImageResource(R.drawable.ic_aug);
                        imgammo1.setImageResource(R.drawable.ic_5_56_mm);
                        type1.setText("AR");
                        damage1.setText("43");
                        bullet1.setText("870 m/s");
                        time1.setText("0.0857s");
                        normalreload1.setText("3.66 second");
                    }
                    else if(position==19)
                    {
                        // QBZ95
                        imgweapon1.setImageResource(R.drawable.ic_qbz);
                        imgammo1.setImageResource(R.drawable.ic_5_56_mm);
                        type1.setText("AR");
                        damage1.setText("43");
                        bullet1.setText("870 m/s");
                        time1.setText("0.0867s");
                        normalreload1.setText("2.2 second");
                    }
                    else if(position==20)
                    {
                        // M16A4
                        imgweapon1.setImageResource(R.drawable.ic_m16a4);
                        imgammo1.setImageResource(R.drawable.ic_5_56_mm);
                        type1.setText("AR");
                        damage1.setText("43");
                        bullet1.setText("900 m/s");
                        time1.setText("0.075s");
                        normalreload1.setText("2.2 second");
                    }
                    else if(position==21)
                    {
                        // Tommy Gun
                        imgweapon1.setImageResource(R.drawable.ic_thomson);
                        imgammo1.setImageResource(R.drawable.ic_45_acp);
                        type1.setText("SMG");
                        damage1.setText("40");
                        bullet1.setText("280 m/s");
                        time1.setText("0.086s");
                        normalreload1.setText("3.45 second");
                    }

                    else if(position==22)
                    {
                        // Ump9
                        imgweapon1.setImageResource(R.drawable.ic_ump45);
                        imgammo1.setImageResource(R.drawable.ic_9_mm);
                        type1.setText("SMG");
                        damage1.setText("39");
                        bullet1.setText("400 m/s");
                        time1.setText("0.92s");
                        normalreload1.setText("3.1 second");
                    }
                    else if(position==23)
                    {
                        // PP-19 Bizon
                        imgweapon1.setImageResource(R.drawable.ic_bizon);
                        imgammo1.setImageResource(R.drawable.ic_9_mm);
                        type1.setText("SMG");
                        damage1.setText("35");
                        bullet1.setText("408 m/s");
                        time1.setText("-");
                        normalreload1.setText("2.5 second");
                    }
                    else if(position==24)
                    {
                        // Vector
                        imgweapon1.setImageResource(R.drawable.ic_vector);
                        imgammo1.setImageResource(R.drawable.ic_45_acp);
                        type1.setText("SMG");
                        damage1.setText("34");
                        bullet1.setText("300 m/s");
                        time1.setText("0.055s");
                        normalreload1.setText("2.2 second");
                    }
                    else if(position==25)
                    {
                        // MP5K
                        imgweapon1.setImageResource(R.drawable.ic_mp5k);
                        imgammo1.setImageResource(R.drawable.ic_9_mm);
                        type1.setText("SMG");
                        damage1.setText("33");
                        bullet1.setText("400 m/s");
                        time1.setText("0.066s");
                        normalreload1.setText("4.8 second");
                    }
                    else if(position==26)
                    {
                        // Micro uzi
                        imgweapon1.setImageResource(R.drawable.ic_uzi);
                        imgammo1.setImageResource(R.drawable.ic_9_mm);
                        type1.setText("SMG");
                        damage1.setText("26");
                        bullet1.setText("350 m/s");
                        time1.setText("0.48s");
                        normalreload1.setText("3.1 second");
                    }
                    else if(position==27)
                    {
                        // S12K
                        imgweapon1.setImageResource(R.drawable.ic_s12k);
                        imgammo1.setImageResource(R.drawable.ic_12gauge);
                        type1.setText("Shotgun");
                        damage1.setText("198");
                        bullet1.setText("350 m/s");
                        time1.setText("0.25s");
                        normalreload1.setText("3 second");
                    }
                    else if(position==28)
                    {
                        // S686
                        imgweapon1.setImageResource(R.drawable.ic_s686);
                        imgammo1.setImageResource(R.drawable.ic_12gauge);
                        type1.setText("Shotgun");
                        damage1.setText("216");
                        bullet1.setText("370 m/s");
                        time1.setText("0.2s");
                        normalreload1.setText("3 second");
                    }
                    else if(position==29)
                    {
                        // S1897
                        imgweapon1.setImageResource(R.drawable.ic_s1897);
                        imgammo1.setImageResource(R.drawable.ic_12gauge);
                        type1.setText("Shotgun");
                        damage1.setText("216");
                        bullet1.setText("360 m/s");
                        time1.setText("2.75s");
                        normalreload1.setText("4 second");
                    }
                    else if(position==30)
                    {
                        // Sawed-off
                        imgweapon1.setImageResource(R.drawable.ic_sawed);
                        imgammo1.setImageResource(R.drawable.ic_12gauge);
                        type1.setText("Shotgun");
                        damage1.setText("160");
                        bullet1.setText("330 m/s");
                        time1.setText("-");
                        normalreload1.setText("2 second");
                    }
                    else if(position==31)
                    {
                        // Cross bow
                        imgweapon1.setImageResource(R.drawable.ic_crossbow);
                        imgammo1.setImageResource(R.drawable.ic_quiver_crossbow);
                        type1.setText("Crossbow");
                        damage1.setText("105");
                        bullet1.setText("160 m/s");
                        time1.setText("0.75s");
                        normalreload1.setText("3.549 second");
                    }
                    else if(position==32)
                    {
                        // Skorpion
                        imgweapon1.setImageResource(R.drawable.ic_skorpion);
                        imgammo1.setImageResource(R.drawable.ic_9_mm);
                        type1.setText("Handgun");
                        damage1.setText("22");
                        bullet1.setText("350 m/s");
                        time1.setText("0.25s");
                        normalreload1.setText("3.1 second");
                    }
                    else if(position==33)
                    {
                        // P18C
                        imgweapon1.setImageResource(R.drawable.ic_p18c);
                        imgammo1.setImageResource(R.drawable.ic_9_mm);
                        type1.setText("Handgun");
                        damage1.setText("23");
                        bullet1.setText("375 m/s");
                        time1.setText("0.6s");
                        normalreload1.setText("2 second");
                    }
                    else if(position==34)
                    {
                        // P92
                        imgweapon1.setImageResource(R.drawable.ic_p92);
                        imgammo1.setImageResource(R.drawable.ic_9_mm);
                        type1.setText("Handgun");
                        damage1.setText("35");
                        bullet1.setText("380 m/s");
                        time1.setText("0.090s");
                        normalreload1.setText("2 second");
                    }
                    else if(position==35)
                    {
                        // P1911
                        imgweapon1.setImageResource(R.drawable.ic_p1911);
                        imgammo1.setImageResource(R.drawable.ic_45_acp);
                        type1.setText("Handgun");
                        damage1.setText("41");
                        bullet1.setText("250 m/s");
                        time1.setText("0.110s");
                        normalreload1.setText("2.1 second");
                    }
                    else if(position==36)
                    {
                        // R1895
                        imgweapon1.setImageResource(R.drawable.ic_r1895);
                        imgammo1.setImageResource(R.drawable.ic_7_62_mm);
                        type1.setText("Handgun");
                        damage1.setText("55");
                        bullet1.setText("330 m/s");
                        time1.setText("0.4s");
                        normalreload1.setText("6.25 second");
                    }
                    else if(position==37)
                    {
                        // R45
                        imgweapon1.setImageResource(R.drawable.ic_r45);
                        imgammo1.setImageResource(R.drawable.ic_45_acp);
                        type1.setText("Handgun");
                        damage1.setText("55");
                        bullet1.setText("330 m/s");
                        time1.setText("0.25s");
                        normalreload1.setText("3.2 second");
                    }

                    else
                    {

                    }
                    ((TextView) parent.getChildAt(0)).setTextColor(0xffffffff);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner2.setAdapter(adapter);
            spinner2.setSelection(1);
            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(position==0)
                    {
                        // AWM
                        imgweapon2.setImageResource(R.drawable.ic_awm);
                        imgammo2.setImageResource(R.drawable.ic_300_magnum);
                        type2.setText(" Sniper");
                        damage2.setText("132");
                        bullet2.setText("945 m/s");
                        time2.setText("1.85 s");
                        normalreload2.setText("4.2 second");

                    }
                    else if(position==1)
                    {
                        // M24
                        imgweapon2.setImageResource(R.drawable.ic_m24);
                        imgammo2.setImageResource(R.drawable.ic_7_62_mm);
                        type2.setText("Sniper");
                        damage2.setText("82");
                        bullet2.setText("790 m/s");
                        time2.setText("1.8s");
                        normalreload2.setText("4.3 second");

                    } else if(position==2)
                    {
                        // Kar98k
                        imgweapon2.setImageResource(R.drawable.ic_kar98);
                        imgammo2.setImageResource(R.drawable.ic_7_62_mm);
                        type2.setText("Sniper");
                        damage2.setText("72");
                        bullet2.setText("760 m/s");
                        time2.setText("1.9s");
                        normalreload2.setText("4 second");

                    }
                    else if(position==3)
                    {
                        // Win94
                        imgweapon2.setImageResource(R.drawable.ic_win94);
                        imgammo2.setImageResource(R.drawable.ic_45_acp);
                        type2.setText("Sniper");
                        damage2.setText("66");
                        bullet2.setText("760 m/s");
                        time2.setText("-");
                        normalreload2.setText("-");
                    }
                    else if(position==4)
                    {
                        // MK14
                        imgweapon2.setImageResource(R.drawable.ic_mk14);
                        imgammo2.setImageResource(R.drawable.ic_7_62_mm);
                        type2.setText("DMR");
                        damage2.setText("61");
                        bullet2.setText("853 m/s");
                        time2.setText("0.09s");
                        normalreload2.setText("3.683 second");
                    }
                    else if(position==5)
                    {
                        // SLR
                        imgweapon2.setImageResource(R.drawable.ic_slr);
                        imgammo2.setImageResource(R.drawable.ic_7_62_mm);
                        type2.setText("DMR");
                        damage2.setText("58");
                        bullet2.setText("840 m/s");
                        time2.setText("0.10s");
                        normalreload2.setText("3.683 second");
                    }
                    else if(position==6)
                    {
                        // SKS
                        imgweapon2.setImageResource(R.drawable.ic_sks);
                        imgammo2.setImageResource(R.drawable.ic_7_62_mm);
                        type2.setText("DMR");
                        damage2.setText("53");
                        bullet2.setText("800 m/s");
                        time2.setText("0.09s");
                        normalreload2.setText("4.550 second");
                    }
                    else if(position==7)
                    {
                        // QBU
                        imgweapon2.setImageResource(R.drawable.ic_qbu);
                        imgammo2.setImageResource(R.drawable.ic_5_56_mm);
                        type2.setText("DMR");
                        damage2.setText("48");
                        bullet2.setText("954 m/s");
                        time2.setText("0.09s");
                        normalreload2.setText("3 second");
                    }
                    else if(position==8)
                    {
                        // Mini14
                        imgweapon2.setImageResource(R.drawable.ic_mini14);
                        imgammo2.setImageResource(R.drawable.ic_5_56_mm);
                        type2.setText("DMR");
                        damage2.setText("46");
                        bullet2.setText("990 m/s");
                        time2.setText("0.133s");
                        normalreload2.setText("3.6 second");
                    }
                    else if(position==9)
                    {
                        // VSS
                        imgweapon2.setImageResource(R.drawable.ic_vss);
                        imgammo2.setImageResource(R.drawable.ic_9_mm);
                        type2.setText("DMR");
                        damage2.setText("41");
                        bullet2.setText("330 m/s");
                        time2.setText("0.086s");
                        normalreload2.setText("2.9 second");
                    }
                    else if(position==10)
                    {
                        // M249
                        imgweapon2.setImageResource(R.drawable.ic_m249);
                        imgammo2.setImageResource(R.drawable.ic_5_56_mm);
                        type2.setText("LMG");
                        damage2.setText("45");
                        bullet2.setText("915 m/s");
                        time2.setText("0.075s");
                        normalreload2.setText("7.1 second");
                    }
                    else if(position==11)
                    {
                        // DP-28
                        imgweapon2.setImageResource(R.drawable.ic_dp28);
                        imgammo2.setImageResource(R.drawable.ic_7_62_mm);
                        type2.setText("LMG");
                        damage2.setText("51");
                        bullet2.setText("715 m/s");
                        time2.setText("0.19s");
                        normalreload2.setText("4.5 second");
                    }
                    else if(position==12)
                    {
                        // Groza
                        imgweapon2.setImageResource(R.drawable.ic_groza);
                        imgammo2.setImageResource(R.drawable.ic_7_62_mm);
                        type2.setText("AR");
                        damage2.setText("49");
                        bullet2.setText("715 m/s");
                        time2.setText("0.080s");
                        normalreload2.setText("3 second");
                    }
                    else if(position==13)
                    {
                        // AKM
                        imgweapon2.setImageResource(R.drawable.ic_akm);
                        imgammo2.setImageResource(R.drawable.ic_7_62_mm);
                        type2.setText("AR");
                        damage2.setText("49");
                        bullet2.setText("715 m/s");
                        time2.setText("0.1s");
                        normalreload2.setText("2.9 second");
                    }
                    else if(position==14)
                    {
                        // Mk47 Mutant
                        imgweapon2.setImageResource(R.drawable.ic_mk47);
                        imgammo2.setImageResource(R.drawable.ic_7_62_mm);
                        type2.setText("AR");
                        damage2.setText("49");
                        bullet2.setText("780 m/s");
                        time2.setText("8.0867s");
                        normalreload2.setText("2.2 second");
                    }
                    else if(position==15)
                    {
                        // Beryl M762
                        imgweapon2.setImageResource(R.drawable.ic_m762);
                        imgammo2.setImageResource(R.drawable.ic_7_62_mm);
                        type2.setText("AR");
                        damage2.setText("47");
                        bullet2.setText("715 m/s");
                        time2.setText("0.0s");
                        normalreload2.setText("2.95 second");
                    }
                    else if(position==16)
                    {
                        // M416
                        imgweapon2.setImageResource(R.drawable.ic_m416);
                        imgammo2.setImageResource(R.drawable.ic_5_56_mm);
                        type2.setText("AR");
                        damage2.setText("43");
                        bullet2.setText("880 m/s");
                        time2.setText("0.867s");
                        normalreload2.setText("2.1 second");
                    }
                    else if(position==17)
                    {
                        // SCAR-L
                        imgweapon2.setImageResource(R.drawable.ic_scarl);
                        imgammo2.setImageResource(R.drawable.ic_5_56_mm);
                        type2.setText("AR");
                        damage2.setText("43");
                        bullet2.setText("870 m/s");
                        time2.setText("0.096s");
                        normalreload2.setText("2.2 second");
                    }
                    else if(position==18)
                    {
                        // Aug A3
                        imgweapon2.setImageResource(R.drawable.ic_aug);
                        imgammo2.setImageResource(R.drawable.ic_5_56_mm);
                        type2.setText("AR");
                        damage2.setText("43");
                        bullet2.setText("870 m/s");
                        time2.setText("0.0857s");
                        normalreload2.setText("3.66 second");
                    }
                    else if(position==19)
                    {
                        // QBZ95
                        imgweapon2.setImageResource(R.drawable.ic_qbz);
                        imgammo2.setImageResource(R.drawable.ic_5_56_mm);
                        type2.setText("AR");
                        damage2.setText("43");
                        bullet2.setText("870 m/s");
                        time2.setText("0.0867s");
                        normalreload2.setText("2.2 second");
                    }
                    else if(position==20)
                    {
                        // M16A4
                        imgweapon2.setImageResource(R.drawable.ic_m16a4);
                        imgammo2.setImageResource(R.drawable.ic_5_56_mm);
                        type2.setText("AR");
                        damage2.setText("43");
                        bullet2.setText("900 m/s");
                        time2.setText("0.075s");
                        normalreload2.setText("2.2 second");
                    }
                    else if(position==21)
                    {
                        // Tommy Gun
                        imgweapon2.setImageResource(R.drawable.ic_thomson);
                        imgammo2.setImageResource(R.drawable.ic_45_acp);
                        type2.setText("SMG");
                        damage2.setText("40");
                        bullet2.setText("2280 m/s");
                        time2.setText("0.086s");
                        normalreload2.setText("3.45 second");
                    }

                    else if(position==22)
                    {
                        // Ump9
                        imgweapon2.setImageResource(R.drawable.ic_ump45);
                        imgammo2.setImageResource(R.drawable.ic_9_mm);
                        type2.setText("SMG");
                        damage2.setText("39");
                        bullet2.setText("400 m/s");
                        time2.setText("0.92s");
                        normalreload2.setText("3.1 second");
                    }
                    else if(position==23)
                    {
                        // PP-19 Bizon
                        imgweapon2.setImageResource(R.drawable.ic_bizon);
                        imgammo2.setImageResource(R.drawable.ic_9_mm);
                        type2.setText("SMG");
                        damage2.setText("35");
                        bullet2.setText("408 m/s");
                        time2.setText("-");
                        normalreload2.setText("2.5 second");
                    }
                    else if(position==24)
                    {
                        // Vector
                        imgweapon2.setImageResource(R.drawable.ic_vector);
                        imgammo2.setImageResource(R.drawable.ic_45_acp);
                        type2.setText("SMG");
                        damage2.setText("34");
                        bullet2.setText("300 m/s");
                        time2.setText("0.055s");
                        normalreload2.setText("2.2 second");
                    }
                    else if(position==25)
                    {
                        // MP5K
                        imgweapon2.setImageResource(R.drawable.ic_mp5k);
                        imgammo2.setImageResource(R.drawable.ic_9_mm);
                        type2.setText("SMG");
                        damage2.setText("33");
                        bullet2.setText("400 m/s");
                        time2.setText("0.066s");
                        normalreload2.setText("4.8 second");
                    }
                    else if(position==26)
                    {
                        // Micro uzi
                        imgweapon2.setImageResource(R.drawable.ic_uzi);
                        imgammo2.setImageResource(R.drawable.ic_9_mm);
                        type2.setText("SMG");
                        damage2.setText("26");
                        bullet2.setText("350 m/s");
                        time2.setText("0.48s");
                        normalreload2.setText("3.1 second");
                    }
                    else if(position==27)
                    {
                        // S12K
                        imgweapon2.setImageResource(R.drawable.ic_s12k);
                        imgammo2.setImageResource(R.drawable.ic_12gauge);
                        type2.setText("Shotgun");
                        damage2.setText("198");
                        bullet2.setText("350 m/s");
                        time2.setText("0.25s");
                        normalreload2.setText("3 second");
                    }
                    else if(position==28)
                    {
                        // S686
                        imgweapon2.setImageResource(R.drawable.ic_s686);
                        imgammo2.setImageResource(R.drawable.ic_12gauge);
                        type2.setText("Shotgun");
                        damage2.setText("216");
                        bullet2.setText("370 m/s");
                        time2.setText("0.2s");
                        normalreload2.setText("3 second");
                    }
                    else if(position==29)
                    {
                        // S1897
                        imgweapon2.setImageResource(R.drawable.ic_s1897);
                        imgammo2.setImageResource(R.drawable.ic_12gauge);
                        type2.setText("Shotgun");
                        damage2.setText("216");
                        bullet2.setText("360 m/s");
                        time2.setText("2.75s");
                        normalreload2.setText("4 second");
                    }
                    else if(position==30)
                    {
                        // Sawed-off
                        imgweapon2.setImageResource(R.drawable.ic_sawed);
                        imgammo2.setImageResource(R.drawable.ic_12gauge);
                        type2.setText("Shotgun");
                        damage2.setText("160");
                        bullet2.setText("330 m/s");
                        time2.setText("-");
                        normalreload2.setText("2 second");
                    }
                    else if(position==31)
                    {
                        // Cross bow
                        imgweapon2.setImageResource(R.drawable.ic_crossbow);
                        imgammo2.setImageResource(R.drawable.ic_quiver_crossbow);
                        type2.setText("Crossbow");
                        damage2.setText("105");
                        bullet2.setText("160 m/s");
                        time2.setText("0.75s");
                        normalreload2.setText("3.549 second");
                    }
                    else if(position==32)
                    {
                        // Skorpion
                        imgweapon2.setImageResource(R.drawable.ic_skorpion);
                        imgammo2.setImageResource(R.drawable.ic_9_mm);
                        type2.setText("Handgun");
                        damage2.setText("22");
                        bullet2.setText("350 m/s");
                        time2.setText("0.25s");
                        normalreload2.setText("3.1 second");
                    }
                    else if(position==33)
                    {
                        // P18C
                        imgweapon2.setImageResource(R.drawable.ic_p18c);
                        imgammo2.setImageResource(R.drawable.ic_9_mm);
                        type2.setText("Handgun");
                        damage2.setText("23");
                        bullet2.setText("375 m/s");
                        time2.setText("0.6s");
                        normalreload2.setText("2 second");
                    }
                    else if(position==34)
                    {
                        // P92
                        imgweapon2.setImageResource(R.drawable.ic_p92);
                        imgammo2.setImageResource(R.drawable.ic_9_mm);
                        type2.setText("Handgun");
                        damage2.setText("35");
                        bullet2.setText("380 m/s");
                        time2.setText("0.090s");
                        normalreload2.setText("2 second");
                    }
                    else if(position==35)
                    {
                        // P1911
                        imgweapon2.setImageResource(R.drawable.ic_p1911);
                        imgammo2.setImageResource(R.drawable.ic_45_acp);
                        type2.setText("Handgun");
                        damage2.setText("41");
                        bullet2.setText("250 m/s");
                        time2.setText("0.110s");
                        normalreload2.setText("2.1 second");
                    }
                    else if(position==36)
                    {
                        // R1895
                        imgweapon2.setImageResource(R.drawable.ic_r1895);
                        imgammo2.setImageResource(R.drawable.ic_7_62_mm);
                        type2.setText("Handgun");
                        damage2.setText("55");
                        bullet2.setText("330 m/s");
                        time2.setText("0.4s");
                        normalreload2.setText("6.25 second");
                    }
                    else if(position==37)
                    {
                        // R45
                        imgweapon2.setImageResource(R.drawable.ic_r45);
                        imgammo2.setImageResource(R.drawable.ic_45_acp);
                        type2.setText("Handgun");
                        damage2.setText("55");
                        bullet2.setText("330 m/s");
                        time2.setText("0.25s");
                        normalreload2.setText("3.2 second");
                    }

                    else
                    {

                    }

                    ((TextView) parent.getChildAt(0)).setTextColor(0xffffffff);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void load_RewardedVideoAd_admob() {
        rewardedAd = new RewardedAd(getActivity(),
                google_rewarded);
        CommonFunctions.createProgressBar(getActivity(), getString(R.string.msg_please_wait));
        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                // Ad successfully loaded.
                if (rewardedAd.isLoaded()) {
                    CommonFunctions.destroyProgressBar();
                }
            }

            @Override
            public void onRewardedAdFailedToLoad(LoadAdError adError) {
                // Ad failed to load.
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
    }

    public void googRewardedVideoAdleadshow() {
        RewardedAdCallback adCallback = new RewardedAdCallback() {
            @Override
            public void onRewardedAdOpened() {
                // Ad opened.
            }

            @Override
            public void onRewardedAdClosed() {
                CommonFunctions.setPreference(getActivity(), Constants._true, "true");
                CommonFunctions.destroyProgressBar();
                rlmain.setVisibility(View.VISIBLE);
                rlwatch.setVisibility(View.GONE);


                // Ad closed.

            }

            @Override
            public void onUserEarnedReward(@NonNull com.google.android.gms.ads.rewarded.RewardItem rewardItem) {

            }
        };
        rewardedAd.show(getActivity(), adCallback);
        load_RewardedVideoAd_admob();
    }

    private void loadRewardedVideoAd_fb() {
        fbrewardedVideoAd = new RewardedVideoAd(getActivity(), fb_rewarded);
        com.facebook.ads.RewardedVideoAdListener rewardedVideoAdListener = new RewardedVideoAdListener() {
            @Override
            public void onError(Ad ad, AdError error) {
                Log.e("TAG", "Rewarded video ad failed to load: " + error.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                Log.d("TAG", "Rewarded video ad is loaded and ready to be displayed!");

            }

            @Override
            public void onAdClicked(Ad ad) {
                Log.d("TAG", "Rewarded video ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                Log.d("TAG", "Rewarded video ad impression logged!");
            }

            @Override
            public void onRewardedVideoCompleted() {
                Log.d("TAG", "Rewarded video completed!");
            }

            @Override
            public void onRewardedVideoClosed() {
                Log.d("TAG", "Rewarded video ad closed!");
                CommonFunctions.setPreference(getActivity(), Constants._true, "true");
                rlmain.setVisibility(View.VISIBLE);
                rlwatch.setVisibility(View.GONE);
            }
        };
        fbrewardedVideoAd.loadAd(
                fbrewardedVideoAd.buildLoadAdConfig()
                        .withAdListener(rewardedVideoAdListener)
                        .build());
    }

    public void facebookRewardedVideoAdadshow() {
        fbrewardedVideoAd.show();
        loadRewardedVideoAd_fb();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}