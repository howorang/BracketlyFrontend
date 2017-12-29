package edu.bracketly.frontend.app.tournament.add;

import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import edu.bracketly.frontend.R;
import edu.bracketly.frontend.api.TournamentApi;
import edu.bracketly.frontend.app.BasePresenter;
import edu.bracketly.frontend.consts.BRACKET_TYPE;
import edu.bracketly.frontend.consts.SEEDING_STRATEGY;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by howor on 25.12.2017.
 */

public class AddTournamentPresenter extends BasePresenter<AddTournamentFragment> {

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss", Locale.getDefault());

    private TournamentApi tournamentApi;

    @Inject
    protected AddTournamentPresenter(AddTournamentFragment view, TournamentApi tournamentApi) {
        super(view);
        this.tournamentApi = tournamentApi;
    }

    @Override
    public void onResume() {
        view.bracketTypeSpinner.setAdapter(new ArrayAdapter<>(view.getContext(), R.layout.spinner_simple_item, BRACKET_TYPE.values()));
        view.bracketSeedingSpinner.setAdapter(new ArrayAdapter<>(view.getContext(), R.layout.spinner_simple_item, SEEDING_STRATEGY.values()));
    }

    public void save() {
        String tournamentName = view.nameinput.getText().toString();
        BRACKET_TYPE bracketType = (BRACKET_TYPE) view.bracketTypeSpinner.getSelectedItem();
        SEEDING_STRATEGY seedingStrategy = (SEEDING_STRATEGY) view.bracketSeedingSpinner.getSelectedItem();

        Disposable subscribe = tournamentApi
                .createTournament(tournamentName, dateFormat.format(new Date()), bracketType.name(), seedingStrategy.name())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    Toast.makeText(view.getContext(), "Added", Toast.LENGTH_SHORT).show();
                    view.close();
                });
        disposable.add(subscribe);
    }
}
