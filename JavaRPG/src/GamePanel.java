import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
    
    protected MapModel mapModel; // model for the game
    protected BattleModel battleModel; // model for the game
    protected JLabel status; // current status text
    
    GamePanel(JLabel statusInit, MapModel mapModel1, BattleModel battleModel1) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area.
        // When this component has the keyboard focus, key events are handled by its key listener.
        setFocusable(true);
        
        mapModel = mapModel1;
        battleModel = battleModel1;
        status = statusInit; // initializes the status JLabel
    }
}
