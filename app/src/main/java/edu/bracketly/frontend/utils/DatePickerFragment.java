package edu.bracketly.frontend.utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import java.util.Calendar;

/**
 * Created by howor on 23.01.2018.
 */

public class DatePickerFragment extends DialogFragment {

    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private boolean disablePastDates = true;

    public void setOnDateSetListener(DatePickerDialog.OnDateSetListener onDateSetListener) {
        this.onDateSetListener = onDateSetListener;
    }

    public void setDisablePastDates(boolean disablePastDates) {
        this.disablePastDates = disablePastDates;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), onDateSetListener, year, month, day);
        if (disablePastDates) {
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        }
        return datePickerDialog;
    }
}