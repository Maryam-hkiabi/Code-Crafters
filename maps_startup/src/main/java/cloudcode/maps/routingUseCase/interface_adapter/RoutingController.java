package cloudcode.maps.routingUseCase.interface_adapter;

import cloudcode.maps.routingUseCase.use_case.RoutingInputBoundary;
import cloudcode.maps.routingUseCase.use_case.RoutingInputData;
import com.google.maps.errors.ApiException;

import java.io.IOException;

public class RoutingController {

    final RoutingInputBoundary routingUseCaseInteractor;

    public RoutingController(RoutingInputBoundary routingUseCaseInteractor) {
        this.routingUseCaseInteractor = routingUseCaseInteractor;
    }

    public void execute(String origin, String destination) throws IOException, InterruptedException, ApiException {
        RoutingInputData routingInputData = new RoutingInputData(
                origin, destination);

        routingUseCaseInteractor.execute(routingInputData);
    }
}
