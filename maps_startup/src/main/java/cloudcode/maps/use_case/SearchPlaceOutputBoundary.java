package cloudcode.maps.use_case;

public interface SearchPlaceOutputBoundary {
    void updateSearchResults(SearchPlaceOutputData results);

    void updateRouteResults(SearchPlaceOutputRoute routes);
}
