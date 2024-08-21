package cloudcode.maps.data_access;

import cloudcode.maps.entity.Routes;
import com.google.maps.errors.ApiException;

import java.io.IOException;

/** Interface for accessing location data file
 *
 */
public interface FileDataAccessInterface {
    /** Gets routes for specified origin, destination, and waypoint locations
     *
     * @param origin String of origin location
     * @param destination String of destination location
     * @param waypoint String of waypoint (stopover) location
     * @return Routes object with possible routes
     * @throws IOException in case of input/output error
     * @throws InterruptedException in case of thread interruption
     * @throws ApiException in case of API errors
     */
    Routes fetchResults(String origin, String destination, String waypoint)
            throws IOException, InterruptedException, ApiException;
}
