package cloudcode.maps.interface_adapter;

import com.google.maps.model.EncodedPolyline;
import com.google.maps.model.LatLng;

public class SearchPlaceState {
    private String search = "";
    private String origin = "";
    private String destination = "";

    private Object[][] results;
    private LatLng[] locData;

    private Object[][] routes;
    private LatLng[][] locList;
    private EncodedPolyline[] polylineList;

    private Object[] oriData;
    private Object[] desData;

    public SearchPlaceState(SearchPlaceState copy) {
        search = copy.search;
        origin = copy.origin;
        destination = copy.destination;

        results = copy.results;
        locData = copy.locData;

        routes = copy.routes;
        locList = copy.locList;
        polylineList = copy.polylineList;

        oriData = copy.oriData;
        desData = copy.desData;
    }

    public SearchPlaceState() {}

    public String getSearch() { return search; }

    public void setSearch(String search) { this.search = search; }

    public String getOrigin() { return origin; }

    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }

    public void setDestination(String destination) { this.destination = destination; }

    public Object[][] getResults() { return results; }

    public void setResults(Object[][] results) { this.results = results; }

    public LatLng[] getLocData() { return locData; }

    public void setLocData(LatLng[] locData) { this.locData = locData; }

    public Object[][] getRoutes() { return routes; }

    public void setRoutes(Object[][] routes) { this.routes = routes; }

    public LatLng[][] getLocList() { return locList; }

    public void setLocList(LatLng[][] locList) { this.locList = locList; }

    public EncodedPolyline[] getPolylineList() { return polylineList; }

    public void setPolylineList(EncodedPolyline[] polylines) { this.polylineList = polylines; }

    public Object[] getOriData() { return oriData; }

    public void setOriData(Object[] oriData) { this.oriData = oriData; }

    public Object[] getDesData() { return desData; }

    public void setDesData(Object[] desData) { this.desData = desData; }
}
