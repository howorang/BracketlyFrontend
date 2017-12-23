package edu.bracketly.frontend.api;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by howor on 23.12.2017.
 */

public abstract class BaseObserver<T> extends DisposableObserver<T> {
    @Override
    public void onError(Throwable e) {
        throw new RuntimeException(e);
    }

    @Override
    public void onComplete() {
        //EMPTY
    }
}
