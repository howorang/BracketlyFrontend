package edu.bracketly.frontend.app;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by howor on 23.12.2017.
 */

public abstract class BasePresenter<T> {

    protected final T view;
    protected final CompositeDisposable disposable;

    protected BasePresenter(T view) {
        this.view = view;
        this.disposable = new CompositeDisposable();
    }

    public void onResume() {

    }

    public void onDestroy() {
        disposable.clear();
    }
}
