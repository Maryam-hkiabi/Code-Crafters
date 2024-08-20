package cloudcode.maps.use_case;

import cloudcode.maps.data_access.MapsDataAccessInterface;
import cloudcode.maps.data_access.FileDataAccessInterface;

import cloudcode.maps.entity.Places;
import cloudcode.maps.entity.Routes;
import com.google.maps.errors.ApiException;

import java.io.IOException;

public class SearchPlaceInteractor implements SearchPlaceInputBoundary {

    final MapsDataAccessInterface mapsDataAccessInterface;
    final FileDataAccessInterface fileDataAccessInterface;
    final SearchPlaceOutputBoundary mapsPresenter;

    public SearchPlaceInteractor(MapsDataAccessInterface mapsDataAccessInterface,
                                 FileDataAccessInterface fileDataAccessInterface,
                                 SearchPlaceOutputBoundary mapsPresenter) {

        this.mapsDataAccessInterface = mapsDataAccessInterface;
        this.fileDataAccessInterface = fileDataAccessInterface;
        this.mapsPresenter = mapsPresenter;
    }

    @Override
    public void execute(SearchPlaceInputData searchPlaceInputData) throws IOException, InterruptedException, ApiException {

        String search = searchPlaceInputData.getSearch();
        Places places = mapsDataAccessInterface.fetchResults(search);

        mapsPresenter.updateSearchResults(places);
    }

    public void execute(SearchPlaceInputData searchPlaceOrigin, SearchPlaceInputData searchPlaceDestination,
                        SearchPlaceInputData searchPlaceWaypoint)
            throws IOException, InterruptedException, ApiException {

        String origin = searchPlaceOrigin.getSearch();
        String destination = searchPlaceDestination.getSearch();
        String waypoint = searchPlaceWaypoint.getSearch();

        Routes routes = fileDataAccessInterface.fetchResults(origin, destination, waypoint);

        mapsPresenter.updateRouteResults(routes);
    }
}
