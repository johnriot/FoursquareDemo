package com.neoware.foursquaresearchdemo.controller;

import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.neoware.foursquaresearchdemo.R;
import com.neoware.foursquaresearchdemo.model.Venues;
import com.neoware.foursquaresearchdemo.request.SearchVenuesRequest;
import com.neoware.foursquaresearchdemo.response.SearchVenuesResponse;
import com.neoware.foursquaresearchdemo.task.SearchAsyncTask;
import com.neoware.foursquaresearchdemo.view.Presentation;

import java.lang.ref.WeakReference;

public class LayoutController implements Presentation<SearchVenuesResponse> {
    private static int MIN_NUMBER_LETTERS_FOR_SEARCH = 2;
    private final FloatingActionButton mMiddleFab;
    private final FloatingActionButton mBottomFab;
    private final TextInputLayout mTextInputLayout;
    private final RecyclerView mRecyclerView;
    private final EditText mSearchEditText;
    private final WeakReference<SearchActivity> mSearchActivityRef;
    private final VenuesAdapter mVenuesAdapter;
    private final Context mContext;
    private AsyncTask mSearchAsyncTask;
    private String mVenueName;

    public LayoutController(SearchActivity activity) {
        mSearchActivityRef = new WeakReference<>(activity);
        mContext = activity.getApplicationContext();

        mMiddleFab = (FloatingActionButton) activity.findViewById(R.id.fab_btn_middle);
        mBottomFab = (FloatingActionButton) activity.findViewById(R.id.fab_btn_bottom);
        mTextInputLayout = (TextInputLayout) activity.findViewById(R.id.text_input);
        mSearchEditText = (EditText) activity.findViewById(R.id.search_et);

        // Set up the RecyclerView
        mRecyclerView = (RecyclerView) activity.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(activity));

        // Ensure keyboard will disappear after user presses enter
        detectKeyboardDonePressed();

        // Set up the adapter for controlling the display of Venues
        mVenuesAdapter = new VenuesAdapter(activity);
        mRecyclerView.setAdapter(mVenuesAdapter);
    }

    public void onMiddleFabClick() {
        SearchActivity activity = mSearchActivityRef.get();

        // Get the text that the user entered
        String venueName = mSearchEditText.getText().toString();
        if (venueName.length() < MIN_NUMBER_LETTERS_FOR_SEARCH) {
            Toast.makeText(activity, R.string.enter_two_or_more_characters_prompt, Toast.LENGTH_LONG).show();
            return;
        }

        startSearchAsyncTask(venueName);

        hideKeyboard();

        mMiddleFab.setImageResource(R.drawable.ic_loading);
        Animation rotate = AnimationUtils.loadAnimation(mContext, R.anim.rotate);
        mMiddleFab.startAnimation(rotate);
    }

    public void onBottomFabClick() {
        mVenueName = null;
        Animation moveDown = AnimationUtils.loadAnimation(mContext, R.anim.move_up);
        moveDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mRecyclerView.setAlpha(0.2f);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mTextInputLayout.setVisibility(View.VISIBLE);
                mRecyclerView.setFocusable(false);
                ignoreRecyclerViewTouches();
                mTextInputLayout.setFocusable(true);

                showKeyboard();

                mBottomFab.setVisibility(View.INVISIBLE);
                mMiddleFab.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mBottomFab.startAnimation(moveDown);
    }



    private void moveMiddleFabDown() {
        Animation moveDown = AnimationUtils.loadAnimation(mContext, R.anim.move_down);
        moveDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mTextInputLayout.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mMiddleFab.setVisibility(View.INVISIBLE);
                mBottomFab.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mMiddleFab.startAnimation(moveDown);
    }

    public void dispose() {
        if (mSearchAsyncTask != null) {
            mSearchAsyncTask.cancel(true);
        }
    }

    public String getVenueName() {
        return mVenueName;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Presentation
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void present(SearchVenuesResponse response) {
        SearchActivity activity = mSearchActivityRef.get();
        mSearchEditText.setText(null);

        // Check if we our request was successful
        if (response.wasRequestSuccessful()) {
            Venues venues = response.getVenues();
            // If there were no venues returned, tell the user and set screen ready for next search
            if (venues.size() == 0) {
                Toast.makeText(activity, R.string.no_results_prompt, Toast.LENGTH_LONG).show();
                onSearchReset();
                // If there were search results, then display them
            } else {
                mVenuesAdapter.setItems(response.getVenues());
                onSearchResponse();
            }
            // Tell the user there was an error with there request and prompt a retry
        } else {
            Toast.makeText(activity, R.string.error_with_request_prompt, Toast.LENGTH_LONG).show();
            onSearchReset();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Helper Methods
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private void onSearchResponse() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerView.setAlpha(1.0f);
        stopIgnoringRecyclerViewTouches();
        mMiddleFab.setImageResource(R.drawable.ic_search);
        moveMiddleFabDown();
    }

    private void onSearchReset() {
        mMiddleFab.clearAnimation();
        mMiddleFab.setImageResource(R.drawable.ic_search);
        showKeyboard();
        mTextInputLayout.setFocusable(true);
    }

    public void startSearchAsyncTask(String venueName) {
        mVenueName = venueName;
        mSearchAsyncTask = new SearchAsyncTask(this).execute(new SearchVenuesRequest(venueName));
    }

    private void ignoreRecyclerViewTouches() {
        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(android.view.View v, MotionEvent event) {
                return true;
            }
        });
    }

    private void stopIgnoringRecyclerViewTouches() {
        mRecyclerView.setOnTouchListener(null);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mMiddleFab.getWindowToken(), 0);
    }

    private void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    private void detectKeyboardDonePressed() {
        mSearchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        onMiddleFabClick();
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
}
