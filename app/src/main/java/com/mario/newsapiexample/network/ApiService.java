package com.mario.newsapiexample.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mario on 07/06/18.
 */

public class ApiService {

    public static final String API_BASE_URL = "https://newsapi.org/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static ApiKeyInterceptor interceptor = new ApiKeyInterceptor();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(API_BASE_URL);

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass) {
        if (!httpClient.interceptors().contains(interceptor)) {
            httpClient.addInterceptor(interceptor);

            builder.client(httpClient.build());
            retrofit = builder.build();

        }

        return retrofit.create(serviceClass);
    }
}
