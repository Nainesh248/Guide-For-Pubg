package com.adsum.guideforbattlegroundspubg.fragment;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adsum.guideforbattlegroundspubg.MainActivity;
import com.adsum.guideforbattlegroundspubg.R;
import com.adsum.guideforbattlegroundspubg.SplashActivity;
import com.adsum.guideforbattlegroundspubg.adapter.CombinationAdapter;
import com.adsum.guideforbattlegroundspubg.adapter.LowerRailsAdapter;
import com.adsum.guideforbattlegroundspubg.adapter.LowerRailsDetailAdapter;
import com.adsum.guideforbattlegroundspubg.adapter.MagazinesAdapter;
import com.adsum.guideforbattlegroundspubg.adapter.MagazinesDetailAdapter;
import com.adsum.guideforbattlegroundspubg.adapter.MuzzlesAdapter;
import com.adsum.guideforbattlegroundspubg.adapter.MuzzlesDetailAdapter;
import com.adsum.guideforbattlegroundspubg.adapter.ScopesAdapter;
import com.adsum.guideforbattlegroundspubg.adapter.ScopesDetailAdapter;
import com.adsum.guideforbattlegroundspubg.adapter.StocksAdapter;
import com.adsum.guideforbattlegroundspubg.adapter.StocksDetailAdapter;
import com.adsum.guideforbattlegroundspubg.config.Constants;
import com.adsum.guideforbattlegroundspubg.model.CombinationResponse;
import com.adsum.guideforbattlegroundspubg.model.LowerRailsDetailsResponse;
import com.adsum.guideforbattlegroundspubg.model.LowerRailsResponse;
import com.adsum.guideforbattlegroundspubg.model.MagazinesDetailResponse;
import com.adsum.guideforbattlegroundspubg.model.MagazinesResponse;
import com.adsum.guideforbattlegroundspubg.model.MuzzlesDetailResponse;
import com.adsum.guideforbattlegroundspubg.model.MuzzlesResponse;
import com.adsum.guideforbattlegroundspubg.model.ScopesDetailResponse;
import com.adsum.guideforbattlegroundspubg.model.ScopesResponse;
import com.adsum.guideforbattlegroundspubg.model.StocksDetailResponse;
import com.adsum.guideforbattlegroundspubg.model.StocksResponse;
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

public class DetailFragment extends Fragment {

    View rootView;
    String wpname,wpammos;
    int wpimage;
    TextView weaponname,weaponammos;
    TextView name,damage,bulate,timeb,firinf,impact,magazine,extended,pickup,ready,normal,quick;
    TextView namedetail,damagedetail,bulatedetail,timebdetail,firinfdetail,impactdetail,magazinedetail,extendeddetails,pickupdetail,readydetails,normaldetails,quickdetail;
    ImageView weaponimage;
    ImageView imgammos;
    ProgressBar power,recoil,range,firing;
    TextView perpower,perrecoil,perrange,perfiring;
    TextView txtmuzzles,txtscopes,txtmagazines,txtstocks,txtlowerrails;

    private RecyclerView muzzlesrecycler;
    private MuzzlesDetailAdapter muzzlesDetailAdapter;
    private List<MuzzlesDetailResponse> muzzlesDetailResponses = new ArrayList<>();

    private RecyclerView scopesrecycler;
    private ScopesDetailAdapter scopesDetailAdapter;
    private List<ScopesDetailResponse> scopesDetailResponses = new ArrayList<>();

    private RecyclerView magazinesrecycler;
    private MagazinesDetailAdapter magazinesDetailAdapter;
    private List<MagazinesDetailResponse> magazinesDetailResponses = new ArrayList<>();

    private RecyclerView stocksrecycler;
    private StocksDetailAdapter stocksDetailAdapter;
    private List<StocksDetailResponse> stocksDetailResponses = new ArrayList<>();

    private RecyclerView lowerrailsrecycler;
    private LowerRailsDetailAdapter lowerRailsDetailAdapter;
    private List<LowerRailsDetailsResponse> lowerRailsDetailsResponses = new ArrayList<>();

    private RecyclerView combinationrecycler;
    private CombinationAdapter combinationAdapter;
    private List<CombinationResponse> combinationResponses = new ArrayList<>();

    RelativeLayout soundsrelative,soundone,nosound;
    String google_banner, google_interitial, google_rewarded, google_native;
    String fb_banner, fb_interitial, fb_rewarded, fb_native;
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
        rootView = inflater.inflate(R.layout.activity_detail_fragment, container, false);
        weaponname = (TextView) rootView.findViewById(R.id.txt_weaponame);

        nativeAdLayout = rootView.findViewById(R.id.native_ad_container);
        templateView = rootView.findViewById(R.id.nativeTemplateView);

        google_banner = SplashActivity.appConfigModel.GOOGLE_BANNER_ID;
        google_interitial = SplashActivity.appConfigModel.GOOGLE_INTERITIAL_ID;
        google_rewarded = SplashActivity.appConfigModel.GOOGLE_REWARDED_ID;
        fb_banner = SplashActivity.appConfigModel.FACEBOOK_BANNER_ID;
        fb_interitial = SplashActivity.appConfigModel.FACEBOOK_INTERITIAL_ID;
        fb_rewarded = SplashActivity.appConfigModel.FACEBOOK_REWARDED_ID;
        fb_native = SplashActivity.appConfigModel.FACEBOOK_NATIVEAD_ID;
        google_native = SplashActivity.appConfigModel.GOOGLE_NATIVEAD_ID;

        soundsrelative = (RelativeLayout) rootView.findViewById(R.id.soundall);
        soundone = (RelativeLayout) rootView.findViewById(R.id.soundone);
        nosound = (RelativeLayout) rootView.findViewById(R.id.nosound);

        txtmuzzles = (TextView) rootView.findViewById(R.id.muzzle);
        txtscopes = (TextView) rootView.findViewById(R.id.scopes);
        txtmagazines = (TextView) rootView.findViewById(R.id.magazinetitle);
        txtstocks = (TextView) rootView.findViewById(R.id.stocks);
        txtlowerrails = (TextView) rootView.findViewById(R.id.lowerrails);

        muzzlesrecycler = (RecyclerView) rootView.findViewById(R.id.muzzlerecycler);
        scopesrecycler = (RecyclerView) rootView.findViewById(R.id.scopesrecycler);
        magazinesrecycler = (RecyclerView) rootView.findViewById(R.id.magazinerecycler);
        stocksrecycler = (RecyclerView) rootView.findViewById(R.id.stocksrecycler);
        lowerrailsrecycler = (RecyclerView) rootView.findViewById(R.id.lowerrailsrecycler);
        combinationrecycler = (RecyclerView) rootView.findViewById(R.id.combination_recycler);

        power = (ProgressBar) rootView.findViewById(R.id.power);
        recoil = (ProgressBar) rootView.findViewById(R.id.recoil);
        range = (ProgressBar) rootView.findViewById(R.id.range);
        firing = (ProgressBar) rootView.findViewById(R.id.firing);

        perpower = (TextView) rootView.findViewById(R.id.powernumber);
        perrecoil = (TextView) rootView.findViewById(R.id.recoilnumber);
        perrange = (TextView) rootView.findViewById(R.id.rangenumber);
        perfiring = (TextView) rootView.findViewById(R.id.firingnumber);

        weaponammos = (TextView) rootView.findViewById(R.id.txt_ammos);
        imgammos = (ImageView) rootView.findViewById(R.id.img_ammos);

        namedetail = (TextView) rootView.findViewById(R.id.namedetail);
        damagedetail = (TextView) rootView.findViewById(R.id.damagedetail);
        bulatedetail = (TextView) rootView.findViewById(R.id.bulatedetail);
        timebdetail = (TextView) rootView.findViewById(R.id.timebdetail);
        firinfdetail = (TextView) rootView.findViewById(R.id.firinfdetail);
        impactdetail = (TextView) rootView.findViewById(R.id.impactdetail);
        magazinedetail = (TextView) rootView.findViewById(R.id.magazinedetail);
        extendeddetails = (TextView) rootView.findViewById(R.id.extendeddetails);
        pickupdetail = (TextView) rootView.findViewById(R.id.pickupdetail);
        readydetails = (TextView) rootView.findViewById(R.id.readydetails);
        normaldetails = (TextView) rootView.findViewById(R.id.normaldetails);
        quickdetail = (TextView) rootView.findViewById(R.id.quickdetail);

        name = (TextView) rootView.findViewById(R.id.name);
        damage = (TextView) rootView.findViewById(R.id.damage);
        bulate = (TextView) rootView.findViewById(R.id.bullate);
        timeb = (TextView) rootView.findViewById(R.id.timebetween);
        firinf = (TextView) rootView.findViewById(R.id.firingmode);
        impact = (TextView) rootView.findViewById(R.id.impact);
        magazine = (TextView) rootView.findViewById(R.id.magazine);
        extended = (TextView) rootView.findViewById(R.id.extended);
        pickup = (TextView) rootView.findViewById(R.id.pickup);
        ready = (TextView) rootView.findViewById(R.id.ready);
        normal= (TextView) rootView.findViewById(R.id.normal);
        quick = (TextView) rootView.findViewById(R.id.quick);

        weaponimage = (ImageView) rootView.findViewById(R.id.gunimage);
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
            if (getArguments() != null && getArguments().containsKey(Constants.weaponname)) {
                wpname = getArguments().getString(Constants.weaponname);
                wpimage = getArguments().getInt(Constants.weaponimage);
                weaponname.setText(wpname);
                weaponimage.setImageResource(wpimage);
                bestcombinationdata();
                magazinesdata();
                muzzlesdata();
                scopedata();
                lowerrailsdata();
                stocksdata();
                weapondata();
            }
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

