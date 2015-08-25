package com.neoware.foursquaresearchdemo.request;

public class SearchVenuesRequest {
    private String mLocationName;

    public SearchVenuesRequest(String locationName) {
        mLocationName = locationName;
    }

    public String getLocationName() {
        return mLocationName;
    }
}
