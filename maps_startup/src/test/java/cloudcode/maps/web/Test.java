package cloudcode.maps.web.data_access;

import cloudcode.maps.data_access.GoogleMapsDataAccessObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Polygon;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GoogleMapsDataAccessObjectTest {

    private GoogleMapsDataAccessObject googleMapsDAO;

    @BeforeEach
    void setUp() {
        googleMapsDAO = new GoogleMapsDataAccessObject();
    }

    @Test
    void testPolygonContainsPoint() {
        // Manually test the polygon creation and point containment logic
        Coordinate coordA = new Coordinate(43.666648, -79.403863);
        Coordinate coordB = new Coordinate(43.669714, -79.389430);
        Coordinate coordC = new Coordinate(43.655741, -79.383752);
        Coordinate coordD = new Coordinate(43.6529312083149, -79.3980531738549);

        Coordinate[] bounds = new Coordinate[]{coordA, coordB, coordC, coordD, coordA};

        GeometryFactory gf = new GeometryFactory();
        LinearRing ring = gf.createLinearRing(bounds);
        Polygon rect = gf.createPolygon(ring, null);

        // Test with a point inside the polygon
        Point insidePoint = gf.createPoint(new Coordinate(43.660, -79.395));
        assertTrue(rect.contains(insidePoint));

        // Test with a point outside the polygon
        Point outsidePoint = gf.createPoint(new Coordinate(43.670, -79.380));
        assertFalse(rect.contains(outsidePoint));
    }

    @Test
    void testPolygonWithMockData() {
        // This test simulates what would happen with mock data in a real scenario
        Coordinate coordA = new Coordinate(43.666648, -79.403863);
        Coordinate coordB = new Coordinate(43.669714, -79.389430);
        Coordinate coordC = new Coordinate(43.655741, -79.383752);
        Coordinate coordD = new Coordinate(43.6529312083149, -79.3980531738549);

        Coordinate[] bounds = new Coordinate[]{coordA, coordB, coordC, coordD, coordA};

        GeometryFactory gf = new GeometryFactory();
        LinearRing ring = gf.createLinearRing(bounds);
        Polygon rect = gf.createPolygon(ring, null);

        // Simulate API response data (latitude and longitude pairs)
        List<Point> mockPoints = new ArrayList<>();
        mockPoints.add(gf.createPoint(new Coordinate(43.665, -79.400))); // Inside
        mockPoints.add(gf.createPoint(new Coordinate(43.670, -79.380))); // Outside

        // Simulate filtering logic
        List<Point> filteredPoints = new ArrayList<>();
        for (Point point : mockPoints) {
            if (rect.contains(point)) {
                filteredPoints.add(point);
            }
        }

        // Test the filtered points
        assertEquals(1, filteredPoints.size()); // Only one point should be inside
        assertTrue(rect.contains(filteredPoints.get(0)));
    }
}
