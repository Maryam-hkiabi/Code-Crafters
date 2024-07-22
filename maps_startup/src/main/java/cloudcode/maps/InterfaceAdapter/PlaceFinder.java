package cloudcode.maps.InterfaceAdapter;

import com.google.maps.errors.ApiException;
import com.google.maps.model.FindPlaceFromText;

import java.io.IOException;

public interface PlaceFinder {
    FindPlaceFromText findPlaceFromText(String query) throws IOException, InterruptedException, ApiException;
}

