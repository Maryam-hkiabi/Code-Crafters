package cloudcode.maps;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.PlacesSearchResponse;
import com.google.type.LatLng;

import java.io.IOException;

public class GeocodeTest {

    private static GeoApiContext context = new GeoApiContext.Builder()
            .apiKey("AIzaSyAHh-dlNGXf-xhTCfO9ZBbB3RtR3YZ-kNk")
            .build();

    public static void main(String[] args) throws IOException, InterruptedException, ApiException {

        GeocodingResult[] response1 =  GeocodingApi.geocode(context,
                "27 King's College Circle, Toronto, ON M5S 1A1").await();

        PlacesSearchResponse response2 = PlacesApi.textSearchQuery(context, "centre").await();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        System.out.println(gson.toJson(response1[0].addressComponents));

        System.out.println(response2.results[0]);

        context.shutdown();







    }
}
