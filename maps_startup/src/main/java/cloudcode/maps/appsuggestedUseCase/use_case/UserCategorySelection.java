package cloudcode.maps.appsuggestedUseCase.use_case;

import cloudcode.maps.appsuggestedUseCase.AppSuggestedInteractor;
import com.google.maps.errors.ApiException;

import java.io.IOException;
import java.util.Scanner;

public class UserCategorySelection {

    PlaceCategories placecategories;


    public UserCategorySelection(PlaceCategories placecategories) {
        this.placecategories = placecategories;
    }

    public void execute() throws IOException, InterruptedException, ApiException {
        boolean acceptableinput = false;
        Integer selection = null;
        while (!acceptableinput) {
            Scanner input = new Scanner(System.in);
            System.out.println("Please select a category from the following list by inputting an integer between 1 and 6 (i.e. 1 for the 1st category):" + placecategories.getCategories());

            selection = input.nextInt();

            if (selection <= placecategories.getCategories().size()) {
                acceptableinput = true;
            } else {
                System.out.println("Invalid selection, please choose again.");
            }
        }
        AppSuggestedController appSuggestedController = new AppSuggestedController(new AppSuggestedInteractor(new AppSuggestedPresenter()));
        appSuggestedController.execute(selection, placecategories);

    }
}
