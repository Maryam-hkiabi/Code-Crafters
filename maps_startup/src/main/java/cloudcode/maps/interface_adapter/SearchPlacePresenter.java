package cloudcode.maps.interface_adapter;

import cloudcode.maps.entity.Places;
import cloudcode.maps.entity.Routes;
import cloudcode.maps.use_case.SearchPlaceOutputBoundary;
import cloudcode.maps.use_case.SearchPlaceOutputData;
import cloudcode.maps.use_case.SearchPlaceOutputRoute;

public class SearchPlacePresenter implements SearchPlaceOutputBoundary {

    private final SearchPlaceViewModel searchPlaceViewModel;
    private final ViewManagerModel viewManagerModel;

    public SearchPlacePresenter(ViewManagerModel viewManagerModel, SearchPlaceViewModel searchPlaceViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.searchPlaceViewModel = searchPlaceViewModel;
    }

    @Override
    public void updateSearchResults(Places places) {

        SearchPlaceOutputData results = new SearchPlaceOutputData(places.getPlaces(), places.getLocations());
        SearchPlaceState searchPlaceState = searchPlaceViewModel.getState();

        searchPlaceState.setResults(results.getResults());
        searchPlaceState.setLocData(results.getLocData());

        this.searchPlaceViewModel.setState(searchPlaceState);
        searchPlaceViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(searchPlaceViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void updateRouteResults(Routes routes) {

        SearchPlaceOutputRoute results = new SearchPlaceOutputRoute(routes.getRoutes(), routes.getLocations(), routes.getPolylines(),
                routes.getOriInfo(), routes.getDesInfo());

        SearchPlaceState searchPlaceState = searchPlaceViewModel.getState();

        searchPlaceState.setRoutes(results.getRoutes());
        searchPlaceState.setLocList(results.getLocations());
        searchPlaceState.setPolylineList(results.getPolylines());
        searchPlaceState.setOriData(results.getOriInfo());
        searchPlaceState.setDesData(results.getDesInfo());

        this.searchPlaceViewModel.setState(searchPlaceState);
        searchPlaceViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(searchPlaceViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}