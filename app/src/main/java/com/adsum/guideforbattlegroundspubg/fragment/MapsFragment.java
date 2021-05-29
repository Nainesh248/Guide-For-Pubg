package com.adsum.guideforbattlegroundspubg.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.adsum.guideforbattlegroundspubg.R;
import com.adsum.guideforbattlegroundspubg.SplashActivity;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Arrays;
import java.util.List;

public class MapsFragment extends Fragment {
    String google_banner, google_interitial, google_rewarded, google_native;
    String fb_banner, fb_interitial, fb_rewarded, fb_native;
    View rootView;
    Spinner spinner;
    ImageView imageView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.activity_maps_fragment, container, false);

        spinner = (Spinner) rootView.findViewById(R.id.spn_mapname);
        imageView = (ImageView) rootView.findViewById(R.id.imageView1);
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
        init();
        return rootView;

    }

    private void init() {
        try {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.Camp_Site_Facilities, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    if(arg2==0)
                    {

                        imageView.setImageResource(R.drawable.ic_sanhok1);
                    }
                    else if(arg2==1)
                    {
                        imageView.setImageResource(R.drawable.ic_erangle2);

                    } else if(arg2==2)
                    {
                        imageView.setImageResource(R.drawable.ic_miramar3);

                    }
                    else
                    {
                        imageView.setImageResource(R.drawable.ic_vikendi4);

                    }
                    ((TextView) arg0.getChildAt(0)).setTextColor(0xffffffff);
               /* ((ImageView) rootView.findViewById(R.id.imageView1)).setImageResource(0);
                ((ImageView) rootView.findViewById(R.id.imageView1)).setBackgroundColor(Color.BLACK);*/
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}