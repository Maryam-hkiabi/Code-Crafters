package cloudcode.maps.view.waypoint;

import java.awt.*;
import javax.swing.*;

public class Waypoint extends JButton {

    public Waypoint() {
        setContentAreaFilled(false);
        setIcon(new ImageIcon("maps_startup/src/main/java/cloudcode/maps/view/icon/pin.png"));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setSize(6, 6);
    }
}