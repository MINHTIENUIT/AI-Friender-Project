package com.avnhome.aifriender.Callbacks;

import android.util.Log;

import com.avnhome.aifriender.IBMFriender.FrienderManager;
import com.avnhome.aifriender.IBMFriender.IBMFrienderApiClient;
import com.avnhome.aifriender.Interfaces.OnUserLoaded;
import com.avnhome.aifriender.Model.ErrorCode;
import com.avnhome.aifriender.Model.User;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUserCallback implements Callback<List<User>> {

    private OnUserLoaded onUserLoaded;
    private TwitterSession session;
    private String twitterId;

    public GetUserCallback(OnUserLoaded onUserLoaded, TwitterSession session, String twitterId) {
        this.onUserLoaded = onUserLoaded;
        this.session = session;
        this.twitterId = twitterId;
    }

    @Override
    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
        if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()){
            Log.e("TIEN", "onResponse: Success: " + response.message());
            onUserLoaded.onComplete(response.body().get(0));
        }else{
            Log.e("TIEN", "onResponse: Success check: " + response.isSuccessful());
            Log.e("TIEN", "onResponse: Success check empty: " + response.body().isEmpty());

            User temp = new User.UserBuilder("@" + twitterId).build();
            Call<ErrorCode> createCall = IBMFrienderApiClient.getIBMService().createUser(temp);
            createCall.enqueue(new Callback<ErrorCode>() {
                @Override
                public void onResponse(Call<ErrorCode> call, Response<ErrorCode> response) {
                    if (response.isSuccessful()){
                        FrienderManager.getUser(session);
                        Log.e("TIEN", "onResponse: CreateUser Success");
                    }else{
                        Log.e("TIEN", "onResponse: CreateUser: Failed");
                    }
                }

                @Override
                public void onFailure(Call<ErrorCode> call, Throwable t) {
                    Log.e("TIEN", "onResponse: CreateUser" + t.toString());
                }
            });
        }
    }

    @Override
    public void onFailure(Call<List<User>> call, Throwable t) {
        onUserLoaded.onFailure(t);
    }
}
