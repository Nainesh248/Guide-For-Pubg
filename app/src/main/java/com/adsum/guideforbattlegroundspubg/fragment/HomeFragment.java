package com.adsum.guideforbattlegroundspubg.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.adsum.guideforbattlegroundspubg.R;
import com.adsum.guideforbattlegroundspubg.SplashActivity;
import com.adsum.guideforbattlegroundspubg.activity.DashBoard;
import com.adsum.guideforbattlegroundspubg.config.CommonFunctions;
import com.adsum.guideforbattlegroundspubg.config.Constants;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {
    String google_banner, google_interitial, google_rewarded, google_native;
    String fb_banner, fb_interitial, fb_rewarded, fb_native;
    View rootView;
    CardView cv_weapon,cv_attechment,cv_ammos,cv_equipment,cv_vehicle,cv_consumables;
    TextView txt_weapon,txt_attechment,txt_ammos,txt_equipment,txt_vehicle,txt_consumables;

    TemplateView templateView;
    // creating NativeAdLayout object
    private NativeAdLayout nativeAdLayout;
    // creating  LinearLayout object
    private LinearLayout adView;
    // creating  NativeAd object
    private NativeAd nativeAd;
    boolean showAdds = true;

    int i;
    private InterstitialAd interstitialAd;
    com.facebook.ads.InterstitialAd fbinterstitialAd;
    boolean weapon=false;
    boolean attechment=false;
    boolean ammos=false;
    boolean equipment=false;
    boolean vehicle=false;
    boolean consumables=false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.activity_home_fragment, container, false);
        cv_weapon = (CardView) rootView.findViewById(R.id.cv_weapon);
        cv_attechment = (CardView) rootView.findViewById(R.id.cv_attechment);
        cv_ammos = (CardView) rootView.findViewById(R.id.cv_ammos);
        cv_equipment = (CardView) rootView.findViewById(R.id.cv_equipment);
        cv_vehicle = (CardView) rootView.findViewById(R.id.cv_vehicle);
        cv_consumables = (CardView) rootView.findViewById(R.id.cv_consumables);

        txt_weapon = (TextView) rootView.findViewById(R.id.txt_weapon);
        txt_attechment = (TextView) rootView.findViewById(R.id.txt_attechment);
        txt_ammos = (TextView) rootView.findViewById(R.id.txt_ammos);
        txt_equipment = (TextView) rootView.findViewById(R.id.txt_equipment);
        txt_vehicle = (TextView) rootView.findViewById(R.id.txt_vehicle);
        txt_consumables = (TextView) rootView.findViewById(R.id.txt_consumables);
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
        i = 0;
        nativeAdLayout = rootView.findViewById(R.id.native_ad_container);
        templateView = rootView.findViewById(R.id.nativeTemplateView);

        if (SplashActivity.appConfigModel.IS_GOOGLE.equalsIgnoreCase(Constants.VAL_TRUE)) {
            load_nativeAd_admob();
        } else {
            load_nativeAd_fb();
        }
        if (SplashActivity.appConfigModel.IS_GOOGLE.equalsIgnoreCase(Constants.VAL_TRUE)) {
            setInterstitialAd();
        } else {
            load_InterstitialAd_fb();
        }
        init();
        return rootView;

    }

    private void init() {
        try {
            cv_weapon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v == cv_weapon) {
                        i++;
                        if (i == 4)
                        {
                            weapon = true;
                            if (SplashActivity.appConfigModel.IS_GOOGLE.equalsIgnoreCase(Constants.VAL_TRUE)) {
                                googleinterstialadshow();
                            } else {
                                fbinterstialadshow();
                            }

                            i=0;
                        } else {
                            WeaponFragment weaponFragment = new WeaponFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.catgegoryname,txt_weapon.toString());
                            weaponFragment.setArguments(bundle);
                            ((DashBoard)getActivity()).addFragment(weaponFragment);
                        }

                    }

                }
            });
            cv_attechment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v == cv_attechment) {
                        i++;
                        if (i == 4)
                        {
                            attechment = true;
                            if (SplashActivity.appConfigModel.IS_GOOGLE.equalsIgnoreCase(Constants.VAL_TRUE)) {
                                googleinterstialadshow();
                            } else {
                                fbinterstialadshow();
                            }

                            i=0;
                        } else {
                            AttechmentFragment attechmentFragment = new AttechmentFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.catgegoryname,txt_attechment.toString());
                            attechmentFragment.setArguments(bundle);
                            ((DashBoard)getActivity()).addFragment(attechmentFragment);
                        }

                    }

                }
            });
            cv_ammos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v == cv_ammos) {
                        i++;
                        if (i == 4)
                        {
                            ammos = true;
                            if (SplashActivity.appConfigModel.IS_GOOGLE.equalsIgnoreCase(Constants.VAL_TRUE)) {
                                googleinterstialadshow();
                            } else {
                                fbinterstialadshow();
                            }

                            i=0;
                        } else {
                            AmmosFragment ammosFragment = new AmmosFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.catgegoryname,txt_ammos.toString());
                            ammosFragment.setArguments(bundle);
                            ((DashBoard)getActivity()).addFragment(ammosFragment);
                        }

                    }

                }
            });
            cv_equipment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v == cv_equipment) {
                        i++;
                        if (i == 4)
                        {
                            equipment = true;
                            if (SplashActivity.appConfigModel.IS_GOOGLE.equalsIgnoreCase(Constants.VAL_TRUE)) {
                                googleinterstialadshow();
                            } else {
                                fbinterstialadshow();
                            }

                            i=0;
                        } else {
                            EquipmentFragment equipmentFragment = new EquipmentFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.catgegoryname,txt_equipment.toString());
                            equipmentFragment.setArguments(bundle);
                            ((DashBoard)getActivity()).addFragment(equipmentFragment);
                        }

                    }

                }
            });
            cv_vehicle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v == cv_vehicle) {
                        i++;
                        if (i == 4)
                        {
                            vehicle = true;
                            if (SplashActivity.appConfigModel.IS_GOOGLE.equalsIgnoreCase(Constants.VAL_TRUE)) {
                                googleinterstialadshow();
                            } else {
                                fbinterstialadshow();
                            }

                            i=0;
                        } else {
                            VehicleFragment vehicleFragment = new VehicleFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.catgegoryname,txt_vehicle.toString());
                            vehicleFragment.setArguments(bundle);
                            ((DashBoard)getActivity()).addFragment(vehicleFragment);
                        }

                    }

                }
            });
            cv_consumables.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v == cv_consumables) {
                        i++;
                        if (i == 4)
                        {
                            consumables = true;
                            if (SplashActivity.appConfigModel.IS_GOOGLE.equalsIgnoreCase(Constants.VAL_TRUE)) {
                                googleinterstialadshow();
                            } else {
                                fbinterstialadshow();
                            }

                            i=0;
                        } else {
                            ConsumablesFragment consumablesFragment = new ConsumablesFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString(Constants.catgegoryname,txt_consumables.toString());
                            consumablesFragment.setArguments(bundle);
                            ((DashBoard)getActivity()).addFragment(consumablesFragment);
                        }

                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void load_InterstitialAd_fb() {
        fbinterstitialAd = new com.facebook.ads.InterstitialAd(getActivity(), fb_interitial);
        InterstitialAdListener interstitialAdListener=new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {

            }

            @Override
            public void onInterstitialDismissed(Ad ad) {

            }

            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {

            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        };
    }
    public void setInterstitialAd(){
        interstitialAd = new InterstitialAd(getActivity());

        // set the ad unit ID
        interstitialAd.setAdUnitId(google_interitial);

        AdRequest adRequest = new AdRequest.Builder()
                .build();

        // Load ads into Interstitial Ads
        interstitialAd.loadAd(adRequest);

        interstitialAd.setAdListener(new com.google.android.gms.ads.AdListener() {
            public void onAdLoaded() {
                if (interstitialAd.isLoaded()) {

                }

            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                if(weapon){
                    WeaponFragment weaponFragment = new WeaponFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString(Constants.catgegoryname,txt_weapon.toString());
                    weaponFragment.setArguments(bundle);
                    ((DashBoard)getActivity()).addFragment(weaponFragment);
                }
                else if(attechment){
                    AttechmentFragment attechmentFragment = new AttechmentFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString(Constants.catgegoryname,txt_attechment.toString());
                    attechmentFragment.setArguments(bundle);
                    ((DashBoard)getActivity()).addFragment(attechmentFragment);
                }
                else if(ammos){
                    AmmosFragment ammosFragment = new AmmosFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString(Constants.catgegoryname,txt_ammos.toString());
                    ammosFragment.setArguments(bundle);
                    ((DashBoard)getActivity()).addFragment(ammosFragment);
                }
                else if(equipment){
                    EquipmentFragment equipmentFragment = new EquipmentFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString(Constants.catgegoryname,txt_equipment.toString());
                    equipmentFragment.setArguments(bundle);
                    ((DashBoard)getActivity()).addFragment(equipmentFragment);
                }
                else if(vehicle){
                    VehicleFragment vehicleFragment = new VehicleFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString(Constants.catgegoryname,txt_vehicle.toString());
                    vehicleFragment.setArguments(bundle);
                    ((DashBoard)getActivity()).addFragment(vehicleFragment);
                }else if(consumables){
                    ConsumablesFragment consumablesFragment = new ConsumablesFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString(Constants.catgegoryname,txt_consumables.toString());
                    consumablesFragment.setArguments(bundle);
                    ((DashBoard)getActivity()).addFragment(consumablesFragment);
                }

                 weapon=false;
                 attechment=false;
                 ammos=false;
                 equipment=false;
                 vehicle=false;
                 consumables=false;
            }
        });
    }

    public void googleinterstialadshow(){
        interstitialAd.show();
        setInterstitialAd();

    }
    public void fbinterstialadshow(){
        fbinterstitialAd.show();
        load_InterstitialAd_fb();

    }
    private void load_nativeAd_admob() {
        try {
            AdLoader.Builder builder = new AdLoader.Builder(getActivity(),google_native);
            builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                @Override
                public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                    nativeAdLayout.setVisibility(View.GONE);
                    templateView.setVisibility(View.VISIBLE);
                    templateView.setNativeAd(unifiedNativeAd);
//                        Toast.makeText(HomePage.this, "LoadAd Successfully", Toast.LENGTH_LONG).show();

                }
            }).withAdListener(new com.google.android.gms.ads.AdListener() {
                @Override
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    nativeAdLayout.setVisibility(View.GONE);
                    templateView.setVisibility(View.GONE);
//                        Toast.makeText(HomePage.this, "Error", Toast.LENGTH_LONG).show();
                    Log.e("TAG", "ERROR" + loadAdError.toString());
                    super.onAdFailedToLoad(loadAdError);
                }
            })
                    .build();
            AdLoader adLoader = builder.build();
            if(this.showAdds) {
                adLoader.loadAd(new AdRequest.Builder().build());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void load_nativeAd_fb() {
        try {
            // initializing nativeAd object
            nativeAd = new NativeAd(getActivity(), fb_native);

            // creating  NativeAdListener
            NativeAdListener nativeAdListener = new NativeAdListener() {

                @Override
                public void onMediaDownloaded(Ad ad) {
                    // showing Toast message

                }

                @Override
                public void onError(Ad ad, AdError adError) {
                    // showing Toast message

                }

                @Override
                public void onAdLoaded(Ad ad) {

                    // showing Toast message


                    if (nativeAd == null || nativeAd != ad) {
                        return;
                    }
                    nativeAdLayout.setVisibility(View.VISIBLE);
                    // Inflate Native Ad into Container
                    inflateAd(nativeAd);
                }

                @Override
                public void onAdClicked(Ad ad) {
                    // showing Toast message

                }

                @Override
                public void onLoggingImpression(Ad ad) {
                    // showing Toast message

                }
            };
            if(this.showAdds) {
                nativeAd.loadAd(
                        nativeAd.buildLoadAdConfig()
                                .withAdListener(nativeAdListener)
                                .build());
            }
            // Load an ad

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void inflateAd(NativeAd nativeAd) {
        // unregister the native Ad View
        nativeAd.unregisterView();

        // Add the Ad view into the ad container.
        nativeAdLayout = rootView.findViewById(R.id.native_ad_container);

        LayoutInflater inflater = LayoutInflater.from(getActivity());

        // Inflate the Ad view.
        adView = (LinearLayout) inflater.inflate(R.layout.ad_unified, nativeAdLayout, false);

        // adding view
        nativeAdLayout.addView(adView);

        // Add the AdOptionsView
        LinearLayout adChoicesContainer = rootView.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(getActivity(), nativeAd, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.
        MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

        // Setting  the Text.
        nativeAdTitle.setText(nativeAd.getAdvertiserName());
        nativeAdBody.setText(nativeAd.getAdBodyText());
        nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
        nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
        sponsoredLabel.setText(nativeAd.getSponsoredTranslation());

        // Create a list of clickable views
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);

        // Register the Title and  button to listen for clicks.
        nativeAd.registerViewForInteraction(adView, nativeAdMedia, nativeAdIcon, clickableViews);
    }
}