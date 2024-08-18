package cloudcode.maps.interface_adapter;

import cloudcode.maps.entity.Suggestions;
import cloudcode.maps.entity.SuggestionsFactory;

import cloudcode.maps.view.routing.JXMapViewerCustom;

import cloudcode.maps.view.waypoint.MyWaypoint;
import cloudcode.maps.view.waypoint.WaypointRenderer;

import com.google.maps.model.EncodedPolyline;
import org.jdesktop.swingx.OSMTileFactoryInfo;
import org.jdesktop.swingx.input.PanMouseInputListener;

import org.jdesktop.swingx.mapviewer.DefaultTileFactory;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.TileFactoryInfo;
import org.jdesktop.swingx.mapviewer.WaypointPainter;

import javax.swing.event.MouseInputListener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import java.io.*;
import java.util.*;

public class SearchPlaceViewModel extends ViewModel {

    public final String TITLE_LABEL = "UofT Campus Location Search";
    public final String SEARCH_LABEL = "Search Place";
    public final String SEARCH_BUTTON_LABEL = "Search";

    public final String ROUTING_LABEL = "UofT Campus Location Routing";
    public final String SET_ORIGIN_LABEL = "Set Origin";
    public final String SET_DESTINATION_LABEL = "Set Destination";
    public final String ROUTE_BUTTON_LABEL = "Route";

    public final String[] attrA = {"PLACE NAME", "ADDRESS", "RATING", "USER RATINGS TOTAL"};
    public final String[] attrB = {"ORIGIN", "DESTINATION", "DISTANCE", "TIME"};

    public final String ROUTE_OPTIONS_LABEL = "Route Options";

    public final List<String> initCategories = Arrays.asList(
            "library",
            "event",
            "academic",
            "student",
            "residence",
            "cafe",
            "restaurant",
            "gym",
            "admin",
            "office",
            "museum",
            "park",
            "laboratory",
            "theatre",
            "health",
            "sports"
        );

    public final String locationsHeader = "name,address,latitude,longitude";
    public final String csvPath = "./uoft-campus-locations.csv";

    public SearchPlaceViewModel() { super("search"); }

    private SearchPlaceState state = new SearchPlaceState();

    public SearchPlaceState getState() {
        return state;
    }

    public void setState(SearchPlaceState state) { this.state = state; }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public Suggestions suggestions = SuggestionsFactory.createSuggestions(new ArrayList<>());
    List<String> categories = new ArrayList<>();

    public void setSuggestionsList() {
        categories.add("");
        categories.addAll(initCategories);
        SuggestionsFactory.addCategories(suggestions, categories);
    }

    public List<String> locList = new ArrayList<>();
    public final File csvFile = new File(csvPath);

    public void setLocationsList() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(csvFile));
        String header = reader.readLine();

        assert locationsHeader.equals(header);

        locList.add("");
        String row;

        while ((row = reader.readLine()) != null) {
            String[] entry = row.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

            locList.add(entry[0].replace("\"", ""));
        }
    }

    public final GeoPosition geoA = new GeoPosition(43.666648, -79.403863);
    public final GeoPosition geoB = new GeoPosition(43.669714, -79.389430);
    public final GeoPosition geoC = new GeoPosition(43.655741, -79.383752);
    public final GeoPosition geoD = new GeoPosition(43.6529312083149, -79.3980531738549);

    public void setMapProperties(JXMapViewerCustom jxMapViewer) {
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        jxMapViewer.setTileFactory(tileFactory);

        MouseInputListener mm = new PanMouseInputListener(jxMapViewer);
        jxMapViewer.addMouseListener(mm);
        jxMapViewer.addMouseMotionListener(mm);

        Set<GeoPosition> geoPositionSet = new HashSet<>();

        geoPositionSet.add(geoA);
        geoPositionSet.add(geoB);
        geoPositionSet.add(geoC);
        geoPositionSet.add(geoD);

        jxMapViewer.calculateZoomFrom(geoPositionSet);
        jxMapViewer.setZoom(3);
    }

    public Set<MyWaypoint> waypoints = new HashSet<>();

    public void initWaypoint(JXMapViewerCustom jxMapViewer) {

        WaypointPainter<MyWaypoint> wp = new WaypointRenderer();
        wp.setWaypoints(waypoints);

        jxMapViewer.setOverlayPainter(wp);

        for (MyWaypoint d : waypoints) {
            jxMapViewer.add(d.getButton());
        }
    }

    public void initWaypoint(JXMapViewerCustom jxMapViewer, EncodedPolyline[] polylineList,
                             List<Integer> chosenRoutes) {

        WaypointPainter<MyWaypoint> wp = new WaypointRenderer();
        wp.setWaypoints(waypoints);

        jxMapViewer.setOverlayPainter(wp);

        for (MyWaypoint d : waypoints) {
            jxMapViewer.add(d.getButton());
        }

        if (waypoints.size() == 2) {
            jxMapViewer.setPolylineData(polylineList, chosenRoutes);
        }
    }

    public void clearWaypoint(JXMapViewerCustom jxMapViewer) {
        for (MyWaypoint d : waypoints) {
            jxMapViewer.remove(d.getButton());
        }
        waypoints.clear();

        initWaypoint(jxMapViewer);
    }

    public void addWayPoint(JXMapViewerCustom jxMapViewer, MyWaypoint waypoint) {
        for (MyWaypoint d : waypoints) {
            jxMapViewer.remove(d.getButton());
        }
        waypoints.removeIf(waypoint1 -> waypoint1.getPointType() == waypoint.getPointType());
        waypoints.add(waypoint);

        initWaypoint(jxMapViewer);
    }
}


