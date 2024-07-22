package cloudcode.maps.view;

import cloudcode.maps.interface_adapter.SearchPlaceController;
import cloudcode.maps.interface_adapter.SearchPlaceState;
import cloudcode.maps.interface_adapter.SearchPlaceViewModel;
import com.google.maps.errors.ApiException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class SearchPlaceView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "search";

    private final SearchPlaceViewModel searchPlaceViewModel;
    private final JTextField searchInputField = new JTextField(15);
    private final SearchPlaceController searchPlaceController;

    private final JButton search;

    public SearchPlaceView(SearchPlaceController controller, SearchPlaceViewModel searchPlaceViewModel) {

        this.searchPlaceController = controller;
        this.searchPlaceViewModel = searchPlaceViewModel;
        searchPlaceViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(searchPlaceViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel searchInput = new LabelTextPanel(
                new JLabel(searchPlaceViewModel.SEARCH_LABEL), searchInputField);

        JPanel buttons = new JPanel();
        search = new JButton(searchPlaceViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);

        search.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            try {
                                searchPlaceController.execute(searchInputField.getText());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            } catch (ApiException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
        );

        searchInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SearchPlaceState currentState = searchPlaceViewModel.getState();
                        currentState.setSearch(searchInputField.getText() + e.getKeyChar());
                        searchPlaceViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(searchInput);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SearchPlaceState state = (SearchPlaceState) evt.getNewValue();
        if (state.getSearchError() != null) {
            JOptionPane.showMessageDialog(this, state.getSearchError());
        }
    }
}
