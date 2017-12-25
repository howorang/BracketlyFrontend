package edu.bracketly.frontend.app.login;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import edu.bracketly.frontend.R;
import edu.bracketly.frontend.app.BaseActivity;

;


public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

}
