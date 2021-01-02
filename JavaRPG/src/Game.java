import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

/**
 * This class sets up the top-level frame and widgets for the GUI.
 */
public class Game implements Runnable {
    
    public static final int MAP_DIM = 10;//dimension of world map
    public static final int LENGTH = 30; //length of square side
    public static final int BOARD_WIDTH = LENGTH * MAP_DIM;
    public static final int BOARD_HEIGHT = LENGTH * MAP_DIM;
    
    public void run() {
        
        final JFrame frame = new JFrame("Java RPG");
        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Setting up...");
        status_panel.add(status);
      
        // Game board
        GameModel gameModel = new GameModel();
        final JPanel gameBoard = new JPanel(new CardLayout());
        final PlayerMapPanel mapBoard = new PlayerMapPanel(status, gameModel);
        final BattlePanel battleBoard = new BattlePanel(status, gameModel);
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