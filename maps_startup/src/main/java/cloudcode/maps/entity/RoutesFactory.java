package cloudcode.maps.entity;

import com.google.maps.model.EncodedPolyline;
import com.google.maps.model.LatLng;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.PlacesSearchResult;

public class RoutesFactory {

    public static Routes createRoutes(DirectionsRoute[] routes,
                                      String ori, String des,
                                      LatLng oriLoc, LatLng desLoc,
                                      PlacesSearchResult[] oriMarker, PlacesSearchResult[] desMarker) {

        Object[][] routesData = new Object[routes.length][4];
        LatLng[][] locData = new LatLng[routes.length][2];
        EncodedPolyline[] polyData = new EncodedPolyline[routes.length];

        Object[] oriData = new Object[4];
        Object[] desData = new Object[4];

        for (int r = 0; r < routes.length; r++) {
            routesData[r][0] = ori;
            routesData[r][1] = des;

            locData[r][0] = oriLoc;
            locData[r][1] = desLoc;

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

        oriData[0] = oriMarker[0].name;
        oriData[1] = oriMarker[0].formattedAddress;
        oriData[2] = oriMarker[0].rating;
        oriData[3] = oriMarker[0].userRatingsTotal;

        desData[0] = desMarker[0].name;
        desData[1] = desMarker[0].formattedAddress;
        desData[2] = desMarker[0].rating;
        desData[3] = desMarker[0].userRatingsTotal;

        return new Routes(routesData, locData, polyData, oriData, desData);
    }
}
