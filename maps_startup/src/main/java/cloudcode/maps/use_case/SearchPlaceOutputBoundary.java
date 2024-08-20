package cloudcode.maps.use_case;

import cloudcode.maps.entity.Places;
import cloudcode.maps.entity.Routes;

public interface SearchPlaceOutputBoundary {
    void updateSearchResults(Places places);

    void updateRouteResults(Routes routes);
}
