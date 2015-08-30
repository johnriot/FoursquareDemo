package com.neoware.foursquaresearchdemo.boundary.mock;

import com.neoware.foursquaresearchdemo.model.Venue;
import com.neoware.foursquaresearchdemo.model.Venues;

import java.util.Arrays;

public interface MockSearchVenues {

    String MOCK_PHOTO_URL = "www.does.not.exist.probably";

    Venue BOROUGH_MARKET = new Venue("Borough Market", "https://irs3.4sqi.net/img/general/720x960/15524443_5aXijcZjea06SB2r03axIl-QgxYyvn3BlwO0Zvx9Zwc.jpg");
    Venue PRIMROSE_HILL = new Venue("Primrose Hill", "https://irs0.4sqi.net/img/general/612x612/afgzTQ6YrC9YZrZgzl4nbjGlsPgao-rvzhUFfiJ1L3U.jpg");
    Venue HYDE_PARK = new Venue("Hyde Park", "https://irs3.4sqi.net/img/general/720x720/20896080_v6VSRbH7s4Df4Hor7qL6d1RKraMbwYPFf4OwZaKthgw.jpg");

    Venues FOURSQUARE_VENUES = new Venues(Arrays.asList(BOROUGH_MARKET, PRIMROSE_HILL, HYDE_PARK));

    String PLACE_NAME = "Anglesey";

    String SEARCH_VENUES_FILE_NAME = "search_venues.json";

    int HTTP_SUCCESS_CODE = 200;
}
