package com.avnhome.aifriender.Services;

import com.twitter.sdk.android.core.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TwitterService {
    @GET("/1.1/users/show.json")
    Call<User> getUser(@Query("user_id")long userId);

    @GET("/1.1/users/lookup.json")
    Call<List<User>> lookupUser(@Query("screen_name") String twitterId);

}
