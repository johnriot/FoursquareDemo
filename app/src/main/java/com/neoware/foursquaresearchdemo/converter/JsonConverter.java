package com.neoware.foursquaresearchdemo.converter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.neoware.foursquaresearchdemo.model.Venue;
import com.neoware.foursquaresearchdemo.model.Venues;

import java.io.IOException;
import java.io.Reader;

public class JsonConverter {

    private final ObjectMapper objectMapper;

    public JsonConverter(ObjectMapper objectMapper, SimpleModule module) {
        this.objectMapper = objectMapper;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        configureModule(module);
        objectMapper.registerModule(module);
    }

    private void configureModule(SimpleModule module) {
        module.addDeserializer(Venues.class, new VenuesConverter());
        module.addDeserializer(Venue.class, new VenueConverter());
    }

    public <T> T readValue(Reader reader, Class<T> valueType) throws IOException {
        return objectMapper.readValue(reader, valueType);
    }
}
