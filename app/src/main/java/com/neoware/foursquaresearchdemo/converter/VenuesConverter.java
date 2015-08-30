package com.neoware.foursquaresearchdemo.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.neoware.foursquaresearchdemo.model.Venue;
import com.neoware.foursquaresearchdemo.model.Venues;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VenuesConverter extends JsonDeserializer<Venues> {

    @Override
    public Venues deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException {
        return parser.readValueAs(VenuesBuilder.class).build();
    }

    static class VenuesBuilder {
        public List<Item> items;

        Venues build() {
            List<Venue> venues = new ArrayList<>();
            for (Item item : items) {
                if (item.venue != null) {
                    venues.add(item.venue);
                }
            }
            return new Venues(venues);
        }
    }

    static class Item {
        public Venue venue;
    }
}
