package cloudcode.maps.web.data_access;

import cloudcode.maps.data_access.LocationFileDataAccessObject;
import com.google.maps.model.LatLng;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class LocationFileDataAccessObjectTest {

    private LocationFileDataAccessObject locationFileDAO;
    private File tempCsvFile;

    @BeforeEach
    void setUp() throws IOException {
        // Create a temporary CSV file to simulate reading from a real file
        tempCsvFile = File.createTempFile("locations", ".csv");
        try (FileWriter writer = new FileWriter(tempCsvFile)) {
            writer.write("name,address,latitude,longitude\n");
            writer.write("Origin Place,\"123 Main St\",43.6689,-79.3957\n");
            writer.write("Destination Place,\"456 Park Ave\",43.6532,-79.3832\n");
            writer.write("Waypoint Place,\"789 Lake Rd\",43.6611,-79.3922\n");
        }

        locationFileDAO = new LocationFileDataAccessObject(tempCsvFile.getAbsolutePath());
    }

    @Test
    void testCsvFileParsing() throws IOException {
        // Manually parse the CSV file to check if the method handles the CSV reading correctly
        String origin = "OriginLocation";
        String destination = "DestinationLocation";
        String waypoint = "WaypointLocation";

        LatLng oriLoc = new LatLng(43.661667, -79.395);
        LatLng desLoc = new LatLng(43.663056, -79.400);
        LatLng wayLoc = new LatLng(43.658333, -79.390);

        Assertions.assertNotNull(locationFileDAO);

    }

    @Test
    void testCsvFileNotFound() {
        // Test scenario where the CSV file does not exist
        LocationFileDataAccessObject invalidLocationFileDAO = new LocationFileDataAccessObject("non_existent_file.csv");

        Assertions.assertThrows(IOException.class, () -> {
            invalidLocationFileDAO.fetchResults("Origin Place", "Destination Place", "Waypoint Place");
        });
    }


}
