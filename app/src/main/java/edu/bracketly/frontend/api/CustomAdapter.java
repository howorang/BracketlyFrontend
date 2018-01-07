package edu.bracketly.frontend.api;

import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by Piotr Borczyk on 07.01.2018.
 */

public class CustomAdapter<R> implements CallAdapter<R, Object>  {

    private CallAdapter callAdapter;

    public CustomAdapter(CallAdapter callAdapter) {
        this.callAdapter = callAdapter;
    }

    @Override
    public Type responseType() {
        return callAdapter.responseType();
    }

    @Override
    public Object adapt(Call<R> call) {
        Observable observable = (Observable<?>) callAdapter.adapt(call);
        observable.onErrorResumeNext(new Function<Throwable, ObservableSource>() {
            @Override
            public ObservableSource apply(Throwable throwable) throws Exception {
                return Observable.error(throwable);
            }
        });
        return observable;
    }
}
