package cloudcode.maps.DataAccess;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Places_Implementation implements PlacesDAO {

        private final GeoApiContext context;

        public Places_Implementation(String apiKey) {
            this.context = new GeoApiContext.Builder()
                    .apiKey(apiKey)
                    .build();
        }

        @Override
        public List<PlacesSearchResult> findPlacesByQuery(String query) {
            List<PlacesSearchResult> places = new ArrayList<>();
            try {
                PlacesSearchResponse response = PlacesApi.textSearchQuery(context, query).await();
                Collections.addAll(places, response.results);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return places;
        }
    }



