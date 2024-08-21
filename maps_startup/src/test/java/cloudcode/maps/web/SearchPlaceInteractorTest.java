package cloudcode.maps.web.use_case;

import cloudcode.maps.data_access.MapsDataAccessInterface;
import cloudcode.maps.data_access.FileDataAccessInterface;
import cloudcode.maps.entity.Places;
import cloudcode.maps.entity.Routes;
import cloudcode.maps.use_case.SearchPlaceInputData;
import cloudcode.maps.use_case.SearchPlaceInteractor;
import cloudcode.maps.use_case.SearchPlaceOutputBoundary;
import com.google.maps.errors.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*; // Assuming Mockito is allowed
import static org.junit.jupiter.api.Assertions.*;

class SearchPlaceInteractorTest {

    private MapsDataAccessInterface mapsDataAccessInterface;
    private FileDataAccessInterface fileDataAccessInterface;
    private SearchPlaceOutputBoundary mapsPresenter;
    private SearchPlaceInteractor interactor;

    @BeforeEach
    void setUp() {
        mapsDataAccessInterface = mock(MapsDataAccessInterface.class);
        fileDataAccessInterface = mock(FileDataAccessInterface.class);
        mapsPresenter = mock(SearchPlaceOutputBoundary.class);
        interactor = new SearchPlaceInteractor(mapsDataAccessInterface, fileDataAccessInterface, mapsPresenter);
    }

    @Test
    void testExecuteSearchWithSinglePlace() throws IOException, InterruptedException, ApiException {
        // Setup
        SearchPlaceInputData inputData = new SearchPlaceInputData("test search");
        Places mockPlaces = mock(Places.class);
        when(mapsDataAccessInterface.fetchResults("test search")).thenReturn(mockPlaces);

        // Execute
        interactor.execute(inputData);

        // Verify interactions
        verify(mapsDataAccessInterface).fetchResults("test search");
        verify(mapsPresenter).updateSearchResults(mockPlaces);
    }

    @Test
    void testExecuteSearchWithRoute() throws IOException, InterruptedException, ApiException {
        // Setup
        SearchPlaceInputData origin = new SearchPlaceInputData("OriginLocation");
        SearchPlaceInputData destination = new SearchPlaceInputData("DestinationLocation");
        SearchPlaceInputData waypoint = new SearchPlaceInputData("WaypointLocation");
        Routes mockRoutes = mock(Routes.class);
        when(fileDataAccessInterface.fetchResults("OriginLocation", "DestinationLocation", "WaypointLocation")).thenReturn(mockRoutes);

        // Execute
        interactor.execute(origin, destination, waypoint);

        // Verify interactions
        verify(fileDataAccessInterface).fetchResults("OriginLocation", "DestinationLocation", "WaypointLocation");
        verify(mapsPresenter).updateRouteResults(mockRoutes);
    }

    @Test
    void testExecuteSearchWithExceptionHandling() {
        // Setup
        SearchPlaceInputData inputData = new SearchPlaceInputData("test search");

        try {
            when(mapsDataAccessInterface.fetchResults("test search")).thenThrow(new IOException("Test exception"));
        } catch (IOException | InterruptedException | ApiException e) {
            fail("Setup failed with exception: " + e.getMessage());
        }

        // Execute & Verify that exception is thrown
        assertThrows(IOException.class, () -> interactor.execute(inputData));

        // Verify that presenter is not called since an exception is thrown
        verify(mapsPresenter, never()).updateSearchResults(any());
    }
}
