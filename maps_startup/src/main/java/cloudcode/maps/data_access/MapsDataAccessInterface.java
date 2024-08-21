package cloudcode.maps.data_access;

import cloudcode.maps.entity.Places;
import com.google.maps.errors.ApiException;

import java.io.IOException;

/** Interface for accessing places information for Google Maps Places API search
 *
 */
public interface MapsDataAccessInterface {
    /** Gets places results from Places API call
     *
     * @param search String query for Places API call
     * @return Places object with place information from API call
     * @throws IOException in case of input/output error
     * @throws InterruptedException in case of thread interruption
     * @throws ApiException in case of API errors
     */
    Places fetchResults(String search) throws IOException, InterruptedException, ApiException;
}
