package com.neoware.foursquaresearchdemo.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.neoware.foursquaresearchdemo.model.Venue;

import java.io.IOException;
import java.util.List;

public class VenueConverter extends JsonDeserializer<Venue> {

    @Override
    public Venue deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException {
        return parser.readValueAs(VenueBuilder.class).build();
    }

    static class VenueBuilder {
        public String name;
        public FeaturedPhoto featuredPhotos;

        Venue build() {
            String photoUrl = null;
            if(featuredPhotos != null && featuredPhotos.items != null && featuredPhotos.items.size() > 0 &&
                    featuredPhotos.items.get(0) != null) {
                photoUrl = featuredPhotos.items.get(0).getImageUrl();
            }
            return new Venue(name, photoUrl);
        }
    }

    static class FeaturedPhoto {
        public List<Photo> items;
    }

    static class Photo {
        public String prefix;
        public String suffix;
        public int width;
        public int height;
        public String getImageUrl() {
            return prefix + width + "x" + height + suffix;
        }
    }
}
