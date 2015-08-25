package com.neoware.foursquaresearchdemo.response;

import com.neoware.foursquaresearchdemo.model.Venues;

public class SearchVenuesResponse {

    private Venues mVenues;

    public SearchVenuesResponse(Venues venues) {
        mVenues = venues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchVenuesResponse response = (SearchVenuesResponse) o;

        if (mVenues != null ? !mVenues.equals(response.mVenues) : response.mVenues != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return mVenues != null ? mVenues.hashCode() : 0;
    }
}
