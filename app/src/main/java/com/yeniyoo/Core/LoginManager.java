package com.yeniyoo.Core;

import android.content.SharedPreferences;

import com.yeniyoo.Core.network.DefaultListener;
import com.yeniyoo.Model.LoginToken;
import com.yeniyoo.Model.User;

import java.util.concurrent.Semaphore;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Mathpresso3 on 2015-08-02.
 */

public class LoginManager {
    // Singleton!
    private User mUser;
    private String mLoginToken;

    /*
     * Empty user
     */
    private User mEmptyUser;

    /*
     * This lock is used to forbid multiple concurrent login request
     */
    private Semaphore lock = new Semaphore(1);

    /*
     * constructor
     */
    public LoginManager() {
        LocalStore localStore = AppController.getInstance().getLocalStore();

        mEmptyUser = new User();

        mUser = localStore.getUser();
        mLoginToken = localStore.getLoginToken();
    }

    /*
     * clear
     * Clear all login information (=logout)
     *
     * @return  boolean  True if successfully cleared, False if not.
     */
    public void clear() {
        try {
            // Acquire lock, to prevent the following flow:
            //  performLogin() -> clear() -> setLoginToken()
            lock.acquire();
            try {
                mUser = null;
                mLoginToken = null;
                AppController.getInstance().getLocalStore().clearLoginData();
            }
            finally {
                lock.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
     * isLoggedIn
     */
    public boolean isLoggedIn() {
        if (mLoginToken != null) {
            return true;
        }
        return false;
    }

    /*
     * getLoginToken
     */
    public String getLoginToken() {
        if(mLoginToken != null) {
            return mLoginToken;
        }else{
            return "";
        }
    }

    /*
     * setLoginToken
     */
    private void setLoginToken(String loginToken) {
        this.mLoginToken = loginToken;
        AppController.getInstance().getLocalStore().storeLoginToken(loginToken);
    }
    /*
     * setUser
     */
    private void setUser(User user) {
        this.mUser = user;
       AppController.getInstance().getLocalStore().storeUser(user);
    }


    /*
     * performFacebookLogin
     *  - send login request
     *  - on success:
     *    - loadUser()
     *    - setLoginToken()
     *
     * @return  boolean  True if request is created, False if not.
     *//*
     * getUser
     */


    public User getUser() {
        if(mUser != null) {
            return mUser;
        }
        return mEmptyUser;
    }

    /*
     * loadUser
     *  - send user profile request
     *  - on success: set mUser
     */
    public void loadUser(final DefaultListener listener) {
        Call<User> userCall = AppController.getInstance().getRestService().getUserService().getUser();
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccess()) {
                    setUser(response.body());
                    listener.onSuccess();
                }else{
                    listener.onFailure();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onFailure();
            }
        });
    }

    public void performFacebookLogin(String id, final DefaultListener defaultListener) {
        AppController.getInstance().getRestService().getUserService().login(id).enqueue(new Callback<LoginToken>() {
            @Override
            public void onResponse(Call<LoginToken> call, Response<LoginToken> response) {
                if(response.isSuccess()){
                    setLoginToken(response.body().getToken());
                    defaultListener.onSuccess();
                }
                else{
                    defaultListener.onFailure();
                }
            }

            @Override
            public void onFailure(Call<LoginToken> call, Throwable t) {
                defaultListener.onFailure();
            }
        });
    }
}