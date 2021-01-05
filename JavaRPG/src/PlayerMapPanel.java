/**
 * CIS 120 HW09 - TicTacToe Demo
 * (c) University of Pennsylvania
 * Created by Bayley Tuch, Sabrina Green, and Nicolas Corona in Fall 2020.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class PlayerMapPanel extends GamePanel {
    
    private SquarePanel[][] squareBoard;
    private MapModel mapModel;
    /**
     * Initializes the game board.
     */
    public PlayerMapPanel(JLabel statusInit, GameModel gameModel1) {
        
        super(statusInit, gameModel1);
        mapModel = gameModel.getMapModel();
        
        //initalize squareBoard
        int dim = mapModel.getPlayerMapDim();
        squareBoard = new SquarePanel[dim][dim];
        for (int i = 0; i < squareBoard.length; i++) {
            for (int j = 0; j < squareBoard.length; j++) {
                squareBoard[i][j] = new SquarePanel(i, j, Game.LENGTH);
            }
        }

        /*
         * Controller
         * Listens for mouseclicks.  Updates the model, then updates the game board
         * based off of the updated model.
         */
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint();
                
                for (int i = 0; i < mapModel.getPlayerMapDim(); i++) {
                    for (int j = 0; j < mapModel.getPlayerMapDim(); j++) {
                        if (squareBoard[i][j].contains(p)) {
                            int xMovement = i - ((mapModel.getPlayerMapDim() - 1) / 2);
                            int yMovement = j - ((mapModel.getPlayerMapDim() - 1) / 2);
                            //TODO: gamePanel.switchToPhase(mapModel.update(xMovement, yMovement)));
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
        mapModel.reset();
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
        g.drawLine(1, 1, Game.BOARD_WIDTH, 1);
        g.drawLine(1, Game.BOARD_HEIGHT, Game.BOARD_WIDTH, Game.BOARD_HEIGHT);
        g.drawLine(1, 1, 1, Game.BOARD_HEIGHT);
        g.drawLine(Game.BOARD_WIDTH, 1, Game.BOARD_WIDTH, Game.BOARD_HEIGHT);
        //square grid\
        drawSquareGrid(g);
        
    }

    
    /*
     * Draws the initial borders of the square grid
     */
    private void drawSquareGrid(Graphics g) {
        for (int i = 0; i < mapModel.getPlayerMapDim(); i++) {
            for (int j = 0; j < mapModel.getPlayerMapDim(); j++) {
                g.setColor(Color.black);
                g.drawRect(squareBoard[i][j].x,  squareBoard[i][j].y, (int) squareBoard[i][j].getWidth(), (int) squareBoard[i][j].getHeight());
                Tile tile = mapModel.getPlayerMapCell(i, j);
                if (!tile.entities.isEmpty()) {
                    g.setColor(Color.red);
                    g.fillRect(squareBoard[i][j].x + 1, squareBoard[i][j].y + 1, squareBoard[i][j].width - 2, squareBoard[i][j].height - 2);
                } else {
                switch (tile.getTerrain()) {
                    case GRASS:
                        g.setColor(Color.green);
                        g.fillRect(squareBoard[i][j].x + 1, squareBoard[i][j].y + 1, squareBoard[i][j].width - 2, squareBoard[i][j].height - 2);
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
                if (i == 3 && j == 3) { //TODO: make this not dependent on range
                    g.setColor(Color.gray);
                    g.fillRect(squareBoard[i][j].x + 1, squareBoard[i][j].y + 1, squareBoard[i][j].width - 2, squareBoard[i][j].height - 2);
                }
            }
        }
    }
    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Game.BOARD_WIDTH, Game.BOARD_HEIGHT);
    }
}
