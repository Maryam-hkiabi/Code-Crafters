package cloudcode.maps.view.routing;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.ResponsePath;
import com.graphhopper.config.*;

import com.graphhopper.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RoutingService {

    private static RoutingService instance;
    private final GraphHopper hopper;

    public static RoutingService getInstance() {
        if (instance == null) {
            instance = new RoutingService();
        }
        return instance;
    }

    private RoutingService() {
        hopper = createGraphHopperInstance("maps_startup/src/main/java/cloudcode/maps/view/routing/ontario-latest.osm.pbf");
    }

    private GraphHopper createGraphHopperInstance(String ghLoc) {
        GraphHopper graphHopper = new GraphHopper();

        graphHopper.setOSMFile(ghLoc);

        graphHopper.setGraphHopperLocation("target/routing-graph-cache");

        graphHopper.setProfiles(new Profile("foot").setCustomModel(GHUtility.loadCustomModelFromJar("foot.json")))
                .setEncodedValuesString("foot_access, hike_rating, foot_priority, foot_average_speed");

        graphHopper.getCHPreparationHandler().setCHProfiles(new CHProfile("foot"));

        graphHopper.importOrLoad();

        return graphHopper;
    }

    public List<RoutingData> routing(double fromLat, double fromLong, double toLat, double toLong) {

        GHRequest req = new GHRequest(fromLat, fromLong, toLat, toLong).
                setProfile("foot").
                setLocale(Locale.US);

        GHResponse rsp = hopper.route(req);

        if (rsp.hasErrors()) {
            throw new RuntimeException(rsp.getErrors().toString());
        }

        ResponsePath path = rsp.getBest();

        PointList pointList = path.getPoints();
        double distance = path.getDistance();
        long time = path.getTime();

        Translation tr = hopper.getTranslationMap().getWithFallBack(Locale.US);
        InstructionList il = path.getInstructions();

        List<RoutingData> l = new ArrayList<>();

        for (Instruction i : il) {
            // System.out.println("distance " + i.getDistance() + " instruction: " + i.getTurnDescription(tr));
            l.add(new RoutingData(i.getDistance(), i.getTurnDescription(tr), i.getPoints()));
        }

        return l;
    }
}
