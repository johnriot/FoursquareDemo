package converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.neoware.foursquaresearchdemo.converter.JsonConverter;
import com.neoware.foursquaresearchdemo.model.Venue;
import com.neoware.foursquaresearchdemo.model.Venues;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class VenuesConverterTest {
    static final Venue TRE_YSGAWEN_HALL = new Venue("Tre-Ysgawen Hall Country House Hotel & Spa Llangefni");
    static final Venue ORIEL_YNYS_MON = new Venue("Oriel Ynys Mon");
    static final Venue THEATR_FACH = new Venue("Theatr Fach");

    final JsonConverter converter = new JsonConverter(new ObjectMapper(), new SimpleModule());

    @Test
    public void parseDataCorrectly() throws Exception {
        Venues venues = converter.readValue(new ResourceReader("venues.json"), Venues.class);

        assertThat(venues).containsExactly(TRE_YSGAWEN_HALL, ORIEL_YNYS_MON, THEATR_FACH);
    }
}
