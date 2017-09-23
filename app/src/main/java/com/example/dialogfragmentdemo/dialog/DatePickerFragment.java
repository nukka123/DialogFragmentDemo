package com.example.dialogfragmentdemo.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * 日付ダイアログのピッカー例
 * https://developer.android.com/guide/topics/ui/controls/pickers.html#DatePicker
 */
public class DatePickerFragment extends AbstractDialogFragment implements DatePickerDialog.OnDateSetListener {

    public static final String EXTRA_YEAR = "year";
    public static final String EXTRA_MONTH = "month";
    public static final String EXTRA_DAY = "dayOfMonth";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getContext(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Intent data = new Intent();
        data.putExtra(EXTRA_YEAR, year);
        data.putExtra(EXTRA_MONTH, month);
        data.putExtra(EXTRA_DAY, dayOfMonth);
        notifyDialogResult(DialogInterface.BUTTON_POSITIVE, data);
    }

    public static class Builder extends AbstractDialogFragment.Builder {
        @NonNull
        @Override
        protected AbstractDialogFragment build() {
            return new DatePickerFragment();
        }
    }
}
