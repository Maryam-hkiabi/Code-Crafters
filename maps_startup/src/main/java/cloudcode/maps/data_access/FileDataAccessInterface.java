package cloudcode.maps.data_access;

import cloudcode.maps.entity.Routes;
import com.google.maps.errors.ApiException;

import java.io.IOException;

public interface FileDataAccessInterface {

    Routes fetchResults(String origin, String destination) throws IOException, InterruptedException, ApiException;
}
