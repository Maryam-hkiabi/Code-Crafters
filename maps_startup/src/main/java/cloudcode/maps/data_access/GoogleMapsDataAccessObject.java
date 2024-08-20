package cloudcode.maps.data_access;

import cloudcode.maps.Context;
import com.google.maps.GeoApiContext;

import cloudcode.maps.entity.PlacesFactory;
import cloudcode.maps.entity.Places;

import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;

import org.locationtech.jts.geom.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoogleMapsDataAccessObject implements MapsDataAccessInterface {

    private final GeoApiContext context = Context.context;

    public GoogleMapsDataAccessObject() {}

    public Places fetchResults(String search) throws IOException, InterruptedException, ApiException {

        PlacesSearchResponse response;

        LatLng locX =  new LatLng(43.6635, -79.3961);

        if (search.isEmpty()) {
            response = PlacesApi.nearbySearchQuery(context, locX).await();
        } else {
            response = PlacesApi.textSearchQuery(context, search).await();
        }

        context.shutdown();

        List<PlacesSearchResult> resultList = new ArrayList<>();

        Coordinate coordA = new Coordinate(43.666648, -79.403863);
        Coordinate coordB = new Coordinate(43.669714, -79.389430);
        Coordinate coordC = new Coordinate(43.655741, -79.383752);
        Coordinate coordD = new Coordinate(43.6529312083149, -79.3980531738549);

        Coordinate[] bounds = new Coordinate[]{coordA, coordB, coordC, coordD, coordA};

        GeometryFactory gf = new GeometryFactory();
        LinearRing ring = gf.createLinearRing(bounds);
        Polygon rect = gf.createPolygon(ring, null);

        for (int i = 0; i < response.results.length; i++) {
            double lat = response.results[i].geometry.location.lat;
            double lng = response.results[i].geometry.location.lng;

            Point checkPoint = gf.createPoint(new Coordinate(lat, lng));

            if (rect.contains(checkPoint)) {
                resultList.add(response.results[i]);
            }
        }

        return PlacesFactory.createPlaces(resultList);
    }
}
