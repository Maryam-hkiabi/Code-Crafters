package cloudcode.maps.routingUseCase.use_case;

import cloudcode.maps.appsuggestedUseCase.use_case.AppSuggestedOutputData;

public interface RoutingOutputBoundary {

    void prepareSuccessView(RoutingOutputData routes);
}
