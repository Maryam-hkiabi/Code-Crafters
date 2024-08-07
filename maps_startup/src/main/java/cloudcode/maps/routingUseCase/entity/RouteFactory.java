package cloudcode.maps.routingUseCase.entity;

public class RouteFactory {

    public Route create(String summary, String start, String end, String displaydistance, Float meterdistance,
                        String displayduration, Float secondsduration, String polyline) {
        return new Route(summary, start, end, displaydistance, meterdistance, displayduration, secondsduration, polyline);
    }
}
