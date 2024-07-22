package cloudcode.maps.UseCases;

import com.google.maps.*;
import com.google.maps.PlacesApi;
import com.google.maps.TextSearchRequest;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import java.util.IllformedLocaleException;
import java.util.List;
import java.util.Scanner;
import com.google.maps.GeoApiContext;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import java.util.Scanner;


public class TextSearch {

    private GeoApiContext context;
    private String apiKey;

    public TextSearch(String apiKey) {
        this.apiKey = apiKey;
        this.context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
    }

    public String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a keyword/phrase to search for a place:");
        return scanner.nextLine();
    }

    public PlacesSearchResult[] textSearch(String query) {
        try {
            PlacesSearchResponse response = PlacesApi.textSearchQuery(context, query).await();
            return response.results;
        } catch (Exception e) {
            e.printStackTrace();
            return new PlacesSearchResult[0];
        }
    }

    public void printResponse(PlacesSearchResult[] results) {
        if (results.length == 0) {
            System.out.println("No places found.");
        } else {
            System.out.println("Places found:");
            for (int i = 0; i < results.length; i++) {
                PlacesSearchResult place = results[i];
                System.out.println((i + 1) + ". " + place.name + " - " + place.formattedAddress);
            }

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the number of the place you want to select:");
            int selection = scanner.nextInt();

            if (selection < 1 || selection > results.length) {
                System.out.println("Invalid selection.");
            } else {
                PlacesSearchResult selectedPlace = results[selection - 1];
                System.out.println("You selected: " + selectedPlace.name + " - " + selectedPlace.formattedAddress);
                // Additional code to show the selected location on the map can be added here.
            }
        }
    }

    public void shutdown() {
        context.shutdown();
    }

    public static void main(String[] args) {
        String apiKey = "AIzaSyAHh-dlNGXf-xhTCfO9ZBbB3RtR3YZ-kNk";
        TextSearch textSearch = new TextSearch(apiKey);

        String query = textSearch.getUserInput();
        PlacesSearchResult[] results = textSearch.textSearch(query);
        textSearch.printResponse(results);

        textSearch.shutdown();
    }
}
