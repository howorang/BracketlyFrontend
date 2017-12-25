package edu.bracketly.frontend.app.tournament.add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.bracketly.frontend.R;
import edu.bracketly.frontend.app.BaseFragment;


/**
 * A placeholder fragment containing a simple view.
 */
public class AddTournamentFragment extends BaseFragment<AddTournamentPresenter> {

    @BindView(R.id.name_input)
    EditText nameinput;

    @BindView(R.id.event_date_input)
    EditText eventDateinput;

    @BindView(R.id.bracket_type_spinner)
    Spinner bracketTypeSpinner;

    @BindView(R.id.bracket_seeding_spinner)
    Spinner bracketSeedingSpinner;

    private Unbinder unbinder;

    public AddTournamentFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_tournament, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.save_button)
    public void onSaveButtonClick(View view) {
        presenter.save();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public void close() {
        getActivity().finish();
    }
}
