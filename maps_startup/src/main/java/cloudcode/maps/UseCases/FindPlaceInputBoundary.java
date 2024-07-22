package cloudcode.maps.UseCases;

import com.google.maps.errors.ApiException;
import com.google.maps.model.FindPlaceFromText;
import java.io.IOException;

public interface FindPlaceInputBoundary {
    FindPlaceFromTextResponse findPlace(String query) throws IOException, InterruptedException, ApiException;
}

