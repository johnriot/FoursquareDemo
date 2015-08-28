package com.neoware.foursquaresearchdemo.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.neoware.foursquaresearchdemo.model.Venue;
import com.neoware.foursquaresearchdemo.model.Venues;
import com.neoware.foursquaresearchdemo.response.SearchVenuesResponse;

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
        module.addDeserializer(SearchVenuesResponse.class, new ResponseConverter());
    }

    public <T> T readValue(Reader reader, Class<T> valueType) throws IOException {
        return objectMapper.readValue(reader, valueType);
    }

    public String writeValueAsString(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new JsonConverterException(e);
        }
    }

    public static class JsonConverterException extends RuntimeException {
        public JsonConverterException(Exception cause) {
            super(cause);
        }
    }
}
