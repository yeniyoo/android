package com.yeniyoo.Core.Service;

import com.yeniyoo.Core.network.DefaultListener;
import com.yeniyoo.Model.LoginToken;
import com.yeniyoo.Model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by YongJae on 2016-02-10.
 */
public interface UserService {
    @GET("/user/me/")
    Call<User> getUser();

    @FormUrlEncoded
    @POST("/users/facebook-auth/")
    Call<Boolean> register(@Field("access_token") String token);

    @FormUrlEncoded
    @POST("/user/login/")
    Call<LoginToken> login(@Field("facebook_id") String id);

    @FormUrlEncoded
    @POST("/users")
    Call<DefaultListener> postAge(@Field("age") Integer age);
}