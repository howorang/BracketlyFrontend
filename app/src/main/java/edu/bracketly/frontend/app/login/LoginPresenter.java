package edu.bracketly.frontend.app.login;

import javax.inject.Inject;

import edu.bracketly.frontend.api.BasicAuthInterceptor;
import edu.bracketly.frontend.api.TournamentAPI;
import edu.bracketly.frontend.app.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by howor on 22.12.2017.
 */

public class LoginPresenter extends BasePresenter<LoginActivityFragment> {

    private BasicAuthInterceptor authInterceptor;

    private TournamentAPI tournamentAPI;

    @Inject
    public LoginPresenter(LoginActivityFragment view, TournamentAPI tournamentAPI, BasicAuthInterceptor authInterceptor) {
        super(view);
        this.tournamentAPI = tournamentAPI;
        this.authInterceptor = authInterceptor;
    }

    public void login(String username, String password) {
        authInterceptor.setCredentials(username, password);
        Disposable subscribe = tournamentAPI.getAllTournaments()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(p -> view.onLogin());
        disposable.add(subscribe);
    }
}
