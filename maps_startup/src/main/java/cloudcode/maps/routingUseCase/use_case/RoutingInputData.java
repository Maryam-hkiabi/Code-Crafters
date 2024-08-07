package cloudcode.maps.routingUseCase.use_case;

public class RoutingInputData {

    final private String origin;
    final private String destination;

    public RoutingInputData(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }

    String getStart() {
        return origin;
    }

    String getEnd() {
        return destination;
    }
}
