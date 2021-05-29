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
import com.adsum.guideforbattlegroundspubg.adapter.LowerRailsAdapter;
import com.adsum.guideforbattlegroundspubg.adapter.MagazinesAdapter;
import com.adsum.guideforbattlegroundspubg.adapter.MuzzlesAdapter;
import com.adsum.guideforbattlegroundspubg.adapter.ScopesAdapter;
import com.adsum.guideforbattlegroundspubg.adapter.StocksAdapter;
import com.adsum.guideforbattlegroundspubg.adapter.ThrowabilesAdapter;
import com.adsum.guideforbattlegroundspubg.config.Constants;
import com.adsum.guideforbattlegroundspubg.model.AmmosResponse;
import com.adsum.guideforbattlegroundspubg.model.ConsumablesResponse;
import com.adsum.guideforbattlegroundspubg.model.LowerRailsResponse;
import com.adsum.guideforbattlegroundspubg.model.MagazinesResponse;
import com.adsum.guideforbattlegroundspubg.model.MuzzlesResponse;
import com.adsum.guideforbattlegroundspubg.model.ScopesResponse;
import com.adsum.guideforbattlegroundspubg.model.StocksResponse;
import com.adsum.guideforbattlegroundspubg.model.ThrowabilesResponse;
import com.adsum.guideforbattlegroundspubg.model.VehiclesResponse;
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

public class AttechmentFragment extends Fragment {
    String google_banner, google_interitial, google_rewarded, google_native;
    String fb_banner, fb_interitial, fb_rewarded, fb_native;
    View rootView;

    private RecyclerView muzzlesrecycler;
    private MuzzlesAdapter muzzlesAdapter;
    private List<MuzzlesResponse> muzzlesResponses = new ArrayList<>();

    private RecyclerView lowerrailsrecycler;
    private LowerRailsAdapter lowerRailsAdapter;
    private List<LowerRailsResponse> lowerRailsResponses = new ArrayList<>();

    private RecyclerView scopesrecycler;
    private ScopesAdapter scopesAdapter;
    private List<ScopesResponse> scopesResponses = new ArrayList<>();

    private RecyclerView magazinesrecycler;
    private MagazinesAdapter magazinesAdapter;
    private List<MagazinesResponse> magazinesResponses = new ArrayList<>();

    private RecyclerView stocksrecycler;
    private StocksAdapter stocksAdapter;
    private List<StocksResponse> stocksResponses = new ArrayList<>();

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
    boolean muzzle = false;
    boolean magazin = false;
    boolean scope = false;
    boolean lowerrails = false;
    boolean stock = false;
    int i;
    List<MuzzlesResponse> muzzleList;
    List<LowerRailsResponse> lowerailsList;
    List<ScopesResponse> scopsList;
    List<MagazinesResponse> magzinesList;
    List<StocksResponse> stcksList;

