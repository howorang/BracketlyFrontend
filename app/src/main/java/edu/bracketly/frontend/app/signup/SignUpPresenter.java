package edu.bracketly.frontend.app.signup;

import com.google.common.base.Strings;

import javax.inject.Inject;

import edu.bracketly.frontend.api.BaseObserver;
import edu.bracketly.frontend.api.UserApi;
import edu.bracketly.frontend.app.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by howor on 23.01.2018.
 */

public class SignUpPresenter extends BasePresenter<SignUpActivity> {

    private UserApi userApi;

    @Inject
    public SignUpPresenter(SignUpActivity view, UserApi userApi) {
        super(view);
        this.userApi = userApi;
    }

    @Override
    public void onResume() {

    }

    public void onSignUpClick() {
        if (validateFields()) {
            Disposable subscribe = userApi.createUser(view.getUsername(), view.getPassword())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new BaseObserver<Void>() {
                        @Override
                        public void onNext(Void aVoid) {
                            view.displaySignedUpMessage();
                        }

                        @Override
                        public void onError(Throwable e) {
                            handleError(e);
                        }
                    });
            disposable.add(subscribe);
        } else {
            view.displayValidationErrorMessage();
        }
    }

    private void handleError(Throwable throwable) {
        view.displayMessage(getErrorMessage(throwable));
    }

    private boolean validateFields() {
        boolean isUsernameFilled = !Strings.isNullOrEmpty(view.getUsername());
        boolean isPasswordFilled = !Strings.isNullOrEmpty(view.getPassword());
        return isUsernameFilled && isPasswordFilled;
    }
}
