package cloudcode.maps.interface_adapter;

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
    public void updateSearchResults(SearchPlaceOutputData results) {

        SearchPlaceState searchPlaceState = searchPlaceViewModel.getState();

        searchPlaceState.setResults(results.getResults());
        searchPlaceState.setLocData(results.getLocData());

        this.searchPlaceViewModel.setState(searchPlaceState);
        searchPlaceViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(searchPlaceViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void updateRouteResults(SearchPlaceOutputRoute routes) {

        SearchPlaceState searchPlaceState = searchPlaceViewModel.getState();

        searchPlaceState.setRoutes(routes.getRoutes());
        searchPlaceState.setLocList(routes.getLocList());

        this.searchPlaceViewModel.setState(searchPlaceState);
        searchPlaceViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(searchPlaceViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
