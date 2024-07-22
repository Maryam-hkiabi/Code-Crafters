package cloudcode.maps.interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ResultsPlaceViewModel extends ViewModel {

    private ResultsPlaceState state = new ResultsPlaceState();

    public void setState(ResultsPlaceState state) { this.state = state; }

    public ResultsPlaceViewModel() {
        super("results");
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ResultsPlaceState getState() {
        return state;
    }
}
