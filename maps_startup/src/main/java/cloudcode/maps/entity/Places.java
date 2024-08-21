package cloudcode.maps.entity;

import com.google.maps.model.LatLng;

/** Object holding places information from a Places API call
 *
 */
public class Places {

    private final Object[][] places;
    private final LatLng[] locations;

    /** Constructs a Places object
     *
     * @param places Array of objects holding places information
     * @param locations Array holding LatLng locations for places
     */
    public Places(Object[][] places, LatLng[] locations) {
        this.places = places;
        this.locations = locations;
    }

    /** Gets array of places information
     *
     * @return Array of objects holding places information
     */
    public Object[][] getPlaces() { return places; }

    /** Gets array of LatLng locations of places
     *
     * @return Array holding LatLng locations for places
     */
    public LatLng[] getLocations() { return locations; }
}
