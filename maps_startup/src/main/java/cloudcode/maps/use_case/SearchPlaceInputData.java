package cloudcode.maps.use_case;

public class SearchPlaceInputData {

    final private String search;

    public SearchPlaceInputData(String search) {
        this.search = search;
    }

    String getSearch() { return search; }
}
