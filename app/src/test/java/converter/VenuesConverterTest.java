package converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.neoware.foursquaresearchdemo.converter.JsonConverter;
import com.neoware.foursquaresearchdemo.converter.ResourceReader;
import com.neoware.foursquaresearchdemo.model.Venues;

import org.junit.Test;

import static com.neoware.foursquaresearchdemo.boundary.mock.MockSearchVenues.ORIEL_YNYS_MON;
import static com.neoware.foursquaresearchdemo.boundary.mock.MockSearchVenues.THEATR_FACH;
import static com.neoware.foursquaresearchdemo.boundary.mock.MockSearchVenues.TRE_YSGAWEN_HALL;
import static org.fest.assertions.api.Assertions.assertThat;

public class VenuesConverterTest {

    final JsonConverter mConverter = new JsonConverter(new ObjectMapper(), new SimpleModule());

    @Test
    public void parseDataCorrectly() throws Exception {
        Venues venues = mConverter.readValue(new ResourceReader("venues.json"), Venues.class);

        assertThat(venues).containsExactly(TRE_YSGAWEN_HALL, ORIEL_YNYS_MON, THEATR_FACH);
    }
}
