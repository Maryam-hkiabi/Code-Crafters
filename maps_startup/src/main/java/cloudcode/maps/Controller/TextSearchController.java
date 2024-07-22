package cloudcode.maps.Controller;

import cloudcode.maps.UseCases.TextSearch_InputBoundary;
import cloudcode.maps.UseCases.TextSearch_Interactor;
import cloudcode.maps.UseCases.TextSearch_OutputData;
import cloudcode.maps.UseCases.TextSearch_InputData;
import cloudcode.maps.UseCases.TextSearch_OutputBoundary;

public class TextSearchController {
    private TextSearch_InputBoundary inputBoundary;

    public TextSearchController(TextSearch_InputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void searchPlaces(String query) {
        TextSearch_InputData inputData = new TextSearch_InputData(query);
        inputBoundary.searchPlaces(inputData);
    }

    public void handleSearch(String query) {
        TextSearch_InputData inputData = new TextSearch_InputData(query);
        inputBoundary.searchPlaces(inputData);
    }
}
