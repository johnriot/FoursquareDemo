package converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.neoware.foursquaresearchdemo.converter.JsonConverter;
import com.neoware.foursquaresearchdemo.converter.ResourceReader;
import com.neoware.foursquaresearchdemo.response.SearchVenuesResponse;

import org.junit.Test;

import static com.neoware.foursquaresearchdemo.boundary.mock.MockSearchVenues.HTTP_SUCCESS_CODE;
import static org.fest.assertions.api.Assertions.assertThat;

public class ResponseConverterTest {
    final JsonConverter mConverter = new JsonConverter(new ObjectMapper(), new SimpleModule());

        @Test
        public void parseDataCorrectly() throws Exception {
            SearchVenuesResponse response = mConverter.readValue(new ResourceReader("search_venues.json"), SearchVenuesResponse.class);
            assertThat(response.getHttpResultCode()).isEqualTo(HTTP_SUCCESS_CODE);
        }
}
