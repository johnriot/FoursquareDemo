package scenario;

import com.neoware.foursquaresearchdemo.boundary.FoursquareApi;
import com.neoware.foursquaresearchdemo.boundary.mock.MockSearchVenues;
import com.neoware.foursquaresearchdemo.model.Venues;
import com.neoware.foursquaresearchdemo.request.SearchVenuesRequest;
import com.neoware.foursquaresearchdemo.response.SearchVenuesResponse;
import com.neoware.foursquaresearchdemo.scenario.SearchVenuesScenario;
import com.neoware.foursquaresearchdemo.view.Presentation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static com.neoware.foursquaresearchdemo.boundary.mock.MockSearchVenues.PLACE_NAME;

public class SearchVenuesScenarioTest {
    static final Venues ANGLESEY_VENUES = new Venues(Collections.EMPTY_LIST);

    @Mock
    Presentation<SearchVenuesResponse> presentation;
    @Mock
    FoursquareApi mFoursquareApi;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void notifyThePresentationWithTheRetrievedRepositories() throws Exception {
        // @Given The foursquare api, A user interface, A search request containing a keyword
        // @When The user triggers a search request through the ui
        // @Then The ui should display the foursquare venues retrieved for that request

        SearchVenuesScenario scenario = new SearchVenuesScenario(presentation, mFoursquareApi, new SearchVenuesRequest(PLACE_NAME));
        SearchVenuesResponse response = new SearchVenuesResponse(MockSearchVenues.HTTP_SUCCESS_CODE, ANGLESEY_VENUES);

        when(mFoursquareApi.searchVenues(PLACE_NAME)).thenReturn(response);
        scenario.run();
        verify(presentation).present(eq(response));
    }
}
