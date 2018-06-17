package com.mario.newsapiexample.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import org.joda.time.DateTime;
import org.joda.time.Minutes;

import java.text.SimpleDateFormat;

public class Utils {

    public static boolean isConnected(Context context) {
        final NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public static String calculateTimePassed(String date) {
        final DateTime givenTime = new DateTime(date);
        final DateTime currentTime = new DateTime();
        final SimpleDateFormat formatter = new SimpleDateFormat("MMM d"); // i.e. Feb 7

        final int minutes = Minutes.minutesBetween(givenTime, currentTime).getMinutes();

        // If minutes are bigger than 60 then use hours
        if (minutes > 60) {
            // Convert to hours
            final int hours = minutes / 60;
            // If passed time is more than 24 hours then use days
            if (hours >= 24) {
                // Convert to days
                final int days = hours / 24;
                if (days > 7) {
                    // If passed time is bigger than 7 days then display date
                    return formatter.format(givenTime.getMillis());
                } else {
                    // If days are smaller than 7 then return number of days
                    return days + " days ago";
                }
            } else {
                return hours + " hours ago";
            }
        } else {
            return minutes + " minutes ago";
        }
    }

    public static void showKeyboard(Context context, View view) {
        view.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    public static void hideKeyboard(Context context, View view) {
        view.clearFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