    private void weapondata() {
        try {
            if(wpname.equalsIgnoreCase("AWM")){

                CombinationResponse combination= new CombinationResponse(getString(R.string.scop6x),"",R.drawable.ic_6x_scope);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.suppressorar),"",R.drawable.ic_suppressor_ar_dmr_s12k);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.exquickdrawmagsr),"",R.drawable.ic_extended_quickdraw_mag_dmr_sr);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.cheekpad),"",R.drawable.ic_cheak_pad);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MagazinesDetailResponse magazines= new MagazinesDetailResponse(getString(R.string.exquickmagdsr),"",R.drawable.ic_extended_mag_dmr_sr);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.quickdrawmagsr),"",R.drawable.ic_quickdeaw_mag_dmr_sr);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.exquickdrawmagsr),"",R.drawable.ic_extended_quickdraw_mag_dmr_sr);
                magazinesDetailResponses.add(magazines);
                magazinesDetailAdapter.notifyDataSetChanged();


                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.compensatorar), "", R.drawable.ic_compensator_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.suppressorar), "", R.drawable.ic_suppressor_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.flashhidersr), "", R.drawable.ic_flashhider_dmr_sr);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();



                ScopesDetailResponse scope= new ScopesDetailResponse(getString(R.string.scop8x), "", R.drawable.ic_8x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop6x), "",R.drawable.ic_6x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop4x), "",R.drawable.ic_4x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop3x), "",R.drawable.ic_3x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop2x), "",R.drawable.ic_2x_scope );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.reddot), "",R.drawable.ic_red_dot );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.holographic), "",R.drawable.ic_holographic);
                scopesDetailResponses.add(scope);
                scopesDetailAdapter.notifyDataSetChanged();

                StocksDetailResponse stocks= new StocksDetailResponse(getString(R.string.cheekpad), "", R.drawable.ic_cheak_pad);
                stocksDetailResponses.add(stocks);
                stocksDetailAdapter.notifyDataSetChanged();

                txtlowerrails.setText("No lower rails");

                soundsrelative.setVisibility(View.VISIBLE);
                power.setProgress(100);
                perpower.setText("100%");
                recoil.setProgress(35);
                perrecoil.setText("35%");
                range.setProgress(100);
                perrange.setText("100%");
                firing.setProgress(4);
                perfiring.setText("4%");
                weaponammos.setText("300 Magnum");
                imgammos.setImageResource(R.drawable.ic_300_magnum);
                namedetail.setText("AWM");
                damagedetail.setText("132");
                bulatedetail.setText("945 m/s");
                timebdetail.setText("1.85S");
                firinfdetail.setText("Single");
                impactdetail.setText("4000");
                magazinedetail.setText("5 rounds");
                extendeddetails.setText("7 rounds");
                pickupdetail.setText("150 ms");
                readydetails.setText("500 ms");
                normaldetails.setText("4.2 second");
                quickdetail.setText("2.3 second");
            }
            else if(wpname.equalsIgnoreCase("M24")){

                MagazinesDetailResponse magazines= new MagazinesDetailResponse(getString(R.string.exquickmagdsr),"",R.drawable.ic_extended_mag_dmr_sr);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.quickdrawmagsr),"",R.drawable.ic_quickdeaw_mag_dmr_sr);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.exquickdrawmagsr),"",R.drawable.ic_extended_quickdraw_mag_dmr_sr);
                magazinesDetailResponses.add(magazines);
                magazinesDetailAdapter.notifyDataSetChanged();

                CombinationResponse combination= new CombinationResponse(getString(R.string.suppressorsmg),"",R.drawable.ic_suppressor_smg);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.chcoke),"",R.drawable.ic_chcoke);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.exquickdrawmagsr),"",R.drawable.ic_extended_quickdraw_mag_dmr_sr);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.cheekpad),"",R.drawable.ic_cheak_pad);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.compensatorar), "", R.drawable.ic_compensator_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.suppressorar), "", R.drawable.ic_suppressor_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.flashhidersr), "", R.drawable.ic_flashhider_dmr_sr);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();


                ScopesDetailResponse scope= new ScopesDetailResponse(getString(R.string.scop8x), "", R.drawable.ic_8x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop6x), "",R.drawable.ic_6x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop4x), "",R.drawable.ic_4x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop3x), "",R.drawable.ic_3x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop2x), "",R.drawable.ic_2x_scope );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.reddot), "",R.drawable.ic_red_dot );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.holographic), "",R.drawable.ic_holographic);
                scopesDetailResponses.add(scope);
                scopesDetailAdapter.notifyDataSetChanged();


                StocksDetailResponse stocks= new StocksDetailResponse(getString(R.string.cheekpad), "", R.drawable.ic_cheak_pad);
                stocksDetailResponses.add(stocks);
                stocksDetailAdapter.notifyDataSetChanged();

                txtlowerrails.setText("No lower rails");

                soundsrelative.setVisibility(View.VISIBLE);
                power.setProgress(80);
                perpower.setText("80%");
                recoil.setProgress(33);
                perrecoil.setText("33%");
                range.setProgress(96);
                perrange.setText("96%");
                firing.setProgress(5);
                perfiring.setText("5%");
                weaponammos.setText("7.62 mm");
                imgammos.setImageResource(R.drawable.ic_7_62_mm);
                namedetail.setText("M24");
                damagedetail.setText("72");
                bulatedetail.setText("790 m/s");
                timebdetail.setText("1.8S");
                firinfdetail.setText("Single");
                impactdetail.setText("2000");
                magazinedetail.setText("5 rounds");
                extendeddetails.setText("7 rounds");
                pickupdetail.setText("150 ms");
                readydetails.setText("500 ms");
                normaldetails.setText("4.3 second");
                quickdetail.setText("1.8 second");
            }
            else if(wpname.equalsIgnoreCase("Kar98k")){

                CombinationResponse combination= new CombinationResponse(getString(R.string.scop8x),"",R.drawable.ic_8x_scope);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.suppressorar),"",R.drawable.ic_suppressor_ar_dmr_s12k);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.cheekpad),"",R.drawable.ic_cheak_pad);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.compensatorar), "", R.drawable.ic_compensator_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.suppressorar), "", R.drawable.ic_suppressor_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.flashhidersr), "", R.drawable.ic_flashhider_dmr_sr);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();

                ScopesDetailResponse scope= new ScopesDetailResponse(getString(R.string.scop8x), "", R.drawable.ic_8x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop6x), "",R.drawable.ic_6x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop4x), "",R.drawable.ic_4x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop3x), "",R.drawable.ic_3x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop2x), "",R.drawable.ic_2x_scope );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.reddot), "",R.drawable.ic_red_dot );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.holographic), "",R.drawable.ic_holographic);
                scopesDetailResponses.add(scope);
                scopesDetailAdapter.notifyDataSetChanged();

                StocksDetailResponse stocks= new StocksDetailResponse(getString(R.string.cheekpad), "", R.drawable.ic_cheak_pad);
                stocksDetailResponses.add(stocks);
                stocks= new StocksDetailResponse(getString(R.string.bulletsnipers), "", R.drawable.ic_bullet_sniper);
                stocksDetailResponses.add(stocks);
                stocksDetailAdapter.notifyDataSetChanged();

                txtlowerrails.setText("No lower rails");
                txtmagazines.setText("No magazines");

                soundsrelative.setVisibility(View.VISIBLE);
                power.setProgress(69);
                perpower.setText("69%");
                recoil.setProgress(30);
                perrecoil.setText("30%");
                range.setProgress(80);
                perrange.setText("80%");
                firing.setProgress(4);
                perfiring.setText("4%");
                weaponammos.setText("7.62 mm");
                imgammos.setImageResource(R.drawable.ic_7_62_mm);
                namedetail.setText("Kar98k");
                damagedetail.setText("72");
                bulatedetail.setText("760 m/s");
                timebdetail.setText("1.9S");
                firinfdetail.setText("Single");
                impactdetail.setText("16000");
                magazinedetail.setText("5 rounds");
                extendeddetails.setVisibility(View.GONE);
                extended.setVisibility(View.GONE);
                pickupdetail.setText("150 ms");
                readydetails.setText("500 ms");
                normaldetails.setText("4 second");
                quickdetail.setText("1.69 second");
            }
            else if(wpname.equalsIgnoreCase("Win94")){

                CombinationResponse combination= new CombinationResponse(getString(R.string.bulletsnipers),"",R.drawable.ic_bullet_sniper);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                StocksDetailResponse  stocks= new StocksDetailResponse(getString(R.string.bulletsnipers), "", R.drawable.ic_bullet_sniper);
                stocksDetailResponses.add(stocks);
                stocksDetailAdapter.notifyDataSetChanged();

                txtmuzzles.setText(" No muzzles");
                txtscopes.setText("No scopes");
                txtmagazines.setText("No magazines");
                txtlowerrails.setText("No lower rails");

                soundone.setVisibility(View.VISIBLE);
                power.setProgress(60);
                perpower.setText("60%");
                recoil.setProgress(56);
                perrecoil.setText("56%");
                range.setProgress(80);
                perrange.setText("80%");
                firing.setProgress(11);
                perfiring.setText("11%");
                weaponammos.setText(".45 ACP");
                imgammos.setImageResource(R.drawable.ic_45_acp);
                namedetail.setText("Win94");
                damagedetail.setText("66");
                bulatedetail.setText("760 m/s");
                timebdetail.setVisibility(View.GONE);
                timeb.setVisibility(View.GONE);
                firinfdetail.setText("Single");
                impactdetail.setText("16000");
                magazinedetail.setText("8 rounds");
                extendeddetails.setVisibility(View.GONE);
                extended.setVisibility(View.GONE);
                pickupdetail.setVisibility(View.GONE);
                pickup.setVisibility(View.GONE);
                readydetails.setVisibility(View.GONE);
                ready.setVisibility(View.GONE);
                normaldetails.setVisibility(View.GONE);
                normal.setVisibility(View.GONE);
                quickdetail.setVisibility(View.GONE);
                quick.setVisibility(View.GONE);
            }
            else if(wpname.equalsIgnoreCase("MK14")){

                CombinationResponse combination= new CombinationResponse(getString(R.string.scop8x),"",R.drawable.ic_8x_scope);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.compensatorar),"",R.drawable.ic_compensator_ar_dmr_s12k);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.exquickdrawmagsr),"",R.drawable.ic_extended_quickdraw_mag_dmr_sr);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.cheekpad),"",R.drawable.ic_cheak_pad);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MagazinesDetailResponse magazines= new MagazinesDetailResponse(getString(R.string.exquickmagdsr),"",R.drawable.ic_extended_mag_dmr_sr);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.quickdrawmagsr),"",R.drawable.ic_quickdeaw_mag_dmr_sr);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.exquickdrawmagsr),"",R.drawable.ic_extended_quickdraw_mag_dmr_sr);
                magazinesDetailResponses.add(magazines);
                magazinesDetailAdapter.notifyDataSetChanged();


                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.compensatorar), "", R.drawable.ic_compensator_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.suppressorar), "", R.drawable.ic_suppressor_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.flashhidersr), "", R.drawable.ic_flashhider_dmr_sr);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();



                ScopesDetailResponse scope= new ScopesDetailResponse(getString(R.string.scop8x), "", R.drawable.ic_8x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop6x), "",R.drawable.ic_6x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop4x), "",R.drawable.ic_4x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop3x), "",R.drawable.ic_3x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop2x), "",R.drawable.ic_2x_scope );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.reddot), "",R.drawable.ic_red_dot );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.holographic), "",R.drawable.ic_holographic);
                scopesDetailResponses.add(scope);
                scopesDetailAdapter.notifyDataSetChanged();

                StocksDetailResponse stocks= new StocksDetailResponse(getString(R.string.cheekpad), "", R.drawable.ic_cheak_pad);
                stocksDetailResponses.add(stocks);
                stocksDetailAdapter.notifyDataSetChanged();

                txtlowerrails.setText("No lower rails");

                soundsrelative.setVisibility(View.VISIBLE);
                power.setProgress(55);
                perpower.setText("55%");
                recoil.setProgress(56);
                perrecoil.setText("56%");
                range.setProgress(80);
                perrange.setText("80%");
                firing.setProgress(26);
                perfiring.setText("26%");
                weaponammos.setText("7.62 mm");
                imgammos.setImageResource(R.drawable.ic_7_62_mm);
                namedetail.setText("MK14");
                damagedetail.setText("61");
                bulatedetail.setText("853 m/s");
                timebdetail.setText("0.09S");
                firinfdetail.setText("Semi-Auto,Full-Auto");
                impactdetail.setText("20000");
                magazinedetail.setText("10 rounds");
                extendeddetails.setText("20 rounds");
                pickupdetail.setText("150 ms");
                readydetails.setText("400 ms");
                normaldetails.setText("3.683 second");
                quickdetail.setText("2.783 second");
            }
            else if(wpname.equalsIgnoreCase("SLR")){

                CombinationResponse combination= new CombinationResponse(getString(R.string.suppressorsmg),"",R.drawable.ic_suppressor_smg);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.compensatorar),"",R.drawable.ic_compensator_ar_dmr_s12k);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.exquickdrawmagsr),"",R.drawable.ic_extended_quickdraw_mag_dmr_sr);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.cheekpad),"",R.drawable.ic_cheak_pad);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MagazinesDetailResponse magazines= new MagazinesDetailResponse(getString(R.string.exquickmagdsr),"",R.drawable.ic_extended_mag_dmr_sr);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.quickdrawmagsr),"",R.drawable.ic_quickdeaw_mag_dmr_sr);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.exquickdrawmagsr),"",R.drawable.ic_extended_quickdraw_mag_dmr_sr);
                magazinesDetailResponses.add(magazines);
                magazinesDetailAdapter.notifyDataSetChanged();


                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.compensatorar), "", R.drawable.ic_compensator_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.suppressorar), "", R.drawable.ic_suppressor_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.flashhidersr), "", R.drawable.ic_flashhider_dmr_sr);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();



                ScopesDetailResponse scope= new ScopesDetailResponse(getString(R.string.scop8x), "", R.drawable.ic_8x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop6x), "",R.drawable.ic_6x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop4x), "",R.drawable.ic_4x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop3x), "",R.drawable.ic_3x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop2x), "",R.drawable.ic_2x_scope );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.reddot), "",R.drawable.ic_red_dot );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.holographic), "",R.drawable.ic_holographic);
                scopesDetailResponses.add(scope);
                scopesDetailAdapter.notifyDataSetChanged();

                StocksDetailResponse stocks= new StocksDetailResponse(getString(R.string.cheekpad), "", R.drawable.ic_cheak_pad);
                stocksDetailResponses.add(stocks);
                stocksDetailAdapter.notifyDataSetChanged();

                txtlowerrails.setText("No lower rails");

                soundsrelative.setVisibility(View.VISIBLE);
                power.setProgress(54);
                perpower.setText("54%");
                recoil.setProgress(49);
                perrecoil.setText("49%");
                range.setProgress(60);
                perrange.setText("60%");
                firing.setProgress(25);
                perfiring.setText("25%");
                weaponammos.setText("7.62 mm");
                imgammos.setImageResource(R.drawable.ic_7_62_mm);
                namedetail.setText("SLR");
                damagedetail.setText("58");
                bulatedetail.setText("840 m/s");
                timebdetail.setText("0.10S");
                firinfdetail.setText("Semi-Auto");
                impactdetail.setText("20000");
                magazinedetail.setText("10 rounds");
                extendeddetails.setText("20 rounds");
                pickupdetail.setText("150 ms");
                readydetails.setText("400 ms");
                normaldetails.setText("3.683 second");
                quickdetail.setText("2.783 second");
            }
            else if(wpname.equalsIgnoreCase("SKS")){

                CombinationResponse combination= new CombinationResponse(getString(R.string.suppressorsmg),"",R.drawable.ic_suppressor_smg);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.compensatorar),"",R.drawable.ic_compensator_ar_dmr_s12k);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.exquickdrawmagsr),"",R.drawable.ic_extended_quickdraw_mag_dmr_sr);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.cheekpad),"",R.drawable.ic_cheak_pad);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.vertical),"",R.drawable.ic_vertical_foregrip);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MagazinesDetailResponse magazines= new MagazinesDetailResponse(getString(R.string.exquickmagdsr),"",R.drawable.ic_extended_mag_dmr_sr);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.quickdrawmagsr),"",R.drawable.ic_quickdeaw_mag_dmr_sr);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.exquickdrawmagsr),"",R.drawable.ic_extended_quickdraw_mag_dmr_sr);
                magazinesDetailResponses.add(magazines);
                magazinesDetailAdapter.notifyDataSetChanged();


                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.compensatorar), "", R.drawable.ic_compensator_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.suppressorar), "", R.drawable.ic_suppressor_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.flashhidersr), "", R.drawable.ic_flashhider_dmr_sr);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();



                ScopesDetailResponse scope= new ScopesDetailResponse(getString(R.string.scop8x), "", R.drawable.ic_8x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop6x), "",R.drawable.ic_6x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop4x), "",R.drawable.ic_4x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop3x), "",R.drawable.ic_3x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop2x), "",R.drawable.ic_2x_scope );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.reddot), "",R.drawable.ic_red_dot );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.holographic), "",R.drawable.ic_holographic);
                scopesDetailResponses.add(scope);
                scopesDetailAdapter.notifyDataSetChanged();

                StocksDetailResponse stocks= new StocksDetailResponse(getString(R.string.cheekpad), "", R.drawable.ic_cheak_pad);
                stocksDetailResponses.add(stocks);
                stocksDetailAdapter.notifyDataSetChanged();

                LowerRailsDetailsResponse lower= new LowerRailsDetailsResponse(getString(R.string.angledforegrip), "", R.drawable.ic_angled_foregrip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.lightgrip), "", R.drawable.ic_light_grip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.thumbgrip), "", R.drawable.ic_thumb_grip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.vertical), "", R.drawable.ic_vertical_foregrip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.halfgrip), "", R.drawable.ic_half_grip);
                lowerRailsDetailsResponses.add(lower);
                lowerRailsDetailAdapter.notifyDataSetChanged();

                soundsrelative.setVisibility(View.VISIBLE);
                power.setProgress(49);
                perpower.setText("49%");
                recoil.setProgress(47);
                perrecoil.setText("47%");
                range.setProgress(60);
                perrange.setText("60%");
                firing.setProgress(31);
                perfiring.setText("31%");
                weaponammos.setText("7.62 mm");
                imgammos.setImageResource(R.drawable.ic_7_62_mm);
                namedetail.setText("SKS");
                damagedetail.setText("53");
                bulatedetail.setText("800 m/s");
                timebdetail.setText("0.09S");
                firinfdetail.setText("Semi-Auto");
                impactdetail.setText("20000");
                magazinedetail.setText("10 rounds");
                extendeddetails.setText("20 rounds");
                pickupdetail.setText("150 ms");
                readydetails.setText("400 ms");
                normaldetails.setText("4.550 second");
                quickdetail.setText("3.1 second");
            }
            else if(wpname.equalsIgnoreCase("QBU")){

                CombinationResponse combination= new CombinationResponse(getString(R.string.scop8x),"",R.drawable.ic_8x_scope);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.compensatorar),"",R.drawable.ic_compensator_ar_dmr_s12k);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.exquickdrawmagsr),"",R.drawable.ic_extended_quickdraw_mag_dmr_sr);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MagazinesDetailResponse magazines= new MagazinesDetailResponse(getString(R.string.exquickmagdsr),"",R.drawable.ic_extended_mag_dmr_sr);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.quickdrawmagsr),"",R.drawable.ic_quickdeaw_mag_dmr_sr);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.exquickdrawmagsr),"",R.drawable.ic_extended_quickdraw_mag_dmr_sr);
                magazinesDetailResponses.add(magazines);
                magazinesDetailAdapter.notifyDataSetChanged();


                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.compensatorar), "", R.drawable.ic_compensator_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.suppressorar), "", R.drawable.ic_suppressor_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.flashhidersr), "", R.drawable.ic_flashhider_dmr_sr);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();



                ScopesDetailResponse scope= new ScopesDetailResponse(getString(R.string.scop8x), "", R.drawable.ic_8x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop6x), "",R.drawable.ic_6x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop4x), "",R.drawable.ic_4x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop3x), "",R.drawable.ic_3x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop2x), "",R.drawable.ic_2x_scope );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.reddot), "",R.drawable.ic_red_dot );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.holographic), "",R.drawable.ic_holographic);
                scopesDetailResponses.add(scope);
                scopesDetailAdapter.notifyDataSetChanged();

                txtlowerrails.setText("No lower rails");
                txtstocks.setText("No stocks");

                soundsrelative.setVisibility(View.VISIBLE);
                power.setProgress(59);
                perpower.setText("59%");
                recoil.setProgress(49);
                perrecoil.setText("49%");
                range.setProgress(72);
                perrange.setText("72%");
                firing.setProgress(56);
                perfiring.setText("56%");
                weaponammos.setText("5.56 mm");
                imgammos.setImageResource(R.drawable.ic_5_56_mm);
                namedetail.setText("QBU");
                damagedetail.setText("48");
                bulatedetail.setText("954 m/s");
                timebdetail.setText("0.09S");
                firinfdetail.setText("Semi-Auto");
                impactdetail.setText("1000");
                magazinedetail.setText("10 rounds");
                extendeddetails.setText("20 rounds");
                pickupdetail.setText("150 ms");
                readydetails.setText("400 ms");
                normaldetails.setText("3 second");
                quickdetail.setText("2 second");
            }
            else if(wpname.equalsIgnoreCase("Mini14")){

                CombinationResponse combination= new CombinationResponse(getString(R.string.scop8x),"",R.drawable.ic_8x_scope);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.compensatorar),"",R.drawable.ic_compensator_ar_dmr_s12k);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.exquickdrawmagsr),"",R.drawable.ic_extended_quickdraw_mag_dmr_sr);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MagazinesDetailResponse magazines= new MagazinesDetailResponse(getString(R.string.exquickmagdsr),"",R.drawable.ic_extended_mag_dmr_sr);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.quickdrawmagsr),"",R.drawable.ic_quickdeaw_mag_dmr_sr);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.exquickdrawmagsr),"",R.drawable.ic_extended_quickdraw_mag_dmr_sr);
                magazinesDetailResponses.add(magazines);
                magazinesDetailAdapter.notifyDataSetChanged();


                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.compensatorar), "", R.drawable.ic_compensator_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.suppressorar), "", R.drawable.ic_suppressor_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.flashhidersr), "", R.drawable.ic_flashhider_dmr_sr);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();



                ScopesDetailResponse scope= new ScopesDetailResponse(getString(R.string.scop8x), "", R.drawable.ic_8x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop6x), "",R.drawable.ic_6x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop4x), "",R.drawable.ic_4x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop3x), "",R.drawable.ic_3x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop2x), "",R.drawable.ic_2x_scope );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.reddot), "",R.drawable.ic_red_dot );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.holographic), "",R.drawable.ic_holographic);
                scopesDetailResponses.add(scope);
                scopesDetailAdapter.notifyDataSetChanged();

                txtlowerrails.setText("No lower rails");
                txtstocks.setText("No stocks");

                soundsrelative.setVisibility(View.VISIBLE);
                power.setProgress(41);
                perpower.setText("41%");
                recoil.setProgress(35);
                perrecoil.setText("35%");
                range.setProgress(64);
                perrange.setText("64%");
                firing.setProgress(48);
                perfiring.setText("48%");
                weaponammos.setText("5.56 mm");
                imgammos.setImageResource(R.drawable.ic_5_56_mm);
                namedetail.setText("Mini14");
                damagedetail.setText("46");
                bulatedetail.setText("990 m/s");
                timebdetail.setText("0.133S");
                firinfdetail.setText("Semi-Auto");
                impactdetail.setText("1000");
                magazinedetail.setText("20 rounds");
                extendeddetails.setText("30 rounds");
                pickupdetail.setText("150 ms");
                readydetails.setText("500 ms");
                normaldetails.setText("3.6 second");
                quickdetail.setText("2.7 second");
            }
            else if(wpname.equalsIgnoreCase("VSS")){

                CombinationResponse
                        combination= new CombinationResponse(getString(R.string.exquickdrawmagsr),"",R.drawable.ic_extended_quickdraw_mag_dmr_sr);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.cheekpad),"",R.drawable.ic_cheak_pad);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MagazinesDetailResponse magazines= new MagazinesDetailResponse(getString(R.string.exquickmagdsr),"",R.drawable.ic_extended_mag_dmr_sr);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.quickdrawmagsr),"",R.drawable.ic_quickdeaw_mag_dmr_sr);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.exquickdrawmagsr),"",R.drawable.ic_extended_quickdraw_mag_dmr_sr);
                magazinesDetailResponses.add(magazines);
                magazinesDetailAdapter.notifyDataSetChanged();

                txtmuzzles.setText(" No muzzles");
                txtscopes.setText("No scopes");
                txtlowerrails.setText("No lower rails");

                StocksDetailResponse stocks= new StocksDetailResponse(getString(R.string.cheekpad), "", R.drawable.ic_cheak_pad);
                stocksDetailResponses.add(stocks);
                stocksDetailAdapter.notifyDataSetChanged();

                soundone.setVisibility(View.VISIBLE);
                power.setProgress(32);
                perpower.setText("32%");
                recoil.setProgress(25);
                perrecoil.setText("25%");
                range.setProgress(37);
                perrange.setText("37%");
                firing.setProgress(22);
                perfiring.setText("22%");
                weaponammos.setText("9 mm");
                imgammos.setImageResource(R.drawable.ic_9_mm);
                namedetail.setText("VSS");
                damagedetail.setText("41");
                bulatedetail.setText("330 m/s");
                timebdetail.setText("0.08S");
                firinfdetail.setText("Single,Auto");
                impactdetail.setText("2000");
                magazinedetail.setText("10 rounds");
                extendeddetails.setText("20 rounds");
                pickupdetail.setText("150 ms");
                readydetails.setText("400 ms");
                normaldetails.setText("2.9 second");
                quickdetail.setText("2.1 second");
            }
            else if(wpname.equalsIgnoreCase("M249")){

                CombinationResponse combination= new CombinationResponse(getString(R.string.scop4x),"",R.drawable.ic_4x_scope);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                ScopesDetailResponse
                scope = new ScopesDetailResponse(getString(R.string.scop6x), "",R.drawable.ic_6x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop4x), "",R.drawable.ic_4x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop3x), "",R.drawable.ic_3x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop2x), "",R.drawable.ic_2x_scope );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.reddot), "",R.drawable.ic_red_dot );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.holographic), "",R.drawable.ic_holographic);
                scopesDetailResponses.add(scope);
                scopesDetailAdapter.notifyDataSetChanged();

                txtmagazines.setText("No magazines");
                txtmuzzles.setText(" No muzzles");
                txtlowerrails.setText("No lower rails");
                txtstocks.setText("No stocks");

                soundone.setVisibility(View.VISIBLE);
                power.setProgress(40);
                perpower.setText("40%");
                recoil.setProgress(36);
                perrecoil.setText("36%");
                range.setProgress(71);
                perrange.setText("71%");
                firing.setProgress(76);
                perfiring.setText("76%");
                weaponammos.setText("5.56 mm");
                imgammos.setImageResource(R.drawable.ic_5_56_mm);
                namedetail.setText("M249");
                damagedetail.setText("45");
                bulatedetail.setText("915 m/s");
                timebdetail.setText("0.07S");
                firinfdetail.setText("Auto");
                impactdetail.setText("10000");
                magazinedetail.setText("100 rounds");
                extendeddetails.setVisibility(View.GONE);
                extended.setVisibility(View.GONE);
                pickupdetail.setText("150 ms");
                readydetails.setText("500 ms");
                normaldetails.setText("7.1 second");
                quickdetail.setVisibility(View.GONE);
                quick.setVisibility(View.GONE);
            }
            else if(wpname.equalsIgnoreCase("DP-28")){

                CombinationResponse combination= new CombinationResponse(getString(R.string.scop6x),"",R.drawable.ic_6x_scope);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                ScopesDetailResponse
                scope = new ScopesDetailResponse(getString(R.string.scop6x), "",R.drawable.ic_6x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop4x), "",R.drawable.ic_4x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop3x), "",R.drawable.ic_3x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop2x), "",R.drawable.ic_2x_scope );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.reddot), "",R.drawable.ic_red_dot );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.holographic), "",R.drawable.ic_holographic);
                scopesDetailResponses.add(scope);
                scopesDetailAdapter.notifyDataSetChanged();

                txtmagazines.setText("No magazines");
                txtmuzzles.setText(" No muzzles");
                txtlowerrails.setText("No lower rails");
                txtstocks.setText("No stocks");

                soundone.setVisibility(View.VISIBLE);
                power.setProgress(45);
                perpower.setText("45%");
                recoil.setProgress(30);
                perrecoil.setText("30%");
                range.setProgress(35);
                perrange.setText("35%");
                firing.setProgress(43);
                perfiring.setText("43%");
                weaponammos.setText("7.62 mm");
                imgammos.setImageResource(R.drawable.ic_7_62_mm);
                namedetail.setText("DP-28");
                damagedetail.setText("51");
                bulatedetail.setText("715 m/s");
                timebdetail.setText("0.19S");
                firinfdetail.setText("Auto");
                impactdetail.setText("1000");
                magazinedetail.setText("47 rounds");
                extendeddetails.setVisibility(View.GONE);
                extended.setVisibility(View.GONE);
                pickupdetail.setText("150 ms");
                readydetails.setText("400 ms");
                normaldetails.setText("4.5 second");
                quickdetail.setVisibility(View.GONE);
                quick.setVisibility(View.GONE);
            }
            else if(wpname.equalsIgnoreCase("Groza")){

                CombinationResponse combination= new CombinationResponse(getString(R.string.scop4x),"",R.drawable.ic_4x_scope);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.compensatorar),"",R.drawable.ic_compensator_ar_dmr_s12k);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.exquickdrawmagar),"",R.drawable.ic_extended_mag_ar_smr_s12k);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MagazinesDetailResponse magazines= new MagazinesDetailResponse(getString(R.string.exquickmagddmr),"",R.drawable.ic_extended_mag_sr_dmr_s12k);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.quickdrawmagar),"",R.drawable.ic_quickdraw_mag_ar_smr_s12k);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.exquickdrawmagar),"",R.drawable.ic_extended_mag_ar_smr_s12k);
                magazinesDetailResponses.add(magazines);
                magazinesDetailAdapter.notifyDataSetChanged();


                MuzzlesDetailResponse
                muzzles= new MuzzlesDetailResponse(getString(R.string.flashhidersmg), "", R.drawable.ic_flashhider_smg);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();

                ScopesDetailResponse
                        scope = new ScopesDetailResponse(getString(R.string.scop6x), "",R.drawable.ic_6x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop4x), "",R.drawable.ic_4x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop3x), "",R.drawable.ic_3x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop2x), "",R.drawable.ic_2x_scope );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.reddot), "",R.drawable.ic_red_dot );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.holographic), "",R.drawable.ic_holographic);
                scopesDetailResponses.add(scope);
                scopesDetailAdapter.notifyDataSetChanged();

                txtlowerrails.setText("No lower rails");
                txtstocks.setText("No stocks");

                soundsrelative.setVisibility(View.VISIBLE);
                power.setProgress(40);
                perpower.setText("40%");
                recoil.setProgress(32);
                perrecoil.setText("32%");
                range.setProgress(60);
                perrange.setText("60%");
                firing.setProgress(72);
                perfiring.setText("72%");
                weaponammos.setText("7.62 mm");
                imgammos.setImageResource(R.drawable.ic_7_62_mm);
                namedetail.setText("Groza");
                damagedetail.setText("49");
                bulatedetail.setText("715 m/s");
                timebdetail.setText("0.080S");
                firinfdetail.setText("Single,Auto");
                impactdetail.setText("1000");
                magazinedetail.setText("30 rounds");
                extendeddetails.setText("40 rounds");
                pickupdetail.setText("150 ms");
                readydetails.setText("400 ms");
                normaldetails.setText("3 second");
                quickdetail.setText("2.25 second");
            }
            else if(wpname.equalsIgnoreCase("AKM")){

                CombinationResponse combination= new CombinationResponse(getString(R.string.scop2x),"",R.drawable.ic_2x_scope);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.compensatorar),"",R.drawable.ic_compensator_ar_dmr_s12k);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.exquickdrawmagar),"",R.drawable.ic_extended_mag_ar_smr_s12k);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MagazinesDetailResponse magazines= new MagazinesDetailResponse(getString(R.string.exquickmagddmr),"",R.drawable.ic_extended_mag_sr_dmr_s12k);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.quickdrawmagar),"",R.drawable.ic_quickdraw_mag_ar_smr_s12k);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.exquickdrawmagar),"",R.drawable.ic_extended_mag_ar_smr_s12k);
                magazinesDetailResponses.add(magazines);
                magazinesDetailAdapter.notifyDataSetChanged();

                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.compensatorar), "", R.drawable.ic_compensator_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.flashhidersmg), "", R.drawable.ic_flashhider_smg);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.flashhiderar), "", R.drawable.ic_flashhider_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();

                ScopesDetailResponse
                        scope = new ScopesDetailResponse(getString(R.string.scop6x), "",R.drawable.ic_6x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop4x), "",R.drawable.ic_4x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop3x), "",R.drawable.ic_3x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop2x), "",R.drawable.ic_2x_scope );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.reddot), "",R.drawable.ic_red_dot );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.holographic), "",R.drawable.ic_holographic);
                scopesDetailResponses.add(scope);
                scopesDetailAdapter.notifyDataSetChanged();

                txtlowerrails.setText("No lower rails");
                txtstocks.setText("No stocks");

                soundsrelative.setVisibility(View.VISIBLE);
                power.setProgress(40);
                perpower.setText("40%");
                recoil.setProgress(34);
                perrecoil.setText("34%");
                range.setProgress(60);
                perrange.setText("60%");
                firing.setProgress(61);
                perfiring.setText("61%");
                weaponammos.setText("7.62 mm");
                imgammos.setImageResource(R.drawable.ic_7_62_mm);
                namedetail.setText("AKM");
                damagedetail.setText("49");
                bulatedetail.setText("715 m/s");
                timebdetail.setText("0.01S");
                firinfdetail.setText("Single,Auto");
                impactdetail.setText("1000");
                magazinedetail.setText("30 rounds");
                extendeddetails.setText("40 rounds");
                pickupdetail.setText("150 ms");
                readydetails.setText("400 ms");
                normaldetails.setText("2.9 second");
                quickdetail.setText("2.25 second");
            }
            else if(wpname.equalsIgnoreCase("Mk47 Mutant")){

                CombinationResponse
                        combination= new CombinationResponse(getString(R.string.scop4x),"",R.drawable.ic_4x_scope);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.compensatorar),"",R.drawable.ic_compensator_ar_dmr_s12k);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.exquickdrawmagar),"",R.drawable.ic_extended_mag_ar_smr_s12k);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.halfgrip),"",R.drawable.ic_half_grip);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MagazinesDetailResponse magazines= new MagazinesDetailResponse(getString(R.string.exquickmagddmr),"",R.drawable.ic_extended_mag_sr_dmr_s12k);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.quickdrawmagar),"",R.drawable.ic_quickdraw_mag_ar_smr_s12k);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.exquickdrawmagar),"",R.drawable.ic_extended_mag_ar_smr_s12k);
                magazinesDetailResponses.add(magazines);
                magazinesDetailAdapter.notifyDataSetChanged();

                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.compensatorar), "", R.drawable.ic_compensator_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.flashhidersmg), "", R.drawable.ic_flashhider_smg);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();

                ScopesDetailResponse
                        scope = new ScopesDetailResponse(getString(R.string.scop6x), "",R.drawable.ic_6x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop4x), "",R.drawable.ic_4x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop3x), "",R.drawable.ic_3x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop2x), "",R.drawable.ic_2x_scope );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.reddot), "",R.drawable.ic_red_dot );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.holographic), "",R.drawable.ic_holographic);
                scopesDetailResponses.add(scope);
                scopesDetailAdapter.notifyDataSetChanged();

                LowerRailsDetailsResponse lower= new LowerRailsDetailsResponse(getString(R.string.angledforegrip), "", R.drawable.ic_angled_foregrip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.lightgrip), "", R.drawable.ic_light_grip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.thumbgrip), "", R.drawable.ic_thumb_grip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.vertical), "", R.drawable.ic_vertical_foregrip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.halfgrip), "", R.drawable.ic_half_grip);
                lowerRailsDetailsResponses.add(lower);
                lowerRailsDetailAdapter.notifyDataSetChanged();

                txtstocks.setText("No stocks");

                soundsrelative.setVisibility(View.VISIBLE);
                power.setProgress(40);
                perpower.setText("40%");
                recoil.setProgress(40);
                perrecoil.setText("40%");
                range.setProgress(63);
                perrange.setText("63%");
                firing.setProgress(65);
                perfiring.setText("65%");
                weaponammos.setText("7.62 mm");
                imgammos.setImageResource(R.drawable.ic_7_62_mm);
                namedetail.setText("Mk47 Mutant");
                damagedetail.setText("49");
                bulatedetail.setText("780 m/s");
                timebdetail.setText("0.0867S");
                firinfdetail.setText("Single,Burst");
                impactdetail.setText("1000");
                magazinedetail.setText("20 rounds");
                extendeddetails.setText("30 rounds");
                pickupdetail.setText("150 ms");
                readydetails.setText("500 ms");
                normaldetails.setText("2.2 second");
                quickdetail.setText("1.9 second");
            }
            else if(wpname.equalsIgnoreCase("Beryl M762")){

                CombinationResponse  combination= new CombinationResponse(getString(R.string.scop4x),"",R.drawable.ic_4x_scope);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.compensatorar),"",R.drawable.ic_compensator_ar_dmr_s12k);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.exquickdrawmagar),"",R.drawable.ic_extended_mag_ar_smr_s12k);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.halfgrip),"",R.drawable.ic_half_grip);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MagazinesDetailResponse magazines= new MagazinesDetailResponse(getString(R.string.exquickmagddmr),"",R.drawable.ic_extended_mag_sr_dmr_s12k);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.quickdrawmagar),"",R.drawable.ic_quickdraw_mag_ar_smr_s12k);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.exquickdrawmagar),"",R.drawable.ic_extended_mag_ar_smr_s12k);
                magazinesDetailResponses.add(magazines);
                magazinesDetailAdapter.notifyDataSetChanged();

                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.compensatorar), "", R.drawable.ic_compensator_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.flashhidersmg), "", R.drawable.ic_flashhider_smg);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();

                ScopesDetailResponse
                        scope = new ScopesDetailResponse(getString(R.string.scop6x), "",R.drawable.ic_6x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop4x), "",R.drawable.ic_4x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop3x), "",R.drawable.ic_3x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop2x), "",R.drawable.ic_2x_scope );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.reddot), "",R.drawable.ic_red_dot );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.holographic), "",R.drawable.ic_holographic);
                scopesDetailResponses.add(scope);
                scopesDetailAdapter.notifyDataSetChanged();

                LowerRailsDetailsResponse lower= new LowerRailsDetailsResponse(getString(R.string.angledforegrip), "", R.drawable.ic_angled_foregrip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.lightgrip), "", R.drawable.ic_light_grip);
                lowerRailsDetailsResponses.add(lower);
                lowerRailsDetailAdapter.notifyDataSetChanged();

                txtstocks.setText("No stocks");

                soundsrelative.setVisibility(View.VISIBLE);
                power.setProgress(55);
                perpower.setText("55%");
                recoil.setProgress(45);
                perrecoil.setText("45%");
                range.setProgress(75);
                perrange.setText("75%");
                firing.setProgress(77);
                perfiring.setText("77%");
                weaponammos.setText("7.62 mm");
                imgammos.setImageResource(R.drawable.ic_7_62_mm);
                namedetail.setText("Beryl M762");
                damagedetail.setText("47");
                bulatedetail.setText("715 m/s");
                timebdetail.setText("0.07S");
                firinfdetail.setText("Single,Burst,Auto");
                impactdetail.setText("1000");
                magazinedetail.setText("30 rounds");
                extendeddetails.setText("40 rounds");
                pickupdetail.setText("150 ms");
                readydetails.setText("500 ms");
                normaldetails.setText("2.95 second");
                quickdetail.setText("2.25 second");
            }
            else if(wpname.equalsIgnoreCase("M416")){

                CombinationResponse
                        combination= new CombinationResponse(getString(R.string.scop4x),"",R.drawable.ic_4x_scope);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.compensatorar),"",R.drawable.ic_compensator_ar_dmr_s12k);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.exquickdrawmagar),"",R.drawable.ic_extended_mag_ar_smr_s12k);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.halfgrip),"",R.drawable.ic_half_grip);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.tacticalstock),"",R.drawable.ic_tactical_stock_m416_vector);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MagazinesDetailResponse magazines= new MagazinesDetailResponse(getString(R.string.exquickmagddmr),"",R.drawable.ic_extended_mag_sr_dmr_s12k);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.quickdrawmagar),"",R.drawable.ic_quickdraw_mag_ar_smr_s12k);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.exquickdrawmagar),"",R.drawable.ic_extended_mag_ar_smr_s12k);
                magazinesDetailResponses.add(magazines);
                magazinesDetailAdapter.notifyDataSetChanged();

                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.compensatorar), "", R.drawable.ic_compensator_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.flashhidersmg), "", R.drawable.ic_flashhider_smg);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.flashhiderar), "", R.drawable.ic_flashhider_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();

                ScopesDetailResponse
                        scope = new ScopesDetailResponse(getString(R.string.scop6x), "",R.drawable.ic_6x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop4x), "",R.drawable.ic_4x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop3x), "",R.drawable.ic_3x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop2x), "",R.drawable.ic_2x_scope );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.reddot), "",R.drawable.ic_red_dot );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.holographic), "",R.drawable.ic_holographic);
                scopesDetailResponses.add(scope);
                scopesDetailAdapter.notifyDataSetChanged();

                LowerRailsDetailsResponse lower= new LowerRailsDetailsResponse(getString(R.string.angledforegrip), "", R.drawable.ic_angled_foregrip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.lightgrip), "", R.drawable.ic_light_grip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.thumbgrip), "", R.drawable.ic_thumb_grip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.vertical), "", R.drawable.ic_vertical_foregrip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.halfgrip), "", R.drawable.ic_half_grip);
                lowerRailsDetailsResponses.add(lower);
                lowerRailsDetailAdapter.notifyDataSetChanged();

                StocksDetailResponse stocks= new StocksDetailResponse(getString(R.string.tacticalstock), "", R.drawable.ic_tactical_stock_m416_vector);
                stocksDetailResponses.add(stocks);
                stocksDetailAdapter.notifyDataSetChanged();

                soundsrelative.setVisibility(View.VISIBLE);
                power.setProgress(42);
                perpower.setText("42%");
                recoil.setProgress(31);
                perrecoil.setText("31%");
                range.setProgress(56);
                perrange.setText("56%");
                firing.setProgress(80);
                perfiring.setText("80%");
                weaponammos.setText("5.56 mm");
                imgammos.setImageResource(R.drawable.ic_5_56_mm);
                namedetail.setText("M416");
                damagedetail.setText("43");
                bulatedetail.setText("880 m/s");
                timebdetail.setText("0.0867S");
                firinfdetail.setText("Single,Auto");
                impactdetail.setText("3500");
                magazinedetail.setText("30 rounds");
                extendeddetails.setText("40 rounds");
                pickupdetail.setText("150 ms");
                readydetails.setText("500 ms");
                normaldetails.setText("2.1 second");
                quickdetail.setText("1.9 second");
            }
            else if(wpname.equalsIgnoreCase("SCAR-L")){

                CombinationResponse  combination= new CombinationResponse(getString(R.string.scop4x),"",R.drawable.ic_4x_scope);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.compensatorar),"",R.drawable.ic_compensator_ar_dmr_s12k);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.exquickdrawmagar),"",R.drawable.ic_extended_mag_ar_smr_s12k);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.halfgrip),"",R.drawable.ic_half_grip);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MagazinesDetailResponse magazines= new MagazinesDetailResponse(getString(R.string.exquickmagddmr),"",R.drawable.ic_extended_mag_sr_dmr_s12k);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.quickdrawmagar),"",R.drawable.ic_quickdraw_mag_ar_smr_s12k);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.exquickdrawmagar),"",R.drawable.ic_extended_mag_ar_smr_s12k);
                magazinesDetailResponses.add(magazines);
                magazinesDetailAdapter.notifyDataSetChanged();

                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.compensatorar), "", R.drawable.ic_compensator_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.flashhidersmg), "", R.drawable.ic_flashhider_smg);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.flashhiderar), "", R.drawable.ic_flashhider_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();

                ScopesDetailResponse
                        scope = new ScopesDetailResponse(getString(R.string.scop6x), "",R.drawable.ic_6x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop4x), "",R.drawable.ic_4x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop3x), "",R.drawable.ic_3x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop2x), "",R.drawable.ic_2x_scope );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.reddot), "",R.drawable.ic_red_dot );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.holographic), "",R.drawable.ic_holographic);
                scopesDetailResponses.add(scope);
                scopesDetailAdapter.notifyDataSetChanged();

                LowerRailsDetailsResponse lower= new LowerRailsDetailsResponse(getString(R.string.angledforegrip), "", R.drawable.ic_angled_foregrip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.lightgrip), "", R.drawable.ic_light_grip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.thumbgrip), "", R.drawable.ic_thumb_grip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.vertical), "", R.drawable.ic_vertical_foregrip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.halfgrip), "", R.drawable.ic_half_grip);
                lowerRailsDetailsResponses.add(lower);
                lowerRailsDetailAdapter.notifyDataSetChanged();

                txtstocks.setText("No stocks");

                soundsrelative.setVisibility(View.VISIBLE);
                power.setProgress(42);
                perpower.setText("42%");
                recoil.setProgress(31);
                perrecoil.setText("31%");
                range.setProgress(55);
                perrange.setText("55%");
                firing.setProgress(70);
                perfiring.setText("70%");
                weaponammos.setText("5.56 mm");
                imgammos.setImageResource(R.drawable.ic_5_56_mm);
                namedetail.setText("SCAR-L");
                damagedetail.setText("43");
                bulatedetail.setText("870 m/s");
                timebdetail.setText("0.096S");
                firinfdetail.setText("Single,Auto");
                impactdetail.setText("9000");
                magazinedetail.setText("30 rounds");
                extendeddetails.setText("40 rounds");
                pickupdetail.setText("150 ms");
                readydetails.setText("500 ms");
                normaldetails.setText("2.2 second");
                quickdetail.setText("1.9 second");
            }
            else if(wpname.equalsIgnoreCase("Aug A3")){

                CombinationResponse  combination= new CombinationResponse(getString(R.string.scop4x),"",R.drawable.ic_4x_scope);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.compensatorar),"",R.drawable.ic_compensator_ar_dmr_s12k);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.exquickdrawmagar),"",R.drawable.ic_extended_mag_ar_smr_s12k);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.halfgrip),"",R.drawable.ic_half_grip);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MagazinesDetailResponse magazines= new MagazinesDetailResponse(getString(R.string.exquickmagddmr),"",R.drawable.ic_extended_mag_sr_dmr_s12k);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.quickdrawmagar),"",R.drawable.ic_quickdraw_mag_ar_smr_s12k);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.exquickdrawmagar),"",R.drawable.ic_extended_mag_ar_smr_s12k);
                magazinesDetailResponses.add(magazines);
                magazinesDetailAdapter.notifyDataSetChanged();

                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.compensatorar), "", R.drawable.ic_compensator_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.flashhidersmg), "", R.drawable.ic_flashhider_smg);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.flashhiderar), "", R.drawable.ic_flashhider_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();

                ScopesDetailResponse
                        scope = new ScopesDetailResponse(getString(R.string.scop6x), "",R.drawable.ic_6x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop4x), "",R.drawable.ic_4x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop3x), "",R.drawable.ic_3x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop2x), "",R.drawable.ic_2x_scope );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.reddot), "",R.drawable.ic_red_dot );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.holographic), "",R.drawable.ic_holographic);
                scopesDetailResponses.add(scope);
                scopesDetailAdapter.notifyDataSetChanged();

                LowerRailsDetailsResponse lower= new LowerRailsDetailsResponse(getString(R.string.angledforegrip), "", R.drawable.ic_angled_foregrip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.lightgrip), "", R.drawable.ic_light_grip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.thumbgrip), "", R.drawable.ic_thumb_grip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.vertical), "", R.drawable.ic_vertical_foregrip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.halfgrip), "", R.drawable.ic_half_grip);
                lowerRailsDetailsResponses.add(lower);
                lowerRailsDetailAdapter.notifyDataSetChanged();

                txtstocks.setText("No stocks");

                soundsrelative.setVisibility(View.VISIBLE);
                power.setProgress(42);
                perpower.setText("42%");
                recoil.setProgress(31);
                perrecoil.setText("31%");
                range.setProgress(50);
                perrange.setText("50%");
                firing.setProgress(63);
                perfiring.setText("63%");
                weaponammos.setText("5.56 mm");
                imgammos.setImageResource(R.drawable.ic_5_56_mm);
                namedetail.setText("Aug A3");
                damagedetail.setText("43");
                bulatedetail.setText("870 m/s");
                timebdetail.setText("0.0857S");
                firinfdetail.setText("Single,Auto");
                impactdetail.setText("9000");
                magazinedetail.setText("30 rounds");
                extendeddetails.setText("40 rounds");
                pickupdetail.setText("150 ms");
                readydetails.setText("500 ms");
                normaldetails.setText("3.66 second");
                quickdetail.setText("3 second");
            }
            else if(wpname.equalsIgnoreCase("QBZ95")){

                CombinationResponse  combination= new CombinationResponse(getString(R.string.scop4x),"",R.drawable.ic_4x_scope);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.compensatorar),"",R.drawable.ic_compensator_ar_dmr_s12k);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.exquickdrawmagar),"",R.drawable.ic_extended_mag_ar_smr_s12k);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.halfgrip),"",R.drawable.ic_half_grip);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MagazinesDetailResponse magazines= new MagazinesDetailResponse(getString(R.string.exquickmagddmr),"",R.drawable.ic_extended_mag_sr_dmr_s12k);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.quickdrawmagar),"",R.drawable.ic_quickdraw_mag_ar_smr_s12k);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.exquickdrawmagar),"",R.drawable.ic_extended_mag_ar_smr_s12k);
                magazinesDetailResponses.add(magazines);
                magazinesDetailAdapter.notifyDataSetChanged();

                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.compensatorar), "", R.drawable.ic_compensator_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.flashhidersmg), "", R.drawable.ic_flashhider_smg);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.flashhiderar), "", R.drawable.ic_flashhider_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();

                ScopesDetailResponse
                        scope = new ScopesDetailResponse(getString(R.string.scop6x), "",R.drawable.ic_6x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop4x), "",R.drawable.ic_4x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop3x), "",R.drawable.ic_3x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop2x), "",R.drawable.ic_2x_scope );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.reddot), "",R.drawable.ic_red_dot );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.holographic), "",R.drawable.ic_holographic);
                scopesDetailResponses.add(scope);
                scopesDetailAdapter.notifyDataSetChanged();

                LowerRailsDetailsResponse lower= new LowerRailsDetailsResponse(getString(R.string.angledforegrip), "", R.drawable.ic_angled_foregrip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.lightgrip), "", R.drawable.ic_light_grip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.thumbgrip), "", R.drawable.ic_thumb_grip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.vertical), "", R.drawable.ic_vertical_foregrip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.halfgrip), "", R.drawable.ic_half_grip);
                lowerRailsDetailsResponses.add(lower);
                lowerRailsDetailAdapter.notifyDataSetChanged();

                txtstocks.setText("No stocks");

                soundsrelative.setVisibility(View.VISIBLE);
                power.setProgress(50);
                perpower.setText("50%");
                recoil.setProgress(45);
                perrecoil.setText("45%");
                range.setProgress(64);
                perrange.setText("64%");
                firing.setProgress(85);
                perfiring.setText("85%");
                weaponammos.setText("5.56 mm");
                imgammos.setImageResource(R.drawable.ic_5_56_mm);
                namedetail.setText("QBZ95");
                damagedetail.setText("43");
                bulatedetail.setText("870 m/s");
                timebdetail.setText("0.0867S");
                firinfdetail.setText("Single,Auto");
                impactdetail.setText("9000");
                magazinedetail.setText("30 rounds");
                extendeddetails.setText("40 rounds");
                pickupdetail.setText("150 ms");
                readydetails.setText("500 ms");
                normaldetails.setText("2.2 second");
                quickdetail.setText("1.9 second");
            }
            else if(wpname.equalsIgnoreCase("M16A4")){

                CombinationResponse  combination= new CombinationResponse(getString(R.string.scop4x),"",R.drawable.ic_4x_scope);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.compensatorar),"",R.drawable.ic_compensator_ar_dmr_s12k);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.exquickdrawmagar),"",R.drawable.ic_extended_mag_ar_smr_s12k);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MagazinesDetailResponse magazines= new MagazinesDetailResponse(getString(R.string.exquickmagddmr),"",R.drawable.ic_extended_mag_sr_dmr_s12k);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.quickdrawmagar),"",R.drawable.ic_quickdraw_mag_ar_smr_s12k);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.exquickdrawmagar),"",R.drawable.ic_extended_mag_ar_smr_s12k);
                magazinesDetailResponses.add(magazines);
                magazinesDetailAdapter.notifyDataSetChanged();

                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.compensatorar), "", R.drawable.ic_compensator_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.flashhidersmg), "", R.drawable.ic_flashhider_smg);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();

                ScopesDetailResponse
                        scope = new ScopesDetailResponse(getString(R.string.scop6x), "",R.drawable.ic_6x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop4x), "",R.drawable.ic_4x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop3x), "",R.drawable.ic_3x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop2x), "",R.drawable.ic_2x_scope );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.reddot), "",R.drawable.ic_red_dot );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.holographic), "",R.drawable.ic_holographic);
                scopesDetailResponses.add(scope);
                scopesDetailAdapter.notifyDataSetChanged();


                txtlowerrails.setText("No lower rails");
                txtstocks.setText("No stocks");

                soundsrelative.setVisibility(View.VISIBLE);
                power.setProgress(42);
                perpower.setText("42%");
                recoil.setProgress(34);
                perrecoil.setText("34%");
                range.setProgress(64);
                perrange.setText("64%");
                firing.setProgress(10);
                perfiring.setText("10%");
                weaponammos.setText("5.56 mm");
                imgammos.setImageResource(R.drawable.ic_5_56_mm);
                namedetail.setText("M16A4");
                damagedetail.setText("43");
                bulatedetail.setText("900 m/s");
                timebdetail.setText("0.075S");
                firinfdetail.setText("Single,Burst");
                impactdetail.setText("8000");
                magazinedetail.setText("30 rounds");
                extendeddetails.setText("40 rounds");
                pickupdetail.setText("150 ms");
                readydetails.setText("500 ms");
                normaldetails.setText("2.2 second");
                quickdetail.setText("1.9 second");
            }
            else if(wpname.equalsIgnoreCase("Tommy Gun")){

                CombinationResponse  combination= new CombinationResponse(getString(R.string.suppressorhandguns),"",R.drawable.ic_suppressor_handgunsg);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.exquickdrawmagsmg),"",R.drawable.ic_extended_quickdraw_mag_smg);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.vertical),"",R.drawable.ic_vertical_foregrip);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MagazinesDetailResponse magazines= new MagazinesDetailResponse(getString(R.string.exquickmagdsmg),"",R.drawable.ic_extended_mag_smg);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.quickdrawmagsmg),"",R.drawable.ic_quickdrawmag_smg);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.exquickdrawmagsmg),"",R.drawable.ic_extended_quickdraw_mag_smg);
                magazinesDetailResponses.add(magazines);
                magazinesDetailAdapter.notifyDataSetChanged();

                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.suppressorsmg), "", R.drawable.ic_suppressor_smg);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();


                LowerRailsDetailsResponse lower= new LowerRailsDetailsResponse(getString(R.string.vertical), "", R.drawable.ic_vertical_foregrip);
                lowerRailsDetailsResponses.add(lower);
                lowerRailsDetailAdapter.notifyDataSetChanged();


                txtmagazines.setText("No magazines");
                txtmuzzles.setText(" No muzzles");
                txtlowerrails.setText("No lower rails");


                soundsrelative.setVisibility(View.VISIBLE);
                power.setProgress(36);
                perpower.setText("36%");
                recoil.setProgress(44);
                perrecoil.setText("44%");
                range.setProgress(38);
                perrange.setText("38%");
                firing.setProgress(60);
                perfiring.setText("60%");
                weaponammos.setText(".45 ACP");
                imgammos.setImageResource(R.drawable.ic_45_acp);
                namedetail.setText("Tommy Gun");
                damagedetail.setText("40");
                bulatedetail.setText("280 m/s");
                timebdetail.setText("0.086S");
                firinfdetail.setText("Single,Auto");
                impactdetail.setText("7000");
                magazinedetail.setText("30 rounds");
                extendeddetails.setText("50 rounds");
                pickupdetail.setText("150 ms");
                readydetails.setText("500 ms");
                normaldetails.setText("3.45 second");
                quickdetail.setText("2.85 second");
            }
            else if(wpname.equalsIgnoreCase("Ump9")){

                CombinationResponse combination= new CombinationResponse(getString(R.string.scop2x),"",R.drawable.ic_2x_scope);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.compensatorsmg),"",R.drawable.ic_compensator_smg);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.exquickdrawmagsmg),"",R.drawable.ic_extended_quickdraw_mag_smg);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.halfgrip),"",R.drawable.ic_half_grip);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MagazinesDetailResponse magazines= new MagazinesDetailResponse(getString(R.string.exquickmagdsmg),"",R.drawable.ic_extended_mag_smg);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.quickdrawmagsmg),"",R.drawable.ic_quickdrawmag_smg);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.exquickdrawmagsmg),"",R.drawable.ic_extended_quickdraw_mag_smg);
                magazinesDetailResponses.add(magazines);
                magazinesDetailAdapter.notifyDataSetChanged();

                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.compensatorsmg), "", R.drawable.ic_compensator_smg);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.suppressorsmg), "", R.drawable.ic_suppressor_smg);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.flashhidersmg), "", R.drawable.ic_flashhider_smg);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();

                ScopesDetailResponse
                        scope = new ScopesDetailResponse(getString(R.string.scop6x), "",R.drawable.ic_6x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop4x), "",R.drawable.ic_4x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop3x), "",R.drawable.ic_3x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop2x), "",R.drawable.ic_2x_scope );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.reddot), "",R.drawable.ic_red_dot );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.holographic), "",R.drawable.ic_holographic);
                scopesDetailResponses.add(scope);
                scopesDetailAdapter.notifyDataSetChanged();

                LowerRailsDetailsResponse lower= new LowerRailsDetailsResponse(getString(R.string.angledforegrip), "", R.drawable.ic_angled_foregrip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.lightgrip), "", R.drawable.ic_light_grip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.thumbgrip), "", R.drawable.ic_thumb_grip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.vertical), "", R.drawable.ic_vertical_foregrip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.halfgrip), "", R.drawable.ic_half_grip);
                lowerRailsDetailsResponses.add(lower);
                lowerRailsDetailAdapter.notifyDataSetChanged();

                txtstocks.setText("No stocks");

                soundsrelative.setVisibility(View.VISIBLE);
                power.setProgress(35);
                perpower.setText("35%");
                recoil.setProgress(37);
                perrecoil.setText("37%");
                range.setProgress(30);
                perrange.setText("30%");
                firing.setProgress(65);
                perfiring.setText("65%");
                weaponammos.setText("9 mm");
                imgammos.setImageResource(R.drawable.ic_9_mm);
                namedetail.setText("Ump9");
                damagedetail.setText("39");
                bulatedetail.setText("480 m/s");
                timebdetail.setText("0.92S");
                firinfdetail.setText("Single,Auto,Burst");
                impactdetail.setText("7000");
                magazinedetail.setText("30 rounds");
                extendeddetails.setText("40 rounds");
                pickupdetail.setText("150 ms");
                readydetails.setText("500 ms");
                normaldetails.setText("3.1 second");
                quickdetail.setText("2.55 second");
            }
            else if(wpname.equalsIgnoreCase("PP-19 Bizon")){

                CombinationResponse
                combination= new CombinationResponse(getString(R.string.scop2x),"",R.drawable.ic_2x_scope);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.suppressorhandguns),"",R.drawable.ic_suppressor_handgunsg);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.compensatorsmg), "", R.drawable.ic_compensator_smg);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.suppressorsmg), "", R.drawable.ic_suppressor_smg);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.flashhidersmg), "", R.drawable.ic_flashhider_smg);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();

                ScopesDetailResponse
                        scope = new ScopesDetailResponse(getString(R.string.scop6x), "",R.drawable.ic_6x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop4x), "",R.drawable.ic_4x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop3x), "",R.drawable.ic_3x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop2x), "",R.drawable.ic_2x_scope );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.reddot), "",R.drawable.ic_red_dot );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.holographic), "",R.drawable.ic_holographic);
                scopesDetailResponses.add(scope);
                scopesDetailAdapter.notifyDataSetChanged();

                txtmagazines.setText("No magazines");
                txtlowerrails.setText("No lower rails");
                txtstocks.setText("No stocks");

                nosound.setVisibility(View.VISIBLE);
                nosound.setVisibility(View.VISIBLE);
                power.setProgress(31);
                perpower.setText("31%");
                recoil.setProgress(30);
                perrecoil.setText("30%");
                range.setProgress(20);
                perrange.setText("20%");
                firing.setProgress(55);
                perfiring.setText("55%");
                weaponammos.setText("9 mm");
                imgammos.setImageResource(R.drawable.ic_9_mm);
                namedetail.setText("PP-19 Bizon");
                damagedetail.setText("35");
                bulatedetail.setText("408 m/s");
                timebdetail.setVisibility(View.GONE);
                timeb.setVisibility(View.GONE);
                firinfdetail.setText("Single,Auto");
                impactdetail.setVisibility(View.GONE);
                impact.setVisibility(View.GONE);
                magazinedetail.setText("53 rounds");
                extendeddetails.setVisibility(View.GONE);
                extended.setVisibility(View.GONE);
                pickupdetail.setText("150 ms");
                readydetails.setText("500 ms");
                normaldetails.setText("3.1 second");
                quickdetail.setVisibility(View.GONE);
                quick.setVisibility(View.GONE);
            }
            else if(wpname.equalsIgnoreCase("Vector")){

                CombinationResponse combination= new CombinationResponse(getString(R.string.scop2x),"",R.drawable.ic_2x_scope);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.compensatorsmg),"",R.drawable.ic_compensator_smg);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.exquickdrawmagsmg),"",R.drawable.ic_extended_quickdraw_mag_smg);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.halfgrip),"",R.drawable.ic_half_grip);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.tacticalstock),"",R.drawable.ic_tactical_stock_m416_vector);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MagazinesDetailResponse magazines= new MagazinesDetailResponse(getString(R.string.exquickmagdsmg),"",R.drawable.ic_extended_mag_smg);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.quickdrawmagsmg),"",R.drawable.ic_quickdrawmag_smg);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.exquickdrawmagsmg),"",R.drawable.ic_extended_quickdraw_mag_smg);
                magazinesDetailResponses.add(magazines);
                magazinesDetailAdapter.notifyDataSetChanged();

                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.compensatorsmg), "", R.drawable.ic_compensator_smg);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.suppressorsmg), "", R.drawable.ic_suppressor_smg);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.flashhidersmg), "", R.drawable.ic_flashhider_smg);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();

                ScopesDetailResponse
                        scope = new ScopesDetailResponse(getString(R.string.scop6x), "",R.drawable.ic_6x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop4x), "",R.drawable.ic_4x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop3x), "",R.drawable.ic_3x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop2x), "",R.drawable.ic_2x_scope );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.reddot), "",R.drawable.ic_red_dot );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.holographic), "",R.drawable.ic_holographic);
                scopesDetailResponses.add(scope);
                scopesDetailAdapter.notifyDataSetChanged();

                LowerRailsDetailsResponse
                lower= new LowerRailsDetailsResponse(getString(R.string.lightgrip), "", R.drawable.ic_light_grip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.vertical), "", R.drawable.ic_vertical_foregrip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.halfgrip), "", R.drawable.ic_half_grip);
                lowerRailsDetailsResponses.add(lower);
                lowerRailsDetailAdapter.notifyDataSetChanged();

                StocksDetailResponse stocks= new StocksDetailResponse(getString(R.string.tacticalstock), "", R.drawable.ic_tactical_stock_m416_vector);
                stocksDetailResponses.add(stocks);
                stocksDetailAdapter.notifyDataSetChanged();

                soundsrelative.setVisibility(View.VISIBLE);
                power.setProgress(30);
                perpower.setText("30%");
                recoil.setProgress(30);
                perrecoil.setText("30%");
                range.setProgress(20);
                perrange.setText("20%");
                firing.setProgress(55);
                perfiring.setText("55%");
                weaponammos.setText("9 mm");
                imgammos.setImageResource(R.drawable.ic_9_mm);
                namedetail.setText("Vector");
                damagedetail.setText("34");
                bulatedetail.setText("300 m/s");
                timebdetail.setText("0.055S");
                firinfdetail.setText("Single,Auto,Burst");
                impactdetail.setText("7000");
                magazinedetail.setText("13 rounds");
                extendeddetails.setText("25 rounds");
                pickupdetail.setText("150 ms");
                readydetails.setText("500 ms");
                normaldetails.setText("2.2 second");
                quickdetail.setText("2.5 second");
            }
            else if(wpname.equalsIgnoreCase("MP5K")){

                CombinationResponse  combination= new CombinationResponse(getString(R.string.scop2x),"",R.drawable.ic_2x_scope);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.compensatorsmg),"",R.drawable.ic_compensator_smg);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.exquickdrawmagsmg),"",R.drawable.ic_extended_quickdraw_mag_smg);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.halfgrip),"",R.drawable.ic_half_grip);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MagazinesDetailResponse magazines= new MagazinesDetailResponse(getString(R.string.exquickmagdsmg),"",R.drawable.ic_extended_mag_smg);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.quickdrawmagar),"",R.drawable.ic_quickdraw_mag_ar_smr_s12k);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.exquickdrawmagsmg),"",R.drawable.ic_extended_quickdraw_mag_smg);
                magazinesDetailResponses.add(magazines);
                magazinesDetailAdapter.notifyDataSetChanged();

                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.suppressorsmg), "", R.drawable.ic_suppressor_smg);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();

                ScopesDetailResponse
                        scope = new ScopesDetailResponse(getString(R.string.scop6x), "",R.drawable.ic_6x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop4x), "",R.drawable.ic_4x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop3x), "",R.drawable.ic_3x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop2x), "",R.drawable.ic_2x_scope );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.reddot), "",R.drawable.ic_red_dot );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.holographic), "",R.drawable.ic_holographic);
                scopesDetailResponses.add(scope);
                scopesDetailAdapter.notifyDataSetChanged();

                LowerRailsDetailsResponse lower= new LowerRailsDetailsResponse(getString(R.string.angledforegrip), "", R.drawable.ic_angled_foregrip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.lightgrip), "", R.drawable.ic_light_grip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.thumbgrip), "", R.drawable.ic_thumb_grip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.vertical), "", R.drawable.ic_vertical_foregrip);
                lowerRailsDetailsResponses.add(lower);
                lower= new LowerRailsDetailsResponse(getString(R.string.halfgrip), "", R.drawable.ic_half_grip);
                lowerRailsDetailsResponses.add(lower);
                lowerRailsDetailAdapter.notifyDataSetChanged();

                StocksDetailResponse stocks= new StocksDetailResponse(getString(R.string.stockmicro), "", R.drawable.ic_stock_uzi);
                stocksDetailResponses.add(stocks);
                stocksDetailAdapter.notifyDataSetChanged();

                nosound.setVisibility(View.VISIBLE);
                power.setProgress(31);
                perpower.setText("31%");
                recoil.setProgress(34);
                perrecoil.setText("34%");
                range.setProgress(31);
                perrange.setText("31%");
                firing.setProgress(72);
                perfiring.setText("72%");
                weaponammos.setText("9 mm");
                imgammos.setImageResource(R.drawable.ic_9_mm);
                namedetail.setText("MP5K");
                damagedetail.setText("33");
                bulatedetail.setText("400 m/s");
                timebdetail.setText("0.066S");
                firinfdetail.setText("Single,Auto,Burst");
                impactdetail.setText("7000");
                magazinedetail.setText("30 rounds");
                extendeddetails.setText("40 rounds");
                pickupdetail.setText("150 ms");
                readydetails.setText("500 ms");
                normaldetails.setText("4.8 second");
                quickdetail.setText("2.75 second");
            }
            else if(wpname.equalsIgnoreCase("Micro uzi")){

                MagazinesDetailResponse magazines= new MagazinesDetailResponse(getString(R.string.exquickmagdsmg),"",R.drawable.ic_extended_mag_smg);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.quickdrawmagsmg),"",R.drawable.ic_quickdrawmag_smg);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.exquickdrawmagsmg),"",R.drawable.ic_extended_quickdraw_mag_smg);
                magazinesDetailResponses.add(magazines);
                magazinesDetailAdapter.notifyDataSetChanged();

                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.compensatorsmg), "", R.drawable.ic_compensator_smg);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.suppressorsmg), "", R.drawable.ic_suppressor_smg);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.flashhidersmg), "", R.drawable.ic_flashhider_smg);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();



                txtscopes.setText("No scopes");
                txtlowerrails.setText("No lower rails");


                CombinationResponse
                        combination= new CombinationResponse(getString(R.string.compensatorsmg),"",R.drawable.ic_compensator_smg);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.exquickdrawmagsmg),"",R.drawable.ic_extended_quickdraw_mag_smg);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.stockmicro),"",R.drawable.ic_stock_uzi);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();


                StocksDetailResponse stocks= new StocksDetailResponse(getString(R.string.stockmicro), "", R.drawable.ic_stock_uzi);
                stocksDetailResponses.add(stocks);
                stocksDetailAdapter.notifyDataSetChanged();

                soundsrelative.setVisibility(View.VISIBLE);
                power.setProgress(25);
                perpower.setText("25%");
                recoil.setProgress(45);
                perrecoil.setText("45%");
                range.setProgress(26);
                perrange.setText("26%");
                firing.setProgress(81);
                perfiring.setText("81%");
                weaponammos.setText("9 mm");
                imgammos.setImageResource(R.drawable.ic_9_mm);
                namedetail.setText("Micro uzi");
                damagedetail.setText("26");
                bulatedetail.setText("350 m/s");
                timebdetail.setText("0.48S");
                firinfdetail.setText("Single,Auto");
                impactdetail.setText("5000");
                magazinedetail.setText("25 rounds");
                extendeddetails.setText("35 rounds");
                pickupdetail.setText("150 ms");
                readydetails.setText("500 ms");
                normaldetails.setText("3.1 second");
                quickdetail.setText("2.5 second");
            }
            else if(wpname.equalsIgnoreCase("S12K")){

                CombinationResponse combination= new CombinationResponse(getString(R.string.scop2x),"",R.drawable.ic_2x_scope);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.duckbill),"",R.drawable.ic_duckbill);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.exquickdrawmagsmg),"",R.drawable.ic_extended_quickdraw_mag_smg);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MagazinesDetailResponse magazines= new MagazinesDetailResponse(getString(R.string.exquickmagddmr),"",R.drawable.ic_extended_mag_sr_dmr_s12k);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.quickdrawmagar),"",R.drawable.ic_quickdraw_mag_ar_smr_s12k);
                magazinesDetailResponses.add(magazines);
                magazines=new MagazinesDetailResponse(getString(R.string.exquickdrawmagar),"",R.drawable.ic_extended_mag_ar_smr_s12k);
                magazinesDetailResponses.add(magazines);
                magazinesDetailAdapter.notifyDataSetChanged();

                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.compensatorar), "", R.drawable.ic_compensator_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.suppressorar), "", R.drawable.ic_suppressor_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.flashhiderar), "", R.drawable.ic_flashhider_ar_dmr_s12k);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();

                ScopesDetailResponse
                        scope = new ScopesDetailResponse(getString(R.string.scop6x), "",R.drawable.ic_6x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop4x), "",R.drawable.ic_4x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop3x), "",R.drawable.ic_3x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop2x), "",R.drawable.ic_2x_scope );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.reddot), "",R.drawable.ic_red_dot );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.holographic), "",R.drawable.ic_holographic);
                scopesDetailResponses.add(scope);
                scopesDetailAdapter.notifyDataSetChanged();

                txtlowerrails.setText("No lower rails");
                txtstocks.setText("No stocks");

                soundsrelative.setVisibility(View.VISIBLE);
                power.setProgress(90);
                perpower.setText("90%");
                recoil.setProgress(86);
                perrecoil.setText("86%");
                range.setProgress(9);
                perrange.setText("9%");
                firing.setProgress(14);
                perfiring.setText("14%");
                weaponammos.setText("12 Gauge");
                imgammos.setImageResource(R.drawable.ic_12gauge);
                namedetail.setText("S12K");
                damagedetail.setText("198");
                bulatedetail.setText("350 m/s");
                timebdetail.setText("0.25S");
                firinfdetail.setText("Single");
                impactdetail.setText("5000");
                magazinedetail.setText("5 rounds");
                extendeddetails.setText("8 rounds");
                pickupdetail.setText("150 ms");
                readydetails.setText("500 ms");
                normaldetails.setText("3 second");
                quickdetail.setText("2.4 second");
            }
            else if(wpname.equalsIgnoreCase("S686")){

                CombinationResponse combination= new CombinationResponse(getString(R.string.chcoke),"",R.drawable.ic_chcoke);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.bullets1897),"",R.drawable.ic_bullet_s1897_s686);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MagazinesDetailResponse magazines= new MagazinesDetailResponse(getString(R.string.bullets1897),"",R.drawable.ic_bullet_s1897_s686);
                magazinesDetailResponses.add(magazines);
                magazinesDetailAdapter.notifyDataSetChanged();

                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.chcoke), "", R.drawable.ic_chcoke);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();

                txtscopes.setText("No scopes");
                txtlowerrails.setText("No lower rails");
                txtstocks.setText("No stocks");

                soundone.setVisibility(View.VISIBLE);
                power.setProgress(100);
                perpower.setText("100%");
                recoil.setProgress(78);
                perrecoil.setText("78%");
                range.setProgress(13);
                perrange.setText("13%");
                firing.setProgress(6);
                perfiring.setText("6%");
                weaponammos.setText("12 Gauge");
                imgammos.setImageResource(R.drawable.ic_12gauge);
                namedetail.setText("S686");
                damagedetail.setText("216");
                bulatedetail.setText("370 m/s");
                timebdetail.setText("0.2S");
                firinfdetail.setText("Single");
                impactdetail.setText("5000");
                magazinedetail.setText("2 rounds");
                extendeddetails.setVisibility(View.GONE);
                extended.setVisibility(View.GONE);
                pickupdetail.setText("500 ms");
                readydetails.setVisibility(View.GONE);
                ready.setVisibility(View.GONE);
                normaldetails.setText("3 second");
                quickdetail.setVisibility(View.GONE);
                quick.setVisibility(View.GONE);
            }
            else if(wpname.equalsIgnoreCase("S1897")){

                CombinationResponse combination= new CombinationResponse(getString(R.string.chcoke),"",R.drawable.ic_chcoke);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.bullets1897),"",R.drawable.ic_bullet_s1897_s686);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MagazinesDetailResponse magazines= new MagazinesDetailResponse(getString(R.string.bullets1897),"",R.drawable.ic_bullet_s1897_s686);
                magazinesDetailResponses.add(magazines);
                magazinesDetailAdapter.notifyDataSetChanged();

                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.chcoke), "", R.drawable.ic_chcoke);
                muzzlesDetailResponses.add(muzzles);
                muzzles= new MuzzlesDetailResponse(getString(R.string.duckbill), "", R.drawable.ic_duckbill);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();

                txtscopes.setText("No scopes");
                txtlowerrails.setText("No lower rails");
                txtstocks.setText("No stocks");

                soundone.setVisibility(View.VISIBLE);
                power.setProgress(100);
                perpower.setText("100%");
                recoil.setProgress(77);
                perrecoil.setText("77%");
                range.setProgress(13);
                perrange.setText("13%");
                firing.setProgress(7);
                perfiring.setText("7%");
                weaponammos.setText("12 Gauge");
                imgammos.setImageResource(R.drawable.ic_12gauge);
                namedetail.setText("S1897");
                damagedetail.setText("216");
                bulatedetail.setText("360 m/s");
                timebdetail.setText("0.75S");
                firinfdetail.setText("Single");
                impactdetail.setText("5000");
                magazinedetail.setText("5 rounds");
                extendeddetails.setVisibility(View.GONE);
                extended.setVisibility(View.GONE);
                pickupdetail.setText("150 ms");
                readydetails.setText("500 ms");
                normaldetails.setText("4 second");
                quickdetail.setVisibility(View.GONE);
                quick.setVisibility(View.GONE);
            }
            else if(wpname.equalsIgnoreCase("Sawed-off")){

                CombinationResponse combination= new CombinationResponse(getString(R.string.chcoke),"",R.drawable.ic_chcoke);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.chcoke), "", R.drawable.ic_chcoke);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();

                txtmagazines.setText("No magazines");
                txtscopes.setText("No scopes");
                txtlowerrails.setText("No lower rails");
                txtstocks.setText("No stocks");

                soundone.setVisibility(View.VISIBLE);
                power.setProgress(70);
                perpower.setText("70%");
                recoil.setProgress(43);
                perrecoil.setText("43%");
                range.setProgress(10);
                perrange.setText("10%");
                firing.setProgress(5);
                perfiring.setText("5%");
                weaponammos.setText("12 Gauge");
                imgammos.setImageResource(R.drawable.ic_12gauge);
                namedetail.setText("Sawed-off");
                damagedetail.setText("160");
                bulatedetail.setText("300 m/s");
                timebdetail.setVisibility(View.GONE);
                timeb.setVisibility(View.GONE);
                firinfdetail.setText("Semi-Auto");
                impactdetail.setVisibility(View.GONE);
                impact.setVisibility(View.GONE);
                magazinedetail.setText("2 rounds");
                extendeddetails.setVisibility(View.GONE);
                extended.setVisibility(View.GONE);
                pickupdetail.setText("150 ms");
                readydetails.setText("500 ms");
                normaldetails.setText("2 second");
                quickdetail.setVisibility(View.GONE);
                quick.setVisibility(View.GONE);
            }
            else if(wpname.equalsIgnoreCase("R45")){

                CombinationResponse
                        combination= new CombinationResponse(getString(R.string.reddot),"",R.drawable.ic_red_dot);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                ScopesDetailResponse scope= new ScopesDetailResponse(getString(R.string.reddot), "", R.drawable.ic_red_dot);
                scopesDetailResponses.add(scope);
                scopesDetailAdapter.notifyDataSetChanged();

                txtmagazines.setText("No magazines");
                txtmuzzles.setText(" No muzzles");
                txtlowerrails.setText("No lower rails");
                txtstocks.setText("No stocks");

                soundone.setVisibility(View.VISIBLE);
                power.setProgress(46);
                perpower.setText("46%");
                recoil.setProgress(43);
                perrecoil.setText("43%");
                range.setProgress(25);
                perrange.setText("25%");
                firing.setProgress(16);
                perfiring.setText("16%");
                weaponammos.setText(".45 ACP");
               imgammos.setImageResource(R.drawable.ic_45_acp);
                namedetail.setText("R45");
                damagedetail.setText("55");
                bulatedetail.setText("330 m/s");
                timebdetail.setText("0.25S");
                firinfdetail.setText("Single");
                impactdetail.setText("8000");
                magazinedetail.setText("6 rounds");
                extendeddetails.setVisibility(View.GONE);
                extended.setVisibility(View.GONE);
                pickupdetail.setText("150 ms");
                readydetails.setText("500 ms");
                normaldetails.setText("3.2 second");
                quickdetail.setVisibility(View.GONE);
                quick.setVisibility(View.GONE);
            }
            else if(wpname.equalsIgnoreCase("R1895")){

                CombinationResponse combination= new CombinationResponse(getString(R.string.suppressorsr),"",R.drawable.ic_suppressor_dmr_sr);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.suppressorsr), "", R.drawable.ic_suppressor_dmr_sr);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();

                txtmagazines.setText("No magazines");
                txtscopes.setText("No scopes");
                txtlowerrails.setText("No lower rails");
                txtstocks.setText("No stocks");

                soundsrelative.setVisibility(View.VISIBLE);
                power.setProgress(46);
                perpower.setText("46%");
                recoil.setProgress(30);
                perrecoil.setText("30%");
                range.setProgress(31);
                perrange.setText("31%");
                firing.setProgress(10);
                perfiring.setText("10%");
                weaponammos.setText("7.62 mm");
                imgammos.setImageResource(R.drawable.ic_7_62_mm);
                namedetail.setText("R1895");
                damagedetail.setText("55");
                bulatedetail.setText("330 m/s");
                timebdetail.setText("0.4S");
                firinfdetail.setText("Single");
                impactdetail.setText("8000");
                magazinedetail.setText("7 rounds");
                extendeddetails.setVisibility(View.GONE);
                extended.setVisibility(View.GONE);
                pickupdetail.setText("150 ms");
                readydetails.setText("500 ms");
                normaldetails.setText("6.25 second");
                quickdetail.setVisibility(View.GONE);
                quick.setVisibility(View.GONE);
            }
            else if(wpname.equalsIgnoreCase("P1911")){

                CombinationResponse  combination= new CombinationResponse(getString(R.string.reddot),"",R.drawable.ic_red_dot);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.suppressorsr),"",R.drawable.ic_suppressor_dmr_sr);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.exquickdrawmaghandgun),"",R.drawable.ic_extended_quickdraw_mag_hundgun);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MagazinesDetailResponse magazines= new MagazinesDetailResponse(getString(R.string.exquickmaghand),"",R.drawable.ic_extended_mag_hundgun);
                magazinesDetailResponses.add(magazines);
                magazines= new MagazinesDetailResponse(getString(R.string.quickdrawmaghandgun),"",R.drawable.ic_quickdraw_mag_handgun);
                magazinesDetailResponses.add(magazines);
                magazines= new MagazinesDetailResponse(getString(R.string.exquickdrawmaghandgun),"",R.drawable.ic_extended_quickdraw_mag_hundgun);
                magazinesDetailResponses.add(magazines);
                magazinesDetailAdapter.notifyDataSetChanged();

                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.chcoke), "", R.drawable.ic_chcoke);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();

                ScopesDetailResponse scope= new ScopesDetailResponse(getString(R.string.reddot), "", R.drawable.ic_red_dot);
                scopesDetailResponses.add(scope);
                scopesDetailAdapter.notifyDataSetChanged();

                txtlowerrails.setText("No lower rails");
                txtstocks.setText("No stocks");

                soundsrelative.setVisibility(View.VISIBLE);
                power.setProgress(35);
                perpower.setText("35%");
                recoil.setProgress(30);
                perrecoil.setText("30%");
                range.setProgress(15);
                perrange.setText("15%");
                firing.setProgress(30);
                perfiring.setText("30%");
                weaponammos.setText(".45 ACP");
                imgammos.setImageResource(R.drawable.ic_45_acp);
                namedetail.setText("P1911");
                damagedetail.setText("41");
                bulatedetail.setText("250 m/s");
                timebdetail.setText("0.110S");
                firinfdetail.setText("Single");
                impactdetail.setText("6000");
                magazinedetail.setText("7 rounds");
                extendeddetails.setText("12 rounds");
                pickupdetail.setText("150 ms");
                readydetails.setText("500 ms");
                normaldetails.setText("2.1 second");
                quickdetail.setText("1.8 second");
            }
            else if(wpname.equalsIgnoreCase("P92")){

                CombinationResponse combination= new CombinationResponse(getString(R.string.reddot),"",R.drawable.ic_red_dot);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.suppressorsr),"",R.drawable.ic_suppressor_dmr_sr);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.exquickdrawmaghandgun),"",R.drawable.ic_extended_quickdraw_mag_hundgun);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MagazinesDetailResponse magazines= new MagazinesDetailResponse(getString(R.string.exquickmaghand),"",R.drawable.ic_extended_mag_hundgun);
                magazinesDetailResponses.add(magazines);
                magazines= new MagazinesDetailResponse(getString(R.string.quickdrawmaghandgun),"",R.drawable.ic_quickdraw_mag_handgun);
                magazinesDetailResponses.add(magazines);
                magazines= new MagazinesDetailResponse(getString(R.string.exquickdrawmaghandgun),"",R.drawable.ic_extended_quickdraw_mag_hundgun);
                magazinesDetailResponses.add(magazines);
                magazinesDetailAdapter.notifyDataSetChanged();

                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.chcoke), "", R.drawable.ic_chcoke);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();

                ScopesDetailResponse scope= new ScopesDetailResponse(getString(R.string.reddot), "", R.drawable.ic_red_dot);
                scopesDetailResponses.add(scope);
                scopesDetailAdapter.notifyDataSetChanged();

                txtlowerrails.setText("No lower rails");
                txtstocks.setText("No stocks");

                soundsrelative.setVisibility(View.VISIBLE);
                power.setProgress(30);
                perpower.setText("30%");
                recoil.setProgress(27);
                perrecoil.setText("27%");
                range.setProgress(15);
                perrange.setText("15%");
                firing.setProgress(45);
                perfiring.setText("45%");
                weaponammos.setText("9 mm");
                imgammos.setImageResource(R.drawable.ic_9_mm);
                namedetail.setText("P92");
                damagedetail.setText("35");
                bulatedetail.setText("380 m/s");
                timebdetail.setText("0.090S");
                firinfdetail.setText("Single");
                impactdetail.setText("7000");
                magazinedetail.setText("15 rounds");
                extendeddetails.setText("20 rounds");
                pickupdetail.setText("150 ms");
                readydetails.setText("500 ms");
                normaldetails.setText("2 second");
                quickdetail.setText("1.7 second");
            }
            else if(wpname.equalsIgnoreCase("P18C")){

                CombinationResponse combination= new CombinationResponse(getString(R.string.reddot),"",R.drawable.ic_red_dot);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.suppressorsr),"",R.drawable.ic_suppressor_dmr_sr);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.exquickdrawmaghandgun),"",R.drawable.ic_extended_quickdraw_mag_hundgun);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MagazinesDetailResponse magazines= new MagazinesDetailResponse(getString(R.string.exquickmaghand),"",R.drawable.ic_extended_mag_hundgun);
                magazinesDetailResponses.add(magazines);
                magazines= new MagazinesDetailResponse(getString(R.string.quickdrawmaghandgun),"",R.drawable.ic_quickdraw_mag_handgun);
                magazinesDetailResponses.add(magazines);
                magazines= new MagazinesDetailResponse(getString(R.string.exquickdrawmaghandgun),"",R.drawable.ic_extended_quickdraw_mag_hundgun);
                magazinesDetailResponses.add(magazines);
                magazinesDetailAdapter.notifyDataSetChanged();

                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.chcoke), "", R.drawable.ic_chcoke);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();

                ScopesDetailResponse scope= new ScopesDetailResponse(getString(R.string.reddot), "", R.drawable.ic_red_dot);
                scopesDetailResponses.add(scope);
                scopesDetailAdapter.notifyDataSetChanged();

                txtlowerrails.setText("No lower rails");
                txtstocks.setText("No stocks");

                soundsrelative.setVisibility(View.VISIBLE);
                power.setProgress(20);
                perpower.setText("20%");
                recoil.setProgress(25);
                perrecoil.setText("25%");
                range.setProgress(10);
                perrange.setText("10%");
                firing.setProgress(67);
                perfiring.setText("67%");
                weaponammos.setText("9 mm");
                imgammos.setImageResource(R.drawable.ic_9_mm);
                namedetail.setText("P18C");
                damagedetail.setText("23");
                bulatedetail.setText("375 m/s");
                timebdetail.setText("0.6S");
                firinfdetail.setText("Single,Auto");
                impactdetail.setText("7000");
                magazinedetail.setText("17 rounds");
                extendeddetails.setText("25 rounds");
                pickupdetail.setText("150 ms");
                readydetails.setText("500 ms");
                normaldetails.setText("2 second");
                quickdetail.setText("1.7 second");
            }
            else if(wpname.equalsIgnoreCase("Skorpion")){

                CombinationResponse combination= new CombinationResponse(getString(R.string.reddot),"",R.drawable.ic_red_dot);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.suppressorsr),"",R.drawable.ic_suppressor_dmr_sr);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.exquickdrawmaghandgun),"",R.drawable.ic_extended_quickdraw_mag_hundgun);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                MagazinesDetailResponse magazines= new MagazinesDetailResponse(getString(R.string.exquickmaghand),"",R.drawable.ic_extended_mag_hundgun);
                magazinesDetailResponses.add(magazines);
                magazinesDetailAdapter.notifyDataSetChanged();

                MuzzlesDetailResponse muzzles= new MuzzlesDetailResponse(getString(R.string.chcoke), "", R.drawable.ic_chcoke);
                muzzlesDetailResponses.add(muzzles);
                muzzlesDetailAdapter.notifyDataSetChanged();

                ScopesDetailResponse scope= new ScopesDetailResponse(getString(R.string.reddot), "", R.drawable.ic_red_dot);
                scopesDetailResponses.add(scope);
                scopesDetailAdapter.notifyDataSetChanged();

                txtlowerrails.setText("No lower rails");
                txtstocks.setText("No stocks");

                nosound.setVisibility(View.VISIBLE);
                power.setProgress(35);
                perpower.setText("35%");
                recoil.setProgress(45);
                perrecoil.setText("45%");
                range.setProgress(37);
                perrange.setText("37%");
                firing.setProgress(90);
                perfiring.setText("90%");
                weaponammos.setText("9 mm");
                imgammos.setImageResource(R.drawable.ic_9_mm);
                namedetail.setText("Skorpion");
                damagedetail.setText("22");
                bulatedetail.setText("350 m/s");
                timebdetail.setText("0.25S");
                firinfdetail.setText("Single,Auto");
                impactdetail.setText("5000");
                magazinedetail.setText("20 rounds");
                extendeddetails.setText("40 rounds");
                pickupdetail.setText("150 ms");
                readydetails.setText("500 ms");
                normaldetails.setText("3.1 second");
                quickdetail.setText("2.5 second");
            }
            else if(wpname.equalsIgnoreCase("Cross-bow")){

                CombinationResponse combination= new CombinationResponse(getString(R.string.scop4x),"",R.drawable.ic_4x_scope);
                combinationResponses.add(combination);
                combination= new CombinationResponse(getString(R.string.quiver),"",R.drawable.ic_quiver_crossbow);
                combinationResponses.add(combination);
                magazinesDetailAdapter.notifyDataSetChanged();

                ScopesDetailResponse
                        scope = new ScopesDetailResponse(getString(R.string.scop6x), "",R.drawable.ic_6x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop4x), "",R.drawable.ic_4x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop3x), "",R.drawable.ic_3x_scope);
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.scop2x), "",R.drawable.ic_2x_scope );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.reddot), "",R.drawable.ic_red_dot );
                scopesDetailResponses.add(scope);
                scope = new ScopesDetailResponse(getString(R.string.holographic), "",R.drawable.ic_holographic);
                scopesDetailResponses.add(scope);
                scopesDetailAdapter.notifyDataSetChanged();

                LowerRailsDetailsResponse lower= new LowerRailsDetailsResponse(getString(R.string.quiver), "Reduces vertical, horizontal recoil and recoil recovery time", R.drawable.ic_quiver_crossbow);
                lowerRailsDetailsResponses.add(lower);
                lowerRailsDetailAdapter.notifyDataSetChanged();

                txtmagazines.setText("No magazines");
                txtmuzzles.setText(" No muzzles");
                txtstocks.setText("No stocks");

                power.setProgress(96);
                perpower.setText("96%");
                recoil.setProgress(20);
                perrecoil.setText("20%");
                range.setProgress(16);
                perrange.setText("16%");
                firing.setProgress(2);
                perfiring.setText("2%");
                weaponammos.setText("Bolt");
                imgammos.setImageResource(R.drawable.ic_quiver_crossbow);
                namedetail.setText("Cross-bow");
                damagedetail.setText("105");
                bulatedetail.setText("160 m/s");
                timebdetail.setText("0.75S");
                firinfdetail.setText("Single");
                impactdetail.setText("8000");
                magazinedetail.setText("1 rounds");
                extendeddetails.setVisibility(View.GONE);
                extended.setVisibility(View.GONE);
                pickupdetail.setText("150 ms");
                readydetails.setText("500 ms");
                normaldetails.setText("3.549 second");
                quickdetail.setText("2.5 second");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void magazinesdata() {
        try {
            magazinesDetailAdapter = new MagazinesDetailAdapter(magazinesDetailResponses, new MagazinesDetailAdapter.AdapterCallback() {
            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
            magazinesrecycler.setLayoutManager(mLayoutManager);
            magazinesrecycler.setItemAnimator(new DefaultItemAnimator());
            magazinesrecycler.setAdapter(magazinesDetailAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void muzzlesdata() {
        try {
            muzzlesDetailAdapter = new MuzzlesDetailAdapter(muzzlesDetailResponses, new MuzzlesDetailAdapter.AdapterCallback() {

            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
            muzzlesrecycler.setLayoutManager(mLayoutManager);
            muzzlesrecycler.setItemAnimator(new DefaultItemAnimator());
            muzzlesrecycler.setAdapter(muzzlesDetailAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void scopedata() {
        try {
            scopesDetailAdapter = new ScopesDetailAdapter(scopesDetailResponses, new ScopesDetailAdapter.AdapterCallback() {

            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
            scopesrecycler.setLayoutManager(mLayoutManager);
            scopesrecycler.setItemAnimator(new DefaultItemAnimator());
            scopesrecycler.setAdapter(scopesDetailAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void stocksdata() {
        try {
            stocksDetailAdapter = new StocksDetailAdapter(stocksDetailResponses, new StocksDetailAdapter.AdapterCallback() {
            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
            stocksrecycler.setLayoutManager(mLayoutManager);
            stocksrecycler.setItemAnimator(new DefaultItemAnimator());
            stocksrecycler.setAdapter(stocksDetailAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void lowerrailsdata() {
        try {
            lowerRailsDetailAdapter = new LowerRailsDetailAdapter(lowerRailsDetailsResponses, new LowerRailsDetailAdapter.AdapterCallback() {
            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
            lowerrailsrecycler.setLayoutManager(mLayoutManager);
            lowerrailsrecycler.setItemAnimator(new DefaultItemAnimator());
            lowerrailsrecycler.setAdapter(lowerRailsDetailAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void bestcombinationdata() {
        try {
            combinationAdapter = new CombinationAdapter(combinationResponses, new CombinationAdapter.AdapterCallback() {
            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            combinationrecycler.setLayoutManager(mLayoutManager);
            combinationrecycler.setItemAnimator(new DefaultItemAnimator());
            combinationrecycler.setAdapter(combinationAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}