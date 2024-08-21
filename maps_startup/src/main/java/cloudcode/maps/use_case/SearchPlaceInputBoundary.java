package cloudcode.maps.use_case;

import com.google.maps.errors.ApiException;

import java.io.IOException;

/** Interface for passing input data to the SearchPlaceInteractor
 *
 */
public interface SearchPlaceInputBoundary {

    /** Provided a single place search, gets and passes places information to the presenter
     *
     * @param searchPlaceInputData SearchPlaceInputData object holding search query
     * @throws IOException in case of input/output error
     * @throws InterruptedException in case of thread interruption
     * @throws ApiException in case of API errors
     */
    void execute(SearchPlaceInputData searchPlaceInputData) throws IOException, InterruptedException, ApiException;

    /** Provided multiple places as input data (origin, waypoint, destination), gets and passes routes information
     * to the presenter
     *
     * @param searchPlaceOrigin SearchPlaceInputData object holding origin
     * @param searchPlaceDestination SearchPlaceInputData object holding destination
     * @param searchPlaceWaypoint SearchPlaceInputData object holding waypoint
     * @throws IOException in case of input/output error
     * @throws InterruptedException in case of thread interruption
     * @throws ApiException in case of API errors
     */
    void execute(SearchPlaceInputData searchPlaceOrigin, SearchPlaceInputData searchPlaceDestination, SearchPlaceInputData searchPlaceWaypoint) throws IOException, InterruptedException, ApiException;

    void save(SearchPlaceInputData searchPlaceInputData) throws IOException;

    void remove(SearchPlaceInputData searchPlaceInputData) throws IOException;

    void clear() throws IOException;
}
