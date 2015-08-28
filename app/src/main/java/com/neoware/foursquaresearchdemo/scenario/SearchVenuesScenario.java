package com.neoware.foursquaresearchdemo.scenario;

import com.neoware.foursquaresearchdemo.boundary.FoursquareApi;
import com.neoware.foursquaresearchdemo.request.SearchVenuesRequest;
import com.neoware.foursquaresearchdemo.response.SearchVenuesResponse;
import com.neoware.foursquaresearchdemo.view.Presentation;

import java.io.IOException;

public class SearchVenuesScenario implements Runnable {

    private final Presentation<SearchVenuesResponse> mPresentation;
    private final FoursquareApi mFoursquareApi;
    private final SearchVenuesRequest mRequest;

    public SearchVenuesScenario(Presentation<SearchVenuesResponse> presentation, FoursquareApi githubApi, SearchVenuesRequest request) {
        mPresentation = presentation;
        mFoursquareApi = githubApi;
        mRequest = request;
    }

    @Override
    public void run() {
        String locationName = mRequest.getLocationName();
        SearchVenuesResponse response = null;
        try {
            response = mFoursquareApi.searchVenues(locationName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mPresentation.present(response);
    }
}
