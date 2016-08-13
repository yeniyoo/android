package com.yeniyoo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

import com.yeniyoo.Core.AppController;
import com.yeniyoo.Core.network.DefaultListener;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * Created by YJLaptop on 2016-07-16.
 */

public class SplashActivity extends AppCompatActivity {
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.activity_flash);


        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.gelato.gelato",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        if (AppController.getInstance().getLoginManager().isLoggedIn()) {
            AppController.getInstance().getLoginManager().loadUser(new DefaultListener() {
                @Override
                public void onSuccess() {
                    activity.finish();
                    moveToMainActivity();
                }

                @Override
                public void onFailure() {
                    AppController.getInstance().getLoginManager().clear();
                    moveToLoginActivity();
                    activity.finish();
                }
            });
        } else {
            moveToLoginActivity();
        }
    }

    private void moveToLoginActivity() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        activity.finish();
    }
    private void moveToMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        activity.finish();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}