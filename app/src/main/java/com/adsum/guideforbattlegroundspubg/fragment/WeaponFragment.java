package com.adsum.guideforbattlegroundspubg.fragment;

import android.graphics.Movie;
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
import com.adsum.guideforbattlegroundspubg.adapter.ARAdapter;
import com.adsum.guideforbattlegroundspubg.adapter.BowsAdapter;
import com.adsum.guideforbattlegroundspubg.adapter.DmrAdapter;
import com.adsum.guideforbattlegroundspubg.adapter.HandgunsAdapter;
import com.adsum.guideforbattlegroundspubg.adapter.LmgAdapter;
import com.adsum.guideforbattlegroundspubg.adapter.MeleeAdapter;
import com.adsum.guideforbattlegroundspubg.adapter.ShotgunsAdapter;
import com.adsum.guideforbattlegroundspubg.adapter.SmgAdapter;
import com.adsum.guideforbattlegroundspubg.adapter.SniperAdapter;
import com.adsum.guideforbattlegroundspubg.adapter.ThrowabilesAdapter;
import com.adsum.guideforbattlegroundspubg.config.Constants;
import com.adsum.guideforbattlegroundspubg.model.ARResponse;
import com.adsum.guideforbattlegroundspubg.model.BowsResponse;
import com.adsum.guideforbattlegroundspubg.model.DmrResponse;
import com.adsum.guideforbattlegroundspubg.model.HandgunsResponse;
import com.adsum.guideforbattlegroundspubg.model.LmgResponse;
import com.adsum.guideforbattlegroundspubg.model.MeleeResponse;
import com.adsum.guideforbattlegroundspubg.model.ShotgunsResponse;
import com.adsum.guideforbattlegroundspubg.model.SmgResponse;
import com.adsum.guideforbattlegroundspubg.model.SniperResponse;
import com.adsum.guideforbattlegroundspubg.model.ThrowabilesResponse;
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

public class WeaponFragment extends Fragment {
    String google_banner, google_interitial, google_rewarded, google_native;
    String fb_banner, fb_interitial, fb_rewarded, fb_native;
    String catggoryname;
    View rootView;
    private RecyclerView sniperrecycler;
    private SniperAdapter sniperAdapter;
    private List<SniperResponse> sniperResponses = new ArrayList<>();

    private RecyclerView dmrrecycler;
    private DmrAdapter dmrAdapter;
    private List<DmrResponse> dmrResponses = new ArrayList<>();


    private RecyclerView lmgrecycler;
    private LmgAdapter lmgAdapter;
    private List<LmgResponse> lmgResponses = new ArrayList<>();

 private RecyclerView Arrecycler;
    private ARAdapter ArAdapter;
    private List<ARResponse> ArResponses = new ArrayList<>();

    private RecyclerView smgrecycler;
    private SmgAdapter smgAdapter;
    private List<SmgResponse> smgResponses = new ArrayList<>();

    private RecyclerView shotgunrecycler;
    private ShotgunsAdapter shotgunAdapter;
    private List<ShotgunsResponse> shotgunResponses = new ArrayList<>();

    private RecyclerView handgunrecycler;
    private HandgunsAdapter handgunsAdapter;
    private List<HandgunsResponse> handgunsResponses = new ArrayList<>();

    private RecyclerView bowsrecycler;
    private BowsAdapter bowsAdapter;
    private List<BowsResponse> bowsResponses = new ArrayList<>();

    private RecyclerView meleerecycler;
    private MeleeAdapter meleeAdapter;
    private List<MeleeResponse> meleeResponses = new ArrayList<>();

    private RecyclerView throwrecycler;
    private ThrowabilesAdapter throwabilesAdapter;
    private List<ThrowabilesResponse> throwabilesResponses = new ArrayList<>();

    boolean showAdds = true;
    // creating NativeAdLayout object
    private NativeAdLayout nativeAdLayout;
    private NativeAdLayout nativeAdLayout1;
    private NativeAdLayout nativeAdLayout2;
    // creating  LinearLayout object
    private LinearLayout adView;
    // creating  NativeAd object
    private NativeAd nativeAd;

    TemplateView templateView;
    TemplateView templateView1;
    TemplateView templateView2;

