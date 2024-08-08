package cloudcode.maps.interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchPlaceViewModel extends ViewModel {

    public final String TITLE_LABEL = "UofT Campus Location Search";
    public final String SEARCH_LABEL = "Search Place";
    public final String SEARCH_BUTTON_LABEL = "Search";
    public final String ROUTE_BUTTON_LABEL = "Route";
    public final String SET_ORIGIN_LABEL = "Set Origin";
    public final String SET_DESTINATION_LABEL = "Set Destination";

    private SearchPlaceState state = new SearchPlaceState();

    public SearchPlaceViewModel() { super("search"); }

    public void setState(SearchPlaceState state) { this.state = state; }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() { support.firePropertyChange("state", null, this.state); }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SearchPlaceState getState() {
        return state;
    }
}
