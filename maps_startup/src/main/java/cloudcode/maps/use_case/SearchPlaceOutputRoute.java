package cloudcode.maps.use_case;

import com.google.maps.model.LatLng;

public class SearchPlaceOutputRoute {

    private final Object[][] routes;
    private final LatLng[][] locList;

    public SearchPlaceOutputRoute(Object[][] routes, LatLng[][] locList) {

        this.routes = routes;
        this.locList = locList;
    }

    public Object[][] getRoutes() { return routes; }
    public LatLng[][] getLocList() { return locList; }
}

