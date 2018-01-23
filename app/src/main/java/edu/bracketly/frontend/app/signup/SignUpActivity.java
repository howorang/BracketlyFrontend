package edu.bracketly.frontend.app.signup;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import edu.bracketly.frontend.R;
import edu.bracketly.frontend.app.BaseActivity;

public class SignUpActivity extends BaseActivity {

    @BindView(R.id.login_input)
    EditText loginInput;

    @BindView(R.id.password_input)
    EditText passwordInput;

    @Inject
    protected SignUpPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.sign_up_button)
    void onSignUpClick() {
        presenter.onSignUpClick();
    }

    public String getUsername() {
        return loginInput.getText().toString();
    }

    public String getPassword() {
        return passwordInput.getText().toString();
    }

    public void displaySignedUpMessage() {
        Toast.makeText(this, "Signed Up!", Toast.LENGTH_LONG).show();
        finish();
    }

    public void displayValidationErrorMessage() {
        Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
    }
}
