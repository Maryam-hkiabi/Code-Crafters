package cloudcode.maps.entity;

import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResult;

import java.util.List;

public class PlacesFactory {

    public static Places createPlaces(List<PlacesSearchResult> resultList) {

        Object[][] resultsData = new Object[resultList.size()][4];
        LatLng[] locData = new LatLng[resultList.size()];

        for (int r = 0; r < resultList.size(); r++) {
            resultsData[r][0] = resultList.get(r).name;
            resultsData[r][1] = resultList.get(r).formattedAddress;
            resultsData[r][2] = resultList.get(r).rating;
            resultsData[r][3] = resultList.get(r).userRatingsTotal;

            locData[r] = resultList.get(r).geometry.location;
        }

        return new Places(resultsData, locData);
    }
}
