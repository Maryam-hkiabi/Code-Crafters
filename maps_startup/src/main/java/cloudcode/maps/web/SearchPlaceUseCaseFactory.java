package cloudcode.maps.web;

import cloudcode.maps.data_access.*;
import cloudcode.maps.interface_adapter.*;
import cloudcode.maps.use_case.SearchPlaceInputBoundary;
import cloudcode.maps.use_case.SearchPlaceInteractor;
import cloudcode.maps.use_case.SearchPlaceOutputBoundary;
import cloudcode.maps.use_case.SearchPlaceOutputData;
import cloudcode.maps.view.SearchPlaceView;

import javax.swing.*;
import java.io.IOException;

public class SearchPlaceUseCaseFactory {

    private SearchPlaceUseCaseFactory() {}

    public static SearchPlaceView create(ViewManagerModel viewManagerModel, SearchPlaceViewModel searchPlaceViewModel, ResultsPlaceViewModel resultsPlaceViewModel) {

        try {
            SearchPlaceController searchPlaceController = createUserSearchPlaceUseCase(viewManagerModel, searchPlaceViewModel, resultsPlaceViewModel);
            return new SearchPlaceView(searchPlaceController, searchPlaceViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SearchPlaceController createUserSearchPlaceUseCase(ViewManagerModel viewManagerModel, SearchPlaceViewModel searchPlaceViewModel, ResultsPlaceViewModel resultsPlaceViewModel) throws IOException {
        ResultsDataAccessInterface mapsDataAccessObject = new MapsDataAccessObject();

        SearchPlaceOutputBoundary searchPlaceOutputBoundary = new SearchPlacePresenter(viewManagerModel, searchPlaceViewModel, resultsPlaceViewModel);

        SearchPlaceInputBoundary mapsSearchPlaceInteractor = new SearchPlaceInteractor(mapsDataAccessObject, searchPlaceOutputBoundary);

        return new SearchPlaceController(mapsSearchPlaceInteractor);
    }
}
