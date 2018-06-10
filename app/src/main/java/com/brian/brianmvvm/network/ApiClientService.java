package com.brian.brianmvvm.network;

import android.content.Context;

import com.brian.brianmvvm.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Brian
 * @date: 6/5/18
 */


public final class ApiClientService {

    private Retrofit mRetrofit;

    private GsonConverterFactory mGsonConverterFactory;

    private ApiClientService() {
    }

    public static ApiClientService getInstance() {
        return SingleHelperClass.INSTANCE;
    }

    private Cache makeCache(Context context) {
        final int CACHE_SIZE = 10 * 1024 * 1024;  // 10 MB
        return new Cache(context.getApplicationContext().getCacheDir(), CACHE_SIZE);
    }

    private OkHttpClient makeOkHttpClient(Context context) {
        return new OkHttpClient.Builder()
                .cache(makeCache(context))
                .addInterceptor(makeLoggingInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30000, TimeUnit.MILLISECONDS).build();
    }

    private HttpLoggingInterceptor makeLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);
        return logging;
    }

    private GsonConverterFactory gsonConverterFactory() {
        if (null == mGsonConverterFactory) {
            mGsonConverterFactory = GsonConverterFactory.create();
        }
        return mGsonConverterFactory;
    }

    public Retrofit createService(Context context) {
        if (null == mRetrofit) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.API_URL)
                    .client(makeOkHttpClient(context))
                    .addConverterFactory(gsonConverterFactory())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    private static final class SingleHelperClass {
        private static final ApiClientService INSTANCE = new ApiClientService();
    }
}
