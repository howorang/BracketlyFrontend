package edu.bracketly.frontend.app.match;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import frontend.bracketly.edu.bracketlyapp.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MatchActivityFragment extends Fragment {

    public MatchActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_match, container, false);
    }
}
