package com.neoware.foursquaresearchdemo.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.neoware.foursquaresearchdemo.R;


public class SearchActivity extends AppCompatActivity {
    private static String VENUE_NAME_STATE = "VENUE_NAME_STATE";
    private LayoutController mLayoutController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Set support toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialise our controller for animation control
        mLayoutController = new LayoutController(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLayoutController != null) {
            mLayoutController.dispose();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(VENUE_NAME_STATE, mLayoutController.getVenueName());
    }

    @Override
    protected void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);
        String venueName = inState.getString(VENUE_NAME_STATE, null);
        if (venueName != null) {
            mLayoutController.startSearchAsyncTask(venueName);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Reacting to Button Presses
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void onMiddleFabClick(View view) {
        mLayoutController.onMiddleFabClick();
    }

    public void onBottomFabClick(View view) {
        mLayoutController.onBottomFabClick();
    }


}
