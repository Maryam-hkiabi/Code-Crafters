package cloudcode.maps.use_case;

import cloudcode.maps.data_access.ResultsDataAccessInterface;

import com.google.maps.*;
import cloudcode.maps.Context;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;

import java.io.IOException;

public class SearchPlaceInteractor implements SearchPlaceInputBoundary {
    final ResultsDataAccessInterface mapsDataAccessObject;
    final SearchPlaceOutputBoundary mapsPresenter;

    public SearchPlaceInteractor(ResultsDataAccessInterface resultsDataAccessInterface,
                                 SearchPlaceOutputBoundary searchPlaceOutputBoundary) {

        this.mapsDataAccessObject = resultsDataAccessInterface;
        this.mapsPresenter = searchPlaceOutputBoundary;
    }

    @Override
    public void execute(SearchPlaceInputData searchPlaceInputData) throws IOException, InterruptedException, ApiException {

        String search = searchPlaceInputData.getSearch();

        PlacesSearchResponse response;

        if (search.isEmpty()) {
            response = PlacesApi.nearbySearchQuery(Context.context,
                    new LatLng(43.6635, -79.3961)).await();
        } else {
            response = PlacesApi.textSearchQuery(Context.context,
                    search).await();
        }

        Object[][] resultsData = new Object[response.results.length][4];
        LatLng[] locData = new LatLng[response.results.length];

        for (int r = 0; r < response.results.length; r++) {
            resultsData[r][0] = response.results[r].name;
            resultsData[r][1] = response.results[r].formattedAddress;
            resultsData[r][2] = response.results[r].rating;
            resultsData[r][3] = response.results[r].userRatingsTotal;

            locData[r] = response.results[r].geometry.location;
        }

        SearchPlaceOutputData preparedResults = new SearchPlaceOutputData(resultsData, locData);

        mapsPresenter.updateSearchResults(preparedResults);

        Context.context.shutdown();
    }

    public void execute(SearchPlaceInputData searchPlaceOrigin, SearchPlaceInputData searchPlaceDestination) throws IOException, InterruptedException, ApiException {

        String origin = searchPlaceOrigin.getSearch();
        String destination = searchPlaceDestination.getSearch();

        PlacesSearchResponse ori = PlacesApi.textSearchQuery(Context.context, origin).await();
        PlacesSearchResponse des = PlacesApi.textSearchQuery(Context.context, destination).await();

        String oriAddress = ori.results[0].formattedAddress;
        String desAddress = des.results[0].formattedAddress;

        LatLng oriLoc = ori.results[0].geometry.location;
        LatLng desLoc = des.results[0].geometry.location;

        DirectionsResult response = DirectionsApi.getDirections(Context.context, oriAddress, desAddress).await();

        Object[][] routesData = new Object[response.routes.length][4];
        LatLng[][] locData = new LatLng[response.routes.length][2];

        for (int r = 0; r < response.routes.length; r++) {
            routesData[r][0] = oriAddress;
            routesData[r][1] = desAddress;

            locData[r][0] = oriLoc;
            locData[r][1] = desLoc;
        }

        SearchPlaceOutputRoute preparedRoutes = new SearchPlaceOutputRoute(routesData, locData);

        mapsPresenter.updateRouteResults(preparedRoutes);

        Context.context.shutdown();
    }
}
