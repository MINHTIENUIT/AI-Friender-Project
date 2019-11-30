package com.avnhome.aifriender.IBMFriender;

import com.avnhome.aifriender.Interfaces.OnLoadedListener;
import com.avnhome.aifriender.Model.User;
import com.avnhome.aifriender.Services.IBMService;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FrienderManager{

    public static void getUser(TwitterSession session, final OnLoadedListener listener){
        IBMService service = IBMFrienderApiClient.getIBMService();
        service.getUser("@"+session.getUserName() + "12")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<User>>() {
                  @Override
                  public void onSubscribe(Disposable d) {

                  }

                  @Override
                  public void onSuccess(List<User> users) {
                        if (!users.isEmpty()){
                            listener.onComplete(users.get(0));
                        }else{
                            listener.onComplete(null);
                        }
                  }

                  @Override
                  public void onError(Throwable e) {
                        listener.onFailure(e);
                  }
              });
    }
}