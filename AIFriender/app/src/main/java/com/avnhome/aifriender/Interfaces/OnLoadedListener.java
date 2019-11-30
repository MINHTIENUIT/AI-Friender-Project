package com.avnhome.aifriender.Interfaces;

import com.avnhome.aifriender.Model.User;

public interface OnLoadedListener {
    void onComplete(User user);
    void onFailure(Throwable t);
}
