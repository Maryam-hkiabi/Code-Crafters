package cloudcode.maps.use_case;

public class SearchPlaceOutputData {

    private final String results;

    public SearchPlaceOutputData(String results) {
        this.results = results;
    }

    public String getResults() { return results; }
}
