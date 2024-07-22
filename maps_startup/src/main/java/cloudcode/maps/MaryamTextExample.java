package cloudcode.maps;

import com.google.maps.*;
import com.google.maps.model.PlacesSearchResponse;

public class MaryamTextExample {

    public static void main(String[] args) {
        String apiKey = "AIzaSyAHh-dlNGXf-xhTCfO9ZBbB3RtR3YZ-kNk";
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();

        try {
            TextSearchRequest request = PlacesApi.textSearchQuery(context, "Restaurants");
            PlacesSearchResponse response = request.await();


            System.out.println(response.results[0]);
            System.out.println(response.results[1]);
            System.out.println(response.results[2]);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            context.shutdown();
        }
    }
}

