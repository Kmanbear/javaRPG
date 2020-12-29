import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.Arrays;

public class Player {
    private Point2D location;
    private Tile[][] playerMap;
    private int playerMapRange;
    
    Player(int x, int y, int range) {
        this.location = new Point2D.Double(x, y);
        playerMapRange = range;
        int dim = 2 * playerMapRange + 1;
        playerMap = new Tile[dim][dim];
    }
    
    public Point2D getLocation() {
        Double locationCopy = new Point2D.Double(location.getX(), location.getY());
        return locationCopy;
    }
    
    public void setLocation(int x, int y) {
        location.setLocation(x, y);
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

    
    
}
