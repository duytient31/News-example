package com.mario.newsapiexample.components.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mario.newsapiexample.components.ui.search.SearchActivity;

/**
 * Created by Mario on 11/06/18.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, SearchActivity.class));
        finish();
    }

}