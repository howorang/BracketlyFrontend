package edu.bracketly.frontend.app.login;

import javax.inject.Inject;

import edu.bracketly.frontend.api.BasicAuthInterceptor;
import edu.bracketly.frontend.api.UserApi;
import edu.bracketly.frontend.app.BasePresenter;
import edu.bracketly.frontend.app.UserContextHelper;
import edu.bracketly.frontend.navigation.Navigator;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by howor on 22.12.2017.
 */

public class LoginPresenter extends BasePresenter<LoginActivityFragment> {

    private BasicAuthInterceptor authInterceptor;

    private UserContextHelper userContextHelper;

    private UserApi userApi;

    @Inject
    public LoginPresenter(LoginActivityFragment view, UserApi userApi, BasicAuthInterceptor authInterceptor,
                          UserContextHelper userContextHelper) {
        super(view);
        this.userApi = userApi;
        this.authInterceptor = authInterceptor;
        this.userContextHelper = userContextHelper;
    }

    @Override
    public void onResume() {

    }

    public void login(String username, String password) {
        authInterceptor.setCredentials(username, password);
        Disposable subscribe = userApi.aboutMe()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userDto -> {
                    userContextHelper.setCurrentUser(userDto);
                    view.onLogin();
                }, this::handleError);
        disposable.add(subscribe);
    }

    void onSignUpLinkClick() {
        Navigator.openSingUpActivity(view.getContext());
    }
}
