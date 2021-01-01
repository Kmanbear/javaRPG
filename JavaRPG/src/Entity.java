import java.awt.Point;
import java.util.List;

public abstract class Entity {
    
    protected int health;
    protected Point location;
    //private image
    
    //common methods
    //TODO: display pixel art image
    
    Entity(int x, int y) {
        this.location = new Point(x, y);
    }
    
    
    
    //abstract methods
    public abstract List<Point> calculatePossibleMoves();



    public void setLocation(Point point) {
        location.setLocation(point);
    }
}
