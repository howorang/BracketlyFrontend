package edu.bracketly.frontend.app.login;

import javax.inject.Inject;

import edu.bracketly.frontend.api.BasicAuthInterceptor;
import edu.bracketly.frontend.api.TournamentApi;
import edu.bracketly.frontend.app.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by howor on 22.12.2017.
 */

public class LoginPresenter extends BasePresenter<LoginActivityFragment> {

    private BasicAuthInterceptor authInterceptor;

    private TournamentApi tournamentApi;

    @Inject
    public LoginPresenter(LoginActivityFragment view, TournamentApi tournamentApi, BasicAuthInterceptor authInterceptor) {
        super(view);
        this.tournamentApi = tournamentApi;
        this.authInterceptor = authInterceptor;
    }

    public void login(String username, String password) {
        authInterceptor.setCredentials(username, password);
        Disposable subscribe = tournamentApi.getAllTournaments()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(p -> view.onLogin());
        disposable.add(subscribe);
    }
}
