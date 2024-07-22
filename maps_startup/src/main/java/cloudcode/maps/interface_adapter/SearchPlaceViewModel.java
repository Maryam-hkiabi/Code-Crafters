package cloudcode.maps.interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchPlaceViewModel extends ViewModel {

    public final String TITLE_LABEL = "Search Place View";
    public final String SEARCH_LABEL = "Search Place";

    public final String SEARCH_BUTTON_LABEL = "Search";

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
