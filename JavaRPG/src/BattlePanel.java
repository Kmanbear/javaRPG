import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BattlePanel extends JPanel {

    public BattlePanel(JLabel statusInit) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area.
        // When this component has the keyboard focus, key events are handled by its key listener.
        setFocusable(true);
    }

}
