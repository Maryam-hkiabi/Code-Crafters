package cloudcode.maps.web.web;

import cloudcode.maps.web.MapsController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MapsControllerTest {

    private MapsController mapsController;
    private Model model;

    @BeforeEach
    void setUp() {
        mapsController = new MapsController();
        model = mock(Model.class);

        // Mock environment variables for testing
        System.setProperty("K_REVISION", "test-revision");
        System.setProperty("K_SERVICE", "test-service");
    }


    @Test
    void testMapsWithDefaultEnvironmentVariables() {
        // Reset the environment variables for default values
        System.clearProperty("K_REVISION");
        System.clearProperty("K_SERVICE");

        // Call the controller method
        String viewName = mapsController.maps(model);

        // Verify the model attributes
        verify(model).addAttribute("revision", "???");
        verify(model).addAttribute("service", "???");

        // Verify the view name
        assertEquals("index", viewName);
    }
}
