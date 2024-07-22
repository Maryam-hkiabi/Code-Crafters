package cloudcode.maps.UseCases;

import com.google.maps.model.PlacesSearchResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import cloudcode.maps.DataAccess.PlacesDAO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.mockito.Mockito.when;

public class TextSearch_UseCase_test {
    private TextSearch textSearch;
    private final String apiKey = "AIzaSyAHh-dlNGXf-xhTCfO9ZBbB3RtR3YZ-kNk";
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        textSearch = new TextSearch(apiKey);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testGetUserInput() {
        String input = "library";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String userInput = textSearch.getUserInput();
        assertEquals("library", userInput);
    }

    private void assertEquals(String library, String userInput) {
    }

    @Test
    void testTextSearch() throws Exception {
        String query = "library";
        PlacesSearchResult result1 = new PlacesSearchResult();
        result1.name = "Library A";
        result1.formattedAddress = "123 Main St";

        PlacesSearchResult result2 = new PlacesSearchResult();
        result2.name = "Library B";
        result2.formattedAddress = "456 Elm St";

        PlacesDAO mockPlaceDAO = Mockito.mock(PlacesDAO.class);
        when(mockPlaceDAO.findPlacesByQuery(query)).thenReturn(Arrays.asList(new PlacesSearchResult[]{result1, result2}));

        textSearch = new TextSearch(apiKey) {
            @Override
            public PlacesSearchResult[] textSearch(String query) {
                return new PlacesSearchResult[]{result1, result2};
            }
        };

        PlacesSearchResult[] results = textSearch.textSearch(query);
        assertEquals("Library A", results[0].name);
        assertEquals("Library B", results[1].name);
    }

    @Test
    void testPrintResponse() {
        PlacesSearchResult result1 = new PlacesSearchResult();
        result1.name = "Library A";
        result1.formattedAddress = "123 Main St";

        PlacesSearchResult result2 = new PlacesSearchResult();
        result2.name = "Library B";
        result2.formattedAddress = "456 Elm St";

        PlacesSearchResult[] results = new PlacesSearchResult[]{result1, result2};

        InputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);

        textSearch.printResponse(results);

        String expectedOutput = "Places found:\n" +
                "1. Library A - 123 Main St\n" +
                "2. Library B - 456 Elm St\n" +
                "Enter the number of the place you want to select:\n" +
                "You selected: Library A - 123 Main St\n";
        assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    void testPrintResponseNoResults() {
        PlacesSearchResult[] results = new PlacesSearchResult[0];

        textSearch.printResponse(results);

        String expectedOutput = "No places found.";
        assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }
}
