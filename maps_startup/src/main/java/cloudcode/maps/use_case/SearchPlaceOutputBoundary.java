package cloudcode.maps.use_case;

import cloudcode.maps.entity.Places;
import cloudcode.maps.entity.Routes;

public interface SearchPlaceOutputBoundary {

    /** Uses Places data to update SearchPlaceState and update SearchPlaceViewModel
     *
     * @param places Places object with places results information
     */
    void updateSearchResults(Places places);

    /** Uses Routes data to update SearchPlaceState and update SearchPlaceViewModel
     *
     * @param routes Routes object with routes results information
     */
    void updateRouteResults(Routes routes);
}
