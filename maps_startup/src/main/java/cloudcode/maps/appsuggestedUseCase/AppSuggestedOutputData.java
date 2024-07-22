package cloudcode.maps.appsuggestedUseCase;

import java.util.ArrayList;

public class AppSuggestedOutputData {

    final public ArrayList<ArrayList<String>> places;

    public AppSuggestedOutputData(ArrayList<ArrayList<String>> places) {
        this.places = places;
    }

    ArrayList<ArrayList<String>> getPlaces() {
        return places;
    }
}

