package com.yeniyoo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.yeniyoo.Core.AppController;
import com.yeniyoo.Core.network.DefaultListener;
import com.yeniyoo.tools.RippleView;

import org.json.JSONException;
import org.json.JSONObject;

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

    int adminCount = 0;


    private void registerByFacebook(LoginResult loginResult) {
        final String accessToken = loginResult.getAccessToken().getToken();
        final String id = loginResult.getAccessToken().getUserId();
        Bundle params = new Bundle();
        params.putString("fields", "id,first_name,gender,picture");
        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                String name = object.optString("first_name");
                String gender = object.optString("gender");
                String picture = "";
                try {
                    picture = object.getJSONObject("picture").getJSONObject("data").optString("url");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                AppController.getInstance().getRestService().getUserService().register(id, accessToken, name, picture, gender).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if(response.isSuccess()) {
                            AppController.getInstance().getLoginManager().performFacebookLogin(id, new DefaultListener() {
                                @Override
                                public void onSuccess() {
                                    AppController.getInstance().getLoginManager().loadUser(new DefaultListener() {
                                        @Override
                                        public void onSuccess() {

                                        }

                                        @Override
                                        public void onFailure() {

                                        }
                                    });
                                }

                                @Override
                                public void onFailure() {
                                    Log.d("asd", "asd");
                                }
                            });
                        }else{
                            Log.d("asd","asd");
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Log.d("asd","asd");
                    }
                });
            }
        });
        request.setParameters(params);
        request.executeAsync();
    }

    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(final LoginResult loginResult) {
            final String id = loginResult.getAccessToken().getUserId();
            AppController.getInstance().getLoginManager().performFacebookLogin(
                    id,
                    new DefaultListener() {
                        @Override
                        public void onSuccess() {
                            AppController.getInstance().getLoginManager().loadUser(new DefaultListener() {
                                @Override
                                public void onSuccess() {

                                }

                                @Override
                                public void onFailure() {

                                }
                            });

                        }

                        @Override
                        public void onFailure() {
                            registerByFacebook(loginResult);
                        }
                    });
        }

        @Override
        public void onCancel() {
            Log.d("asd","Asdsa");
        }

        @Override
        public void onError(FacebookException e) {
            Log.d("asd","Asdsa");
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
