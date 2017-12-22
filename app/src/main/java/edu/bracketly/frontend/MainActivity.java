package edu.bracketly.frontend;

import android.os.Bundle;

import edu.bracketly.frontend.app.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
