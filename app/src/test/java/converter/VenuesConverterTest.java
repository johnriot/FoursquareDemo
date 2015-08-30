package converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.neoware.foursquaresearchdemo.converter.JsonConverter;
import com.neoware.foursquaresearchdemo.converter.ResourceReader;
import com.neoware.foursquaresearchdemo.model.Venues;

import org.junit.Test;

import static com.neoware.foursquaresearchdemo.boundary.mock.MockSearchVenues.PRIMROSE_HILL;
import static com.neoware.foursquaresearchdemo.boundary.mock.MockSearchVenues.HYDE_PARK;
import static com.neoware.foursquaresearchdemo.boundary.mock.MockSearchVenues.BOROUGH_MARKET;
import static org.fest.assertions.api.Assertions.assertThat;

public class VenuesConverterTest {

    final JsonConverter mConverter = new JsonConverter(new ObjectMapper(), new SimpleModule());

    @Test
    public void parseDataCorrectly() throws Exception {
        Venues venues = mConverter.readValue(new ResourceReader("venues.json"), Venues.class);

        assertThat(venues).containsExactly(BOROUGH_MARKET, PRIMROSE_HILL, HYDE_PARK);
    }
}
