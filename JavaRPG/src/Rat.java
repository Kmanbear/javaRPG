import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Rat extends Entity {
    
    Rat(int x, int y) {
        super(x, y);
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
