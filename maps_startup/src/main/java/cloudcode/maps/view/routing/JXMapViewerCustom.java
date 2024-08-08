package cloudcode.maps.view.routing;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.List;

import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.GeoPosition;

public class JXMapViewerCustom extends JXMapViewer {

    public List<RoutingData> getRoutingData() {
        return routingData;
    }

    public void setRoutingData(List<RoutingData> routingData) {
        this.routingData = routingData;
        repaint();
    }

    private List<RoutingData> routingData;

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if (routingData != null && !routingData.isEmpty()) {
            Graphics2D graphics2D = (Graphics2D) graphics.create();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Path2D path2D = new Path2D.Double();
            first = true;

            for (RoutingData data : routingData) {
                draw(path2D, data);
            }

            graphics2D.setStroke(new BasicStroke(5f,
                    BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND,
                    TOP_ALIGNMENT)
            );
            graphics2D.setColor(new Color(255, 23, 255));
            graphics2D.draw(path2D);
            graphics2D.dispose();
        }
    }

    private boolean first = true;

    private void draw(Path2D path2D, RoutingData data) {
        data.getPointList().forEach(ghPoint3D -> {
            Point2D point = convertGeoPositionToPoint(new GeoPosition(ghPoint3D.getLat(), ghPoint3D.getLon()));
            if (first) {
                first = false;
                path2D.moveTo(point.getX(), point.getY());
            } else {
                path2D.lineTo(point.getX(), point.getY());
            }
        });
    }
}
