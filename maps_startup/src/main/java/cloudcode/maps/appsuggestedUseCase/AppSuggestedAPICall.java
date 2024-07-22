package cloudcode.maps.appsuggestedUseCase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlacesSearchResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AppSuggestedAPICall {

    private static GeoApiContext context = (new GeoApiContext.Builder()).apiKey("AIzaSyAHh-dlNGXf-xhTCfO9ZBbB3RtR3YZ-kNk").build();
    private String query;

    public AppSuggestedAPICall(String query) {
        this.query = query;
    }

    public ArrayList<ArrayList<String>> execute() throws IOException, InterruptedException, ApiException {

        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> addresses = new ArrayList<String>();
        ArrayList<String> placeIDs = new ArrayList<String>();
        ArrayList<ArrayList<String>> attributes = new ArrayList<ArrayList<String>>();


        PlacesSearchResponse response = (PlacesSearchResponse) PlacesApi.textSearchQuery(context, query, null).await();
        Gson gson = (new GsonBuilder()).setPrettyPrinting().create();

        for (int i = 0; i < 7; i++) {
            names.add((gson.toJson(response.results[i].name)));
            addresses.add((gson.toJson(response.results[i].formattedAddress)));
            placeIDs.add((gson.toJson(response.results[i].placeId)));
        }

        context.shutdown();

        attributes.add(names);
        attributes.add(addresses);
        attributes.add(placeIDs);
        return attributes;
    }
}

