package cloudcode.maps.appsuggestedUseCase.use_case;

import java.util.ArrayList;

public class PlaceCategories {

    private final ArrayList<String> categories;

    public PlaceCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    ArrayList<String> getCategories() {
        return categories;
    }
}
