package edu.bracketly.frontend.app;

import android.widget.Toast;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * Created by howor on 23.12.2017.
 */

public abstract class BaseFragment<P extends Presenter> extends DaggerFragment {

    @Inject
    protected P presenter;

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

    public void displayMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
