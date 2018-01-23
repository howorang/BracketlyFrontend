package edu.bracketly.frontend.app.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.bracketly.frontend.R;
import edu.bracketly.frontend.app.BaseFragment;
import edu.bracketly.frontend.navigation.Navigator;


/**
 * A placeholder fragment containing a simple view.
 */
public class LoginActivityFragment extends BaseFragment<LoginPresenter> {

    private Unbinder unbinder;

    @BindView(R.id.login_button)
    Button loginButton;

    @BindView(R.id.login_input)
    EditText loginInput;

    @BindView(R.id.password_input)
    EditText passwordInput;

    public LoginActivityFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.login_button)
    void login(View view) {
        presenter.login(loginInput.getText().toString(), passwordInput.getText().toString());
    }

    public void onLogin() {
        Navigator.openMainActivity(getContext());
    }


    @OnClick(R.id.sign_up_link)
    void onSingUpLinkClick() {
        presenter.onSignUpLinkClick();
    }
}
