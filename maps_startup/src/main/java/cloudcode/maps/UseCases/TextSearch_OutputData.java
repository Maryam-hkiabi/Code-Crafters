package cloudcode.maps.UseCases;

import com.google.maps.PlacesApi;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import java.io.IOException;
import com.google.maps.TextSearchRequest;
import java.util.List;

public class TextSearch_OutputData {
    private List<PlacesSearchResult> places;

    public TextSearch_OutputData(List<PlacesSearchResult> places) {
        this.places = places;
    }

    public List<PlacesSearchResult> getPlaces() {
        return places;
    }

}
