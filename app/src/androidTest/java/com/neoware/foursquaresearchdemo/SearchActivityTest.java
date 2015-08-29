package com.neoware.foursquaresearchdemo;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;

import com.neoware.foursquaresearchdemo.controller.SearchActivity;

import java.util.concurrent.TimeUnit;

public class SearchActivityTest extends ActivityInstrumentationTestCase2<SearchActivity> {

    public SearchActivityTest() {
        super(SearchActivity.class);
    }

    public void testInitialViewsCreatedAndVisible() {
        SearchActivity activity = getActivity();
        FloatingActionButton bottomFab = (FloatingActionButton) activity.findViewById(R.id.fab_btn_bottom);
        FloatingActionButton middleFab = (FloatingActionButton) activity.findViewById(R.id.fab_btn_middle);
        TextInputLayout textInputLayout = (TextInputLayout) activity.findViewById(R.id.text_input);
        EditText searchEditText = (EditText) activity.findViewById(R.id.search_et);

        assertEquals(View.VISIBLE, middleFab.getVisibility());
        assertEquals(View.VISIBLE, textInputLayout.getVisibility());
        assertEquals(View.VISIBLE, searchEditText.getVisibility());
        assertEquals(View.GONE, bottomFab.getVisibility());
    }

    public void testSearchFinishedViewsCreatedAndVisible() throws Throwable {
        final SearchActivity activity = getActivity();
        TextInputLayout textInputLayout = (TextInputLayout) activity.findViewById(R.id.text_input);
        FloatingActionButton bottomFab = (FloatingActionButton) activity.findViewById(R.id.fab_btn_bottom);
        final FloatingActionButton middleFab = (FloatingActionButton) activity.findViewById(R.id.fab_btn_middle);

        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.onMiddleFabClick(middleFab);
                // Pressing the Middle FAB for the second time simulates what will happen in the future when
                // results are loaded. This behaviour will soon change.
                activity.onMiddleFabClick(middleFab);
            }
        });

        final int testTimeOutSeconds = 2;
        try {
            TimeUnit.SECONDS.sleep(testTimeOutSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(View.INVISIBLE, textInputLayout.getVisibility());
        assertEquals(View.INVISIBLE, middleFab.getVisibility());
        assertEquals(View.VISIBLE, bottomFab.getVisibility());
    }
}
