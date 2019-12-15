package com.avnhome.aifriender.Twitter;

import android.content.Context;

import com.avnhome.aifriender.Interfaces.OnLoadedListener;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TwitterManager {
    private static final String CONSUMER_KEY = "dy68m6OavPEITu5T1fgbIW8sV";
    private static final String CONSUMER_SECRET = "YJq3L0LJ0aFTmM38c8KUaPcaQ0wTthjdyZsRTOkal4FOtoMGZu";
    private static final TwitterAuthConfig mTwitterAuthConfig = new TwitterAuthConfig(CONSUMER_KEY,CONSUMER_SECRET);
    public static void initialize(Context context){
        TwitterConfig twitterConfig = new TwitterConfig.Builder(context)
                .twitterAuthConfig(mTwitterAuthConfig).build();
        Twitter.initialize(twitterConfig);
    }

    public static void getLookupUser(String twitterID, final OnLoadedListener<User> listener){
        TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        if (session == null) return;

        final String screen_name = twitterID.replace("@","");
        Call<List<User>> call = new CustomTwitterApiClient(session).getTwitterService().lookupUser(screen_name);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()){
                    listener.onComplete(response.body().get(0));
                }else {
                    listener.onFailure(new Throwable("Twitter " + screen_name + " is not found"));
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                listener.onFailure(t);
            }
        });
    }
}
