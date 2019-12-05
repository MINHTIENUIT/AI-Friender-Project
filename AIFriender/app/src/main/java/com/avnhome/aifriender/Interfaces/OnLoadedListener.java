package com.avnhome.aifriender.Interfaces;

public interface OnLoadedListener<T> {
    void onComplete(T t);
    void onFailure(Throwable t);
}
