package cloudcode.maps.use_case;

import com.google.maps.errors.ApiException;

import java.io.IOException;

public interface SearchPlaceInputBoundary {
    void execute(SearchPlaceInputData searchPlaceInputData) throws IOException, InterruptedException, ApiException;

    void execute(SearchPlaceInputData searchPlaceOrigin, SearchPlaceInputData searchPlaceDestination, SearchPlaceInputData searchPlaceWaypoint) throws IOException, InterruptedException, ApiException;

    void save(SearchPlaceInputData searchPlaceInputData) throws IOException;

    void remove(SearchPlaceInputData searchPlaceInputData) throws IOException;

    void clear() throws IOException;
}
