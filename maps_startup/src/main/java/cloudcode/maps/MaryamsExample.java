package cloudcode.maps;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

public class MaryamsExample {



        public static void main(String[] args) {
            String apiKey = "AIzaSyAHh-dlNGXf-xhTCfO9ZBbB3RtR3YZ-kNk";
            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey(apiKey)
                    .build();

            try {
                GeocodingResult[] results = GeocodingApi.geocode(context, "5120 Yonge St, North York, ON, M2N 5N9, Canada").await();

                System.out.println(results[0].formattedAddress);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                context.shutdown();
            }
        }
    }

