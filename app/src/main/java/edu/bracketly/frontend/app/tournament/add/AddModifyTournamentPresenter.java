package edu.bracketly.frontend.app.tournament.add;

import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.common.base.Strings;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import edu.bracketly.frontend.R;
import edu.bracketly.frontend.api.TournamentApi;
import edu.bracketly.frontend.app.BasePresenter;
import edu.bracketly.frontend.consts.BRACKET_TYPE;
import edu.bracketly.frontend.consts.SEEDING_STRATEGY;
import edu.bracketly.frontend.dto.TournamentDto;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by howor on 25.12.2017.
 */

public class AddModifyTournamentPresenter extends BasePresenter<AddModifyTournamentFragment> {

    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss", Locale.getDefault());
    private static DateFormat prettydateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private static DateFormat prettyHourFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

    private Calendar eventDateCalendar = Calendar.getInstance();

    private TournamentApi tournamentApi;

    private boolean editMode;
    private TournamentDto tournamentDto;

    public void setTournamentDto(TournamentDto tournamentDto) {
        this.tournamentDto = tournamentDto;
    }

    @Inject
    protected AddModifyTournamentPresenter(AddModifyTournamentFragment view, TournamentApi tournamentApi) {
        super(view);
        this.tournamentApi = tournamentApi;
    }

    @Override
    public void onResume() {
        editMode = tournamentDto != null;
        view.bracketTypeSpinner.setAdapter(new ArrayAdapter<>(view.getContext(), R.layout.spinner_simple_item, BRACKET_TYPE.values()));
        view.bracketSeedingSpinner.setAdapter(new ArrayAdapter<>(view.getContext(), R.layout.spinner_simple_item, SEEDING_STRATEGY.values()));
        initFields();
    }

    private void initFields() {
        if (editMode) {
            eventDateCalendar.setTime(tournamentDto.getEventDate());
            updateViewDate();
            view.nameinput.setText(tournamentDto.getName());
            view.setBracketSpinnerValue(tournamentDto.getBracketType());
            view.setSeedingSpinnerValue(tournamentDto.getSeedingStrategy());
        } else {
            Date tomorrow = getTomorrow();
            eventDateCalendar.setTime(tomorrow);
            updateViewDate();
        }
    }

    private Date getTomorrow() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }


    public void onSaveClick() {
        if (!areFieldsValid()) {
            return;
        }
        BRACKET_TYPE bracketType = (BRACKET_TYPE) view.bracketTypeSpinner.getSelectedItem();
        SEEDING_STRATEGY seedingStrategy = (SEEDING_STRATEGY) view.bracketSeedingSpinner.getSelectedItem();
        if (editMode) {
            saveChanges(bracketType, seedingStrategy);
        } else {
            saveNewTournament(bracketType, seedingStrategy);
        }
    }

    private void saveChanges(BRACKET_TYPE bracketType, SEEDING_STRATEGY seedingStrategy) {
        Disposable subscribe = tournamentApi
                .editTournament(tournamentDto.getId(), view.getTournamentName(),
                        dateFormat.format(eventDateCalendar.getTime()), bracketType.name(),
                        seedingStrategy.name()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    Toast.makeText(view.getContext(), "Changes saved", Toast.LENGTH_SHORT).show();
                    view.close();
                });
        disposable.add(subscribe);
    }

    private void saveNewTournament(BRACKET_TYPE bracketType, SEEDING_STRATEGY seedingStrategy) {
        Disposable subscribe = tournamentApi
                .createTournament(view.getTournamentName(), dateFormat.format(eventDateCalendar.getTime()), bracketType.name(), seedingStrategy.name())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    Toast.makeText(view.getContext(), "Added", Toast.LENGTH_SHORT).show();
                    view.close();
                });
        disposable.add(subscribe);
    }

    private boolean areFieldsValid() {
        boolean isTournamentNameFilled = !Strings.isNullOrEmpty(view.getTournamentName());
        return isTournamentNameFilled;
    }

    public void setEventDate(int year, int month, int date) {
        eventDateCalendar.set(Calendar.YEAR, year);
        eventDateCalendar.set(Calendar.MONTH, month);
        eventDateCalendar.set(Calendar.DATE, date);
        updateViewDate();
    }

    public void setEventHour(int hourOfTheDay, int minute) {
        eventDateCalendar.set(Calendar.HOUR_OF_DAY, hourOfTheDay);
        eventDateCalendar.set(Calendar.MINUTE, minute);
        eventDateCalendar.set(Calendar.SECOND, 0);
        updateViewDate();
    }

    private void updateViewDate() {
        Date date = eventDateCalendar.getTime();
        view.eventHourInput.setText(prettyHourFormat.format(date));
        view.eventDateinput.setText(prettydateFormat.format(date));
    }
}
