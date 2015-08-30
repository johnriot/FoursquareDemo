package com.neoware.foursquaresearchdemo.boundary.retrofit;

import com.neoware.foursquaresearchdemo.response.SearchVenuesResponse;

import retrofit.http.GET;
import retrofit.http.Query;


public interface RetrofitFoursquareApi {
    String ENDPOINT_URL = "https://api.foursquare.com";
    String AUTH_TOKEN_QUERY = "oauth_token";
    String VERSION_QUERY = "v";
    String PHOTO_COUNT_QUERY = "photoCount";

    // These two values are taken from Fourquare's "Try Me" web demo.
    // For a real application we need to do token exchange and persist the OAuth token (securely).
    String AUTH_TOKEN_VALUE = "MCQM2XHOEFU24SBT5KR50MHCA3TSZT1JUDGSPJW2GAU4AYWM";
    String VERSION_VALUE = "20150825";

    String PHOTO_COUNT_VALUE = "1";
    String PREDEFINED_QUERY =   AUTH_TOKEN_QUERY + "=" + AUTH_TOKEN_VALUE + "&" +
                                VERSION_QUERY + "=" + VERSION_VALUE + "&" +
                                PHOTO_COUNT_QUERY + "=" + PHOTO_COUNT_VALUE;

    /**
     * Creates a query like the following:
     * https://api.foursquare.com/v2/venues/explore?near=london&photoCount=1&oauth_token=MCQM2XHOEFU24SBT5KR50MHCA3TSZT1JUDGSPJW2GAU4AYWM&v=20150830
     */
    @GET("/v2/venues/explore?" + PREDEFINED_QUERY)
    SearchVenuesResponse searchVenues(
            @Query("near") String locationName);
}
