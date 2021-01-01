import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class sets up the top-level frame and widgets for the GUI.
 */
public class Game implements Runnable {
    public void run() {
        
        final JFrame frame = new JFrame("Hex");
        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Setting up...");
        status_panel.add(status);
      
        // Game board
        MapModel mapModel = new MapModel();
        BattleModel battleModel = new BattleModel();
        final JPanel gameBoard = new JPanel(new CardLayout());
        final PlayerMapPanel mapBoard = new PlayerMapPanel(status, mapModel, battleModel);
        final BattlePanel battleBoard = new BattlePanel(status, mapModel, battleModel);
        gameBoard.add(mapBoard, "mapBoard");
        gameBoard.add(battleBoard, "battleBoard");
        frame.add(gameBoard, BorderLayout.CENTER);
      
        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);
      
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mapBoard.reset();
            }
        });
        control_panel.add(reset);
        
        final JButton switchPanel = new JButton("switchPanel");
        switchPanel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(gameBoard.getLayout());
                cl.previous(gameBoard);
            }
        });
        control_panel.add(switchPanel);
        
        // Put the frame on the screen
        
        frame.setLocation(300, 300);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start the game
        mapBoard.reset();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}