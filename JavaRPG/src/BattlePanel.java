import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BattlePanel extends GamePanel {

//    protected MapModel mapModel; // model for the game
//    protected BattleModel battleModel; // model for the battle
//    protected JLabel status; // current status text
    
    private SquarePanel[][] squareBoard;
    
    public BattlePanel(JLabel statusInit, MapModel mapModel1, BattleModel battleModel1) {
        super(statusInit, mapModel1, battleModel1);
        
        //initalize squareBoard
        int dim = battleModel.getDim();
        squareBoard = new SquarePanel[dim][1];
        for (int i = 0; i < squareBoard.length; i++) {
            squareBoard[i][0] = new SquarePanel(i, 0, battleModel.LENGTH);
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //draws lines to indicate colored sides of boards
        g.drawLine(1, 1, battleModel.BOARD_WIDTH, 1);
        g.drawLine(1, battleModel.BOARD_HEIGHT, battleModel.BOARD_WIDTH, battleModel.BOARD_HEIGHT);
        g.drawLine(1, 1, 1, battleModel.BOARD_HEIGHT);
        g.drawLine(battleModel.BOARD_WIDTH, 1, battleModel.BOARD_WIDTH, battleModel.BOARD_HEIGHT);
        //square grid\
        drawSquareGrid(g);
        
    }

    
    /*
     * Draws the initial borders of the square grid
     */
    private void drawSquareGrid(Graphics g) {
        for (int i = 0; i < battleModel.getDim(); i++) {
            g.setColor(Color.black);
            g.drawRect(squareBoard[i][0].x,  squareBoard[i][0].y, (int) squareBoard[i][0].getWidth(), (int) squareBoard[i][0].getHeight());
        }
    }
    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(battleModel.BOARD_WIDTH, battleModel.BOARD_HEIGHT);
    }

}
