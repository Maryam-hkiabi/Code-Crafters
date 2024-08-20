package cloudcode.maps.view.routing;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

import java.util.List;
import java.util.Random;

import com.google.maps.model.EncodedPolyline;
import com.google.maps.model.LatLng;

import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.GeoPosition;

public class JXMapViewerCustom extends JXMapViewer {

    private EncodedPolyline[] polylineData;
    private List<Integer> chosenRoutes;

    public EncodedPolyline[] getPolylineData() {
        return polylineData;
    }

    public List<Integer> getChosenRoutes() {
        return chosenRoutes;
    }

    public void setPolylineData(EncodedPolyline[] polylineData, List<Integer> chosenRoutes) {
        this.polylineData = polylineData;
        this.chosenRoutes = chosenRoutes;
        repaint();
    }

    private boolean first = true;

    @Override
    protected void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);

        if (polylineData != null && polylineData.length > 0) {
            Graphics2D graphics2D = (Graphics2D) graphics.create();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Path2D path2D = new Path2D.Double();

            graphics2D.setStroke(new BasicStroke(5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, TOP_ALIGNMENT));

            first = true;

            if (!chosenRoutes.isEmpty()) {
                for (int chosenRoute : chosenRoutes) {
                    draw(path2D, polylineData[chosenRoute]);

                    graphics2D.setColor(new Color(255, 0, 255));
                }
            }
            graphics2D.draw(path2D);
            graphics2D.dispose();
        }
    }

    private void draw(Path2D path2D, EncodedPolyline polyline) {
        List<LatLng> pointList =  polyline.decodePath();

        pointList.forEach(latLng -> {
            Point2D point = convertGeoPositionToPoint(new GeoPosition(latLng.lat, latLng.lng));

            if (first) {
                first = false;
                path2D.moveTo(point.getX(), point.getY());
            } else {
                path2D.lineTo(point.getX(), point.getY());
            }
        });
    }
}
