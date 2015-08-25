package com.neoware.foursquaresearchdemo.boundary;

import com.neoware.foursquaresearchdemo.model.Venues;

import java.io.IOException;

public interface FoursquareApi {
    Venues searchVenues(String locationName) throws IOException;
}