    private InterstitialAd interstitialAd;
    com.facebook.ads.InterstitialAd fbinterstitialAd;
    boolean sniper = false;
    boolean dmr = false;
    boolean lmg = false;
    boolean ar = false;
    boolean smg = false;
    boolean shot = false;
    boolean hand = false;
    boolean bows = false;
    boolean melee = false;
    boolean throwa = false;
    int i;
    List<SniperResponse> sniprlist;
    List<DmrResponse> dmrslist;
    List<ARResponse> arslist;
    List<SmgResponse> smgslist;
    List<LmgResponse> lmglist;
    List<ShotgunsResponse> shotgunlist;
    List<HandgunsResponse> handgunlist;
    List<BowsResponse> bowslist;
    List<MeleeResponse> melelist;
    List<ThrowabilesResponse> throwlist;
    int pos;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.activity_weapon_fragment, container, false);

        sniperrecycler = (RecyclerView) rootView.findViewById(R.id.recycler_view_sniper);
        dmrrecycler = (RecyclerView) rootView.findViewById(R.id.recycler_view_dmr);
       lmgrecycler = (RecyclerView) rootView.findViewById(R.id.recycler_view_lmg);
       Arrecycler = (RecyclerView) rootView.findViewById(R.id.recycler_view_ar);
       smgrecycler = (RecyclerView) rootView.findViewById(R.id.recycler_view_smg);
       shotgunrecycler = (RecyclerView) rootView.findViewById(R.id.recycler_view_shotguns);
       handgunrecycler = (RecyclerView) rootView.findViewById(R.id.recycler_view_handguns);
        bowsrecycler = (RecyclerView) rootView.findViewById(R.id.recycler_view_bows);
        meleerecycler = (RecyclerView) rootView.findViewById(R.id.recycler_view_melle);
        throwrecycler = (RecyclerView) rootView.findViewById(R.id.recycler_view_throwabiles);

        nativeAdLayout = rootView.findViewById(R.id.native_ad_container);
        nativeAdLayout1 = rootView.findViewById(R.id.native_ad_container1);
        nativeAdLayout2 = rootView.findViewById(R.id.native_ad_container2);

        templateView = rootView.findViewById(R.id.nativeTemplateView);
        templateView1 = rootView.findViewById(R.id.nativeTemplateView1);
        templateView2 = rootView.findViewById(R.id.nativeTemplateView2);
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
                snpierdata();
                dmrdata();
                lmgdata();
                ardata();
                smgdata();
                handgunsdata();
                shotgunsdata();
                bowsdata();
                meleedata();
                throwdata();
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
                    nativeAdLayout1.setVisibility(View.GONE);
                    nativeAdLayout2.setVisibility(View.GONE);

                    templateView.setVisibility(View.VISIBLE);
                    templateView1.setVisibility(View.VISIBLE);
                    templateView2.setVisibility(View.VISIBLE);

                    templateView.setNativeAd(unifiedNativeAd);
                    templateView1.setNativeAd(unifiedNativeAd);
                    templateView2.setNativeAd(unifiedNativeAd);
