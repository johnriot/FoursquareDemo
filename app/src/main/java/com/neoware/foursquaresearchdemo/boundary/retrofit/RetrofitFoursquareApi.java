package com.neoware.foursquaresearchdemo.boundary.retrofit;

import com.neoware.foursquaresearchdemo.response.SearchVenuesResponse;

import retrofit.http.GET;
import retrofit.http.Query;


public interface RetrofitFoursquareApi {
    String ENDPOINT_URL = "https://api.foursquare.com";
    String AUTH_TOKEN_QUERY = "oauth_token";
    String VERSION_QUERY = "v";

    // These two values are taken from Fourquare's "Try Me" web demo.
    // For a real application we need to do token exchange and persist the OAuth token (securely).
    String AUTH_TOKEN_VALUE = "MCQM2XHOEFU24SBT5KR50MHCA3TSZT1JUDGSPJW2GAU4AYWM";
    String VERSION_VALUE = "20150825";
    String PREDEFINED_QUERY =  AUTH_TOKEN_QUERY + "=" + AUTH_TOKEN_VALUE + "&" +
                               VERSION_QUERY + "=" + VERSION_VALUE;

    /**
     * Creates a query like the following:
     * https://api.foursquare.com/v2/venues/search?near=london&oauth_token=MCQM2XHOEFU24SBT5KR50MHCA3TSZT1JUDGSPJW2GAU4AYWM&v=20150825
     */
    @GET("/v2/venues/search?" + PREDEFINED_QUERY)
    SearchVenuesResponse searchVenues(
            @Query("near") String locationName);
}
