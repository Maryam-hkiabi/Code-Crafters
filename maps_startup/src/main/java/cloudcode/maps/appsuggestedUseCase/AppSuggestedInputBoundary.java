package cloudcode.maps.appsuggestedUseCase;

import com.google.maps.errors.ApiException;

import java.io.IOException;

public interface AppSuggestedInputBoundary {
    void execute(AppSuggestedInputData appsuggestedInputData) throws IOException, InterruptedException, ApiException;
}

