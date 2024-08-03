package cloudcode.maps;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TrafficModel;
import com.google.maps.model.TransitMode;
import com.google.maps.model.TransitRoutingPreference;
import com.google.maps.model.TravelMode;
import com.google.maps.model.Unit;
import com.google.maps.errors.ApiException;
import java.io.IOException;

public class DirectionsWaypointsTest {

    private static GeoApiContext context = new GeoApiContext.Builder()
            .apiKey("AIzaSyAHh-dlNGXf-xhTCfO9ZBbB3RtR3YZ-kNk")
            .build();

    public static void main(String[] args) throws IOException, InterruptedException, ApiException {

        DirectionsApiRequest directionsrequest = new DirectionsApiRequest(context);
        TravelMode travelMode = TravelMode.WALKING;

        DirectionsResult response = directionsrequest.origin("1 Spadina Cres, Toronto, ON M5S 2J5")
                .destination("100 St Joseph St, Toronto, ON M5S 2C4").mode(travelMode).alternatives(true)
                .waypoints("7 Hart House Cir, Toronto, ON M5S 3H3").await();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        System.out.println(gson.toJson(response));

        context.shutdown();
    }
}

