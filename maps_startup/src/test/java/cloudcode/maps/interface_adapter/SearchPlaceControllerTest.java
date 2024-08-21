package cloudcode.maps.interface_adapter;

import cloudcode.maps.use_case.SearchPlaceInputBoundary;
import cloudcode.maps.use_case.SearchPlaceInputData;
import com.google.maps.errors.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class SearchPlaceControllerTest {

    private SearchPlaceInputBoundary mockSearchPlaceInputBoundary;
    private SearchPlaceController searchPlaceController;
    //those test was not working because the excution of controller create different address or pointer every time.
    @BeforeEach
    void setUp() {
        mockSearchPlaceInputBoundary = mock(SearchPlaceInputBoundary.class);
        searchPlaceController = new SearchPlaceController(mockSearchPlaceInputBoundary);
    }

    @Test
    void testExecuteSearchSuccess() throws IOException, InterruptedException, ApiException {
        // Define input
        String input = "test input";
        SearchPlaceInputData searchPlaceInputData = new SearchPlaceInputData(input);

        // Execute the search
        searchPlaceController.executeSearch(input);

        // Verify that execute was called once with the correct argument
        verify(mockSearchPlaceInputBoundary).execute(searchPlaceInputData);
    }

    @Test
    void testExecuteRouteSuccess() throws IOException, InterruptedException, ApiException {
        // Define inputs
        String origin = "origin";
        String destination = "destination";
        String waypoint = "waypoint";

        SearchPlaceInputData searchPlaceOrigin = new SearchPlaceInputData(origin);
        SearchPlaceInputData searchPlaceDestination = new SearchPlaceInputData(destination);
        SearchPlaceInputData searchPlaceWaypoint = new SearchPlaceInputData(waypoint);

        // Execute the route
        searchPlaceController.executeRoute(origin, destination, waypoint);

        // Verify that execute was called once with the correct arguments
        verify(mockSearchPlaceInputBoundary).execute(searchPlaceOrigin, searchPlaceDestination, searchPlaceWaypoint);
    }

    @Test
    void testExecuteSearchInteraction() throws IOException, InterruptedException, ApiException {
        // Define input
        String input = "test input";
        SearchPlaceInputData searchPlaceInputData = new SearchPlaceInputData(input);

        // Execute the search
        searchPlaceController.executeSearch(input);

        // Verify interaction with the mock
        verify(mockSearchPlaceInputBoundary, times(1)).execute(searchPlaceInputData);
    }

    @Test
    void testExecuteRouteInteraction() throws IOException, InterruptedException, ApiException {
        // Define inputs
        String origin = "origin";
        String destination = "destination";
        String waypoint = "waypoint";

        SearchPlaceInputData searchPlaceOrigin = new SearchPlaceInputData(origin);
        SearchPlaceInputData searchPlaceDestination = new SearchPlaceInputData(destination);
        SearchPlaceInputData searchPlaceWaypoint = new SearchPlaceInputData(waypoint);

        // Execute the route
        searchPlaceController.executeRoute(origin, destination, waypoint);

        // Verify interaction with the mock
        verify(mockSearchPlaceInputBoundary, times(1)).execute(searchPlaceOrigin, searchPlaceDestination, searchPlaceWaypoint);
    }
}
