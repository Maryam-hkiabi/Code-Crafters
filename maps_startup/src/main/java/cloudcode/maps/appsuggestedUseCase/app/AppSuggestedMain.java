package cloudcode.maps.appsuggestedUseCase.app;

import cloudcode.maps.appsuggestedUseCase.entity.PlaceCategories;
import cloudcode.maps.appsuggestedUseCase.interface_adapter.UserCategorySelection;
import com.google.maps.errors.ApiException;

import java.io.IOException;
import java.util.*;

public class AppSuggestedMain {

    public static void main(String[] args) throws IOException, InterruptedException, ApiException {
        ArrayList<String> categories = new ArrayList<String>(
            Arrays.asList("Cafes","Restaurants", "Student Services", "Libraries", "Parks", "Colleges"));
        PlaceCategories placeCategories = new PlaceCategories(categories);
        UserCategorySelection userCategorySelection = new UserCategorySelection(placeCategories);
        userCategorySelection.execute();
    }

}