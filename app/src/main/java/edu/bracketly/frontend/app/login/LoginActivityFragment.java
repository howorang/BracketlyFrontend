package edu.bracketly.frontend.app.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import edu.bracketly.frontend.R;
import edu.bracketly.frontend.app.BaseFragment;
import edu.bracketly.frontend.navigation.Navigator;


/**
 * A placeholder fragment containing a simple view.
 */
public class LoginActivityFragment extends BaseFragment {

    @BindView(R.id.login_button)
    Button loginButton;

    @BindView(R.id.login_input)
    EditText loginInput;

    @BindView(R.id.password_input)
    EditText passwordInput;

    @Inject
    LoginPresenter presenter;

    public LoginActivityFragment() {
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @OnClick(R.id.login_button)
    void login(View view) {
        presenter.login(loginInput.getText().toString(), passwordInput.getText().toString());
    }

    public void onLogin() {
        Navigator.openMainActivity(getContext());
    }

    public void onBadCredentials() {
        Toast.makeText(getContext(), "Bad credentials", Toast.LENGTH_SHORT).show();
    }
}
