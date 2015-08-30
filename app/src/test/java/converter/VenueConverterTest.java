package converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.neoware.foursquaresearchdemo.converter.JsonConverter;
import com.neoware.foursquaresearchdemo.converter.ResourceReader;
import com.neoware.foursquaresearchdemo.model.Venue;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static com.neoware.foursquaresearchdemo.boundary.mock.MockSearchVenues.BOROUGH_MARKET;

public class VenueConverterTest {

    final JsonConverter mConverter = new JsonConverter(new ObjectMapper(), new SimpleModule());

    @Test
    public void parseDataCorrectly() throws Exception {
        Venue actual = mConverter.readValue(new ResourceReader("venue.json"), Venue.class);

        assertThat(actual).isEqualTo(BOROUGH_MARKET);
    }
}
