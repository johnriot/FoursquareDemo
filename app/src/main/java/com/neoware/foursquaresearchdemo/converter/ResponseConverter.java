package com.neoware.foursquaresearchdemo.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.neoware.foursquaresearchdemo.model.Venue;
import com.neoware.foursquaresearchdemo.model.Venues;
import com.neoware.foursquaresearchdemo.response.SearchVenuesResponse;

import java.io.IOException;
import java.util.ArrayList;
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
            // Check for invalid arguments
            if(response == null || response.groups == null || response.groups.size() < 1
                    || response.groups.get(0) == null || response.groups.get(0).items == null) {
                return null;
            }

            // Build the response
            List<Venue> venues = new ArrayList<>();
            for (Item item : response.groups.get(0).items) {
                if (item.venue != null) {
                    venues.add(item.venue);
                }
            }
            return new SearchVenuesResponse(meta.code, new Venues(venues));
        }
    }

    static class Meta {
        public int code;
    }

    static class Response {
        public List<Group> groups;
    }

    static class Group {
        public List<Item> items;
    }

    static class Item {
        public Venue venue;
    }
}
