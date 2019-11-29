package com.avnhome.aifriender.Services;

import com.avnhome.aifriender.Model.ErrorCode;
import com.avnhome.aifriender.Model.PersonalityOfChart;
import com.avnhome.aifriender.Model.User;
import com.avnhome.aifriender.Model.UserTwitter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IBMService {

    String IBM_SERVICE_URL = "https://service.avnhome.com";

    @GET("/system1/user/bytwitterid")
    Single<List<User>> getUser(@Query("id") String twitterId);

    @POST("/system1/user")
    Call<ErrorCode> createUser(@Body User user);

    @GET("/system1/user/pinsight")
    Call<PersonalityOfChart> getPersonality(@Query("twitterid") String twitterId, @Query("id") String id);

    @PUT("/system1/user/byid?id={id}")
    Call<ErrorCode> updateUser(@Path("id") String id, @Body User user);

    @GET("/system1/user/closest/byid")
    Call<List<User>> getColsest(@Query("id") String id);

}