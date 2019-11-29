package com.avnhome.aifriender.IBMFriender;

import com.avnhome.aifriender.Model.User;
import com.avnhome.aifriender.Services.IBMService;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FrienderManager{

    private static List<User> userList = new ArrayList<>();

    public static User getUser(TwitterSession session){
        IBMService service = IBMFrienderApiClient.getIBMService();
        service.getUser("@realDonaldTrump")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new SingleObserver<List<User>>() {
                  @Override
                  public void onSubscribe(Disposable d) {

                  }

                  @Override
                  public void onSuccess(List<User> users) {
                        userList = new ArrayList<>(users);
                      System.out.println("TIEN: success");
                  }

                  @Override
                  public void onError(Throwable e) {

                  }
              });
        if (!userList.isEmpty()){
            return userList.get(0);
        }else {
            return new User.UserBuilder(session.getUserName()).build();
        }
    }
}
