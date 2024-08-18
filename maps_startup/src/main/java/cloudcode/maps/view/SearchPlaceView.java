package cloudcode.maps.view;

import cloudcode.maps.interface_adapter.SearchPlaceController;
import cloudcode.maps.interface_adapter.SearchPlaceState;
import cloudcode.maps.interface_adapter.SearchPlaceViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import cloudcode.maps.view.routing.JXMapViewerCustom;

import com.google.maps.errors.ApiException;
import com.google.maps.model.*;

import org.jdesktop.swingx.mapviewer.*;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import cloudcode.maps.view.waypoint.MyWaypoint;
import cloudcode.maps.view.waypoint.EventWaypoint;

public class SearchPlaceView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "search";

    private final SearchPlaceViewModel searchPlaceViewModel;
    private final SearchPlaceController searchPlaceController;

    private final JXMapViewerCustom jxMapViewer;

    private final JButton search;
    private final JButton route;

    // JTable resultsTable;
    // JTable routesTable;

    private EventWaypoint getEvent() {
        return waypoint -> JOptionPane.showMessageDialog(this, waypoint.getName(),
                "UofT Maps - Place Information", JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon("maps_startup/src/main/java/cloudcode/maps/view/icon/map.png"));
    }
    private EventWaypoint mapEvent;

    public SearchPlaceView(SearchPlaceController controller, SearchPlaceViewModel viewModel,
                           JXMapViewerCustom mapViewer) throws IOException {

        this.searchPlaceController = controller;
        this.searchPlaceViewModel = viewModel;

        this.jxMapViewer = mapViewer;

        searchPlaceViewModel.addPropertyChangeListener(this);

        searchPlaceViewModel.setSuggestionsList();
        searchPlaceViewModel.setLocationsList();
        searchPlaceViewModel.setMapProperties(jxMapViewer);

        mapEvent = getEvent();

        JLabel searchTitle = new JLabel(searchPlaceViewModel.TITLE_LABEL);
        searchTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel routeTitle = new JLabel(searchPlaceViewModel.ROUTING_LABEL);
        routeTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel routeOptions = new JLabel(searchPlaceViewModel.ROUTE_OPTIONS_LABEL);
        routeOptions.setAlignmentX(Component.CENTER_ALIGNMENT);

        JComboBox<String> searchInputField = new JComboBox<>(searchPlaceViewModel.suggestions.getSuggestions().toArray(new String[0]));

        searchInputField.setEditable(true);

        JComboBox<String> originInputField = new JComboBox<>(searchPlaceViewModel.locList.toArray(new String[0]));
        JComboBox<String> destinationInputField = new JComboBox<>(searchPlaceViewModel.locList.toArray(new String[0]));

        AutoCompleteDecorator.decorate(originInputField);
        AutoCompleteDecorator.decorate(destinationInputField);

        LabelInputPanel searchInput = new LabelInputPanel(
                new JLabel(searchPlaceViewModel.SEARCH_LABEL), searchInputField);

        LabelInputPanel originInput = new LabelInputPanel(
                new JLabel(searchPlaceViewModel.SET_ORIGIN_LABEL), originInputField);

        LabelInputPanel destinationInput = new LabelInputPanel(
                new JLabel(searchPlaceViewModel.SET_DESTINATION_LABEL), destinationInputField);

        searchInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        originInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        destinationInput.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonA = new JPanel();
        JPanel buttonB = new JPanel();

        buttonA.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonB.setAlignmentX(Component.CENTER_ALIGNMENT);

        search = new JButton(searchPlaceViewModel.SEARCH_BUTTON_LABEL);
        buttonA.add(search);

        route = new JButton(searchPlaceViewModel.ROUTE_BUTTON_LABEL);
        buttonB.add(route);

        search.addActionListener(
                evt -> {
                    if (evt.getSource().equals(search)) {
                        try {
                            searchPlaceController.executeSearch(searchInputField.getSelectedItem().toString());

                            Object[][] results = searchPlaceViewModel.getState().getResults();
                            LatLng[] locData = searchPlaceViewModel.getState().getLocData();

                            for (int i = 0; i < results.length; i++) {
                                String formattedName =
                                        searchPlaceViewModel.attrA[0] + ": " + results[i][0].toString() + "\r\n" +
                                        searchPlaceViewModel.attrA[1] + ": " + results[i][1].toString() + "\r\n" +
                                        searchPlaceViewModel.attrA[2] + ": " + results[i][2].toString() + "\r\n" +
                                        searchPlaceViewModel.attrA[3] + ": " + results[i][3].toString();

                                searchPlaceViewModel.waypoints.add(new MyWaypoint(formattedName,
                                        mapEvent, new GeoPosition(locData[i].lat, locData[i].lng)));
                            }
                            searchPlaceViewModel.initWaypoint(jxMapViewer);

                            // resultsTable = new JTable(results, attrA);
                            // resultsTable.setAlignmentX(Component.CENTER_ALIGNMENT);

                            // JScrollPane scrollPane = new JScrollPane(resultsTable);
                            // scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

                            // this.add(scrollPane);

                            this.revalidate();
                            this.repaint();

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
                            EncodedPolyline[] polylineList = searchPlaceViewModel.getState().getPolylineList();

                            Object[] oriData = searchPlaceViewModel.getState().getOriData();
                            Object[] desData = searchPlaceViewModel.getState().getDesData();

                            String formattedOri =
                                    searchPlaceViewModel.attrA[0] + ": " + oriData[0].toString() + "\r\n" +
                                    searchPlaceViewModel.attrA[1] + ": " + oriData[1].toString() + "\r\n" +
                                    searchPlaceViewModel.attrA[2] + ": " + oriData[2].toString() + "\r\n" +
                                    searchPlaceViewModel.attrA[3] + ": " + oriData[3].toString();

                            String formattedDes =
                                    searchPlaceViewModel.attrA[0] + ": " + desData[0].toString() + "\r\n" +
                                    searchPlaceViewModel.attrA[1] + ": " + desData[1].toString() + "\r\n" +
                                    searchPlaceViewModel.attrA[2] + ": " + desData[2].toString() + "\r\n" +
                                    searchPlaceViewModel.attrA[3] + ": " + desData[3].toString();

                            for (int i = 0; i < routes.length; i++) {
                                MyWaypoint waypointA = new MyWaypoint(formattedOri, MyWaypoint.PointType.START,
                                        mapEvent, new GeoPosition(locList[i][0].lat, locList[i][0].lng));

                                MyWaypoint waypointB = new MyWaypoint(formattedDes, MyWaypoint.PointType.END,
                                        mapEvent, new GeoPosition(locList[i][1].lat, locList[i][1].lng));

                                if (searchPlaceViewModel.waypoints.isEmpty()) {
                                    searchPlaceViewModel.waypoints.add(waypointA);
                                    searchPlaceViewModel.waypoints.add(waypointB);
                                }
                            }
                            searchPlaceViewModel.initWaypoint(jxMapViewer);

                            buttonB.remove(route);
                            buttonB.add(routeOptions);

                            List<Integer> currentList = new ArrayList<>(polylineList.length);

                            for (int i = 0; i < polylineList.length; i++) {
                                int currentRoute = i;

                                JCheckBox chooseRoute = new JCheckBox(
                                        new AbstractAction(routes[i][2] + " (" + routes[i][3] + ")") {
                                            @Override
                                            public void actionPerformed(ActionEvent ev) {
                                                JCheckBox routeCheck = (JCheckBox) ev.getSource();

                                                if (routeCheck.isSelected()) {
                                                    currentList.add(currentRoute);
                                                } else {
                                                    if (currentList.contains(currentRoute)) {
                                                        currentList.remove(currentList.get(currentList.indexOf(currentRoute)));
                                                    }
                                                }
                                                searchPlaceViewModel.initWaypoint(jxMapViewer, polylineList, currentList);
                                                System.out.println(currentList);
                                            }
                                        });

                                buttonB.add(chooseRoute);
                            }

                            // routesTable = new JTable(routes, searchPlaceViewModel.attrB);
                            // routesTable.setAlignmentX(Component.CENTER_ALIGNMENT);

                            // JScrollPane scrollPane = new JScrollPane(routesTable);
                            // scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

                            // this.add(scrollPane);

                            this.revalidate();
                            this.repaint();

                        } catch (IOException | InterruptedException | ApiException e) {
                            if (originInputField.getSelectedItem().toString().isEmpty() || destinationInputField.getSelectedItem().toString().isEmpty()) {
                                JOptionPane.showMessageDialog(this,
                                        "Invalid Request: Missing Value for Origin or Destination",
                                        "UofT Maps - Invalid Route Request",
                                        JOptionPane.ERROR_MESSAGE,
                                        new ImageIcon("maps_startup/src/main/java/cloudcode/maps/view/icon/error.png"));
                            } else {
                                throw new RuntimeException(e);
                            }
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

        this.add(Box.createVerticalStrut(10));

        this.add(searchTitle);
        this.add(Box.createVerticalStrut(5));

        this.add(searchInput);
        this.add(buttonA);

        this.add(Box.createVerticalStrut(15));

        this.add(routeTitle);
        this.add(Box.createVerticalStrut(5));

        this.add(originInput);
        this.add(destinationInput);
        this.add(buttonB);

        this.add(Box.createVerticalStrut(5));
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
       SearchPlaceState state = (SearchPlaceState) evt.getNewValue();
    }
}
