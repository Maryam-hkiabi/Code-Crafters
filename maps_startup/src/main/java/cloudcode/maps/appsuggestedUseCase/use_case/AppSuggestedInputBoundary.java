package cloudcode.maps.appsuggestedUseCase.use_case;

import com.google.maps.errors.ApiException;

import java.io.IOException;

public interface AppSuggestedInputBoundary {
    void execute(AppSuggestedInputData appsuggestedInputData) throws IOException, InterruptedException, ApiException;
}

