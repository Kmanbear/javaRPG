import java.awt.geom.Point2D;
import java.util.List;

public abstract class Entity {
    
    protected int health;
    protected Point2D location;
    //private image
    
    //common methods
    //TODO: display pixel art image
    
    Entity(int x, int y) {
        this.location = new Point2D.Double(x, y);
    }
    
    //abstract methods
    public abstract List<Point2D> calculatePossibleMoves();
}
