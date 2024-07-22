package cloudcode.maps.interface_adapter;

public class SearchPlaceState {
    private String search = "";
    private String searchError = null;

    public SearchPlaceState(SearchPlaceState copy) {
        search = copy.search;
        searchError = copy.searchError;
    }

    public SearchPlaceState() {}

    public String getSearch() { return search; }

    public String getSearchError() { return searchError; }

    public void setSearch(String search) { this.search = search; }

    public void setSearchError(String searchError) { this.searchError = searchError; }
}
