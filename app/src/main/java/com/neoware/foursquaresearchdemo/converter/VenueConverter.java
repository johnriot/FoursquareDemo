package com.neoware.foursquaresearchdemo.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.neoware.foursquaresearchdemo.model.Venue;

import java.io.IOException;

public class VenueConverter extends JsonDeserializer<Venue> {

    @Override
    public Venue deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException {
        return parser.readValueAs(VenueBuilder.class).build();
    }

    static class VenueBuilder {
        public String name;

        Venue build() {
            return new Venue(name);
        }
    }
}
