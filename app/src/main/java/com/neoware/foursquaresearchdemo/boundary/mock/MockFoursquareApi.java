package com.neoware.foursquaresearchdemo.boundary.mock;

import com.neoware.foursquaresearchdemo.boundary.FoursquareApi;
import com.neoware.foursquaresearchdemo.converter.JsonConverter;
import com.neoware.foursquaresearchdemo.converter.ResourceReader;
import com.neoware.foursquaresearchdemo.model.Venues;
import com.neoware.foursquaresearchdemo.response.SearchVenuesResponse;

import java.io.IOException;

import static com.neoware.foursquaresearchdemo.boundary.mock.MockSearchVenues.FOURSQUARE_VENUES;
import static com.neoware.foursquaresearchdemo.boundary.mock.MockSearchVenues.PLACE_NAME;
import static com.neoware.foursquaresearchdemo.boundary.mock.MockSearchVenues.SEARCH_VENUES_FILE_NAME;

public class MockFoursquareApi implements FoursquareApi {

    private final JsonConverter mJsonConverter;

    public MockFoursquareApi(JsonConverter jsonConverter) {
        mJsonConverter = jsonConverter;
    }

    @Override
    public SearchVenuesResponse searchVenues(String locationName) throws IOException {
        if (PLACE_NAME.equalsIgnoreCase(locationName)) {
            return new SearchVenuesResponse(MockSearchVenues.HTTP_SUCCESS_CODE, FOURSQUARE_VENUES);
        }
        return new SearchVenuesResponse(MockSearchVenues.HTTP_SUCCESS_CODE, mJsonConverter.readValue(new ResourceReader(SEARCH_VENUES_FILE_NAME), Venues.class));
    }
}
