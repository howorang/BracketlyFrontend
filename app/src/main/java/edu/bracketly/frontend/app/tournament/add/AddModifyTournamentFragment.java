package edu.bracketly.frontend.app.tournament.add;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import edu.bracketly.frontend.R;
import edu.bracketly.frontend.app.BaseFragment;
import edu.bracketly.frontend.consts.BRACKET_TYPE;
import edu.bracketly.frontend.consts.SEEDING_STRATEGY;
import edu.bracketly.frontend.dto.TournamentDto;
import edu.bracketly.frontend.utils.DatePickerFragment;
import edu.bracketly.frontend.utils.TimePickerFragment;


/**
 * A placeholder fragment containing a simple view.
 */
public class AddModifyTournamentFragment extends BaseFragment<AddModifyTournamentPresenter> {

    public static final String TOURNAMENT_DTO = "tournamentDto";

    @BindView(R.id.name_input)
    EditText nameinput;

    @BindView(R.id.event_date_input)
    EditText eventDateinput;

    @BindView(R.id.event_hour_input)
    EditText eventHourInput;

    @BindView(R.id.bracket_type_spinner)
    Spinner bracketTypeSpinner;

    @BindView(R.id.bracket_seeding_spinner)
    Spinner bracketSeedingSpinner;

    private Unbinder unbinder;

    public AddModifyTournamentFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(TOURNAMENT_DTO)) {
            TournamentDto tournamentDto = (TournamentDto) getArguments().getSerializable(TOURNAMENT_DTO);
            presenter.setTournamentDto(tournamentDto);
        }
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
        presenter.onSaveClick();
    }

    @OnClick(R.id.event_date_input)
    void onEventDateInputClick() {
        showEventDatePickerDialog();
    }

    @OnClick(R.id.event_hour_input)
    void onEventHourInputClick() {
        showEventTimePickerDialog();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public void close() {
        getActivity().finish();
    }

    public String getTournamentName() {
        return nameinput.getText().toString();
    }

    public void showEventDatePickerDialog() {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                presenter.setEventDate(year, month, date);
            }
        });
        newFragment.show(getActivity().getFragmentManager(), "datePicker");
    }

    public void showEventTimePickerDialog() {
        TimePickerFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.setOnTimeSetListener(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfTheDay, int minute) {
                presenter.setEventHour(hourOfTheDay, minute);
            }
        });
        timePickerFragment.show(getActivity().getFragmentManager(), "timePicker");
    }

    void setEventDateInputTextVaule(String value) {
        eventDateinput.setText(value);
    }

    void setEventTimeInputValue(String vaule) {
        eventHourInput.setText(vaule);
    }

    public void setBracketSpinnerValue(BRACKET_TYPE bracketSpinnerValue) {
        setSpinnerValue(bracketTypeSpinner, bracketSpinnerValue);
    }

    public void setSeedingSpinnerValue(SEEDING_STRATEGY spinnerValue) {
        setSpinnerValue(bracketSeedingSpinner, spinnerValue);
    }

    private void setSpinnerValue(Spinner spinner, Object value) {
        int i = 0;
        for (i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(value))
                break;
        }
        spinner.setSelection(i);
    }

}
