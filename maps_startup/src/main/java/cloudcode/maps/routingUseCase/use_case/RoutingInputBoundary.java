package cloudcode.maps.routingUseCase.use_case;

import cloudcode.maps.appsuggestedUseCase.use_case.AppSuggestedInputData;
import com.google.maps.errors.ApiException;

import java.io.IOException;

public interface RoutingInputBoundary {
    void execute(RoutingInputData routingInputData) throws IOException, InterruptedException, ApiException;
}
