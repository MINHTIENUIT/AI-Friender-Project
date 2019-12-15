package com.avnhome.aifriender.IBMFriender;

import com.avnhome.aifriender.Interfaces.OnLoadedListener;
import com.avnhome.aifriender.Model.PersonalityOfChart;
import com.avnhome.aifriender.Model.User;
import com.avnhome.aifriender.Services.IBMService;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FrienderManager{

    public static void getUser(String twitterID, final OnLoadedListener listener){
        IBMService service = IBMFrienderApiClient.getIBMService();
//        service.getUser("@"+session.getUserName())
        service.getUser(twitterID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<User>>() {
                  @Override
                  public void onSubscribe(Disposable d) {

                  }

                  @Override
                  public void onSuccess(List<User> users) {
                        if (!users.isEmpty()){
                            listener.onComplete(users.get(8));
                        }else{
                            listener.onFailure(new Throwable("User dose not available"));
                            listener.onComplete(null);
                        }
                  }

                  @Override
                  public void onError(Throwable e) {
                        listener.onFailure(e);
                  }
              });
    }

    public static void getPersonality(String twitterId, String id, final OnLoadedListener<PersonalityOfChart> onLoadedListener){
        IBMService service = IBMFrienderApiClient.getIBMService();
        service.getPersonality(twitterId,id).enqueue(new Callback<PersonalityOfChart>() {
            @Override
            public void onResponse(Call<PersonalityOfChart> call, Response<PersonalityOfChart> response) {
                if (response.isSuccessful() && response.body() != null){
                    onLoadedListener.onComplete(response.body());
                }else{
                    onLoadedListener.onFailure(new Throwable("Get Personality: Failed"));
                }
            }

            @Override
            public void onFailure(Call<PersonalityOfChart> call, Throwable t) {
                onLoadedListener.onFailure(t);
            }
        });
    }

    public static void getFriend(String userId, final OnLoadedListener<List<User>> onLoadedListener){
        IBMService service = IBMFrienderApiClient.getIBMService();
        service.getColsest(userId).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()){
                    onLoadedListener.onComplete(response.body());
                }else{
                    onLoadedListener.onFailure(new Throwable("Load Closest: failed"));
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                onLoadedListener.onFailure(t);
            }
        });
    }
}