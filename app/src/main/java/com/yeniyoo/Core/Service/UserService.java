package com.yeniyoo.Core.Service;

import com.yeniyoo.Core.network.DefaultListener;
import com.yeniyoo.Core.network.DefaultResponse;
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
    @GET("/api/users/")
    Call<User> getUser();

    @FormUrlEncoded
    @POST("/api/users/facebook-auth/")
    Call<DefaultResponse> register(@Field("access_token") String token);

    @FormUrlEncoded
    @POST("/api/users")
    Call<DefaultListener> postAge(@Field("age") Integer age);
}