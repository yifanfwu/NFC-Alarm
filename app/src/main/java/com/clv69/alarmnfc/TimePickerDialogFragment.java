package com.clv69.alarmnfc;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class TimePickerDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getActivity(), AlarmManagerHelper.class);
        alarmIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);

        if (minute == 0) {
            Toast.makeText(getActivity(), "Alarm set for " + hourOfDay + ":00", Toast.LENGTH_LONG).show();
        } else if (minute < 10) {
            Toast.makeText(getActivity(), "Alarm set for " + hourOfDay + ":0" + minute, Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getActivity(), "Alarm set for " + hourOfDay + ":" + minute, Toast.LENGTH_LONG).show();
        }

    }
}