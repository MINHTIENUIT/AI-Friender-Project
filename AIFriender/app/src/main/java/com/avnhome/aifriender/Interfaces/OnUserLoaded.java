package com.avnhome.aifriender.Interfaces;

import com.avnhome.aifriender.Model.User;

import java.util.List;

import retrofit2.Response;

public interface OnUserLoaded {
    void onComplete(User user);
    void onFailure(Throwable t);
}
