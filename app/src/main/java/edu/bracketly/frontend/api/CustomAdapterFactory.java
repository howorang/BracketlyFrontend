package edu.bracketly.frontend.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by Piotr Borczyk on 07.01.2018.
 */

public class CustomAdapterFactory extends CallAdapter.Factory {
    private RxJava2CallAdapterFactory wrapped;

    public CustomAdapterFactory(RxJava2CallAdapterFactory wrapped) {
        this.wrapped = wrapped;
    }

    public static CustomAdapterFactory create(RxJava2CallAdapterFactory factory) {
        return new CustomAdapterFactory(factory);
    }

    @Nullable
    @Override
    public CallAdapter<?, ?> get(@NonNull Type returnType, @NonNull Annotation[] annotations, @NonNull Retrofit retrofit) {
        return new CustomAdapter(wrapped.get(returnType, annotations, retrofit));
    }
}
