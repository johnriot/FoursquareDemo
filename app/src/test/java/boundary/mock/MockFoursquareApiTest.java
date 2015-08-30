package boundary.mock;

import com.neoware.foursquaresearchdemo.boundary.mock.MockFoursquareApi;
import com.neoware.foursquaresearchdemo.boundary.mock.MockSearchVenues;
import com.neoware.foursquaresearchdemo.converter.JsonConverter;
import com.neoware.foursquaresearchdemo.converter.ResourceReader;
import com.neoware.foursquaresearchdemo.model.Venues;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MockFoursquareApiTest {

    @Mock
    JsonConverter mJsonConverter;
    @InjectMocks
    MockFoursquareApi mMockFoursquareApi;

    @Test
    public void searchInMemoryVenuesForAngleseyKeyword() throws Exception {
        Venues venuesForAngleseyKeyword = mMockFoursquareApi.searchVenues(MockSearchVenues.PLACE_NAME).getVenues();
        assertThat(venuesForAngleseyKeyword).isEqualTo(MockSearchVenues.FOURSQUARE_VENUES);
    }

    @Test
    public void mockSearchVenuesForAnyOtherKeyword() throws Exception {
        ResourceReader mockedResourceReader = new ResourceReader(MockSearchVenues.SEARCH_VENUES_FILE_NAME);
        Venues mockedJson = new Venues(Arrays.asList(MockSearchVenues.BOROUGH_MARKET));
        when(mJsonConverter.readValue(eq(mockedResourceReader), eq(Venues.class))).thenReturn(mockedJson);

        Venues venuesForAnyKeyword = mMockFoursquareApi.searchVenues("any keyword").getVenues();

        assertThat(venuesForAnyKeyword).isEqualTo(mockedJson);
    }
}   

