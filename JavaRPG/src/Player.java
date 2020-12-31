import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player extends Entity {
    private Tile[][] playerMap;
    private int playerMapRange;
    
    Player(int x, int y, int range) {
        super(x, y);
        playerMapRange = range;
        int dim = 2 * playerMapRange + 1;
        playerMap = new Tile[dim][dim];
    }
    
    public Point2D getLocation() {
        Double locationCopy = new Point2D.Double(location.getX(), location.getY());
        return locationCopy;
    }
    
    public void setLocation(Point2D newLocation) {
        location.setLocation(newLocation);
    }

    public void setPlayerMap(Tile[][] newPlayerMap) {
        playerMap = newPlayerMap;     
    }

    public int getPlayerMapRange() {
        return playerMapRange;
    }
    
    public Tile[][] getPlayerMap() {
        System.out.println(Arrays.deepToString(playerMap));
        Tile [][] boardCopy = new Tile[playerMap.length][];
        for (int i = 0; i < playerMap.length; i++) {
            boardCopy[i] = playerMap[i].clone();
        }
        return boardCopy;
    }

    @Override
    public List<Point2D> calculatePossibleMoves() {
        List<Point2D> possibleMoves = new ArrayList<Point2D>();
        //rats move only one space (no diagonal)
        possibleMoves.add(new Point2D.Double(location.getX(), location.getY()));
        possibleMoves.add(new Point2D.Double(location.getX() + 1, location.getY()));
        possibleMoves.add(new Point2D.Double(location.getX() - 1, location.getY()));
        possibleMoves.add(new Point2D.Double(location.getX(), location.getY() + 1));
        possibleMoves.add(new Point2D.Double(location.getX(), location.getY() - 1));
        //remove impossible moves
        
        return possibleMoves;
    }

    
    
}
