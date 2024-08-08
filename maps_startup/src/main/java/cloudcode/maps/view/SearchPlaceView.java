package cloudcode.maps.view;

import cloudcode.maps.interface_adapter.SearchPlaceController;
import cloudcode.maps.interface_adapter.SearchPlaceState;
import cloudcode.maps.interface_adapter.SearchPlaceViewModel;

import cloudcode.maps.view.routing.JXMapViewerCustom;
import cloudcode.maps.view.routing.RoutingData;
import cloudcode.maps.view.routing.RoutingService;
import cloudcode.maps.view.waypoint.EventWaypoint;
import org.jdesktop.swingx.mapviewer.*;
import org.jdesktop.swingx.input.*;
import org.jdesktop.swingx.OSMTileFactoryInfo;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import com.google.maps.errors.ApiException;
import com.google.maps.model.*;

import cloudcode.maps.view.waypoint.MyWaypoint;
import cloudcode.maps.view.waypoint.WaypointRenderer;

public class SearchPlaceView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "search";

    private final SearchPlaceViewModel searchPlaceViewModel;
    private final SearchPlaceController searchPlaceController;

    private final JButton search;private final JButton route;

    JXMapViewerCustom jxMapViewer;

    JComboBox<String> searchInputField = new JComboBox<>(new String[]{"", "library", "cafe", "sports"});
    JComboBox<String> originInputField = new JComboBox<>(new String[]{"", "Robarts Library"});
    JComboBox<String> destinationInputField = new JComboBox<>(new String[]{"", "Victoria College"});

    String[] attrA = {"Place Name", "Address", "Rating", "User Ratings"};
    String[] attrB = {"Origin", "Destination", "Distance", "Time"};

    JTable resultsTable;
    JTable routesTable;

    private final Set<MyWaypoint> waypoints = new HashSet<>();
    private EventWaypoint event;
    private List<RoutingData> routingData = new ArrayList<>();

    private void initWaypoint() {
        WaypointPainter<MyWaypoint> wp = new WaypointRenderer();
        wp.setWaypoints(waypoints);

        jxMapViewer.setOverlayPainter(wp);

        for (MyWaypoint d : waypoints) {
            jxMapViewer.add(d.getButton());
        }

        if (waypoints.size() == 2) {
            GeoPosition start = null;
            GeoPosition end = null;

            for (MyWaypoint w : waypoints) {
                if (w.getPointType() == MyWaypoint.PointType.START) {
                    start = w.getPosition();
                } else if (w.getPointType() == MyWaypoint.PointType.END) {
                    end = w.getPosition();
                }
            }

            if (start != null && end != null) {
                routingData = RoutingService.getInstance().routing(start.getLatitude(), start.getLongitude(), end.getLatitude(), end.getLongitude());
            } else {
                routingData.clear();
            }

            jxMapViewer.setRoutingData(routingData);
        }
    }

    private void clearWaypoint() {
        for (MyWaypoint d : waypoints) {
            jxMapViewer.remove(d.getButton());
        }
        routingData.clear();
        waypoints.clear();
        initWaypoint();
    }
    
    private void addWayPoint(MyWaypoint waypoint) {
        for (MyWaypoint d : waypoints) {
            jxMapViewer.remove(d.getButton());
        }

        waypoints.removeIf(waypoint1 -> waypoint1.getPointType() == waypoint.getPointType());

        waypoints.add(waypoint);
        initWaypoint();
    }

    private EventWaypoint getEvent() {
        return waypoint -> JOptionPane.showMessageDialog(this, waypoint.getName());
    }

    public SearchPlaceView(SearchPlaceController controller, SearchPlaceViewModel viewModel, JXMapViewerCustom mapViewer) {

        this.searchPlaceController = controller;
        this.searchPlaceViewModel = viewModel;
        searchPlaceViewModel.addPropertyChangeListener(this);

        this.jxMapViewer = mapViewer;

        searchInputField.setEditable(true);

        AutoCompleteDecorator.decorate(originInputField);
        AutoCompleteDecorator.decorate(destinationInputField);

        JLabel title = new JLabel(searchPlaceViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelInputPanel searchInput = new LabelInputPanel(
                new JLabel(searchPlaceViewModel.SEARCH_LABEL), searchInputField);

        LabelInputPanel originInput = new LabelInputPanel(
                new JLabel(searchPlaceViewModel.SET_ORIGIN_LABEL), originInputField);

        LabelInputPanel destinationInput = new LabelInputPanel(
                new JLabel(searchPlaceViewModel.SET_DESTINATION_LABEL), destinationInputField);

        JPanel buttonA = new JPanel();
        JPanel buttonB = new JPanel();

        search = new JButton(searchPlaceViewModel.SEARCH_BUTTON_LABEL);
        buttonA.add(search);

        route = new JButton(searchPlaceViewModel.ROUTE_BUTTON_LABEL);
        buttonB.add(route);

        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        jxMapViewer.setTileFactory(tileFactory);

        MouseInputListener mm = new PanMouseInputListener(jxMapViewer);
        jxMapViewer.addMouseListener(mm);
        jxMapViewer.addMouseMotionListener(mm);

        event = getEvent();

        GeoPosition geoA = new GeoPosition(43.666648, -79.403863);
        GeoPosition geoB = new GeoPosition(43.669714, -79.389430);
        GeoPosition geoC = new GeoPosition(43.6608090815737, -79.3858430328655);
        GeoPosition geoD = new GeoPosition(43.657958, -79.399889);

        Set<GeoPosition> geoPositionSet = new HashSet<>();

        geoPositionSet.add(geoA);
        geoPositionSet.add(geoB);
        geoPositionSet.add(geoC);
        geoPositionSet.add(geoD);

        jxMapViewer.calculateZoomFrom(geoPositionSet);
        jxMapViewer.setZoom(4);

        search.addActionListener(
                evt -> {
                    if (evt.getSource().equals(search)) {
                        try {
                            searchPlaceController.executeSearch(searchInputField.getSelectedItem().toString());

                            Object[][] results = searchPlaceViewModel.getState().getResults();
                            LatLng[] locData = searchPlaceViewModel.getState().getLocData();

                            resultsTable = new JTable(results, attrA);
                            resultsTable.setAlignmentX(Component.CENTER_ALIGNMENT);

                            JScrollPane scrollPane = new JScrollPane(resultsTable);
                            scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

                            this.add(scrollPane);

                            for (int i = 0; i < results.length; i++) {
                                waypoints.add(new MyWaypoint(results[i][0].toString() + "\r\n" + results[i][1].toString(),
                                        event, new GeoPosition(locData[i].lat, locData[i].lng)));
                            }

                            initWaypoint();

                        } catch (IOException | InterruptedException | ApiException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );

        route.addActionListener(
                evt -> {
                    if (evt.getSource().equals(route)) {
                        try {
                            searchPlaceController.executeRoute(originInputField.getSelectedItem().toString(),
                                    destinationInputField.getSelectedItem().toString());

                            Object[][] routes = searchPlaceViewModel.getState().getRoutes();
                            LatLng[][] locList = searchPlaceViewModel.getState().getLocList();

                            routesTable = new JTable(routes, attrB);
                            routesTable.setAlignmentX(Component.CENTER_ALIGNMENT);

                            JScrollPane scrollPane = new JScrollPane(routesTable);
                            scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

                            this.add(scrollPane);

                            for (int i = 0; i < routes.length; i++) {
                                waypoints.add(new MyWaypoint(routes[i][0].toString(),
                                        MyWaypoint.PointType.START,
                                        event,
                                        new GeoPosition(locList[i][0].lat, locList[i][0].lng)));

                                waypoints.add(new MyWaypoint(routes[i][1].toString(),
                                        MyWaypoint.PointType.END,
                                        event,
                                        new GeoPosition(locList[i][1].lat, locList[i][1].lng)));
                            }

                            initWaypoint();

                        } catch (IOException | InterruptedException | ApiException e) {
                                throw new RuntimeException(e);
                        }
                    }
                }
        );

        searchInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SearchPlaceState currentState = searchPlaceViewModel.getState();
                        currentState.setSearch(searchInputField.getSelectedItem().toString() + e.getKeyChar());
                        searchPlaceViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        originInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SearchPlaceState currentState = searchPlaceViewModel.getState();
                        currentState.setOrigin(originInputField.getSelectedItem().toString() + e.getKeyChar());
                        searchPlaceViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        destinationInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SearchPlaceState currentState = searchPlaceViewModel.getState();
                        currentState.setDestination(destinationInputField.getSelectedItem().toString() + e.getKeyChar());
                        searchPlaceViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);

        this.add(searchInput);
        this.add(buttonA);

        this.add(originInput);
        this.add(destinationInput);
        this.add(buttonB);
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
       // SearchPlaceState state = (SearchPlaceState) evt.getNewValue();
    }
}
