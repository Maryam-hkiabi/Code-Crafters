package cloudcode.maps.interface_adapter;

import cloudcode.maps.entity.Places;
import cloudcode.maps.entity.Routes;
import cloudcode.maps.use_case.SearchPlaceOutputBoundary;

public class SearchPlacePresenter implements SearchPlaceOutputBoundary {

    private final SearchPlaceViewModel searchPlaceViewModel;
    private final ViewManagerModel viewManagerModel;

    public SearchPlacePresenter(ViewManagerModel viewManagerModel, SearchPlaceViewModel searchPlaceViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.searchPlaceViewModel = searchPlaceViewModel;
    }

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