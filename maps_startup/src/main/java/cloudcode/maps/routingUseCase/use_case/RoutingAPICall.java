package cloudcode.maps.routingUseCase.use_case;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;
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
import java.util.ArrayList;

public class RoutingAPICall {

    private static GeoApiContext context = (new GeoApiContext.Builder()).apiKey("AIzaSyAHh-dlNGXf-xhTCfO9ZBbB3RtR3YZ-kNk").build();
    private String startaddress;
    private String endaddress;

    public RoutingAPICall(String startaddress, String endaddress) {
        this.startaddress = startaddress;
        this.endaddress = endaddress;
    }

    public DirectionsResult execute() throws IOException, InterruptedException, ApiException {

    DirectionsApiRequest directionsrequest = new DirectionsApiRequest(context);
        TravelMode travelMode = TravelMode.WALKING;

        DirectionsResult response = directionsrequest.origin(startaddress)
                .destination(endaddress).mode(travelMode).alternatives(true)
                .await();

        context.shutdown();

        return response;
    }
}
