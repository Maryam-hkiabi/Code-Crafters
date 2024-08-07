package cloudcode.maps.routingUseCase.entity;

import java.util.ArrayList;

public class Route {

    private final String summary;
    private final String startaddress;
    private final String endaddress;
    private final String displaydistance;
    private final Float meterdistance;
    private final String displayduration;
    private final Float secondsduration;
    private final String polyline;

    public Route(String summary, String startaddress, String endaddress, String displaydistance, Float meterdistance,
                 String displayduration, Float secondsduration, String polyline) {
        this.summary = summary;
        this.startaddress = startaddress;
        this.endaddress = endaddress;
        this.displaydistance = displaydistance;
        this.meterdistance = meterdistance;
        this.displayduration = displayduration;
        this.secondsduration = secondsduration;
        this.polyline = polyline;
    }

    public String getSummary() {
        return summary;
    }

    public String getStart() {
        return startaddress;
    }

    public String getEnd() {
        return endaddress;
    }

    public String getDisplayDistance() {
        return displaydistance;
    }

    public Float getMeterDistance() {
        return meterdistance;
    }

    public String getDisplayDuration() {
        return displayduration;
    }

    public Float getSecondsDuration() {
        return secondsduration;
    }

    public String getPolyline() {
        return polyline;
    }
}
