package cloudcode.maps.data_access;

import cloudcode.maps.entity.Places;
import com.google.maps.errors.ApiException;

import java.io.IOException;

public interface MapsDataAccessInterface {

    Places fetchResults(String search) throws IOException, InterruptedException, ApiException;
}
