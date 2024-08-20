package cloudcode.maps.data_access;

import cloudcode.maps.Context;
import com.google.maps.GeoApiContext;

import cloudcode.maps.entity.RoutesFactory;
import cloudcode.maps.entity.Routes;

import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LocationFileDataAccessObject implements FileDataAccessInterface {

    private final File csvFile;

    private final GeoApiContext context = Context.context;

    public LocationFileDataAccessObject(String csvPath) {
        csvFile = new File(csvPath);
    }

    public Routes fetchResults(String origin, String destination, String waypoint)
            throws IOException, InterruptedException, ApiException {

        String oriAddress = "";
        String desAddress = "";
        String wayAddress = "";

        LatLng oriLoc = new LatLng();
        LatLng desLoc = new LatLng();
        LatLng wayLoc = new LatLng();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String header = reader.readLine();

            assert header.equals("name,address,latitude,longitude");

            String row;

            while ((row = reader.readLine()) != null) {
                String[] entry = row.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                if (entry[0].equals(origin)) {
                    oriAddress = String.valueOf(entry[1]);
                    oriLoc = new LatLng(
                            Double.parseDouble(entry[2]),
                            Double.parseDouble(entry[3]));

                } else if (entry[0].equals(destination)) {
                    desAddress = String.valueOf(entry[1]);
                    desLoc = new LatLng(
                            Double.parseDouble(entry[2]),
                            Double.parseDouble(entry[3]));

                } else if (entry[0].equals(waypoint)) {
                    wayAddress = String.valueOf(entry[1]);
                    wayLoc = new LatLng(
                            Double.parseDouble(entry[2]),
                            Double.parseDouble(entry[3]));
                }
            }
        }
        oriAddress = oriAddress.replace("\"", "");
        desAddress = desAddress.replace("\"", "");
        wayAddress = wayAddress.replace("\"", "");

        DirectionsResult response;

        PlacesSearchResult oriMarker = PlacesApi.textSearchQuery(context, origin).await().results[0];
        PlacesSearchResult desMarker = PlacesApi.textSearchQuery(context, destination).await().results[0];
        PlacesSearchResult wayMarker = PlacesApi.textSearchQuery(context, destination).await().results[0];

        if (wayAddress.isEmpty()) {
            response = DirectionsApi.newRequest(context)
                    .origin(oriAddress)
                    .destination(desAddress)
                    .alternatives(true)
                    .mode(TravelMode.WALKING)
                    .await();
        } else {
            response = DirectionsApi.newRequest(context)
                    .origin(oriAddress)
                    .destination(desAddress)
                    .alternatives(true)
                    .mode(TravelMode.WALKING)
                    .waypoints(wayAddress)
                    .await();

            wayMarker = PlacesApi.textSearchQuery(context, waypoint).await().results[0];
        }

        context.shutdown();

        return RoutesFactory.createRoutes(response.routes,
                oriAddress, desAddress, wayAddress, oriLoc, desLoc, wayLoc,
                oriMarker, desMarker, wayMarker);
    }
}
