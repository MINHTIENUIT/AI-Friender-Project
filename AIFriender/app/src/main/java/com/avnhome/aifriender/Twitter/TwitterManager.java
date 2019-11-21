package com.avnhome.aifriender.Twitter;

import android.content.Context;

import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

public class TwitterManager {
    private static final String CONSUMER_KEY = "dy68m6OavPEITu5T1fgbIW8sV";
    private static final String CONSUMER_SECRET = "YJq3L0LJ0aFTmM38c8KUaPcaQ0wTthjdyZsRTOkal4FOtoMGZu";
    private static final TwitterAuthConfig mTwitterAuthConfig = new TwitterAuthConfig(CONSUMER_KEY,CONSUMER_SECRET);
    public static void initialize(Context context){
        TwitterConfig twitterConfig = new TwitterConfig.Builder(context)
                .twitterAuthConfig(mTwitterAuthConfig).build();
        Twitter.initialize(twitterConfig);
    }
}
