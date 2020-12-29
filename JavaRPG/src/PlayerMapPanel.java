/**
 * CIS 120 HW09 - TicTacToe Demo
 * (c) University of Pennsylvania
 * Created by Bayley Tuch, Sabrina Green, and Nicolas Corona in Fall 2020.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class PlayerMapPanel extends JPanel {

    private MapModel hModel; // model for the game
    private JLabel status; // current status text
    private PlayerSquarePanel[][] squareBoard;

    // Game constants
    public static final int MAP_DIM = 10;//dimension of world map
    private static final int LENGTH = 30; //length of square side
    public static final int BOARD_WIDTH = LENGTH * MAP_DIM;
    public static final int BOARD_HEIGHT = LENGTH * MAP_DIM;

    /**
     * Initializes the game board.
     */
    public PlayerMapPanel(JLabel statusInit) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area.
        // When this component has the keyboard focus, key events are handled by its key listener.
        setFocusable(true);
        
        hModel = new MapModel(MAP_DIM); // initializes model for the game
        status = statusInit; // initializes the status JLabel
        
        //initalize squareBoard
        int dim = hModel.getPlayerMapDim();
        squareBoard = new PlayerSquarePanel[dim][dim];
        for (int i = 0; i < squareBoard.length; i++) {
            for (int j = 0; j < squareBoard.length; j++) {
                squareBoard[i][j] = new PlayerSquarePanel(i, j, LENGTH);
            }
        }

        /*
         * Listens for mouseclicks.  Updates the model, then updates the game board
         * based off of the updated model.
         */
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint();
                
                for (int i = 0; i < hModel.getPlayerMapDim(); i++) {
                    for (int j = 0; j < hModel.getPlayerMapDim(); j++) {
                        if (squareBoard[i][j].contains(p)) {
                            int xMovement = i - ((hModel.getPlayerMapDim() - 1) / 2);
                            int yMovement = j - ((hModel.getPlayerMapDim() - 1) / 2);                                 
                            hModel.playerMovement(xMovement, yMovement);
                            hModel.enemyMovement();
                        }
                    }
                }
                
                updateStatus(); // updates the status JLabel
                repaint(); // repaints the game board
            }
        });
    }

    /**
     * (Re-)sets the game to its initial state.
     */
    public void reset() {
        hModel.reset();
        status.setText("Player 1's Turn");
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }

    /**
     * Updates the JLabel to reflect the current state of the game.
     */
    private void updateStatus() {
        status.setText("hello!");
    }

    /**
     * Draws the game board.
     * 
     * There are many ways to draw a game board.  This approach
     * will not be sufficient for most games, because it is not 
     * modular.  All of the logic for drawing the game board is
     * in this method, and it does not take advantage of helper 
     * methods.  Consider breaking up your paintComponent logic
     * into multiple methods or classes, like Mushroom of Doom.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //draws lines to indicate colored sides of boards
        g.drawLine(1, 1, BOARD_WIDTH, 1);
        g.drawLine(1, BOARD_HEIGHT, BOARD_WIDTH, BOARD_HEIGHT);
        g.drawLine(1, 1, 1, BOARD_HEIGHT);
        g.drawLine(BOARD_WIDTH, 1, BOARD_WIDTH, BOARD_HEIGHT);
        //square grid\
        drawSquareGrid(g);
        
    }

    
    /*
     * Draws the initial borders of the square grid
     */
    private void drawSquareGrid(Graphics g) {
        for (int i = 0; i < hModel.getPlayerMapDim(); i++) {
            for (int j = 0; j < hModel.getPlayerMapDim(); j++) {
                g.setColor(Color.black);
                g.drawRect((int) squareBoard[i][j].getX(), (int) squareBoard[i][j].getY(), (int) squareBoard[i][j].getWidth(), (int) squareBoard[i][j].getHeight());
                Tile tile = hModel.getPlayerMapCell(i, j);
                switch(tile.getTerrain()) {
                    case GRASS:
                        g.setColor(Color.green);
                        g.fillRect(squareBoard[i][j].x, squareBoard[i][j].y, squareBoard[i][j].width, squareBoard[i][j].height);
                        break;                       
                    case WATER :
                        g.setColor(Color.green);
                        g.fillRect(squareBoard[i][j].x, squareBoard[i][j].y, squareBoard[i][j].width, squareBoard[i][j].height);
                        break;  
                    case EMPTY:
                        g.setColor(Color.black);
                        g.fillRect(squareBoard[i][j].x, squareBoard[i][j].y, squareBoard[i][j].width, squareBoard[i][j].height);
                        break;
                    default:
                        System.out.println("unhandled terrain draw case");
                }
            }
        }
    }
    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}
