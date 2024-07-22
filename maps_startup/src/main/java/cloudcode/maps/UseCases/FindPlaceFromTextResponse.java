package cloudcode.maps.UseCases;

import cloudcode.maps.Entities.Place;

public class FindPlaceFromTextResponse {
    private final Place place;

    public FindPlaceFromTextResponse(Place place) {
        this.place = place;
    }

    public Place getPlace() {
        return place;
    }
}
