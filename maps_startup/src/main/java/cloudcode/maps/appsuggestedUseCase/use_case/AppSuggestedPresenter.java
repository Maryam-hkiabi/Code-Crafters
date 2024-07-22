package cloudcode.maps.appsuggestedUseCase.use_case;

import cloudcode.maps.appsuggestedUseCase.AppSuggestedOutputBoundary;
import cloudcode.maps.appsuggestedUseCase.AppSuggestedOutputData;

public class AppSuggestedPresenter implements AppSuggestedOutputBoundary {

    public AppSuggestedPresenter() {
    }

    @Override
    public void prepareOutput(AppSuggestedOutputData appsuggestedOutputData) {
         System.out.println("Here are our top suggested places based on your chosen category, organized as lists of place names, addresses, and placeIDs:");
         System.out.println(appsuggestedOutputData.getPlaces());
    }
}