package com.neoware.foursquaresearchdemo.converter;

import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.MimeUtil;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

public class RetrofitConverter implements Converter {
    private static final String CHARSET = "UTF-8";

    private final JsonConverter converter;

    public RetrofitConverter(JsonConverter converter) {
        this.converter = converter;
    }

    @Override
    public Object fromBody(TypedInput body, Type type) throws ConversionException {
        String charset = MimeUtil.parseCharset(body.mimeType(), CHARSET);
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(body.in(), charset);
            return converter.readValue(isr, TypeFactory.rawClass(type));
        } catch (IOException e) {
            throw new ConversionException(e);
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException ignored) {

                }
            }
        }
    }

    @Override
    public TypedOutput toBody(Object object) {
        return new JsonTypedOutput(converter.writeValueAsString(object).getBytes());
    }

    private static class JsonTypedOutput implements TypedOutput {
        private final byte[] jsonBytes;

        JsonTypedOutput(byte[] jsonBytes) {
            this.jsonBytes = jsonBytes;
        }

        @Override
        public String fileName() {
            return null;
        }

        @Override
        public String mimeType() {
            return "application/json; charset=UTF-8";
        }

        @Override
        public long length() {
            return jsonBytes.length;
        }

        @Override
        public void writeTo(OutputStream out) throws IOException {
            out.write(jsonBytes);
        }
    }
}