package cloudcode.maps.appsuggestedUseCase.use_case;

import cloudcode.maps.appsuggestedUseCase.interface_adapter.AppSuggestedPresenter;
import com.google.maps.errors.ApiException;

import java.io.IOException;
import java.util.ArrayList;

public class AppSuggestedInteractor implements AppSuggestedInputBoundary {

    final AppSuggestedOutputBoundary appsuggestedUseCasePresenter;

    public AppSuggestedInteractor(AppSuggestedOutputBoundary appsuggestedOutputBoundary) {
        this.appsuggestedUseCasePresenter = appsuggestedOutputBoundary;
    }

    @Override
    public void execute(AppSuggestedInputData appsuggestedInputData) throws IOException, InterruptedException, ApiException {

            ArrayList<ArrayList<String>> placesinfo = new ArrayList<ArrayList<String>>();

            AppSuggestedAPICall apicall = new AppSuggestedAPICall(appsuggestedInputData.getSelectedcategory());

            ArrayList<ArrayList<String>> apireturn = apicall.execute();

            for (int i = 0; i < 7; i++) {
                ArrayList<String> placeinfo = new ArrayList<String>();
                for (int j = 0; j < 3; j++) {
                    placeinfo.add(apireturn.get(j).get(i));
                }
                placesinfo.add(placeinfo);
            }

            AppSuggestedOutputData appsuggestedOutputData = new AppSuggestedOutputData(placesinfo);
            AppSuggestedPresenter appSuggestedPresenter = new AppSuggestedPresenter();
            appSuggestedPresenter.prepareOutput(appsuggestedOutputData);
        }
    }


