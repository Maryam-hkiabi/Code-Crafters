package cloudcode.maps.entity;

import com.google.maps.model.EncodedPolyline;
import com.google.maps.model.LatLng;

/** Object holding routing information from a Directions API call
 *
 */
public class Routes {

    private final Object[][] routes;
    private final LatLng[] locations;
    private final EncodedPolyline[] polylines;

    private final Object[] oriInfo;
    private final Object[] desInfo;
    private final Object[] wayInfo;

    /** Constructs Routes object
     *
     * @param routes Array of objects holding routes information
     * @param locations Array of LatLng objects for the routes' locations
     * @param polylines Array of EncodedPolylines holding polylines for the routes
     * @param oriInfo Array of objects holding origin places information
     * @param desInfo Array of objects holding destination places information
     * @param wayInfo Array of objects holding waypoint places information
     */
    public Routes(Object[][] routes, LatLng[] locations, EncodedPolyline[] polylines,
                  Object[] oriInfo, Object[] desInfo, Object[] wayInfo) {

        this.routes = routes;
        this.locations = locations;
        this.polylines = polylines;

        this.oriInfo = oriInfo;
        this.desInfo = desInfo;
        this.wayInfo = wayInfo;
    }

    /** Gets array of routes information
     *
     * @return Array of objects holding routes information
     */
    public Object[][] getRoutes() { return routes; }

    /** Gets array of LatLng locations for origin, destination, waypoint
     *
     * @return Array of LatLng locations
     */
    public LatLng[] getLocations() { return locations; }

    /** Gets array of EncodedPolylines for routes
     *
     * @return Array of EncodedPolylines
     */
    public EncodedPolyline[] getPolylines() { return polylines; }

    /** Gets array of origin places information
     *
     * @return Array of objects with origin information
     */
    public Object[] getOriInfo() { return oriInfo; }

    /** Gets array of destination places information
     *
     * @return Array of objects with destination information
     */
    public Object[] getDesInfo() { return desInfo; }

    /** Gets array of waypoint places information
     *
     * @return Array of objects with waypoint information
     */
    public Object[] getWayInfo() { return wayInfo; }
}
