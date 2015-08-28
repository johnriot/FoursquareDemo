package com.neoware.foursquaresearchdemo.boundary;

import com.neoware.foursquaresearchdemo.response.SearchVenuesResponse;

import java.io.IOException;

public interface FoursquareApi {
    SearchVenuesResponse searchVenues(String locationName) throws IOException;
}
