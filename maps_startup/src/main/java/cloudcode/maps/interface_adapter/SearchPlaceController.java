package cloudcode.maps.interface_adapter;

import cloudcode.maps.use_case.SearchPlaceInputBoundary;
import cloudcode.maps.use_case.SearchPlaceInputData;
import com.google.maps.errors.ApiException;

import java.io.IOException;

public class SearchPlaceController {

    final SearchPlaceInputBoundary mapsSearchPlaceUseCaseInteractor;
    public SearchPlaceController(SearchPlaceInputBoundary mapsSearchPlaceUseCaseInteractor) {
        this.mapsSearchPlaceUseCaseInteractor = mapsSearchPlaceUseCaseInteractor;
    }

    public void execute(String input) throws IOException, InterruptedException, ApiException {
        SearchPlaceInputData searchPlaceInputData = new SearchPlaceInputData(input);

        mapsSearchPlaceUseCaseInteractor.execute(searchPlaceInputData);
    }
}
