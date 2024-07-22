package cloudcode.maps.InterfaceAdapter;

import com.google.maps.FindPlaceFromTextRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.FindPlaceFromText;
import com.google.maps.errors.ApiException;

import java.io.IOException;

public class GoogleMapsPlaceFinder implements PlaceFinder {
    private final GeoApiContext context;

    public GoogleMapsPlaceFinder(String apiKey) {
        this.context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
    }

    @Override
    public FindPlaceFromText findPlaceFromText(String query) throws IOException, InterruptedException, ApiException {
        return PlacesApi.findPlaceFromText(context, query,  FindPlaceFromTextRequest.InputType.TEXT_QUERY).await();
    }

    public void shutdown() {
        context.shutdown();
    }
}

