package cloudcode.maps.entity;

import com.google.maps.model.LatLng;

public class Places {

    private final Object[][] places;
    private final LatLng[] locations;

    public Places(Object[][] places, LatLng[] locations) {
        this.places = places;
        this.locations = locations;
    }

    public Object[][] getPlaces() { return places; }

    public LatLng[] getLocations() { return locations; }
}
