package com.multiplica.cleanarchitecture.mvpapplication.utils;

import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by user on 30/07/18.
 */

public class DateUtils {

    public static String DATE_LARGE = "dd/MMM/yyyy";

    public static long currentTimeMillis() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTimeInMillis();
    }

    public static String formatter(long millis, @NonNull String pattern) {
        if (millis <= 0)
            return "";

        SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return formatter.format(calendar.getTime());
    }

}
