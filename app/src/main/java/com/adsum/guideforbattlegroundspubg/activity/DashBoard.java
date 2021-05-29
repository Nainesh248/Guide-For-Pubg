package com.adsum.guideforbattlegroundspubg.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseLongArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.adsum.guideforbattlegroundspubg.R;
import com.adsum.guideforbattlegroundspubg.SplashActivity;
import com.adsum.guideforbattlegroundspubg.config.CommonFunctions;
import com.adsum.guideforbattlegroundspubg.config.Constants;
import com.adsum.guideforbattlegroundspubg.fragment.CompareFragment;
import com.adsum.guideforbattlegroundspubg.fragment.HomeFragment;
import com.adsum.guideforbattlegroundspubg.fragment.MapsFragment;
import com.adsum.guideforbattlegroundspubg.fragment.TipsFragment;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.intellij.lang.annotations.Language;

import java.util.Arrays;
import java.util.List;

public class DashBoard extends AppCompatActivity {
    String google_banner, google_interitial, google_rewarded, google_native;
    String fb_banner, fb_interitial, fb_rewarded, fb_native;

    BottomNavigationView bottomNav;
    FrameLayout fragmentContainer;
    DrawerLayout drawerLayout;
    NavigationView nvView;
    ImageView menu;
    TextView advance;
    View adContainer;
    private AdView mAdView;
    com.facebook.ads.AdView fbAdView;

    boolean showAdds = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        bottomNav  = (BottomNavigationView) findViewById(R.id.bottomnavigation);
        fragmentContainer  = (FrameLayout) findViewById(R.id.fragment_container);
        drawerLayout  = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvView  = (NavigationView) findViewById(R.id.nav_view);
        menu  = (ImageView) findViewById(R.id.img_menu);
        adContainer = findViewById(R.id.adMobView);

        advance = (TextView) findViewById(R.id.advance);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        List<String> testDeviceIds = Arrays.asList("552DE1A067B9147A504354A81D082946");
        RequestConfiguration configuration =
                new RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build();
        AdSettings.addTestDevice("4a203f45-6037-4093-9775-54d7250fda28");

        AudienceNetworkAds.initialize(this);
        mAdView = new AdView(this);
        mAdView.setAdSize(AdSize.LARGE_BANNER);
        google_banner = SplashActivity.appConfigModel.GOOGLE_BANNER_ID;
        google_interitial = SplashActivity.appConfigModel.GOOGLE_INTERITIAL_ID;
        google_rewarded = SplashActivity.appConfigModel.GOOGLE_REWARDED_ID;
        fb_banner = SplashActivity.appConfigModel.FACEBOOK_BANNER_ID;
        fb_interitial = SplashActivity.appConfigModel.FACEBOOK_INTERITIAL_ID;
        fb_rewarded = SplashActivity.appConfigModel.FACEBOOK_REWARDED_ID;
        fb_native = SplashActivity.appConfigModel.FACEBOOK_NATIVEAD_ID;
        google_native = SplashActivity.appConfigModel.GOOGLE_NATIVEAD_ID;
        if (SplashActivity.appConfigModel.IS_GOOGLE.equalsIgnoreCase(Constants.VAL_TRUE)) {
            load_bannerAd_admob();
        } else {
            load_bannerAd_fb();
        }

