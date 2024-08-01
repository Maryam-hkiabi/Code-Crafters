package cloudcode.maps.appsuggestedUseCase.interface_adapter;

import cloudcode.maps.appsuggestedUseCase.use_case.AppSuggestedInputBoundary;
import cloudcode.maps.appsuggestedUseCase.use_case.AppSuggestedInputData;
import cloudcode.maps.appsuggestedUseCase.entity.PlaceCategories;
import com.google.maps.errors.ApiException;

import java.io.IOException;

import cloudcode.maps.appsuggestedUseCase.entity.PlaceCategories;

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

