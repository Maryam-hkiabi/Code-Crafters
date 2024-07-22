package cloudcode.maps.web;

import cloudcode.maps.interface_adapter.ResultsPlaceState;
import cloudcode.maps.interface_adapter.ResultsPlaceViewModel;
import cloudcode.maps.interface_adapter.SearchPlaceViewModel;
import cloudcode.maps.interface_adapter.ViewManagerModel;
import cloudcode.maps.view.ResultsPlaceView;
import cloudcode.maps.view.SearchPlaceView;
import cloudcode.maps.view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("Search Places");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        SearchPlaceViewModel searchPlaceViewModel = new SearchPlaceViewModel();
        ResultsPlaceViewModel resultsPlaceViewModel = new ResultsPlaceViewModel();

        SearchPlaceView searchPlaceView = SearchPlaceUseCaseFactory.create(viewManagerModel, searchPlaceViewModel, resultsPlaceViewModel);
        views.add(searchPlaceView, searchPlaceView.viewName);

        ResultsPlaceView resultsPlaceView = new ResultsPlaceView(resultsPlaceViewModel);
        views.add(resultsPlaceView, resultsPlaceView.viewName);

        viewManagerModel.setActiveView(searchPlaceView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