        init();
    }

    private void init() {
        try {
            bottomNav.setSelectedItemId(R.id.navigation_home);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            bottomMenu();
            menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drawerLayout.openDrawer(GravityCompat.START);

                }
            });
            nvView.inflateMenu(R.menu.drawer_menu);

            setupDrawerContent(nvView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectDrawerItem(menuItem);
                return true;
            }
        });
    }
    private void load_bannerAd_admob() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = new AdView(this);
        mAdView.setAdSize(AdSize.BANNER);
        mAdView.setAdUnitId(google_banner);
        AdRequest adRequest = new AdRequest.Builder().build();
        if(this.showAdds) {
            ((RelativeLayout) adContainer).addView(mAdView);
            mAdView.loadAd(adRequest);
        }

    }
    private void load_bannerAd_fb() {
        fbAdView = new com.facebook.ads.AdView(DashBoard.this, fb_banner, com.facebook.ads.AdSize.BANNER_HEIGHT_50);
        ((RelativeLayout) adContainer).addView(fbAdView);
        AdListener adListener = new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e("TAG", "onError-------- " + ad + "   " + adError);

                Toast.makeText(
                        DashBoard.this,
                        "Error: " + adError.getErrorMessage(),
                        Toast.LENGTH_LONG)
                        .show();
            }

            @Override
            public void onAdLoaded(Ad ad) {
                Log.e("TAG", "onAdLoaded-------- " + ad);
                // Ad loaded callback
            }

            @Override
            public void onAdClicked(Ad ad) {
                Log.e("TAG", "onAdClicked-------- " + ad);
                // Ad clicked callback
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                Log.e("TAG", "onLoggingImpression-------- " + ad);
                // Ad impression logged callback
            }
        };
        if(this.showAdds) {
            fbAdView.loadAd(fbAdView.buildLoadAdConfig().withAdListener(adListener).build());
        }


    }
    private void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass = null;
        MenuItem item = null;
        drawerLayout.closeDrawers();
        switch (menuItem.getItemId()) {
            case R.id.nav_rate:
                rateus();
                break;

            case R.id.nav_share:
                shareapp();
                return;
            default:
                fragmentClass = HomeFragment.class;

        }

        try {
            if (fragmentClass != null) {
                fragment = (Fragment) fragmentClass.newInstance();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        menuItem.setChecked(true);

        setTitle(menuItem.getTitle());

        drawerLayout.closeDrawers();
    }
    public void shareapp() {
        try {
            Intent sharingIntent = new Intent("android.intent.action.SEND");
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra("android.intent.extra.TEXT", "Here's an amazing and free app for Guide Battleground Pubg! DOWNLOAD NOW! https://play.google.com/store/apps/details?id=com.adsum.guideforbattlegroundspubg");
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        } catch (Exception unused) {
        }
    }
    @SuppressLint("WrongConstant")
    private void rateus() {
        StringBuilder sb = new StringBuilder();
        sb.append("market://details?id=");
        sb.append(getPackageName());
        String str = "android.intent.action.VIEW";
        Intent intent = new Intent(str, Uri.parse(sb.toString()));
        intent.addFlags(1208483840);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("http://play.google.com/store/apps/details?id=com.adsum.guideforbattlegroundspubg");
            sb2.append(getPackageName());
            startActivity(new Intent(str, Uri.parse(sb2.toString())));
        }
    }
    private void bottomMenu() {
        bottomNav.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        Fragment fragment = null;
                        MenuItem item = null;
                        switch (menuItem.getItemId()) {
                            case R.id.navigation_home:
                                item = bottomNav.getMenu().findItem(R.id.navigation_home);
                                item.setChecked(true);
                                fragment = new HomeFragment();
                                advance.setText(R.string.battlegruondmobilequide);
                                menu.setVisibility(View.VISIBLE);
                                break;
                            case R.id.navigation_compare:
                                item = bottomNav.getMenu().findItem(R.id.navigation_compare);
                                item.setChecked(true);
                                fragment = new CompareFragment();
                                advance.setText(R.string.title_compare);
                                menu.setVisibility(View.GONE);
                                break;
                            case R.id.navigation_tips:
                                item = bottomNav.getMenu().findItem(R.id.navigation_tips);
                                item.setChecked(true);
                                fragment = new TipsFragment();
                                advance.setText(R.string.title_tips);
                                menu.setVisibility(View.GONE);
                                break;
                            case R.id.navigation_maps:
                                item = bottomNav.getMenu().findItem(R.id.navigation_maps);
                                item.setChecked(true);
                                fragment = new MapsFragment();
                                advance.setText(R.string.title_maps);
                                menu.setVisibility(View.GONE);
                                break;
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                        return false;
                    }

                });
    }
    public boolean addFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}