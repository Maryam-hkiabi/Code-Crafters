package cloudcode.maps.view.waypoint;

import javax.swing.*;
import org.jdesktop.swingx.mapviewer.*;

public class MyWaypoint extends DefaultWaypoint {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public PointType getPointType() {
        return pointType;
    }

    public void setPointType(PointType pointType) {
        this.pointType = pointType;
    }

    public MyWaypoint(String name, PointType pointType, EventWaypoint event, GeoPosition loc) {
        super(loc);
        this.name = name;
        this.pointType = pointType;

        initButton(event);
    }

    public MyWaypoint(String name, EventWaypoint event, GeoPosition loc) {
        super(loc);
        this.name = name;

        initButton(event);
    }

    public MyWaypoint() {
    }

    private String name;
    private JButton button;
    private PointType pointType;

    private void initButton(EventWaypoint event) {

        button = new Waypoint();
        button.addActionListener(e -> event.selected(MyWaypoint.this));
    }

    public enum PointType { START,END }
}
