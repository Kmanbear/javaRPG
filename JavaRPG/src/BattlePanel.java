import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BattlePanel extends GamePanel {

    protected BattleModel battleModel; // model for the battle
//    protected JLabel status; // current status text
    
    private SquarePanel[][] squareBoard;
    
    public BattlePanel(JLabel statusInit, GameModel gameModel1) {
        
        super(statusInit, gameModel1);
        battleModel = gameModel.getCurrentBattleModel();
        
        //initalize squareBoard
        int dim = battleModel.getDim();
        squareBoard = new SquarePanel[dim][1];
        for (int i = 0; i < squareBoard.length; i++) {
            squareBoard[i][0] = new SquarePanel(i, 0, Game.LENGTH);
        }
    }
    
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
        return new Dimension(Game.BOARD_WIDTH, Game.BOARD_HEIGHT);
    }

}