    int pos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.activity_attechment_fragment, container, false);
        muzzlesrecycler = (RecyclerView) rootView.findViewById(R.id.recycler_view_muzzle);
        lowerrailsrecycler = (RecyclerView) rootView.findViewById(R.id.recycler_view_lower);
        scopesrecycler = (RecyclerView) rootView.findViewById(R.id.recycler_view_scopes);
        magazinesrecycler = (RecyclerView) rootView.findViewById(R.id.recycler_view_magazines);
        stocksrecycler = (RecyclerView) rootView.findViewById(R.id.recycler_view_stocks);

        nativeAdLayout = rootView.findViewById(R.id.native_ad_container);
        nativeAdLayout1 = rootView.findViewById(R.id.native_ad_container1);
        nativeAdLayout2 = rootView.findViewById(R.id.native_ad_container2);
        i = 0;
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
            muzzlesdata();
            lowerrailsdata();
            scopedata();
            magazinesdata();
            stocksdata();
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
                if (muzzle) {
                    AttachmentDetailFragment attachmentDetailFragment = new AttachmentDetailFragment();
                    Bundle bundle = new Bundle();
                    //  bundle.putString(Constants.catgegoryname,txt_attechment.toString());
                    bundle.putString(Constants.attechmentname, muzzleList.get(pos).getMuzzlename());
                    bundle.putInt(Constants.attechmentimage, muzzleList.get(pos).getMuzzlesimage());

                    attachmentDetailFragment.setArguments(bundle);
                    ((DashBoard) getActivity()).addFragment(attachmentDetailFragment);
                } else if (magazin) {
                    AttachmentDetailFragment attachmentDetailFragment = new AttachmentDetailFragment();
                    Bundle bundle = new Bundle();
                    //  bundle.putString(Constants.catgegoryname,txt_attechment.toString());
                    bundle.putString(Constants.attechmentname, magzinesList.get(pos).getMagazinesname());
                    bundle.putInt(Constants.attechmentimage, magzinesList.get(pos).getMagazinesimage());

                    attachmentDetailFragment.setArguments(bundle);
                    ((DashBoard) getActivity()).addFragment(attachmentDetailFragment);
                } else if (scope) {
                    AttachmentDetailFragment attachmentDetailFragment = new AttachmentDetailFragment();
                    Bundle bundle = new Bundle();
                    //  bundle.putString(Constants.catgegoryname,txt_attechment.toString());
                    bundle.putString(Constants.attechmentname, scopsList.get(pos).getScopename());
                    bundle.putInt(Constants.attechmentimage, scopsList.get(pos).getScopeimage());

                    attachmentDetailFragment.setArguments(bundle);
                    ((DashBoard) getActivity()).addFragment(attachmentDetailFragment);
                } else if (lowerrails) {
                    AttachmentDetailFragment attachmentDetailFragment = new AttachmentDetailFragment();
                    Bundle bundle = new Bundle();
                    //  bundle.putString(Constants.catgegoryname,txt_attechment.toString());
                    bundle.putString(Constants.attechmentname, lowerailsList.get(pos).getLowerrname());
                    bundle.putInt(Constants.attechmentimage, lowerailsList.get(pos).getLowerrimage());

                    attachmentDetailFragment.setArguments(bundle);
                    ((DashBoard) getActivity()).addFragment(attachmentDetailFragment);
                } else if (stock) {
                    AttachmentDetailFragment attachmentDetailFragment = new AttachmentDetailFragment();
                    Bundle bundle = new Bundle();
                    //  bundle.putString(Constants.catgegoryname,txt_attechment.toString());
                    bundle.putString(Constants.attechmentname, stcksList.get(pos).getStocksname());
                    bundle.putInt(Constants.attechmentimage, stcksList.get(pos).getStocksimage());

                    attachmentDetailFragment.setArguments(bundle);
                    ((DashBoard) getActivity()).addFragment(attachmentDetailFragment);
                }

                muzzle = false;
                magazin = false;
                scope = false;
                lowerrails = false;
                stock = false;
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

    private void load_nativeAd_admob() {
        try {
            AdLoader.Builder builder = new AdLoader.Builder(getActivity(), google_native);
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
            if (this.showAdds) {
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
            if (this.showAdds) {
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

    private void stocksdata() {
        try {
            stocksAdapter = new StocksAdapter(stocksResponses, new StocksAdapter.AdapterCallback() {
                @Override
                public void subcategoryclick(List<StocksResponse> stocksList, int position) {
                    stcksList = stocksList;
                    pos = position;
                    i++;
                    if (i == 4) {
                        stock = true;
                        if (SplashActivity.appConfigModel.IS_GOOGLE.equalsIgnoreCase(Constants.VAL_TRUE)) {
                            googleinterstialadshow();
                        } else {
                            fbinterstialadshow();
                        }

                        i = 0;
                    } else {
                        AttachmentDetailFragment attachmentDetailFragment = new AttachmentDetailFragment();
                        Bundle bundle = new Bundle();
                        //  bundle.putString(Constants.catgegoryname,txt_attechment.toString());
                        bundle.putString(Constants.attechmentname, stocksList.get(position).getStocksname());
                        bundle.putInt(Constants.attechmentimage, stocksList.get(position).getStocksimage());

                        attachmentDetailFragment.setArguments(bundle);
                        ((DashBoard) getActivity()).addFragment(attachmentDetailFragment);
                    }


                }
            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            stocksrecycler.setLayoutManager(mLayoutManager);
            stocksrecycler.setItemAnimator(new DefaultItemAnimator());
            stocksrecycler.setAdapter(stocksAdapter);
            preparestockData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void preparestockData() {
        try {
            StocksResponse stocks = new StocksResponse(getString(R.string.bullets1897), "Decreases Reload Speed by 30%", R.drawable.ic_bullet_s1897_s686);
            stocksResponses.add(stocks);
            stocks = new StocksResponse(getString(R.string.bulletsnipers), "Decreases Reload Speed by 30% and speed base by 50%", R.drawable.ic_bullet_sniper);
            stocksResponses.add(stocks);
            stocks = new StocksResponse(getString(R.string.cheekpad), "Decreases vertical recoil by 20% and first shot recovery time", R.drawable.ic_cheak_pad);
            stocksResponses.add(stocks);
            stocks = new StocksResponse(getString(R.string.stockmicro), "Make recoil recovery faster and reduces weapon sway", R.drawable.ic_stock_uzi);
            stocksResponses.add(stocks);
            stocks = new StocksResponse(getString(R.string.tacticalstock), "Make recoil recovery faster and reduces weapon sway", R.drawable.ic_tactical_stock_m416_vector);
            stocksResponses.add(stocks);
            stocksAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void magazinesdata() {
        try {
            magazinesAdapter = new MagazinesAdapter(magazinesResponses, new MagazinesAdapter.AdapterCallback() {
                @Override
                public void subcategoryclick(List<MagazinesResponse> magazinesList, int position) {
                    magzinesList=magazinesList;
                    pos=position;
                    i++;
                    if (i == 4) {
                        magazin = true;
                        if (SplashActivity.appConfigModel.IS_GOOGLE.equalsIgnoreCase(Constants.VAL_TRUE)) {
                            googleinterstialadshow();
                        } else {
                            fbinterstialadshow();
                        }

                        i = 0;
                    } else {
                        AttachmentDetailFragment attachmentDetailFragment = new AttachmentDetailFragment();
                        Bundle bundle = new Bundle();
                        //  bundle.putString(Constants.catgegoryname,txt_attechment.toString());
                        bundle.putString(Constants.attechmentname, magazinesList.get(position).getMagazinesname());
                        bundle.putInt(Constants.attechmentimage, magazinesList.get(position).getMagazinesimage());

                        attachmentDetailFragment.setArguments(bundle);
                        ((DashBoard) getActivity()).addFragment(attachmentDetailFragment);
                    }

                }
            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            magazinesrecycler.setLayoutManager(mLayoutManager);
            magazinesrecycler.setItemAnimator(new DefaultItemAnimator());
            magazinesrecycler.setAdapter(magazinesAdapter);
            preparemagazinesrData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void preparemagazinesrData() {
        try {
            MagazinesResponse magazines = new MagazinesResponse(getString(R.string.quickdrawmagar), "Decreases Reload Speed by 30%", R.drawable.ic_quickdraw_mag_ar_smr_s12k);
            magazinesResponses.add(magazines);
            magazines = new MagazinesResponse(getString(R.string.quickdrawmagsr), "Decreases Reload Speed by 30%", R.drawable.ic_quickdeaw_mag_dmr_sr);
            magazinesResponses.add(magazines);
            magazines = new MagazinesResponse(getString(R.string.quickdrawmaghandgun), "Decreases Reload Speed by 30%", R.drawable.ic_quickdraw_mag_handgun);
            magazinesResponses.add(magazines);
            magazines = new MagazinesResponse(getString(R.string.quickdrawmagsmg), "Decreases Reload Speed by 30%", R.drawable.ic_quickdrawmag_smg);
            magazinesResponses.add(magazines);
            magazines = new MagazinesResponse(getString(R.string.quickdrawmagvss), "Decreases Reload Speed", R.drawable.ic_quickdrawmag_vss);
            magazinesResponses.add(magazines);
            magazines = new MagazinesResponse(getString(R.string.exquickdrawmagar), "Decreases Reload Speed and icreases magazine capacity", R.drawable.ic_extended_mag_ar_smr_s12k);
            magazinesResponses.add(magazines);
            magazines = new MagazinesResponse(getString(R.string.exquickdrawmagsr), "Decreases Reload Speed and icreases magazine capacity", R.drawable.ic_extended_quickdraw_mag_dmr_sr);
            magazinesResponses.add(magazines);
            magazines = new MagazinesResponse(getString(R.string.exquickdrawmaghandgun), "Decreases Reload Speed and icreases magazine capacity", R.drawable.ic_extended_quickdraw_mag_hundgun);
            magazinesResponses.add(magazines);
            magazines = new MagazinesResponse(getString(R.string.exquickdrawmagsmg), "Decreases Reload Speed and icreases magazine capacity", R.drawable.ic_extended_quickdraw_mag_smg);
            magazinesResponses.add(magazines);
            magazines = new MagazinesResponse(getString(R.string.exquickmagddmr), "Icreases magazine capacity", R.drawable.ic_extended_mag_sr_dmr_s12k);
            magazinesResponses.add(magazines);
            magazines = new MagazinesResponse(getString(R.string.exquickmagdsr), "Icreases magazine capacity", R.drawable.ic_extended_mag_dmr_sr);
            magazinesResponses.add(magazines);
            magazines = new MagazinesResponse(getString(R.string.exquickmagdsmg), "Icreases magazine capacity", R.drawable.ic_extended_mag_smg);
            magazinesResponses.add(magazines);
            magazines = new MagazinesResponse(getString(R.string.exquickmagdvss), "Icreases magazine capacity", R.drawable.ic_extended_mag_vss);
            magazinesResponses.add(magazines);
            magazines = new MagazinesResponse(getString(R.string.exquickmaghand), "Icreases magazine capacity", R.drawable.ic_extended_mag_hundgun);
            magazinesResponses.add(magazines);
            magazinesAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void scopedata() {
        try {
            scopesAdapter = new ScopesAdapter(scopesResponses, new ScopesAdapter.AdapterCallback() {
                @Override
                public void subcategoryclick(List<ScopesResponse> scopesList, int position) {
                    scopsList =scopesList;
                    pos=position;
                    i++;
                    if (i == 4) {
                        scope = true;
                        if (SplashActivity.appConfigModel.IS_GOOGLE.equalsIgnoreCase(Constants.VAL_TRUE)) {
                            googleinterstialadshow();
                        } else {
                            fbinterstialadshow();
                        }

                        i = 0;
                    } else {
                        AttachmentDetailFragment attachmentDetailFragment = new AttachmentDetailFragment();
                        Bundle bundle = new Bundle();
                        //  bundle.putString(Constants.catgegoryname,txt_attechment.toString());
                        bundle.putString(Constants.attechmentname, scopesList.get(position).getScopename());
                        bundle.putInt(Constants.attechmentimage, scopesList.get(position).getScopeimage());

                        attachmentDetailFragment.setArguments(bundle);
                        ((DashBoard) getActivity()).addFragment(attachmentDetailFragment);
                    }

                }
            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            scopesrecycler.setLayoutManager(mLayoutManager);
            scopesrecycler.setItemAnimator(new DefaultItemAnimator());
            scopesrecycler.setAdapter(scopesAdapter);
            preparescopesrData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void preparescopesrData() {
        try {
            ScopesResponse scope = new ScopesResponse(getString(R.string.scop8x), " 8x Magnification", R.drawable.ic_8x_scope);
            scopesResponses.add(scope);
            scope = new ScopesResponse(getString(R.string.scop6x), "6x Magnification", R.drawable.ic_6x_scope);
            scopesResponses.add(scope);
            scope = new ScopesResponse(getString(R.string.scop4x), "4.21x Magnification", R.drawable.ic_4x_scope);
            scopesResponses.add(scope);
            scope = new ScopesResponse(getString(R.string.scop3x), "3x Magnification", R.drawable.ic_3x_scope);
            scopesResponses.add(scope);
            scope = new ScopesResponse(getString(R.string.scop2x), "2x Magnification", R.drawable.ic_2x_scope);
            scopesResponses.add(scope);
            scope = new ScopesResponse(getString(R.string.reddot), "+20.0% faster aim-down-sight speed", R.drawable.ic_red_dot);
            scopesResponses.add(scope);
            scope = new ScopesResponse(getString(R.string.holographic), "+20.0% faster aim-down-sight speed", R.drawable.ic_holographic);
            scopesResponses.add(scope);
            scopesAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void lowerrailsdata() {
        try {
            lowerRailsAdapter = new LowerRailsAdapter(lowerRailsResponses, new LowerRailsAdapter.AdapterCallback() {
                @Override
                public void subcategoryclick(List<LowerRailsResponse> lowerrailsList, int position) {
                    lowerailsList = lowerrailsList;
                    pos =position;
                    i++;
                    if (i == 4) {
                        lowerrails = true;
                        if (SplashActivity.appConfigModel.IS_GOOGLE.equalsIgnoreCase(Constants.VAL_TRUE)) {
                            googleinterstialadshow();
                        } else {
                            fbinterstialadshow();
                        }

                        i = 0;
                    } else {
                        AttachmentDetailFragment attachmentDetailFragment = new AttachmentDetailFragment();
                        Bundle bundle = new Bundle();
                        //  bundle.putString(Constants.catgegoryname,txt_attechment.toString());
                        bundle.putString(Constants.attechmentname, lowerrailsList.get(position).getLowerrname());
                        bundle.putInt(Constants.attechmentimage, lowerrailsList.get(position).getLowerrimage());

                        attachmentDetailFragment.setArguments(bundle);
                        ((DashBoard) getActivity()).addFragment(attachmentDetailFragment);
                    }


                }
            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            lowerrailsrecycler.setLayoutManager(mLayoutManager);
            lowerrailsrecycler.setItemAnimator(new DefaultItemAnimator());
            lowerrailsrecycler.setAdapter(lowerRailsAdapter);
            preparelowerData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void preparelowerData() {
        try {
            LowerRailsResponse lower = new LowerRailsResponse(getString(R.string.angledforegrip), "Reduces vertical, horizontal recoil and recoil recovery time", R.drawable.ic_angled_foregrip);
            lowerRailsResponses.add(lower);
            lower = new LowerRailsResponse(getString(R.string.halfgrip), "Reduces reload time by 30%", R.drawable.ic_half_grip);
            lowerRailsResponses.add(lower);
            lower = new LowerRailsResponse(getString(R.string.quiver), "Reduces vertical recoil and faster aim-down-singht for M416, SCAR-L, UMP9", R.drawable.ic_quiver_crossbow);
            lowerRailsResponses.add(lower);
            lower = new LowerRailsResponse(getString(R.string.vertical), "Reduces vertical recoil and faster aim-down-singht for M416, SCAR-L, UMP9", R.drawable.ic_vertical_foregrip);
            lowerRailsResponses.add(lower);
            lower = new LowerRailsResponse(getString(R.string.thumbgrip), "Reduces scope opening recoil, reduces vertical recoil, increase horizontal recoil", R.drawable.ic_thumb_grip);
            lowerRailsResponses.add(lower);
            lower = new LowerRailsResponse(getString(R.string.lightgrip), "It reduces recoil recovery time but increase, vertical and horizontal recoil", R.drawable.ic_light_grip);
            lowerRailsResponses.add(lower);
            lowerRailsAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void muzzlesdata() {
        try {
            muzzlesAdapter = new MuzzlesAdapter(muzzlesResponses, new MuzzlesAdapter.AdapterCallback() {
                @Override
                public void subcategoryclick(List<MuzzlesResponse> muzzlesList, int position) {
                    muzzleList=muzzlesList;
                    pos=position;
                    i++;
                    if (i == 4) {
                        muzzle = true;
                        if (SplashActivity.appConfigModel.IS_GOOGLE.equalsIgnoreCase(Constants.VAL_TRUE)) {
                            googleinterstialadshow();
                        } else {
                            fbinterstialadshow();
                        }

                        i = 0;
                    } else {
                        AttachmentDetailFragment attachmentDetailFragment = new AttachmentDetailFragment();
                        Bundle bundle = new Bundle();
                        //  bundle.putString(Constants.catgegoryname,txt_attechment.toString());
                        bundle.putString(Constants.attechmentname, muzzlesList.get(position).getMuzzlename());
                        bundle.putInt(Constants.attechmentimage, muzzlesList.get(position).getMuzzlesimage());

                        attachmentDetailFragment.setArguments(bundle);
                        ((DashBoard) getActivity()).addFragment(attachmentDetailFragment);
                    }


                }
            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            muzzlesrecycler.setLayoutManager(mLayoutManager);
            muzzlesrecycler.setItemAnimator(new DefaultItemAnimator());
            muzzlesrecycler.setAdapter(muzzlesAdapter);
            preparemuzzlesData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void preparemuzzlesData() {
        try {
            MuzzlesResponse muzzles = new MuzzlesResponse(getString(R.string.chcoke), "Reduces pellet spread by 25%", R.drawable.ic_chcoke);
            muzzlesResponses.add(muzzles);
            muzzles = new MuzzlesResponse(getString(R.string.compensatorar), "Reduces horizontal and vertical recoil", R.drawable.ic_compensator_ar_dmr_s12k);
            muzzlesResponses.add(muzzles);
            muzzles = new MuzzlesResponse(getString(R.string.compensatorsr), "Reduces horizontal and vertical recoil", R.drawable.ic_compansator_dmr_sr);
            muzzlesResponses.add(muzzles);
            muzzles = new MuzzlesResponse(getString(R.string.compensatorsmg), "Reduces horizontal and vertical recoil", R.drawable.ic_compensator_smg);
            muzzlesResponses.add(muzzles);
            muzzles = new MuzzlesResponse(getString(R.string.duckbill), "Reduces horizontal and vertical recoil", R.drawable.ic_duckbill);
            muzzlesResponses.add(muzzles);
            muzzles = new MuzzlesResponse(getString(R.string.flashhiderar), "Eliminates muzzle flash and reduces horizontal and vertical recoil", R.drawable.ic_flashhider_ar_dmr_s12k);
            muzzlesResponses.add(muzzles);
            muzzles = new MuzzlesResponse(getString(R.string.flashhidersr), "Eliminates muzzle flash and reduces horizontal and vertical recoil", R.drawable.ic_flashhider_dmr_sr);
            muzzlesResponses.add(muzzles);
            muzzles = new MuzzlesResponse(getString(R.string.flashhidersmg), "Reduces weapon nois and eliminates muzzle flash", R.drawable.ic_flashhider_smg);
            muzzlesResponses.add(muzzles);
            muzzles = new MuzzlesResponse(getString(R.string.suppressorar), "Reduces weapon nois and eliminates muzzle flash", R.drawable.ic_suppressor_ar_dmr_s12k);
            muzzlesResponses.add(muzzles);
            muzzles = new MuzzlesResponse(getString(R.string.suppressorsr), "Reduces weapon nois and eliminates muzzle flash", R.drawable.ic_suppressor_dmr_sr);
            muzzlesResponses.add(muzzles);
            muzzles = new MuzzlesResponse(getString(R.string.suppressorhandguns), "Reduces weapon nois and eliminates muzzle flash", R.drawable.ic_suppressor_handgunsg);
            muzzlesResponses.add(muzzles);
            muzzles = new MuzzlesResponse(getString(R.string.suppressorsmg), "Reduces horizontal recoil and faster aim-down-singht for M416, SCAR-L, UMP9", R.drawable.ic_suppressor_smg);
            muzzlesResponses.add(muzzles);
            muzzlesAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