//                        Toast.makeText(HomePage.this, "LoadAd Successfully", Toast.LENGTH_LONG).show();

                }
            }).withAdListener(new com.google.android.gms.ads.AdListener() {
                @Override
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    nativeAdLayout.setVisibility(View.GONE);
                    nativeAdLayout1.setVisibility(View.GONE);
                    nativeAdLayout2.setVisibility(View.GONE);

                    templateView.setVisibility(View.GONE);
                    templateView1.setVisibility(View.GONE);
                    templateView2.setVisibility(View.GONE);
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
                    nativeAdLayout1.setVisibility(View.VISIBLE);
                    nativeAdLayout2.setVisibility(View.VISIBLE);
                    // Inflate Native Ad into Container
                    inflateAd(nativeAd);
                    inflateAd1(nativeAd);
                    inflateAd2(nativeAd);
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
    private void inflateAd1(NativeAd nativeAd) {
        // unregister the native Ad View
        nativeAd.unregisterView();

        // Add the Ad view into the ad container.
        nativeAdLayout1 = rootView.findViewById(R.id.native_ad_container1);

        LayoutInflater inflater = LayoutInflater.from(getActivity());

        // Inflate the Ad view.
        adView = (LinearLayout) inflater.inflate(R.layout.ad_unified, nativeAdLayout1, false);

        // adding view
        nativeAdLayout1.addView(adView);

        // Add the AdOptionsView
        LinearLayout adChoicesContainer = rootView.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(getActivity(), nativeAd, nativeAdLayout1);
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
    private void inflateAd2(NativeAd nativeAd) {
        // unregister the native Ad View
        nativeAd.unregisterView();

        // Add the Ad view into the ad container.
        nativeAdLayout2 = rootView.findViewById(R.id.native_ad_container2);

        LayoutInflater inflater = LayoutInflater.from(getActivity());

        // Inflate the Ad view.
        adView = (LinearLayout) inflater.inflate(R.layout.ad_unified, nativeAdLayout2, false);

        // adding view
        nativeAdLayout2.addView(adView);

        // Add the AdOptionsView
        LinearLayout adChoicesContainer = rootView.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(getActivity(), nativeAd, nativeAdLayout2);
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
    private void throwdata() {
        try {
            throwabilesAdapter = new ThrowabilesAdapter(throwabilesResponses, new ThrowabilesAdapter.AdapterCallback() {
                @Override
                public void subcategoryclick(List<ThrowabilesResponse> throwList, int position) {

                }
            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            throwrecycler.setLayoutManager(mLayoutManager);
            throwrecycler.setItemAnimator(new DefaultItemAnimator());
            throwrecycler.setAdapter(throwabilesAdapter);
            preparethrowData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void preparethrowData() {
        try {
            ThrowabilesResponse throwab= new ThrowabilesResponse(getString(R.string.frage), "Deals a lot damage within its area of effect", R.drawable.ic_flag_grenade);
            throwabilesResponses.add(throwab);
            throwab = new ThrowabilesResponse(getString(R.string.molotive), "Create fire that lasts for afew secounds",R.drawable.ic_molotov);
            throwabilesResponses.add(throwab);
            throwab = new ThrowabilesResponse(getString(R.string.smoke), "Create a smoke cover",R.drawable.ic_smoke);
            throwabilesResponses.add(throwab);
            throwab = new ThrowabilesResponse(getString(R.string.stun), "Make victims blind, deaf for max 10sec",R.drawable.ic_stune_grenade);
            throwabilesResponses.add(throwab);
            throwabilesAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void meleedata() {
        try {
            meleeAdapter = new MeleeAdapter(meleeResponses, new MeleeAdapter.AdapterCallback() {
                @Override
                public void subcategoryclick(List<MeleeResponse> meleeList, int position) {

                }
            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            meleerecycler.setLayoutManager(mLayoutManager);
            meleerecycler.setItemAnimator(new DefaultItemAnimator());
            meleerecycler.setAdapter(meleeAdapter);
            preparemeleeData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void preparemeleeData() {
        try {
            MeleeResponse melee= new MeleeResponse(getString(R.string.pan), "DAMAGE : 80", R.drawable.ic_pan);
            meleeResponses.add(melee);
            melee = new MeleeResponse(getString(R.string.crowbar), "DAMAGE : 60",R.drawable.ic_crowber);
            meleeResponses.add(melee);
            melee = new MeleeResponse(getString(R.string.machete), "DAMAGE : 60",R.drawable.ic_machete);
            meleeResponses.add(melee);
            melee = new MeleeResponse(getString(R.string.sickle), "DAMAGE : 60",R.drawable.ic_sickle);
            meleeResponses.add(melee);
            meleeAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bowsdata() {
        try {
            bowsAdapter = new BowsAdapter(bowsResponses, new BowsAdapter.AdapterCallback() {
                @Override
                public void subcategoryclick(List<BowsResponse> bowsList, int position) {
                    bowslist = bowsList;
                    pos = position;
                    i++;
                    if (i == 4) {
                        bows = true;
                        if (SplashActivity.appConfigModel.IS_GOOGLE.equalsIgnoreCase(Constants.VAL_TRUE)) {
                            googleinterstialadshow();
                        } else {
                            fbinterstialadshow();
                        }

                        i = 0;
                    } else {
                        DetailFragment detailFragment = new DetailFragment();
                        Bundle bundle = new Bundle();
                        //  bundle.putString(Constants.catgegoryname,txt_attechment.toString());
                        bundle.putString(Constants.weaponname,bowsList.get(position).getWeponname());
                        bundle.putInt(Constants.weaponimage,bowsList.get(position).getBowsimage());

                        detailFragment.setArguments(bundle);
                        ((DashBoard)getActivity()).addFragment(detailFragment);
                    }

                }
            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            bowsrecycler.setLayoutManager(mLayoutManager);
            bowsrecycler.setItemAnimator(new DefaultItemAnimator());
            bowsrecycler.setAdapter(bowsAdapter);
            preparebowsData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void preparebowsData() {
        BowsResponse bows= new BowsResponse(getString(R.string.crossbow), "DAMAGE : 105", R.drawable.ic_crossbow);
        bowsResponses.add(bows);
        bowsAdapter.notifyDataSetChanged();
    }

    private void handgunsdata() {
        handgunsAdapter = new HandgunsAdapter(handgunsResponses, new HandgunsAdapter.AdapterCallback() {
            @Override
            public void subcategoryclick(List<HandgunsResponse> handgunsList, int position) {
                handgunlist = handgunsList;
                pos = position;
                i++;
                if (i == 4) {
                    hand = true;
                    if (SplashActivity.appConfigModel.IS_GOOGLE.equalsIgnoreCase(Constants.VAL_TRUE)) {
                        googleinterstialadshow();
                    } else {
                        fbinterstialadshow();
                    }

                    i = 0;
                } else {
                    DetailFragment detailFragment = new DetailFragment();
                    Bundle bundle = new Bundle();
                    //  bundle.putString(Constants.catgegoryname,txt_attechment.toString());
                    bundle.putString(Constants.weaponname,handgunsList.get(position).getWeponname());
                    bundle.putInt(Constants.weaponimage,handgunsList.get(position).getHandgunimage());
                    detailFragment.setArguments(bundle);
                    ((DashBoard)getActivity()).addFragment(detailFragment);
                }

            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        handgunrecycler.setLayoutManager(mLayoutManager);
        handgunrecycler.setItemAnimator(new DefaultItemAnimator());
        handgunrecycler.setAdapter(handgunsAdapter);
        preparehandgunData();
    }

    private void preparehandgunData() {
        try {
            HandgunsResponse handguns= new HandgunsResponse(getString(R.string.r45), "DAMAGE : 55", R.drawable.ic_r45);
            handgunsResponses.add(handguns);
            handguns = new HandgunsResponse(getString(R.string.r1895), "DAMAGE : 55",R.drawable.ic_r1895);
            handgunsResponses.add(handguns);
            handguns = new HandgunsResponse(getString(R.string.p1911), "DAMAGE : 41",R.drawable.ic_p1911 );
            handgunsResponses.add(handguns);
            handguns = new HandgunsResponse(getString(R.string.p92), "DAMAGE : 35",R.drawable.ic_p92);
            handgunsResponses.add(handguns);
            handguns = new HandgunsResponse(getString(R.string.p18c), "DAMAGE : 23",R.drawable.ic_p18c );
            handgunsResponses.add(handguns);
            handguns = new HandgunsResponse(getString(R.string.skorpion), "DAMAGE : 22",R.drawable.ic_skorpion );
            handgunsResponses.add(handguns);
            handguns = new HandgunsResponse(getString(R.string.fliaregun), "DAMAGE : 0",R.drawable.ic_flare_gun );
            handgunsResponses.add(handguns);
            handgunsAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void shotgunsdata() {
        shotgunAdapter = new ShotgunsAdapter(shotgunResponses, new ShotgunsAdapter.AdapterCallback() {
            @Override
            public void subcategoryclick(List<ShotgunsResponse> shotgunsList, int position) {
                shotgunlist = shotgunsList;
                pos = position;
                i++;
                if (i == 4) {
                    shot = true;
                    if (SplashActivity.appConfigModel.IS_GOOGLE.equalsIgnoreCase(Constants.VAL_TRUE)) {
                        googleinterstialadshow();
                    } else {
                        fbinterstialadshow();
                    }

                    i = 0;
                } else {
                    DetailFragment detailFragment = new DetailFragment();
                    Bundle bundle = new Bundle();
                    //  bundle.putString(Constants.catgegoryname,txt_attechment.toString());
                    bundle.putString(Constants.weaponname,shotgunsList.get(position).getWeponname());
                    bundle.putInt(Constants.weaponimage,shotgunsList.get(position).getShotgunimage());
                    detailFragment.setArguments(bundle);
                    ((DashBoard)getActivity()).addFragment(detailFragment);
                }


            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        shotgunrecycler.setLayoutManager(mLayoutManager);
        shotgunrecycler.setItemAnimator(new DefaultItemAnimator());
        shotgunrecycler.setAdapter(shotgunAdapter);
        prepareshotgunData();
    }

    private void prepareshotgunData() {
        try {
            ShotgunsResponse shotgun= new ShotgunsResponse(getString(R.string.s12k), "DAMAGE : 198", R.drawable.ic_s12k);
            shotgunResponses.add(shotgun);
            shotgun = new ShotgunsResponse(getString(R.string.s686), "DAMAGE : 216",R.drawable.ic_s686);
            shotgunResponses.add(shotgun);
            shotgun = new ShotgunsResponse(getString(R.string.s1897), "DAMAGE : 216",R.drawable.ic_s1897);
            shotgunResponses.add(shotgun);
            shotgun = new ShotgunsResponse(getString(R.string.sawedoff), "DAMAGE : 160",R.drawable.ic_sawed);
            shotgunResponses.add(shotgun);
            shotgunAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void smgdata() {
        try {
            smgAdapter = new SmgAdapter(smgResponses, new SmgAdapter.AdapterCallback() {
                @Override
                public void subcategoryclick(List<SmgResponse> smgList, int position) {
                    smgslist = smgList;
                    pos = position;
                    i++;
                    if (i == 4) {
                        smg = true;
                        if (SplashActivity.appConfigModel.IS_GOOGLE.equalsIgnoreCase(Constants.VAL_TRUE)) {
                            googleinterstialadshow();
                        } else {
                            fbinterstialadshow();
                        }

                        i = 0;
                    } else {
                        DetailFragment detailFragment = new DetailFragment();
                        Bundle bundle = new Bundle();
                        //  bundle.putString(Constants.catgegoryname,txt_attechment.toString());
                        bundle.putString(Constants.weaponname,smgList.get(position).getWeponname());
                        bundle.putInt(Constants.weaponimage,smgList.get(position).getSmgimage());
                        detailFragment.setArguments(bundle);
                        ((DashBoard)getActivity()).addFragment(detailFragment);
                    }

                }
            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            smgrecycler.setLayoutManager(mLayoutManager);
            smgrecycler.setItemAnimator(new DefaultItemAnimator());
            smgrecycler.setAdapter(smgAdapter);
            preparesmgData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void preparesmgData() {
        try {
            SmgResponse smg= new SmgResponse(getString(R.string.tommy), "DAMAGE : 40", R.drawable.ic_thomson);
            smgResponses.add(smg);
            smg = new SmgResponse(getString(R.string.ump9), "DAMAGE : 39",R.drawable.ic_ump45);
            smgResponses.add(smg);
            smg = new SmgResponse(getString(R.string.pp19), "DAMAGE : 35",R.drawable.ic_bizon );
            smgResponses.add(smg);
            smg = new SmgResponse(getString(R.string.vector), "DAMAGE : 34",R.drawable.ic_vector );
            smgResponses.add(smg);
            smg = new SmgResponse(getString(R.string.mp5k), "DAMAGE : 33",R.drawable.ic_mp5k );
            smgResponses.add(smg);
            smg = new SmgResponse(getString(R.string.microuzi), "DAMAGE : 26",R.drawable.ic_uzi );
            smgResponses.add(smg);
            smgAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ardata() {
        try {
            ArAdapter = new ARAdapter(ArResponses, new ARAdapter.AdapterCallback() {
                @Override
                public void subcategoryclick(List<ARResponse> arList, int position) {
                    arslist = arList;
                    pos = position;
                    i++;
                    if (i == 4) {
                        ar = true;
                        if (SplashActivity.appConfigModel.IS_GOOGLE.equalsIgnoreCase(Constants.VAL_TRUE)) {
                            googleinterstialadshow();
                        } else {
                            fbinterstialadshow();
                        }

                        i = 0;
                    } else {
                        DetailFragment detailFragment = new DetailFragment();
                        Bundle bundle = new Bundle();
                        //  bundle.putString(Constants.catgegoryname,txt_attechment.toString());
                        bundle.putString(Constants.weaponname,arList.get(position).getWeponname());
                        bundle.putInt(Constants.weaponimage,arList.get(position).getARimage());
                        detailFragment.setArguments(bundle);
                        ((DashBoard)getActivity()).addFragment(detailFragment);
                    }

                }
            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            Arrecycler.setLayoutManager(mLayoutManager);
            Arrecycler.setItemAnimator(new DefaultItemAnimator());
            Arrecycler.setAdapter(ArAdapter);
            preparearData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void preparearData() {
        try {
            ARResponse ar= new ARResponse(getString(R.string.groza), "DAMAGE : 49", R.drawable.ic_groza);
            ArResponses.add(ar);
            ar = new ARResponse(getString(R.string.akm), "DAMAGE : 49",R.drawable.ic_akm);
            ArResponses.add(ar);
            ar = new ARResponse(getString(R.string.mk47), "DAMAGE : 49",R.drawable.ic_mk47 );
            ArResponses.add(ar);
            ar = new ARResponse(getString(R.string.berylm762), "DAMAGE : 47",R.drawable.ic_m762);
            ArResponses.add(ar);
            ar = new ARResponse(getString(R.string.m416), "DAMAGE : 43",R.drawable.ic_m416 );
            ArResponses.add(ar);
            ar = new ARResponse(getString(R.string.scarl), "DAMAGE : 43",R.drawable.ic_scarl );
            ArResponses.add(ar);
            ar = new ARResponse(getString(R.string.auga3), "DAMAGE : 43",R.drawable.ic_aug );
            ArResponses.add(ar);
            ar = new ARResponse(getString(R.string.qbz95), "DAMAGE : 43",R.drawable.ic_qbz );
            ArResponses.add(ar);
            ar = new ARResponse(getString(R.string.m16a4), "DAMAGE : 43",R.drawable.ic_m16a4 );
            ArResponses.add(ar);
            ArAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void lmgdata() {
        try {
            lmgAdapter = new LmgAdapter(lmgResponses, new LmgAdapter.AdapterCallback() {
                @Override
                public void subcategoryclick(List<LmgResponse> lmgList, int position) {
                    lmglist = lmgList;
                    pos = position;
                    i++;
                    if (i == 4) {
                        lmg = true;
                        if (SplashActivity.appConfigModel.IS_GOOGLE.equalsIgnoreCase(Constants.VAL_TRUE)) {
                            googleinterstialadshow();
                        } else {
                            fbinterstialadshow();
                        }

                        i = 0;
                    } else {
                        DetailFragment detailFragment = new DetailFragment();
                        Bundle bundle = new Bundle();
                        //  bundle.putString(Constants.catgegoryname,txt_attechment.toString());
                        bundle.putString(Constants.weaponname,lmgList.get(position).getWeponname());
                        bundle.putInt(Constants.weaponimage,lmgList.get(position).getLmgimage());
                        detailFragment.setArguments(bundle);
                        ((DashBoard)getActivity()).addFragment(detailFragment);
                    }

                }
            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            lmgrecycler.setLayoutManager(mLayoutManager);
            lmgrecycler.setItemAnimator(new DefaultItemAnimator());
            lmgrecycler.setAdapter(lmgAdapter);
            preparelmgData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void preparelmgData() {
        try {
            LmgResponse lmg= new LmgResponse(getString(R.string.m249), "DAMAGE : 45", R.drawable.ic_m249);
            lmgResponses.add(lmg);
            lmg = new LmgResponse(getString(R.string.dp28), "DAMAGE : 51",R.drawable.ic_dp28);
            lmgResponses.add(lmg);
            lmgAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dmrdata() {
        try {
            dmrAdapter = new DmrAdapter(dmrResponses, new DmrAdapter.AdapterCallback() {
                @Override
                public void subcategoryclick(List<DmrResponse> dmrList, int position) {
                    dmrslist = dmrList;
                    pos = position;
                    i++;
                    if (i == 4) {
                        dmr = true;
                        if (SplashActivity.appConfigModel.IS_GOOGLE.equalsIgnoreCase(Constants.VAL_TRUE)) {
                            googleinterstialadshow();
                        } else {
                            fbinterstialadshow();
                        }

                        i = 0;
                    } else {
                        DetailFragment detailFragment = new DetailFragment();
                        Bundle bundle = new Bundle();
                        //  bundle.putString(Constants.catgegoryname,txt_attechment.toString());
                        bundle.putString(Constants.weaponname,dmrList.get(position).getWeponname());
                        bundle.putInt(Constants.weaponimage,dmrList.get(position).getDmrimage());
                        detailFragment.setArguments(bundle);
                        ((DashBoard)getActivity()).addFragment(detailFragment);
                    }
                }
            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            dmrrecycler.setLayoutManager(mLayoutManager);
            dmrrecycler.setItemAnimator(new DefaultItemAnimator());
            dmrrecycler.setAdapter(dmrAdapter);
            preparedmrData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void preparedmrData() {
        try {
            DmrResponse dmr= new DmrResponse(getString(R.string.mk14), "DAMAGE : 61", R.drawable.ic_mk14);
            dmrResponses.add(dmr);
            dmr = new DmrResponse(getString(R.string.slr), "DAMAGE : 58",R.drawable.ic_slr);
            dmrResponses.add(dmr);
            dmr = new DmrResponse(getString(R.string.sks), "DAMAGE : 53",R.drawable.ic_sks );
            dmrResponses.add(dmr);
            dmr = new DmrResponse(getString(R.string.qbu), "DAMAGE : 48",R.drawable.ic_qbu );
            dmrResponses.add(dmr);
            dmr = new DmrResponse(getString(R.string.mini14), "DAMAGE : 46",R.drawable.ic_mini14 );
            dmrResponses.add(dmr);
            dmr = new DmrResponse(getString(R.string.vss), "DAMAGE : 41",R.drawable.ic_vss );
            dmrResponses.add(dmr);
            dmrAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void snpierdata() {
        try {
            sniperAdapter = new SniperAdapter(sniperResponses, new SniperAdapter.AdapterCallback() {
                @Override
                public void subcategoryclick(List<SniperResponse> sniperList, int position) {
                    sniprlist = sniperList;
                    pos = position;
                    i++;
                    if (i == 4) {
                        sniper = true;
                        if (SplashActivity.appConfigModel.IS_GOOGLE.equalsIgnoreCase(Constants.VAL_TRUE)) {
                            googleinterstialadshow();
                        } else {
                            fbinterstialadshow();
                        }

                        i = 0;
                    } else {
                        DetailFragment detailFragment = new DetailFragment();
                        Bundle bundle = new Bundle();
                        //  bundle.putString(Constants.catgegoryname,txt_attechment.toString());
                        bundle.putString(Constants.weaponname,sniperList.get(position).getWeponname());
                        bundle.putInt(Constants.weaponimage,sniperList.get(position).getSniperimage());
                        detailFragment.setArguments(bundle);
                        ((DashBoard)getActivity()).addFragment(detailFragment);
                    }


                }
            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            sniperrecycler.setLayoutManager(mLayoutManager);
            sniperrecycler.setItemAnimator(new DefaultItemAnimator());
            sniperrecycler.setAdapter(sniperAdapter);
            preparesniperData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void preparesniperData() {
        try {
            SniperResponse sniper = new SniperResponse(getString(R.string.awm), "DAMAGE : 132", R.drawable.ic_awm);
            sniperResponses.add(sniper);
            sniper = new SniperResponse(getString(R.string.m24), "DAMAGE : 82",R.drawable.ic_m24 );
            sniperResponses.add(sniper);
            sniper = new SniperResponse(getString(R.string.kar98k), "DAMAGE : 72",R.drawable.ic_kar98 );
            sniperResponses.add(sniper);
            sniper = new SniperResponse(getString(R.string.win94), "DAMAGE : 66",R.drawable.ic_win94 );
            sniperResponses.add(sniper);
            sniperAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void load_InterstitialAd_fb() {
        fbinterstitialAd = new com.facebook.ads.InterstitialAd(getActivity(), fb_interitial);
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
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

    public void setInterstitialAd() {
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
                if (sniper) {
                    DetailFragment detailFragment = new DetailFragment();
                    Bundle bundle = new Bundle();
                    //  bundle.putString(Constants.catgegoryname,txt_attechment.toString());
                    bundle.putString(Constants.weaponname,sniprlist.get(pos).getWeponname());
                    bundle.putInt(Constants.weaponimage,sniprlist.get(pos).getSniperimage());
                    detailFragment.setArguments(bundle);
                    ((DashBoard)getActivity()).addFragment(detailFragment);
                } else if (dmr) {
                    DetailFragment detailFragment = new DetailFragment();
                    Bundle bundle = new Bundle();
                    //  bundle.putString(Constants.catgegoryname,txt_attechment.toString());
                    bundle.putString(Constants.weaponname,dmrslist.get(pos).getWeponname());
                    bundle.putInt(Constants.weaponimage,dmrslist.get(pos).getDmrimage());
                    detailFragment.setArguments(bundle);
                    ((DashBoard)getActivity()).addFragment(detailFragment);
                } else if (lmg) {
                    DetailFragment detailFragment = new DetailFragment();
                    Bundle bundle = new Bundle();
                    //  bundle.putString(Constants.catgegoryname,txt_attechment.toString());
                    bundle.putString(Constants.weaponname,lmglist.get(pos).getWeponname());
                    bundle.putInt(Constants.weaponimage,lmglist.get(pos).getLmgimage());
                    detailFragment.setArguments(bundle);
                    ((DashBoard)getActivity()).addFragment(detailFragment);
                } else if (ar) {
                    DetailFragment detailFragment = new DetailFragment();
                    Bundle bundle = new Bundle();
                    //  bundle.putString(Constants.catgegoryname,txt_attechment.toString());
                    bundle.putString(Constants.weaponname,arslist.get(pos).getWeponname());
                    bundle.putInt(Constants.weaponimage,arslist.get(pos).getARimage());
                    detailFragment.setArguments(bundle);
                    ((DashBoard)getActivity()).addFragment(detailFragment);
                } else if (smg) {
                    DetailFragment detailFragment = new DetailFragment();
                    Bundle bundle = new Bundle();
                    //  bundle.putString(Constants.catgegoryname,txt_attechment.toString());
                    bundle.putString(Constants.weaponname,smgslist.get(pos).getWeponname());
                    bundle.putInt(Constants.weaponimage,smgslist.get(pos).getSmgimage());
                    detailFragment.setArguments(bundle);
                    ((DashBoard)getActivity()).addFragment(detailFragment);
                } else if (shot) {
                    DetailFragment detailFragment = new DetailFragment();
                    Bundle bundle = new Bundle();
                    //  bundle.putString(Constants.catgegoryname,txt_attechment.toString());
                    bundle.putString(Constants.weaponname,shotgunlist.get(pos).getWeponname());
                    bundle.putInt(Constants.weaponimage,shotgunlist.get(pos).getShotgunimage());
                    detailFragment.setArguments(bundle);
                    ((DashBoard)getActivity()).addFragment(detailFragment);
                } else if (hand) {
                    DetailFragment detailFragment = new DetailFragment();
                    Bundle bundle = new Bundle();
                    //  bundle.putString(Constants.catgegoryname,txt_attechment.toString());
                    bundle.putString(Constants.weaponname,handgunlist.get(pos).getWeponname());
                    bundle.putInt(Constants.weaponimage,handgunlist.get(pos).getHandgunimage());
                    detailFragment.setArguments(bundle);
                    ((DashBoard)getActivity()).addFragment(detailFragment);
                } else if (bows) {
                    DetailFragment detailFragment = new DetailFragment();
                    Bundle bundle = new Bundle();
                    //  bundle.putString(Constants.catgegoryname,txt_attechment.toString());
                    bundle.putString(Constants.weaponname,bowslist.get(pos).getWeponname());
                    bundle.putInt(Constants.weaponimage,bowslist.get(pos).getBowsimage());

                    detailFragment.setArguments(bundle);
                    ((DashBoard)getActivity()).addFragment(detailFragment);
                } else if (melee) {

                } else if (throwa) {

                }

                sniper = false;
                 dmr = false;
                 lmg = false;
                 ar = false;
                 smg = false;
                 shot = false;
                 hand = false;
                 bows = false;
                 melee = false;
                 throwa = false;
            }
        });
    }

    public void googleinterstialadshow() {
        interstitialAd.show();
        setInterstitialAd();

    }

    public void fbinterstialadshow() {
        fbinterstitialAd.show();
        load_InterstitialAd_fb();

    }
}
