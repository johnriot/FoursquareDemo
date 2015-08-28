package com.neoware.foursquaresearchdemo.task;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.neoware.foursquaresearchdemo.boundary.retrofit.RetrofitFoursquareApi;
import com.neoware.foursquaresearchdemo.converter.JsonConverter;
import com.neoware.foursquaresearchdemo.converter.RetrofitConverter;
import com.neoware.foursquaresearchdemo.request.SearchVenuesRequest;
import com.neoware.foursquaresearchdemo.response.SearchVenuesResponse;
import com.neoware.foursquaresearchdemo.view.Presentation;

import java.lang.ref.WeakReference;

import retrofit.RestAdapter;

public class SearchAsyncTask extends AsyncTask<SearchVenuesRequest, Void, SearchVenuesResponse> {
    private static final String TAG = SearchAsyncTask.class.getSimpleName();
    private WeakReference<Presentation<SearchVenuesResponse>> mPresentationRef;
    private RetrofitFoursquareApi mApiService;

    public SearchAsyncTask(Presentation<SearchVenuesResponse> presentation) {
        mPresentationRef = new WeakReference<>(presentation);
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(RetrofitFoursquareApi.ENDPOINT_URL)
                .setConverter(new RetrofitConverter(new JsonConverter(new ObjectMapper(), new SimpleModule())))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        mApiService = restAdapter.create(RetrofitFoursquareApi.class);
    }

    @Override
    protected SearchVenuesResponse doInBackground(SearchVenuesRequest... requests) {
        if (requests == null || requests[0] == null) {
            Log.e(TAG, "Invalid input to SearchAsyncTask: requests: " + requests);
            return null;
        }

        String location = requests[0].getLocationName();
        return mApiService.searchVenues(location);
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
}
