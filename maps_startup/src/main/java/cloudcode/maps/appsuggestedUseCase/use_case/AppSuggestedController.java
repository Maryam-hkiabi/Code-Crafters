package cloudcode.maps.appsuggestedUseCase.use_case;

import cloudcode.maps.appsuggestedUseCase.AppSuggestedInputBoundary;
import cloudcode.maps.appsuggestedUseCase.AppSuggestedInputData;
import com.google.maps.errors.ApiException;

import java.io.IOException;

public class AppSuggestedController {

    final AppSuggestedInputBoundary appSuggestedUseCaseInteractor;
    public AppSuggestedController(AppSuggestedInputBoundary appSuggestedUseCaseInteractor) {
        this.appSuggestedUseCaseInteractor = appSuggestedUseCaseInteractor;
    }

    public void execute(Integer selection, PlaceCategories placecategories) throws IOException, InterruptedException, ApiException {
         AppSuggestedInputData appSuggestedInputData = new AppSuggestedInputData(placecategories.getCategories().get(selection - 1));

         appSuggestedUseCaseInteractor.execute(appSuggestedInputData);
    }
}

