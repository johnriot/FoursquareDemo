package com.neoware.foursquaresearchdemo.boundary;

import com.neoware.foursquaresearchdemo.model.Venues;

public interface FoursquareApi {
    Venues searchVenues(String locationName);
}
