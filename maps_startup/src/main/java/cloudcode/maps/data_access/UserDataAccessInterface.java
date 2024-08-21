package cloudcode.maps.data_access;

import java.io.IOException;

public interface UserDataAccessInterface {

    void saveSearch(String search) throws IOException;

    void removeSearch(String search) throws IOException;

    void clearSearchHistory() throws IOException;
}
