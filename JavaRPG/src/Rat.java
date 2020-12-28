import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Rat extends Enemy {
    
    Rat(int x, int y) {
        super(x, y);
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
