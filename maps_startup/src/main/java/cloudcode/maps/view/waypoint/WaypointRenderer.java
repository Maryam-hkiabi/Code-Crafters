package cloudcode.maps.view.waypoint;

import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class WaypointRenderer extends WaypointPainter<MyWaypoint> {

    @Override
    protected void doPaint(Graphics2D graph, JXMapViewer map, int width, int h) {
        for (MyWaypoint wp : getWaypoints()) {

            Point2D points = map.getTileFactory().geoToPixel(wp.getPosition(), map.getZoom());
            Rectangle rect = map.getViewportBounds();

            int x = (int) (points.getX() - rect.getX());
            int y = (int) (points.getY() - rect.getY());

            JButton cmd = wp.getButton();
            cmd.setLocation(x - cmd.getWidth() / 2, y - cmd.getHeight());
        }
    }
}
