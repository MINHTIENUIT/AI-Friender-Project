package com.avnhome.aifriender.IBMFriender;

import android.util.Log;

import com.avnhome.aifriender.Callbacks.GetUserCallback;
import com.avnhome.aifriender.Interfaces.OnUserLoaded;
import com.avnhome.aifriender.Model.User;
import com.avnhome.aifriender.Twitter.CustomTwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FrienderManager implements OnUserLoaded{

    private static FrienderManager manager;
    private TwitterSession twitterSession;
    private User user;

    public FrienderManager(TwitterSession session) {
        this.twitterSession = session;

    }

    public static FrienderManager getInstance(TwitterSession twitterSession){
        if (manager == null){
            user = FrienderManager.getUser(twitterSession);
            manager = new FrienderManager(twitterSession);
        }
        return manager;
    }

    private User getLoadUser(){
        User user =
    }


}
