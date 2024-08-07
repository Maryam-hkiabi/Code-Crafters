package cloudcode.maps.routingUseCase.use_case;

import cloudcode.maps.routingUseCase.entity.Route;

import java.util.ArrayList;

public class RoutingOutputData {

    final private ArrayList<Route> routes;

    public RoutingOutputData(ArrayList<Route> routes) {
        this.routes = routes;
    }

    ArrayList<Route> getRoutes() {
        return routes;
    }
}
