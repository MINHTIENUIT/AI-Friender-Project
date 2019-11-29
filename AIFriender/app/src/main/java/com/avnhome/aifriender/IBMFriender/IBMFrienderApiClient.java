package com.avnhome.aifriender.IBMFriender;

import com.avnhome.aifriender.Services.IBMService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IBMFrienderApiClient {
    public static Retrofit retrofit = null;

    public static Retrofit instance(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(IBMService.IBM_SERVICE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        return retrofit;
    }

    public static IBMService getIBMService(){
        return instance().create(IBMService.class);
    }
}
