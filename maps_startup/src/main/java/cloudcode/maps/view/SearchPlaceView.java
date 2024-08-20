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
        JComboBox<String> waypointInputField = new JComboBox<>(searchPlaceViewModel.locList.toArray(new String[0]));

        AutoCompleteDecorator.decorate(originInputField);
        AutoCompleteDecorator.decorate(destinationInputField);
        AutoCompleteDecorator.decorate(waypointInputField);

        LabelInputPanel searchInput = new LabelInputPanel(
                new JLabel(searchPlaceViewModel.SEARCH_LABEL), searchInputField);

        LabelInputPanel originInput = new LabelInputPanel(
                new JLabel(searchPlaceViewModel.SET_ORIGIN_LABEL), originInputField);

        LabelInputPanel destinationInput = new LabelInputPanel(
                new JLabel(searchPlaceViewModel.SET_DESTINATION_LABEL), destinationInputField);

        LabelInputPanel waypointInput = new LabelInputPanel(
                new JLabel(searchPlaceViewModel.SET_WAYPOINT_LABEL), waypointInputField);

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
                                        searchPlaceViewModel.attr[0] + ": " + results[i][0].toString() + "\r\n" +
                                        searchPlaceViewModel.attr[1] + ": " + results[i][1].toString() + "\r\n" +
                                        searchPlaceViewModel.attr[2] + ": " + results[i][2].toString() + "\r\n" +
                                        searchPlaceViewModel.attr[3] + ": " + results[i][3].toString();

                                searchPlaceViewModel.waypoints.add(new MyWaypoint(formattedName,
                                        mapEvent, new GeoPosition(locData[i].lat, locData[i].lng)));
                            }
                            searchPlaceViewModel.initWaypoint(jxMapViewer);

                            // resultsTable = new JTable(results, attr);
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
                            searchPlaceController.executeRoute(
                                    originInputField.getSelectedItem().toString(),
                                    destinationInputField.getSelectedItem().toString(),
                                    waypointInputField.getSelectedItem().toString()
                            );

                            Object[][] routes = searchPlaceViewModel.getState().getRoutes();
                            LatLng[] locList = searchPlaceViewModel.getState().getLocList();
                            EncodedPolyline[] polylineList = searchPlaceViewModel.getState().getPolylineList();

                            Object[] oriData = searchPlaceViewModel.getState().getOriData();
                            Object[] desData = searchPlaceViewModel.getState().getDesData();
                            Object[] wayData = searchPlaceViewModel.getState().getWayData();

                            String formattedOri =
                                    searchPlaceViewModel.attr[0] + ": " + oriData[0].toString() + "\r\n" +
                                    searchPlaceViewModel.attr[1] + ": " + oriData[1].toString() + "\r\n" +
                                    searchPlaceViewModel.attr[2] + ": " + oriData[2].toString() + "\r\n" +
                                    searchPlaceViewModel.attr[3] + ": " + oriData[3].toString();

                            String formattedDes =
                                    searchPlaceViewModel.attr[0] + ": " + desData[0].toString() + "\r\n" +
                                    searchPlaceViewModel.attr[1] + ": " + desData[1].toString() + "\r\n" +
                                    searchPlaceViewModel.attr[2] + ": " + desData[2].toString() + "\r\n" +
                                    searchPlaceViewModel.attr[3] + ": " + desData[3].toString();

                            String formattedWay =
                                    searchPlaceViewModel.attr[0] + ": " + wayData[0].toString() + "\r\n" +
                                    searchPlaceViewModel.attr[1] + ": " + wayData[1].toString() + "\r\n" +
                                    searchPlaceViewModel.attr[2] + ": " + wayData[2].toString() + "\r\n" +
                                    searchPlaceViewModel.attr[3] + ": " + wayData[3].toString();

                            MyWaypoint waypointA = new MyWaypoint(formattedOri, mapEvent,
                                    new GeoPosition(locList[0].lat, locList[0].lng));

                            MyWaypoint waypointB = new MyWaypoint(formattedDes, mapEvent,
                                    new GeoPosition(locList[1].lat, locList[1].lng));

                            if (formattedWay.equals(formattedDes)) {
                                searchPlaceViewModel.waypoints.add(waypointA);
                                searchPlaceViewModel.waypoints.add(waypointB);

                            } else {
                                MyWaypoint waypointC = new MyWaypoint(formattedWay, mapEvent,
                                        new GeoPosition(locList[2].lat, locList[2].lng));

                                searchPlaceViewModel.waypoints.add(waypointA);
                                searchPlaceViewModel.waypoints.add(waypointB);
                                searchPlaceViewModel.waypoints.add(waypointC);
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
                                            }
                                        });

                                buttonB.add(chooseRoute);
                            }

                            this.revalidate();
                            this.repaint();

                        } catch (IOException | InterruptedException | ApiException e) {
                            ImageIcon errorIcon = new ImageIcon("maps_startup/src/main/java/cloudcode/maps/view/icon/error.png");
                            String errorTitle = "UofT Maps - Invalid Route Request";

                            if (originInputField.getSelectedItem().toString().isEmpty() || destinationInputField.getSelectedItem().toString().isEmpty()) {
                                JOptionPane.showMessageDialog(this,
                                        "INVALID REQUEST: Missing Origin or Destination Value", errorTitle,
                                        JOptionPane.ERROR_MESSAGE, errorIcon);

                            } else if (originInputField.getSelectedItem().equals(destinationInputField.getSelectedItem())) {
                                JOptionPane.showMessageDialog(this,
                                        "INVALID REQUEST: Origin and Destination Cannot Refer to Same Location", errorTitle,
                                        JOptionPane.ERROR_MESSAGE, errorIcon);

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
        this.add(waypointInput);

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
