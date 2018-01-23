package edu.bracketly.frontend.app;

import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Inject;

import edu.bracketly.frontend.dto.ApiError;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.HttpException;

/**
 * Created by howor on 23.12.2017.
 */

public abstract class BasePresenter<T> implements Presenter {

    protected final T view;

    @Inject
    protected Gson gson;
    protected final CompositeDisposable disposable;

    protected BasePresenter(T view) {
        this.view = view;
        this.disposable = new CompositeDisposable();
    }

    public void onDestroy() {
        disposable.clear();
    }

    protected String getErrorMessage(Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            switch (httpException.code()) {
                case 401:
                    return "Bad credentials";
                case 500:
                    String string = null;
                    try {
                        string = httpException.response().errorBody().string();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    ApiError apiError = gson.fromJson(string, ApiError.class);
                    return apiError.getMessage();
                default:
                    return "HTTP: " + httpException.code();
            }
        }
        return throwable.getMessage();
    }
}
