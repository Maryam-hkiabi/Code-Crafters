package cloudcode.maps.app;

import cloudcode.maps.interface_adapter.SearchPlaceViewModel;
import cloudcode.maps.interface_adapter.ViewManagerModel;
import cloudcode.maps.view.SearchPlaceView;
import cloudcode.maps.view.ViewManager;

import cloudcode.maps.view.routing.JXMapViewerCustom;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.*;

/** Main class for the UofTMaps app.
 *
 */
public class UoftMaps {
    /** The main method for running the app.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {

        JFrame application = new JFrame("UofT Maps - Searching & Routing");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        SearchPlaceViewModel searchPlaceViewModel = new SearchPlaceViewModel();

        JXMapViewerCustom jxMapViewer = new JXMapViewerCustom();

        SearchPlaceView searchPlaceView = SearchPlaceUseCaseFactory.create(viewManagerModel, searchPlaceViewModel, jxMapViewer);
        views.add(searchPlaceView, searchPlaceView.viewName);

        viewManagerModel.setActiveView(searchPlaceView.viewName);
        viewManagerModel.firePropertyChanged();

        application.setLayout(new BorderLayout());

        application.add(views, PAGE_START);
        application.add(jxMapViewer, CENTER);

        application.pack();
        application.setVisible(true);
    }
}