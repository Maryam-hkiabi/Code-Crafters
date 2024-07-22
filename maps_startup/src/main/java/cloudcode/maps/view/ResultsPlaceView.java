package cloudcode.maps.view;

import cloudcode.maps.interface_adapter.ResultsPlaceState;
import cloudcode.maps.interface_adapter.ResultsPlaceViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ResultsPlaceView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "results";
    private final ResultsPlaceViewModel resultsPlaceViewModel;

    public ResultsPlaceView(ResultsPlaceViewModel resultsPlaceViewModel) {
        this.resultsPlaceViewModel = resultsPlaceViewModel;
        this.resultsPlaceViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Search Results");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel resultsOutput = new JLabel(resultsPlaceViewModel.getState().getResults());

        // resultsOutput.addKeyListener(new KeyListener() {
        //    @Override
        //    public void keyTyped(KeyEvent e) {
        //        ResultsPlaceState currentState = resultsPlaceViewModel.getState();
        //        currentState.setResults(resultsOutput.getText());
        //        resultsPlaceViewModel.setState(currentState);
        //    }

        //    @Override
        //    public void keyPressed(KeyEvent e) {}

        //    @Override
        //    public void keyReleased(KeyEvent e) {}
        // });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(resultsOutput);
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ResultsPlaceState state = (ResultsPlaceState) evt.getNewValue();
    }
}
