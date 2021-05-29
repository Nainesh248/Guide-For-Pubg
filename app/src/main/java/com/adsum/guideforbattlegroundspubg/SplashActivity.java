package com.adsum.guideforbattlegroundspubg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.adsum.guideforbattlegroundspubg.activity.DashBoard;
import com.adsum.guideforbattlegroundspubg.config.CommonFunctions;
import com.adsum.guideforbattlegroundspubg.config.Constants;
import com.adsum.guideforbattlegroundspubg.model.AppConfigModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public class SplashActivity extends AppCompatActivity {
    public static AppConfigModel appConfigModel = new AppConfigModel();
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();

    }

    private void init() {
        try {
            CommonFunctions.setPreference(SplashActivity.this, Constants._true, "");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (CommonFunctions.checkConnection(SplashActivity.this)) {
                            getremoteconfig();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        CommonFunctions.destroyProgressBar();
                    }

                }
            }, 3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void getremoteconfig() {
        try {
            FirebaseApp.initializeApp(this);
            mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();

            FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                    .setMinimumFetchIntervalInSeconds(0)
                    .build();
            mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);

            mFirebaseRemoteConfig.fetchAndActivate()
                    .addOnCompleteListener(this, new OnCompleteListener<Boolean>() {
                        @Override
                        public void onComplete(@NonNull com.google.android.gms.tasks.Task<Boolean> task) {
                            if (task.isSuccessful()) {
                                appConfigModel.IS_GOOGLE = mFirebaseRemoteConfig.getString(Constants.IS_GOOGLE);
                                appConfigModel.IS_FACEBOOK = mFirebaseRemoteConfig.getString(Constants.IS_FACEBOOK);
                                appConfigModel.GOOGLE_BANNER_ID = mFirebaseRemoteConfig.getString(Constants.GOOGLE_BANNER_ID);
                                appConfigModel.FACEBOOK_BANNER_ID = mFirebaseRemoteConfig.getString(Constants.FACEBOOK_BANNER_ID);
                                appConfigModel.GOOGLE_INTERITIAL_ID = mFirebaseRemoteConfig.getString(Constants.GOOGLE_INTERITIAL_ID);
                                appConfigModel.FACEBOOK_INTERITIAL_ID = mFirebaseRemoteConfig.getString(Constants.FACEBOOK_INTERITIAL_ID);
                                appConfigModel.GOOGLE_REWARDED_ID= mFirebaseRemoteConfig.getString(Constants.GOOGLE_REWARDED_ID);
                                appConfigModel.FACEBOOK_REWARDED_ID= mFirebaseRemoteConfig.getString(Constants.FACEBOOK_REWARDED_ID);
                                appConfigModel.GOOGLE_NATIVEAD_ID= mFirebaseRemoteConfig.getString(Constants.GOOGLE_NATIVEAD_ID);
                                appConfigModel.FACEBOOK_NATIVEAD_ID= mFirebaseRemoteConfig.getString(Constants.FACEBOOK_NATIVEAD_ID);
                                Intent i = new Intent(SplashActivity.this, DashBoard.class);
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(SplashActivity.this, "No Internet Connection",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}