package com.neoware.foursquaresearchdemo.controller;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.neoware.foursquaresearchdemo.R;


public class SearchActivity extends AppCompatActivity {
    private CardView mVenueCard;
    private LayoutController mLayoutController;
    private EditText mSearchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mVenueCard = (CardView) findViewById(R.id.venue_card);

        FloatingActionButton middleFab = (FloatingActionButton) findViewById(R.id.fab_btn_middle);
        FloatingActionButton bottomFab = (FloatingActionButton) findViewById(R.id.fab_btn_bottom);
        TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.text_input);
        mLayoutController = new LayoutController(this, middleFab, bottomFab, textInputLayout);

        mSearchEditText = (EditText) findViewById(R.id.search_et);
        detectKeyboardDonePressed();
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
        // Hide the keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        // Do some fancy layout stuff
        mLayoutController.onMiddleFabClick();
    }

    public void onBottomFabClick(View view) {
        mLayoutController.onBottomFabClick();
    }

    private void detectKeyboardDonePressed() {
        mSearchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        onMiddleFabClick(v);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
}
