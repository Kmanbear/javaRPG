import java.awt.Point;
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
    
    public Point getLocation() {
        Point locationCopy = new Point(location.x, location.y);
        return locationCopy;
    }

    public void setPlayerMap(Tile[][] newPlayerMap) {
        playerMap = newPlayerMap;     
    }

    public int getPlayerMapRange() {
        return playerMapRange;
    }
    
    public Tile[][] getPlayerMap() {
        Tile [][] boardCopy = new Tile[playerMap.length][];
        for (int i = 0; i < playerMap.length; i++) {
            boardCopy[i] = playerMap[i].clone();
        }
        return boardCopy;
    }

    @Override
    public List<Point> calculatePossibleMoves() {
        List<Point> possibleMoves = new ArrayList<Point>();
        //rats move only one space (no diagonal)
        possibleMoves.add(new Point(location.x, location.y));
        possibleMoves.add(new Point(location.x + 1, location.y));
        possibleMoves.add(new Point(location.x - 1, location.y));
        possibleMoves.add(new Point(location.x, location.y + 1));
        possibleMoves.add(new Point(location.x, location.y - 1));
        //remove impossible moves
        
        return possibleMoves;
    }

    
    
}
