package cloudcode.maps.use_case;

import cloudcode.maps.data_access.MapsDataAccessInterface;
import cloudcode.maps.data_access.FileDataAccessInterface;

import cloudcode.maps.entity.Places;
import cloudcode.maps.entity.Routes;
import com.google.maps.errors.ApiException;

import java.io.IOException;

/** Interactor for the SearchPlace use case - accepts search query input data and passes output data
 *
 */
public class SearchPlaceInteractor implements SearchPlaceInputBoundary {

    final MapsDataAccessInterface mapsDataAccessInterface;
    final FileDataAccessInterface fileDataAccessInterface;
    final SearchPlaceOutputBoundary mapsPresenter;

    /** Constructs SearchPlaceInteractor
     *
     * @param mapsDataAccessInterface MapsDataAccessInterface object
     * @param fileDataAccessInterface FileDataAccessInterface object
     * @param mapsPresenter SearchPlaceOutputBoundary for passing output data
     */
    public SearchPlaceInteractor(MapsDataAccessInterface mapsDataAccessInterface,
                                 FileDataAccessInterface fileDataAccessInterface,
                                 SearchPlaceOutputBoundary mapsPresenter) {

        this.mapsDataAccessInterface = mapsDataAccessInterface;
        this.fileDataAccessInterface = fileDataAccessInterface;
        this.mapsPresenter = mapsPresenter;
    }

    /** Provided a single place search, gets and passes places information to the presenter
     *
     * @param searchPlaceInputData SearchPlaceInputData object holding search query
     * @throws IOException in case of input/output error
     * @throws InterruptedException in case of thread interruption
     * @throws ApiException in case of API errors
     */
    @Override
    public void execute(SearchPlaceInputData searchPlaceInputData) throws IOException, InterruptedException, ApiException {

        String search = searchPlaceInputData.getSearch();
        Places places = mapsDataAccessInterface.fetchResults(search);

        mapsPresenter.updateSearchResults(places);
    }

    /** Provided multiple places searches (origin, waypoint, destination), gets and passes routes information
     * to the presenter
     *
     * @param searchPlaceOrigin SearchPlaceInputData object holding origin
     * @param searchPlaceDestination SearchPlaceInputData object holding destination
     * @param searchPlaceWaypoint SearchPlaceInputData object holding waypoint
     * @throws IOException in case of input/output error
     * @throws InterruptedException in case of thread interruption
     * @throws ApiException in case of API errors
     */
    public void execute(SearchPlaceInputData searchPlaceOrigin, SearchPlaceInputData searchPlaceDestination,
                        SearchPlaceInputData searchPlaceWaypoint)
            throws IOException, InterruptedException, ApiException {

        String origin = searchPlaceOrigin.getSearch();
        String destination = searchPlaceDestination.getSearch();
        String waypoint = searchPlaceWaypoint.getSearch();

        Routes routes = fileDataAccessInterface.fetchResults(origin, destination, waypoint);

        mapsPresenter.updateRouteResults(routes);
    }
}
