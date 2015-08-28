package com.neoware.foursquaresearchdemo.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.neoware.foursquaresearchdemo.model.Venue;
import com.neoware.foursquaresearchdemo.model.Venues;
import com.neoware.foursquaresearchdemo.response.SearchVenuesResponse;

import java.io.IOException;
import java.util.List;

public class ResponseConverter extends JsonDeserializer<SearchVenuesResponse> {

    @Override
    public SearchVenuesResponse deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException {
        return parser.readValueAs(ResponseBuilder.class).build();
    }

    static class ResponseBuilder {
        public Meta meta;
        public Response response;
        SearchVenuesResponse build() {
            return new SearchVenuesResponse(meta.code, new Venues(response.venues));
        }
    }

    static class Meta {
        public int code;
    }

    static class Response {
        public List<Venue> venues;
    }
}
