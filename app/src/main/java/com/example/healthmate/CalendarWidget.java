package com.example.healthmate;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CalendarWidget extends AppCompatActivity {
    private void updateDaysOfWeek() {
        Calendar firstDayOfWeek = Calendar.getInstance();
        firstDayOfWeek.set(Calendar.DAY_OF_WEEK, firstDayOfWeek.getFirstDayOfWeek());

        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd", Locale.getDefault());
        for (int i = 0; i < 7; i++) {
            TextView dayTextView = findViewById(getDayTextViewId(i));
            dayTextView.setText(sdf.format(firstDayOfWeek.getTime()));
            firstDayOfWeek.add(Calendar.DAY_OF_MONTH, 1);
        }
    }
    private int getDayTextViewId(int index) {
        switch (index) {
            case 0:
                return R.id.sun;
            case 1:
                return R.id.mon;
            case 2:
                return R.id.tue;
            case 3:
                return R.id.wed;
            case 4:
                return R.id.thu;
            case 5:
                return R.id.fri;
            case 6:
                return R.id.sat;
            default:
                return -1;
        }
    }


}
