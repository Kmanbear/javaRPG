import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Tile {
    final Point2D location;
    private TerrainType terrain;
    List<Entity> entities = new ArrayList<>();
    public Tile(Point2D initLocation) {
        location = initLocation;
    }

    public void setTerrain(TerrainType type){
        this.terrain = type;
    }
    public TerrainType getTerrain() {
        return terrain;
    }
    
    
    
    public void addEntity(Entity e){
        entities.add(e);
    }
    public void removeEntity(Entity e){
        entities.remove(e);
    }

    public boolean getAccessible() {
        switch(terrain) {
        case GRASS:
           return true;
           
        case WATER :
           return false;
           
        default : 
           return false;
        }
    }
}
