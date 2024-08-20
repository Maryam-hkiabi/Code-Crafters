package cloudcode.maps.view;

import javax.swing.*;

class LabelInputPanel extends JPanel {
    LabelInputPanel(JLabel label, JComboBox searchField) {
        this.add(label);
        this.add(searchField);
    }
}


