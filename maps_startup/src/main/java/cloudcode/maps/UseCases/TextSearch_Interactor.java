package cloudcode.maps.UseCases;

import com.google.cloud.*;
import cloudcode.maps.DataAccess.PlacesDAO;
import com.google.maps.model.PlacesSearchResult;
import java.util.List;

public class TextSearch_Interactor implements TextSearch_InputBoundary {
    private final PlacesDAO placeDAO;
    private final TextSearch_OutputBoundary outputBoundary;

    public TextSearch_Interactor(PlacesDAO placeDAO, TextSearch_OutputBoundary outputBoundary) {
        this.placeDAO = placeDAO;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void searchPlaces(TextSearch_InputData inputData) {
        List<PlacesSearchResult> places = placeDAO.findPlacesByQuery(inputData.getQuery());
        TextSearch_OutputData outputData = new TextSearch_OutputData(places);
        outputBoundary.presentPlaces(outputData);
    }
}



