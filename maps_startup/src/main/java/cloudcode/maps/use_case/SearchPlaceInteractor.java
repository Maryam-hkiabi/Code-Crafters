package cloudcode.maps.use_case;

import cloudcode.maps.data_access.ResultsDataAccessInterface;

import com.google.maps.*;
import cloudcode.maps.Context;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

        // Write method that takes in search input, and executes search

        TextSearchRequest request = PlacesApi.textSearchQuery(Context.context, searchPlaceInputData.getSearch());

        PlacesSearchResponse response = request.await();

        SearchPlaceOutputData results = new SearchPlaceOutputData(response.results.toString());

        mapsPresenter.prepareResultsView(results);

        Context.context.shutdown();
    }
}
