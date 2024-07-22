package cloudcode.maps.UseCases;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.PlaceAutocompleteType;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;

import java.util.Scanner;

public class TextSearchUseCase {

    private GeoApiContext context;
    private String apiKey = "AIzaSyAHh-dlNGXf-xhTCfO9ZBbB3RtR3YZ-kNk";

    public TextSearchUseCase(String apiKey) {
        this.apiKey = apiKey;
        this.context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
    }


    public void searchAndSelectDestination(String query) {
        try {
            PlacesSearchResponse response = PlacesApi.textSearchQuery(context, query).await();
            PlacesSearchResult[] results = response.results;

            if (results.length == 0) {
                System.out.println("No results found for the query: " + query);
                return;
            }

            System.out.println("Search results for \"" + query + "\":");
            for (int i = 0; i < results.length; i++) {
                System.out.println((i + 1) + ". " + results[i].name + " - " + results[i].formattedAddress);
            }

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the number of the destination you want to select:");
            int selection = scanner.nextInt();

            if (selection < 1 || selection > results.length) {
                System.out.println("Invalid selection.");
            } else {
                PlacesSearchResult selectedResult = results[selection - 1];
                System.out.println("You selected: " + selectedResult.name + " - " + selectedResult.formattedAddress);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Replace with your actual API key
        String apiKey = "AIzaSyAHh-dlNGXf-xhTCfO9ZBbB3RtR3YZ-kNk";

        TextSearchUseCase useCase = new TextSearchUseCase(apiKey);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a keyword/phrase to search for a library:");
        String query = scanner.nextLine();

        useCase.searchAndSelectDestination(query);
    }
}
