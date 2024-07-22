package cloudcode.maps.interface_adapter;

public class ResultsPlaceState {
    private String results = "";
    private String resultsError = null;

    public ResultsPlaceState(ResultsPlaceState copy) {
        results = copy.results;
        resultsError = copy.resultsError;
    }

    public ResultsPlaceState() {}

    public String getResults() { return results; }

    public String getResultsError() { return resultsError; }

    public void setResults(String results) { this.results = results; }

    public void setResultsError(String resultsError) { this.resultsError = resultsError; }
}
