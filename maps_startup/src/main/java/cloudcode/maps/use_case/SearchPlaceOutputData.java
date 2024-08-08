package cloudcode.maps.use_case;

import com.google.maps.model.LatLng;

public class SearchPlaceOutputData {

    private final Object[][] results;
    private final LatLng[] locData;

    public SearchPlaceOutputData(Object[][] results, LatLng[] locData) {

        this.results = results;
        this.locData = locData;
    }

    public Object[][] getResults() { return results; }
    public LatLng[] getLocData() { return locData; }
}
