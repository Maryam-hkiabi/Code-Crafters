package cloudcode.maps.view.waypoint;

import javax.swing.*;
import org.jdesktop.swingx.mapviewer.*;

public class MyWaypoint extends DefaultWaypoint {
    private String name;
    private JButton button;

    public MyWaypoint(String name, EventWaypoint event, GeoPosition loc) {
        super(loc);
        this.name = name;

        initButton(event);
    }

    public MyWaypoint() {}

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    private void initButton(EventWaypoint event) {
        button = new Waypoint();
        button.addActionListener(e -> event.selected(MyWaypoint.this));
    }
}
