package com.neoware.foursquaresearchdemo;

import android.test.InstrumentationTestCase;

import com.neoware.foursquaresearchdemo.boundary.mock.MockSearchVenues;
import com.neoware.foursquaresearchdemo.request.SearchVenuesRequest;
import com.neoware.foursquaresearchdemo.response.SearchVenuesResponse;
import com.neoware.foursquaresearchdemo.task.SearchAsyncTask;
import com.neoware.foursquaresearchdemo.view.Presentation;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class SearchAsyncTaskTest extends InstrumentationTestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public final void testSuccessfulSearchForVenues() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        final Presentation<SearchVenuesResponse> response = new Presentation<SearchVenuesResponse>() {
            @Override
            public void present(SearchVenuesResponse response) {
                assertNotNull(response);
                assertTrue(response.getHttpResultCode() == MockSearchVenues.HTTP_SUCCESS_CODE);
                assertTrue(response.getVenues().size() > 0);
                signal.countDown();
            }
        };


        // Execute the async task on the UI thread
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
              new SearchAsyncTask(response).execute(new SearchVenuesRequest(MockSearchVenues.PLACE_NAME));
            }
        });

        // Assure test will timeout if hanging, and that we will be notified of this as a failure
        assertTrue(signal.await(10, TimeUnit.SECONDS));
    }
}
