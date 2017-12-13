package com.playground.arch.britt.networkingdemo.network;

import com.playground.arch.britt.networkingdemo.StaticParams;
import com.playground.arch.britt.networkingdemo.network.utils.EnvelopingConverter;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static String FOURSQUARE_BASE_URL = "https://api.foursquare.com/v2/";

    private static HttpLoggingInterceptor logging =
            new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);

    static Interceptor foursquareApiKeyInterceptor = chain -> {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();


        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("client_id", StaticParams.CLIENT_ID)
                .addQueryParameter("client_secret", StaticParams.CLIENT_SECRET)
                .addQueryParameter("v", "20171122")
                .build();

        Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    };

    private static OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .addInterceptor(foursquareApiKeyInterceptor);

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(FOURSQUARE_BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(new EnvelopingConverter())
                    .addConverterFactory(GsonConverterFactory.create());


    private static Retrofit retrofit = retrofitBuilder.build();

    private static FoursquareService foursquareService = retrofit.create(FoursquareService.class);

    public static FoursquareService getFoursquareService() {
        return foursquareService;
    }

}
