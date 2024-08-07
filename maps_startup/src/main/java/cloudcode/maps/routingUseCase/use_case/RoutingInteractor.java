package cloudcode.maps.routingUseCase.use_case;

import cloudcode.maps.appsuggestedUseCase.use_case.AppSuggestedAPICall;
import cloudcode.maps.routingUseCase.entity.RouteFactory;
import cloudcode.maps.routingUseCase.entity.Route;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;

import java.io.IOException;
import java.util.ArrayList;

public class RoutingInteractor implements RoutingInputBoundary {

    final RoutingOutputBoundary routePresenter;
    final RouteFactory routeFactory;

    public RoutingInteractor(RoutingOutputBoundary routingOutputBoundary,
                            RouteFactory routeFactory) {
        this.routePresenter = routingOutputBoundary;
        this.routeFactory = routeFactory;
    }

    @Override
    public void execute(RoutingInputData routingInputData) throws IOException, InterruptedException, ApiException {

        RoutingAPICall apicall = new RoutingAPICall(routingInputData.getStart(), routingInputData.getEnd());

        DirectionsResult apireturn = apicall.execute();

        ArrayList<Route> routes = new ArrayList<Route>();
        String summary;
        String startaddress;
        String endaddress;
        String displaydistance;
        Float meterdistance;
        String displayduration;
        Float secondsduration;
        String polyline;

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        for (int i = 0; i < apireturn.routes.length; i++) {
            summary = gson.toJson(apireturn.routes[i].summary);
            startaddress = gson.toJson(apireturn.routes[i].legs[0].startAddress);
            endaddress = gson.toJson(apireturn.routes[i].legs[0].endAddress);
            displaydistance = gson.toJson(apireturn.routes[i].legs[0].distance.humanReadable);
            meterdistance = Float.valueOf(gson.toJson(apireturn.routes[i].legs[0].distance.inMeters));
            displayduration = gson.toJson(apireturn.routes[i].legs[0].duration.humanReadable);
            secondsduration = Float.valueOf(gson.toJson(apireturn.routes[i].legs[0].duration.inSeconds));
            polyline = gson.toJson(apireturn.routes[i].overviewPolyline.getEncodedPath());

            Route route = routeFactory.create(summary, startaddress, endaddress, displaydistance, meterdistance,
                    displayduration, secondsduration, polyline);
            routes.add(route);
        }

        RoutingOutputData routingOutputData = new RoutingOutputData(routes);
        routePresenter.prepareSuccessView(routingOutputData);
    }
}
