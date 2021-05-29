package com.adsum.guideforbattlegroundspubg.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adsum.guideforbattlegroundspubg.R;
import com.adsum.guideforbattlegroundspubg.SplashActivity;
import com.adsum.guideforbattlegroundspubg.activity.DashBoard;
import com.adsum.guideforbattlegroundspubg.adapter.ConsumablesAdapter;
import com.adsum.guideforbattlegroundspubg.adapter.EquipmentAdapter;
import com.adsum.guideforbattlegroundspubg.config.Constants;
import com.adsum.guideforbattlegroundspubg.model.ConsumablesResponse;
import com.adsum.guideforbattlegroundspubg.model.EquipmentResponse;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsumablesFragment extends Fragment {
    String google_banner, google_interitial, google_rewarded, google_native;
    String fb_banner, fb_interitial, fb_rewarded, fb_native;
    View rootView;

    private RecyclerView consumablesrecycler;
    private ConsumablesAdapter consumablesAdapter;
    private List<ConsumablesResponse> consumablesResponses = new ArrayList<>();

    TemplateView templateView;
    // creating NativeAdLayout object
    private NativeAdLayout nativeAdLayout;
    // creating  LinearLayout object
    private LinearLayout adView;
    // creating  NativeAd object
    private NativeAd nativeAd;
    boolean showAdds = true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.activity_consumables_fragment, container, false);

        consumablesrecycler = (RecyclerView) rootView.findViewById(R.id.recycler_view_consumables);
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

        nativeAdLayout = rootView.findViewById(R.id.native_ad_container);
        templateView = rootView.findViewById(R.id.nativeTemplateView);

        if (SplashActivity.appConfigModel.IS_GOOGLE.equalsIgnoreCase(Constants.VAL_TRUE)) {
            load_nativeAd_admob();
        } else {
            load_nativeAd_fb();
        }

        init();
        return rootView;

    }
    private void init() {
        try {
            consumablesdata();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    private void consumablesdata() {
        try {
            consumablesAdapter = new ConsumablesAdapter(consumablesResponses, new ConsumablesAdapter.AdapterCallback() {
                @Override
                public void subcategoryclick(List<ConsumablesResponse> consumablesList, int position) {

                }
            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            consumablesrecycler.setLayoutManager(mLayoutManager);
            consumablesrecycler.setItemAnimator(new DefaultItemAnimator());
            consumablesrecycler.setAdapter(consumablesAdapter);
            prepareconsumablesData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void prepareconsumablesData() {
        try {
            ConsumablesResponse consumables= new ConsumablesResponse(getString(R.string.energy), "Increase boost by 40 Instantly","Time taken : 4 second" ,"Capacity : 4",R.drawable.ic_energy_drink);
            consumablesResponses.add(consumables);
            consumables = new ConsumablesResponse(getString(R.string.healthkit), "Increase character's health to 75%","Time taken : 6 second","Capacity : 10",R.drawable.ic_health_kit);
            consumablesResponses.add(consumables);
            consumables = new ConsumablesResponse(getString(R.string.Bandage), "Increase character's health to 10%","Time taken : 4 second","Capacity : 2",R.drawable.ic_bandeges);
            consumablesResponses.add(consumables);
            consumables = new ConsumablesResponse(getString(R.string.gastank), "Fill up vehicel's gas tank","Time taken : 6 second","Capacity : 20",R.drawable.ic_gas_tank);
            consumablesResponses.add(consumables);
            consumables = new ConsumablesResponse(getString(R.string.medkit), "Increase character's health to 100%", "Time taken : 8 second","Capacity : 20",R.drawable.ic_med_kit);
            consumablesResponses.add(consumables);
            consumables = new ConsumablesResponse(getString(R.string.pankiller), "Increase boost by 60 Instantly","Time taken : 6 second","Capacity : 10",R.drawable.ic_painkiler);
            consumablesResponses.add(consumables);
            consumables = new ConsumablesResponse(getString(R.string.syringe), "Increase boost by 100 Instantly","Time taken : 6 second","Capacity : 20",R.drawable.ic_adrenline_syringe);
            consumablesResponses.add(consumables);
            consumablesAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
