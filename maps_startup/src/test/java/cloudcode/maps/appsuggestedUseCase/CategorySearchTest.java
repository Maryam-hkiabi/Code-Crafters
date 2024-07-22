package cloudcode.maps.appsuggestedUseCase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlacesSearchResponse;
import java.io.IOException;

public class CategorySearchTest {

    private static GeoApiContext context = (new GeoApiContext.Builder()).apiKey("AIzaSyAHh-dlNGXf-xhTCfO9ZBbB3RtR3YZ-kNk").build();

    public CategorySearchTest() {
    }

    public static void main(String[] args) throws IOException, InterruptedException, ApiException {
        PlacesSearchResponse response2 = (PlacesSearchResponse)PlacesApi.textSearchQuery(context, "cafe", null).await();
        Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
//        System.out.println(gson.toJson(response1[0].addressComponents));
        System.out.println((gson.toJson(response2.results).getClass().getName()));
        context.shutdown();
    }
}

