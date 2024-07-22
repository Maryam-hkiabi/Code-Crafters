package cloudcode.maps.DataAccess;

import com.google.maps.model.PlacesSearchResult;
import java.util.List;

public interface PlacesDAO {
    List<PlacesSearchResult> findPlacesByQuery(String query);
}

