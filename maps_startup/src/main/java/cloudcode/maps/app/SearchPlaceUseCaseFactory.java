package cloudcode.maps.app;

import cloudcode.maps.data_access.*;
import cloudcode.maps.interface_adapter.*;
import cloudcode.maps.use_case.SearchPlaceInputBoundary;
import cloudcode.maps.use_case.SearchPlaceInteractor;
import cloudcode.maps.use_case.SearchPlaceOutputBoundary;
import cloudcode.maps.view.SearchPlaceView;
import cloudcode.maps.view.routing.JXMapViewerCustom;

import javax.swing.*;
import java.io.IOException;

public class SearchPlaceUseCaseFactory {

    private SearchPlaceUseCaseFactory() {}

    public static SearchPlaceView create(ViewManagerModel viewManagerModel, SearchPlaceViewModel searchPlaceViewModel, JXMapViewerCustom jxMapViewer) {

        try {
            SearchPlaceController searchPlaceController = createSearchPlaceUseCase(viewManagerModel, searchPlaceViewModel, jxMapViewer);
            return new SearchPlaceView(searchPlaceController, searchPlaceViewModel, jxMapViewer);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "");
        }

        return null;
    }

    private static SearchPlaceController createSearchPlaceUseCase(ViewManagerModel viewManagerModel, SearchPlaceViewModel searchPlaceViewModel, JXMapViewerCustom jxMapViewer) throws IOException {

        ResultsDataAccessInterface mapsDataAccessObject = new MapsDataAccessObject();

        SearchPlaceOutputBoundary searchPlaceOutputBoundary = new SearchPlacePresenter(viewManagerModel, searchPlaceViewModel);

        SearchPlaceInputBoundary mapsSearchPlaceUseCaseInteractor = new SearchPlaceInteractor(mapsDataAccessObject, searchPlaceOutputBoundary);

        return new SearchPlaceController(mapsSearchPlaceUseCaseInteractor);
    }
}
