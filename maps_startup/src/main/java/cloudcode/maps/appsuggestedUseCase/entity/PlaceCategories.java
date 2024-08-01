package cloudcode.maps.appsuggestedUseCase.entity;

import java.util.ArrayList;

public class PlaceCategories {

    private final ArrayList<String> categories;

    public PlaceCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }
}
