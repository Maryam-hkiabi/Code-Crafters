package cloudcode.maps.entity;

import com.google.maps.model.EncodedPolyline;
import com.google.maps.model.LatLng;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.PlacesSearchResult;

public class RoutesFactory {

    public static Routes createRoutes(DirectionsRoute[] routes,
                                      String ori, String des, String way,
                                      LatLng oriLoc, LatLng desLoc, LatLng wayLoc,
                                      PlacesSearchResult oriMarker, PlacesSearchResult desMarker, PlacesSearchResult wayMarker) {

        Object[][] routesData = new Object[routes.length][5];
        LatLng[] locData = new LatLng[]{oriLoc, desLoc, wayLoc};
        EncodedPolyline[] polyData = new EncodedPolyline[routes.length];

        Object[] oriData = new Object[4];
        Object[] desData = new Object[4];
        Object[] wayData = new Object[4];

        for (int r = 0; r < routes.length; r++) {
            routesData[r][0] = ori;
            routesData[r][1] = des;
            routesData[r][4] = way;

            polyData[r] = routes[r].overviewPolyline;

            long distanceTotal = 0;
            long timeTotal = 0;

            for (int s = 0; s < routes[r].legs.length; s++) {
                distanceTotal += routes[r].legs[s].distance.inMeters;
                timeTotal += routes[r].legs[s].duration.inSeconds;
            }

            routesData[r][2] = distanceTotal + " m";
            routesData[r][3] = timeTotal / 60 + ":" + timeTotal % 60 + " mins";
        }

        oriData[0] = oriMarker.name;
        oriData[1] = oriMarker.formattedAddress;
        oriData[2] = oriMarker.rating;
        oriData[3] = oriMarker.userRatingsTotal;

        desData[0] = desMarker.name;
        desData[1] = desMarker.formattedAddress;
        desData[2] = desMarker.rating;
        desData[3] = desMarker.userRatingsTotal;

        wayData[0] = wayMarker.name;
        wayData[1] = wayMarker.formattedAddress;
        wayData[2] = wayMarker.rating;
        wayData[3] = wayMarker.userRatingsTotal;

        return new Routes(routesData, locData, polyData, oriData, desData, wayData);
    }
}
