package boundary.retrofit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.neoware.foursquaresearchdemo.boundary.mock.MockSearchVenues;
import com.neoware.foursquaresearchdemo.boundary.retrofit.RetrofitFoursquareApi;
import com.neoware.foursquaresearchdemo.converter.JsonConverter;
import com.neoware.foursquaresearchdemo.converter.RetrofitConverter;
import com.neoware.foursquaresearchdemo.response.SearchVenuesResponse;

import org.junit.Test;

import retrofit.RestAdapter;

import static org.fest.assertions.api.Assertions.assertThat;

public class RetrofitFoursquareApiTest {
    @Test
    public void searchRepositories() throws Exception {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(RetrofitFoursquareApi.ENDPOINT_URL)
                .setConverter(new RetrofitConverter(new JsonConverter(new ObjectMapper(), new SimpleModule())))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        RetrofitFoursquareApi apiService = restAdapter.create(RetrofitFoursquareApi.class);
        SearchVenuesResponse repositories = apiService.searchVenues(MockSearchVenues.PLACE_NAME);

        // Keep the test very general against the real server: just that the result code is a success and that there is
        // a non-zero number of venues
        assertThat(repositories.getHttpResultCode()).isEqualTo(MockSearchVenues.HTTP_SUCCESS_CODE);
        assertThat(repositories.getVenues()).isNotEmpty();
    }
}
