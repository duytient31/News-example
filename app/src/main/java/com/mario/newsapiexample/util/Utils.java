package com.mario.newsapiexample.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static boolean isConnected(Context context) {
        final NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public static String convertDate(String date) {
        final DateTime dateTime = new DateTime(date);
        final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(new Date(dateTime.getMillis()));
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
