package boundary.retrofit;

import com.neoware.foursquaresearchdemo.boundary.mock.MockSearchVenues;
import com.neoware.foursquaresearchdemo.boundary.retrofit.FoursquareApiBuilder;
import com.neoware.foursquaresearchdemo.boundary.retrofit.RetrofitFoursquareApi;
import com.neoware.foursquaresearchdemo.response.SearchVenuesResponse;

import org.junit.Test;

import java.util.Arrays;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;

public class RetrofitFoursquareApiTest {
    @Test
    public void searchRepositories() throws Exception {
        RetrofitFoursquareApi apiService = FoursquareApiBuilder.buildApiService(new ErrorHandler() {
            @Override
            public Throwable handleError(RetrofitError cause) {
                fail("Unexpected Retrofit error: " + Arrays.toString(cause.getStackTrace()));
                return null;
            }
        });
        SearchVenuesResponse repositories = apiService.searchVenues(MockSearchVenues.PLACE_NAME);

        // Keep the test very general against the real server: just that the result code is a success and that there is
        // a non-zero number of venues
        assertThat(repositories.wasRequestSuccessful());
        assertThat(repositories.getVenues()).isNotEmpty();
        assertThat(repositories.getVenues().get(0).getName()).isNotNull();
        assertThat(repositories.getVenues().get(0).getPhotoUrl()).isNotNull();
    }
}
