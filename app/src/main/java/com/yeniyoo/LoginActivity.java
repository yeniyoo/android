package com.yeniyoo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.yeniyoo.Core.AppController;
import com.yeniyoo.Core.network.DefaultResponse;
import com.yeniyoo.tools.RippleView;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YJLaptop on 2016-07-16.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private CallbackManager mCallbackManager;
    RippleView btnFacebook;
    LoginButton loginButton;

    private void moveToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(final LoginResult loginResult) {
            final String accessToken = loginResult.getAccessToken().getToken();

            AppController.getInstance().getRestService().getUserService().register(accessToken).enqueue(new Callback<DefaultResponse>() {
                @Override
                public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                    if(response.isSuccess()){
                        String token = response.headers().get("auth-token");
                        AppController.getInstance().getLoginManager().clear();
                        AppController.getInstance().getLocalStore().setLoginToken(token);
                        moveToMainActivity();
                    }
                }

                @Override
                public void onFailure(Call<DefaultResponse> call, Throwable t) {

                }
            });
        }

        @Override
        public void onCancel() {
            Log.d("asd", "Asdsa");
        }

        @Override
        public void onError(FacebookException e) {
            Log.d("asd", "Asdsa");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mCallbackManager = CallbackManager.Factory.create();
        btnFacebook = (RippleView) findViewById(R.id.btnFacebook);
        btnFacebook.setOnClickListener(this);
        loginButton = (LoginButton) findViewById(R.id.btnFacebookLogin);
        loginButton.setReadPermissions(Arrays.asList("public_profile"));
        loginButton.registerCallback(mCallbackManager, mCallback);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFacebook:
                loginButton.performClick();
                break;
        }
    }
}
