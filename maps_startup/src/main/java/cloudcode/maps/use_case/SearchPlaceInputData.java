package cloudcode.maps.use_case;

/** Input data for a place search
 *
 */
public class SearchPlaceInputData {

    final private String search;

    /** Constructs SearchPlaceInputData object
     *
     * @param search String representing search query
     */
    public SearchPlaceInputData(String search) {
        this.search = search;
    }

    /** Getter for search query
     *
     * @return String representing search query
     */
    String getSearch() { return search; }
}
