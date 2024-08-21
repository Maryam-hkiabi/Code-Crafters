package cloudcode.maps.interface_adapter;

import cloudcode.maps.entity.Places;
import cloudcode.maps.entity.Routes;
import cloudcode.maps.use_case.SearchPlaceOutputBoundary;

/**
 *
 */
public class SearchPlacePresenter implements SearchPlaceOutputBoundary {

    private final SearchPlaceViewModel searchPlaceViewModel;
    private final ViewManagerModel viewManagerModel;

    /** Constructs SearchPlacePresenter
     *
     * @param viewManagerModel ViewManagerModel object
     * @param searchPlaceViewModel SearchPlaceViewModel object for displaying search place results
     */
    public SearchPlacePresenter(ViewManagerModel viewManagerModel, SearchPlaceViewModel searchPlaceViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.searchPlaceViewModel = searchPlaceViewModel;
    }

    /** Uses Places data to update SearchPlaceState and update SearchPlaceViewModel
     *
     * @param places Places object with places results information
     */
    @Override
    public void updateSearchResults(Places places) {

        SearchPlaceState searchPlaceState = searchPlaceViewModel.getState();

        searchPlaceState.setResults(places.getPlaces());
        searchPlaceState.setLocData(places.getLocations());

        this.searchPlaceViewModel.setState(searchPlaceState);
        searchPlaceViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(searchPlaceViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /** Uses Routes data to update SearchPlaceState and update SearchPlaceViewModel
     *
     * @param routes Routes object with routes results information
     */
    public void updateRouteResults(Routes routes) {

        SearchPlaceState searchPlaceState = searchPlaceViewModel.getState();

        searchPlaceState.setRoutes(routes.getRoutes());
        searchPlaceState.setLocList(routes.getLocations());
        searchPlaceState.setPolylineList(routes.getPolylines());

        searchPlaceState.setOriData(routes.getOriInfo());
        searchPlaceState.setDesData(routes.getDesInfo());
        searchPlaceState.setWayData(routes.getWayInfo());

        this.searchPlaceViewModel.setState(searchPlaceState);
        searchPlaceViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(searchPlaceViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}