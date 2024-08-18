package cloudcode.maps.entity;

import com.google.maps.model.EncodedPolyline;
import com.google.maps.model.LatLng;

public class Routes {

    private final Object[][] routes;
    private final LatLng[][] locations;
    private final EncodedPolyline[] polylines;

    private final Object[] oriInfo;
    private final Object[] desInfo;

    public Routes(Object[][] routes, LatLng[][] locations, EncodedPolyline[] polylines, Object[] oriInfo, Object[] desInfo) {
        this.routes = routes;
        this.locations = locations;
        this.polylines = polylines;

        this.oriInfo = oriInfo;
        this.desInfo = desInfo;
    }

    public Object[][] getRoutes() { return routes; }

    public LatLng[][] getLocations() { return locations; }

    public EncodedPolyline[] getPolylines() { return polylines; }

    public Object[] getOriInfo() { return oriInfo; }

    public Object[] getDesInfo() { return desInfo; }
}
