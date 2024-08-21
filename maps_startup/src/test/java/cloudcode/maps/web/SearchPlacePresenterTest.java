package cloudcode.maps.web.Interface_adapter;

import cloudcode.maps.entity.Places;
import cloudcode.maps.entity.Routes;
import cloudcode.maps.interface_adapter.SearchPlacePresenter;
import cloudcode.maps.interface_adapter.SearchPlaceState;
import cloudcode.maps.interface_adapter.SearchPlaceViewModel;
import cloudcode.maps.interface_adapter.ViewManagerModel;
import cloudcode.maps.use_case.SearchPlaceOutputBoundary;
import com.google.maps.model.LatLng;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class SearchPlacePresenterTest {

    private SearchPlacePresenter presenter;
    private SearchPlaceViewModel searchPlaceViewModel;
    private ViewManagerModel viewManagerModel;

    @BeforeEach
    void setUp() {
        searchPlaceViewModel = mock(SearchPlaceViewModel.class);
        viewManagerModel = mock(ViewManagerModel.class);

        presenter = new SearchPlacePresenter(viewManagerModel, searchPlaceViewModel);
    }

    @Test
    void testUpdateSearchResults() {
        // Prepare mock data
        Places places = mock(Places.class);
        SearchPlaceState searchPlaceState = mock(SearchPlaceState.class);

        when(searchPlaceViewModel.getState()).thenReturn(searchPlaceState);
        when(places.getPlaces()).thenReturn(new Object[0][]); // Replace with actual mock data as needed
        when(places.getLocations()).thenReturn(new LatLng[0]); // Replace with actual mock data as needed

        // Execute the method
        presenter.updateSearchResults(places);

        // Verify interactions
        verify(searchPlaceState).setResults(places.getPlaces());
        verify(searchPlaceState).setLocData(places.getLocations());
        verify(searchPlaceViewModel).setState(searchPlaceState);
        verify(searchPlaceViewModel).firePropertyChanged();
        verify(viewManagerModel).setActiveView(searchPlaceViewModel.getViewName());
        verify(viewManagerModel).firePropertyChanged();
    }

    @Test
    void testUpdateRouteResults() {
        // Prepare mock data
        Routes routes = mock(Routes.class);
        SearchPlaceState searchPlaceState = mock(SearchPlaceState.class);

        when(searchPlaceViewModel.getState()).thenReturn(searchPlaceState);
        when(routes.getRoutes()).thenReturn(new Object[0][]); // Replace with actual mock data as needed
        when(routes.getLocations()).thenReturn(new LatLng[0]); // Replace with actual mock data as needed
        when(routes.getOriInfo()).thenReturn(new Object[0]); // Replace with actual mock data as needed
        when(routes.getDesInfo()).thenReturn(new Object[0]); // Replace with actual mock data as needed
        when(routes.getWayInfo()).thenReturn(new Object[0]); // Replace with actual mock data as needed

        // Execute the method
        presenter.updateRouteResults(routes);

        // Verify interactions
        verify(searchPlaceState).setRoutes(routes.getRoutes());
        verify(searchPlaceState).setLocList(routes.getLocations());
        verify(searchPlaceState).setPolylineList(routes.getPolylines());
        verify(searchPlaceState).setOriData(routes.getOriInfo());
        verify(searchPlaceState).setDesData(routes.getDesInfo());
        verify(searchPlaceState).setWayData(routes.getWayInfo());
        verify(searchPlaceViewModel).setState(searchPlaceState);
        verify(searchPlaceViewModel).firePropertyChanged();
        verify(viewManagerModel).setActiveView(searchPlaceViewModel.getViewName());
        verify(viewManagerModel).firePropertyChanged();
    }
}
