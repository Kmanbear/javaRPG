import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
    
    protected GameModel gameModel;
    protected JLabel status; // current status text
    
    GamePanel(JLabel statusInit, GameModel gameModel1) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area.
        // When this component has the keyboard focus, key events are handled by its key listener.
        setFocusable(true);
        status = statusInit; // initializes the status JLabel
        gameModel = gameModel1;
    }
}
