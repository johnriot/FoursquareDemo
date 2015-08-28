package com.neoware.foursquaresearchdemo.boundary.mock;

import com.neoware.foursquaresearchdemo.model.Venue;
import com.neoware.foursquaresearchdemo.model.Venues;

import java.util.Arrays;

public interface MockSearchVenues {

    Venue TRE_YSGAWEN_HALL = new Venue("Tre-Ysgawen Hall Country House Hotel & Spa Llangefni");
    Venue ORIEL_YNYS_MON = new Venue("Oriel Ynys Mon");
    Venue THEATR_FACH = new Venue("Theatr Fach");

    Venues FOURSQUARE_VENUES = new Venues(Arrays.asList(TRE_YSGAWEN_HALL, ORIEL_YNYS_MON, THEATR_FACH));

    String PLACE_NAME = "Anglesey";

    String SEARCH_VENUES_FILE_NAME = "search_venues.json";

    int HTTP_SUCCESS_CODE = 200;
}
