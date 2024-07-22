package cloudcode.maps.interface_adapter;

import cloudcode.maps.use_case.SearchPlaceOutputBoundary;
import cloudcode.maps.use_case.SearchPlaceOutputData;

public class SearchPlacePresenter implements SearchPlaceOutputBoundary {

    private final SearchPlaceViewModel searchPlaceViewModel;
    private final ResultsPlaceViewModel resultsPlaceViewModel;
    private final ViewManagerModel viewManagerModel;

    public SearchPlacePresenter(ViewManagerModel viewManagerModel,
                                SearchPlaceViewModel searchPlaceViewModel,
                                ResultsPlaceViewModel resultsPlaceViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.searchPlaceViewModel = searchPlaceViewModel;
        this.resultsPlaceViewModel = resultsPlaceViewModel;
    }

    @Override
    public void prepareResultsView(SearchPlaceOutputData results) {

        SearchPlaceState searchPlaceState = searchPlaceViewModel.getState();
        ResultsPlaceState resultsPlaceState = resultsPlaceViewModel.getState();

        resultsPlaceState.setResults(results.getResults());

        this.resultsPlaceViewModel.setState(resultsPlaceState);
        resultsPlaceViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(resultsPlaceViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
