import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.FindPlaceFromText;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResult;

import java.io.IOException;
import java.util.Arrays;

/*

This in-progress file helps restrict the area of the world map into the size of UofT campus roughly from

*/


/*
public class PlacesSearchResult {
    public static PlacesSearchResult[] findPlace(double lat, double lng)
            throws IOException, InterruptedException, ApiException {
        GeoApiContext ctx = new GeoApiContext.Builder().apiKey("AIzaSyAHh-dlNGXf-xhTCfO9ZBbB3RtR3YZ-kNk").build();

        FindPlaceFromText response = null;
        response = PlacesApi.findPlaceFromText(ctx, "restaurants", FindPlaceFromTextRequest.InputType.TEXT_QUERY)
                .fields(
                        FindPlaceFromTextRequest.FieldMask.NAME,
                        FindPlaceFromTextRequest.FieldMask.FORMATTED_ADDRESS,
                        FindPlaceFromTextRequest.FieldMask.RATING,
                        FindPlaceFromTextRequest.FieldMask.OPENING_HOURS)
                .locationBias(new FindPlaceFromTextRequest.LocationBiasCircular(new LatLng(lat, lng), 10000))
                .await();

        return response != null ? response.candidates : null;
    }


    public static void main() throws IOException, InterruptedException, ApiException {
        PlacesSearchResult[] response = findPlace(-33.8599358, 151.2090295);
        System.out.print(Arrays.toString(response));
    }
}
*/

