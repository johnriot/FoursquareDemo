package com.neoware.foursquaresearchdemo.boundary.retrofit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.neoware.foursquaresearchdemo.converter.JsonConverter;
import com.neoware.foursquaresearchdemo.converter.RetrofitConverter;

import retrofit.ErrorHandler;
import retrofit.RestAdapter;

public class FoursquareApiBuilder {

    public static RetrofitFoursquareApi buildApiService(ErrorHandler errorHandler) {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(RetrofitFoursquareApi.ENDPOINT_URL)
                .setConverter(new RetrofitConverter(new JsonConverter(new ObjectMapper(), new SimpleModule())))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setErrorHandler(errorHandler)
                .build();
        return adapter.create(RetrofitFoursquareApi.class);

    }
}
