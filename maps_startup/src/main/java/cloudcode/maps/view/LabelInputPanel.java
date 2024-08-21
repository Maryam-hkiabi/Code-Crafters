package cloudcode.maps.view;

import javax.swing.*;

/** Input panel for searching
 *
 */
class LabelInputPanel extends JPanel {
    LabelInputPanel(JLabel label, JComboBox searchField) {
        this.add(label);
        this.add(searchField);
    }
}


