package cloudcode.maps;


import cloudcode.maps.UseCases.PinPoint;
import com.google.maps.errors.ApiException;
import com.google.maps.model.FindPlaceFromText;
import org.junit.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class PinPointTest {
    @Test
    public void testPinPointLen() throws IOException, InterruptedException, ApiException {
        String destination = "27 King's College Circle, Toronto, ON M5S 1A1";
        PinPoint.initializeContext();
        FindPlaceFromText response = PinPoint.findPlaceFromText(destination);
        assertEquals(1, response.candidates.length);
    }

    @Test
    public void testPinPointAddress() throws IOException, InterruptedException, ApiException {
        String destination = "27 King's College Circle, Toronto, ON M5S 1A1";
        PinPoint.initializeContext();
        FindPlaceFromText response = PinPoint.findPlaceFromText(destination);
        assertEquals("27 King's College Circle, Toronto, ON M5S 1A1", response.candidates[0].name);
    }
}





