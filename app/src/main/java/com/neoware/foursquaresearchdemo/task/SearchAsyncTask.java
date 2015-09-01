package com.neoware.foursquaresearchdemo.task;

import android.os.AsyncTask;
import android.util.Log;

import com.neoware.foursquaresearchdemo.boundary.retrofit.FoursquareApiBuilder;
import com.neoware.foursquaresearchdemo.boundary.retrofit.RetrofitCheckedException;
import com.neoware.foursquaresearchdemo.boundary.retrofit.RetrofitFoursquareApi;
import com.neoware.foursquaresearchdemo.request.SearchVenuesRequest;
import com.neoware.foursquaresearchdemo.response.SearchVenuesResponse;
import com.neoware.foursquaresearchdemo.view.Presentation;

import java.lang.ref.WeakReference;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;

public class SearchAsyncTask extends AsyncTask<SearchVenuesRequest, Void, SearchVenuesResponse> {
    private static final String TAG = SearchAsyncTask.class.getSimpleName();
    private WeakReference<Presentation<SearchVenuesResponse>> mPresentationRef;
    private RetrofitFoursquareApi mApiService;
    private ErrorHandler mErrorHandler;

    public SearchAsyncTask(Presentation<SearchVenuesResponse> presentation) {
        mPresentationRef = new WeakReference<>(presentation);
        mErrorHandler = new RetrofitErrorHandler();
        mApiService = FoursquareApiBuilder.buildApiService(mErrorHandler);
    }

    @Override
    protected SearchVenuesResponse doInBackground(SearchVenuesRequest... requests) {
        if (requests == null || requests[0] == null) {
            Log.e(TAG, "Invalid input to SearchAsyncTask: requests: " + requests);
            return null;
        }

        String location = requests[0].getLocationName();
        SearchVenuesResponse searchVenuesResponse = null;
        try {
            searchVenuesResponse = mApiService.searchVenues(location);
        } catch (RetrofitCheckedException e) {
            searchVenuesResponse = new SearchVenuesResponse(false, null);
        }
        return searchVenuesResponse;
    }

    @Override
    protected void onPostExecute(SearchVenuesResponse venues) {
        Presentation<SearchVenuesResponse> presentation = mPresentationRef.get();
        if (presentation != null) {
            presentation.present(venues);
        }
        else {
            Log.e(TAG, "Activity dead before search returned.");
        }
    }

    class RetrofitErrorHandler implements ErrorHandler {
        @Override
        public Throwable handleError(RetrofitError cause) {
            return new RetrofitCheckedException(cause);
        }
    }
}


