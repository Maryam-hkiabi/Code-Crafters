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

/** Factory for SearchPlaceView generation.
 */
public class SearchPlaceUseCaseFactory {

    private SearchPlaceUseCaseFactory() {}

    /** Generates SearchPlaceView for SearchPlace use case.
     *
     * @param viewManagerModel Object for managing the view
     * @param searchPlaceViewModel View Model for SearchPlace results
     * @param jxMapViewer Slippy map view
     * @return SearchPlaceView, or null if IOException thrown
     */
    public static SearchPlaceView create(ViewManagerModel viewManagerModel, SearchPlaceViewModel searchPlaceViewModel,
                                         JXMapViewerCustom jxMapViewer) {

        try {
            SearchPlaceController searchPlaceController = createSearchPlaceUseCase(viewManagerModel, searchPlaceViewModel, jxMapViewer);
            return new SearchPlaceView(searchPlaceController, searchPlaceViewModel, jxMapViewer);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "");
        }

        return null;
    }

    /** SearchPlaceController generator for SearchPlace use case.
     *
     * @param viewManagerModel Object for managing the view
     * @param searchPlaceViewModel View Model for SearchPlace results
     * @param jxMapViewer Slippy map view
     * @return SearchPlaceController
     * @throws IOException in case of file access error.
     */
    private static SearchPlaceController createSearchPlaceUseCase(ViewManagerModel viewManagerModel, SearchPlaceViewModel searchPlaceViewModel,
                                                                  JXMapViewerCustom jxMapViewer) throws IOException {

        MapsDataAccessInterface mapsDataAccessObject = new PlacesApiDataAccessObject();
        FileDataAccessInterface fileDataAccessObject = new LocationFileDataAccessObject("./uoft-campus-locations.csv");
        UserDataAccessInterface userDataAccessObject = new UserFileDataAccessObject("./user-search-history.txt");

        SearchPlaceOutputBoundary searchPlaceOutputBoundary = new SearchPlacePresenter(viewManagerModel, searchPlaceViewModel);

        SearchPlaceInputBoundary resultsUseCaseInteractor = new SearchPlaceInteractor(
                mapsDataAccessObject, fileDataAccessObject, userDataAccessObject, searchPlaceOutputBoundary);

        return new SearchPlaceController(resultsUseCaseInteractor);
    }
}
