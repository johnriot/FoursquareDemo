package com.neoware.foursquaresearchdemo.controller;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.neoware.foursquaresearchdemo.R;
import com.neoware.foursquaresearchdemo.model.Venues;
import com.neoware.foursquaresearchdemo.request.SearchVenuesRequest;
import com.neoware.foursquaresearchdemo.response.SearchVenuesResponse;
import com.neoware.foursquaresearchdemo.task.SearchAsyncTask;
import com.neoware.foursquaresearchdemo.view.Presentation;


public class SearchActivity extends AppCompatActivity implements Presentation<SearchVenuesResponse> {
    private static String VENUE_NAME_STATE = "VENUE_NAME_STATE";
    private static int MIN_NUMBER_LETTERS_FOR_SEARCH = 2;
    private LayoutController mLayoutController;
    private EditText mSearchEditText;
    private RecyclerView mRecyclerView;
    private VenuesAdapter mVenuesAdapter;
    private AsyncTask mSearchAsyncTask;
    private String mVenueName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Set support toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get references to the views on-screen
        FloatingActionButton middleFab = (FloatingActionButton) findViewById(R.id.fab_btn_middle);
        FloatingActionButton bottomFab = (FloatingActionButton) findViewById(R.id.fab_btn_bottom);
        TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.text_input);
        mSearchEditText = (EditText) findViewById(R.id.search_et);

        // Set up the RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialise our controller for animation control
        mLayoutController = new LayoutController(this, middleFab, bottomFab, textInputLayout, mRecyclerView);

        // Ensure keyboard will disappear after user presses enter
        detectKeyboardDonePressed();

        // Set up the adapter for controlling the display of Venues
        mVenuesAdapter = new VenuesAdapter(this);
        mRecyclerView.setAdapter(mVenuesAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSearchAsyncTask != null) {
            mSearchAsyncTask.cancel(true);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(VENUE_NAME_STATE, mVenueName);
    }

    @Override
    protected void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);
        mVenueName = inState.getString(VENUE_NAME_STATE, null);
        if (mVenueName != null) {
            startSearchAsyncTask();
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
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void present(SearchVenuesResponse response) {
        mSearchEditText.setText(null);

        // Check if we our request was successful
        if (response.wasRequestSuccessful()) {
            Venues venues = response.getVenues();
            // If there were no venues returned, tell the user and set screen ready for next search
            if (venues.size() == 0) {
                Toast.makeText(this, R.string.no_results_prompt, Toast.LENGTH_LONG).show();
                mLayoutController.onSearchReset();
            // If there were search results, then display them
            } else {
                mVenuesAdapter.setItems(response.getVenues());
                mLayoutController.onSearchResponse();
            }
        // Tell the user there was an error with there request and prompt a retry
        } else {
            Toast.makeText(this, R.string.error_with_request_prompt, Toast.LENGTH_LONG).show();
            mLayoutController.onSearchReset();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Reacting to Button Presses
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void onMiddleFabClick(View view) {
        // Get the text that the user entered
        mVenueName = mSearchEditText.getText().toString();
        if (mVenueName.length() < MIN_NUMBER_LETTERS_FOR_SEARCH) {
            Toast.makeText(this, R.string.enter_two_or_more_characters_prompt, Toast.LENGTH_LONG).show();
            return;
        }

        startSearchAsyncTask();

        // Do some fancy layout stuff
        mLayoutController.onMiddleFabClick();
    }

    public void onBottomFabClick(View view) {
        mVenueName = null;
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

    private void startSearchAsyncTask() {
        mSearchAsyncTask = new SearchAsyncTask(this).execute(new SearchVenuesRequest(mVenueName));
    }
}
