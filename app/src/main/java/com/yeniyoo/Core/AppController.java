package com.yeniyoo.Core;

import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import org.apache.james.mime4j.codec.DecodeMonitor;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static org.apache.james.mime4j.codec.DecoderUtil.decodeEncodedWords;

/**
 * Created by YJLaptop on 2016-07-16.
 */
public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();
    private LocalStore mLocalStore;
    private RestService mRestService;
    public Boolean DEBUG;
    private Retrofit mRetrofit;

    // Instance
    private static AppController mInstance;

    private final Interceptor AUTHENTICATION_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            String loginToken = getLocalStore().getLoginToken();
            Request original = chain.request();
            Request.Builder builder = original.newBuilder().addHeader("Version", String.valueOf(getAppVersion()));
            if (loginToken != null) {
                builder = builder.addHeader("Authorization", String.format("Token %s", loginToken));
            }
            builder.addHeader("Content-Type", "charset=utf-8");
            Request request = builder.build();
            Response response = chain.proceed(request);
            return response;
        }
    };

    private static int getAppVersion() {
        try {
            return getInstance().getPackageManager().getPackageInfo(AppController.getInstance().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private final Interceptor TOAST_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Response response = chain.proceed(original);

            if (response.headers().get("alert") != null) {
                String error = decodeEncodedWords(response.headers().get("alert"), DecodeMonitor.SILENT);
                Intent intent = new Intent("com.yeniyoo.core.toastreciever");
                intent.putExtra("msg", error);
                sendBroadcast(intent);
                Log.d("interceptor_error_log", error);
            }
            return response;
        }
    };


    public Retrofit getRetrofit() {
        if (mRetrofit == null) {
            File httpCacheDirectory = new File(getInstance().getExternalCacheDir(), "responses");
            int cacheSize = 10 * 1024 * 1024; // 10 MiB
            Cache cache = new Cache(httpCacheDirectory, cacheSize);
            OkHttpClient okClient = null;
            okClient = new OkHttpClient.Builder()
                    .cache(cache)
                    .addInterceptor(AUTHENTICATION_INTERCEPTOR)
                    .addInterceptor(TOAST_INTERCEPTOR)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JodaModule());
            objectMapper.setPropertyNamingStrategy(
                    PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
            JacksonConverterFactory jacksonConverterFactory = JacksonConverterFactory.create(objectMapper);
            RxJavaCallAdapterFactory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(AppController.getInstance().getLocalStore().getBaseUrl())
                    .client(okClient)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .addConverterFactory(jacksonConverterFactory)
                    .build();
        }
        return mRetrofit;
    }

    public RestService getRestService() {
        if (mRestService == null) {
            mRestService = new RestService();
        }
        return mRestService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Fresco.initialize(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public LocalStore getLocalStore() {
        if (mLocalStore == null) {
            mLocalStore = new LocalStore(getApplicationContext());
        }
        return mLocalStore;
    }
}
