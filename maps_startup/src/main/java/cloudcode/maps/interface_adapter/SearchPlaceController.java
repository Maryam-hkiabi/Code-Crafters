package cloudcode.maps.interface_adapter;

import cloudcode.maps.use_case.SearchPlaceInputBoundary;
import cloudcode.maps.use_case.SearchPlaceInputData;
import com.google.maps.errors.ApiException;

import java.io.IOException;

/** Controller for SearchPlace use case, passes input data to the SearchPlaceInteractor
 *
 */
public class SearchPlaceController {

    final SearchPlaceInputBoundary mapsSearchPlaceUseCaseInteractor;

    /** Constructs SearchPlaceController
     *
     * @param mapsSearchPlaceUseCaseInteractor SearchPlaceInputBoundary for passing input data
     */
    public SearchPlaceController(SearchPlaceInputBoundary mapsSearchPlaceUseCaseInteractor) {
        this.mapsSearchPlaceUseCaseInteractor = mapsSearchPlaceUseCaseInteractor;
    }

    /** For a single search place input, passes input data to Interactor and executes Interactor method for place search
     *
     * @param input String of search query
     * @throws IOException in case of input/output error
     * @throws InterruptedException in case of thread interruption
     * @throws ApiException in case of API errors
     */
    public void executeSearch(String input) throws IOException, InterruptedException, ApiException {
        SearchPlaceInputData searchPlaceInputData = new SearchPlaceInputData(input);

        mapsSearchPlaceUseCaseInteractor.execute(searchPlaceInputData);
    }

    /** For a multiple search place inputs (origin, destination, waypoint), passes input data to Interactor and executes
     * Interactor method for routing
     *
     * @param destination String of destination
     * @param origin String of origin
     * @param waypoint String of waypoint
     * @throws IOException in case of input/output error
     * @throws InterruptedException in case of thread interruption
     * @throws ApiException in case of API errors
     */
    public void executeRoute(String origin, String destination, String waypoint) throws IOException, InterruptedException, ApiException {
        SearchPlaceInputData searchPlaceOrigin = new SearchPlaceInputData(origin);
        SearchPlaceInputData searchPlaceDestination = new SearchPlaceInputData(destination);
        SearchPlaceInputData searchPlaceWaypoint = new SearchPlaceInputData(waypoint);

        mapsSearchPlaceUseCaseInteractor.execute(searchPlaceOrigin, searchPlaceDestination, searchPlaceWaypoint);
    }

    public void executeSave(String input) throws IOException {
        SearchPlaceInputData searchPlaceInputData = new SearchPlaceInputData(input);

        mapsSearchPlaceUseCaseInteractor.save(searchPlaceInputData);
    }

    public void executeRemove(String input) throws IOException {
        SearchPlaceInputData searchPlaceInputData = new SearchPlaceInputData(input);

        mapsSearchPlaceUseCaseInteractor.remove(searchPlaceInputData);
    }

    public void executeClear() throws IOException {

        mapsSearchPlaceUseCaseInteractor.clear();
    }
}
