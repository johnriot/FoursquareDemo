package com.neoware.foursquaresearchdemo.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.neoware.foursquaresearchdemo.model.Venue;
import com.neoware.foursquaresearchdemo.model.Venues;

import java.io.IOException;
import java.util.List;

public class VenuesConverter extends JsonDeserializer<Venues> {

    @Override
    public Venues deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException {
        return parser.readValueAs(VenuesBuilder.class).build();
    }

    static class VenuesBuilder {
        public List<Venue> mVenues;

        Venues build() {
            return new Venues(mVenues);
        }
    }
}
