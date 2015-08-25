package converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.neoware.foursquaresearchdemo.converter.JsonConverter;
import com.neoware.foursquaresearchdemo.model.Venue;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class VenueConverterTest {

    static final Venue TRE_YSGAWEN_HALL = new Venue("Tre-Ysgawen Hall Country House Hotel & Spa Llangefni");

    final JsonConverter converter = new JsonConverter(new ObjectMapper(), new SimpleModule());

    @Test
    public void parseDataCorrectly() throws Exception {
        Venue actual = converter.readValue(new ResourceReader("venue.json"), Venue.class);

        assertThat(actual).isEqualTo(TRE_YSGAWEN_HALL);
    }
}
