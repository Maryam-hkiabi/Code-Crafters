
package cloudcode.maps.UseCases;


import com.google.maps.*;
import com.google.maps.model.FindPlaceFromText;
import java.util.Scanner;

/*There exist a problem with the findPlacesFromTexts API that we are still trying to solve */

public class PinPoint {

    private static GeoApiContext context;

    public static void main(String[] args){
        initializeContext();
        String destination = getUserInput();
        FindPlaceFromText response = findPlaceFromText(destination);
        printResponse(response);
        shutdownContext();
    }

    public static void initializeContext() {
        context = new GeoApiContext.Builder()
                .apiKey("AIzaSyAHh-dlNGXf-xhTCfO9ZBbB3RtR3YZ-kNk")
                .build();
    }

    public static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your destination: ");
        return scanner.nextLine();
    }

    public static FindPlaceFromText findPlaceFromText(String destination) {
        FindPlaceFromTextRequest request = PlacesApi.findPlaceFromText(context,
                destination, FindPlaceFromTextRequest.InputType.TEXT_QUERY);
        return request.awaitIgnoreError();
    }

    static void printResponse(FindPlaceFromText response) {
        System.out.println(response.candidates[0]);
        context.shutdown();
    }

    private static void shutdownContext() {
        context.shutdown();
    }
}