package cloudcode.maps.entity;

import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResult;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlacesFactoryTest {

    @Test
    void testCreatePlaces() {
        // Prepare test data
        List<PlacesSearchResult> resultList = new ArrayList<>();

        // Create mock PlacesSearchResult objects
        PlacesSearchResult result1 = new PlacesSearchResult();
        result1.name = "Place 1";
        result1.formattedAddress = "Address 1";
        result1.rating = 4.5F;
        result1.userRatingsTotal = 100;
        result1.geometry = new com.google.maps.model.Geometry();
        result1.geometry.location = new LatLng(40.7128, -74.0060);

        PlacesSearchResult result2 = new PlacesSearchResult();
        result2.name = "Place 2";
        result2.formattedAddress = "Address 2";
        result2.rating = 3.8F;
        result2.userRatingsTotal = 50;
        result2.geometry = new com.google.maps.model.Geometry();
        result2.geometry.location = new LatLng(34.0522, -118.2437);

        resultList.add(result1);
        resultList.add(result2);

        // Call the method under test
        Places places = PlacesFactory.createPlaces(resultList);

        // Verify results
        assertNotNull(places);
        assertEquals(2, places.getPlaces().length);
        assertEquals(2, places.getPlaces().length);

        // Verify data for Place 1
        assertEquals("Place 1", places.getPlaces()[0][0]);
        assertEquals("Address 1", places.getPlaces()[0][1]);
        assertEquals(4.5F, places.getPlaces()[0][2]);
        assertEquals(100, places.getPlaces()[0][3]);
        assertEquals(new LatLng(40.7128, -74.0060), places.getLocations()[0]);

        // Verify data for Place 2
        assertEquals("Place 2", places.getPlaces()[1][0]);
        assertEquals("Address 2", places.getPlaces()[1][1]);
        assertEquals(3.8F, places.getPlaces()[1][2]);
        assertEquals(50, places.getPlaces()[1][3]);
        assertEquals(new LatLng(34.0522, -118.2437), places.getLocations()[1]);
    }
}
