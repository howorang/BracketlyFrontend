package edu.bracketly.frontend.app.login;

import javax.inject.Inject;

import edu.bracketly.frontend.api.BaseObserver;
import edu.bracketly.frontend.api.BasicAuthInterceptor;
import edu.bracketly.frontend.api.UserApi;
import edu.bracketly.frontend.app.BasePresenter;
import edu.bracketly.frontend.dto.UserDetailsDto;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * Created by howor on 22.12.2017.
 */

public class LoginPresenter extends BasePresenter<LoginActivityFragment> {

    private BasicAuthInterceptor authInterceptor;

    private UserApi userApi;

    @Inject
    public LoginPresenter(LoginActivityFragment view, UserApi userApi, BasicAuthInterceptor authInterceptor) {
        super(view);
        this.userApi = userApi;
        this.authInterceptor = authInterceptor;
    }

    public void login(String username, String password) {
        authInterceptor.setCredentials(username, password);
        Disposable subscribe = userApi.aboutMe()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new BaseObserver<UserDetailsDto>() {
                    @Override
                    public void onNext(UserDetailsDto userDetailsDto) {
                        view.onLogin();
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleError(e);
                    }
                });
        disposable.add(subscribe);
    }

    private void handleError(Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            switch (httpException.code()) {
                case 401:
                    view.onBadCredentials();
                    break;
            }
        }
    }
}
