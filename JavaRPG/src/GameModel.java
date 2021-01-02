import java.util.ArrayList;

public class GameModel {
    
    
    
    private MapModel mapModel;
    private boolean inBattle = false;
    private BattleModel currentBattleModel = new BattleModel(new Player(0, 0, 0), new ArrayList<Entity>());//TODO: placeholder, rethink battleModel
    
    public GameModel() {
        mapModel = new MapModel();
    }

    public MapModel getMapModel() {
        // TODO Auto-generated method stub
        return mapModel;
    }

    public BattleModel getCurrentBattleModel() {
        // TODO Auto-generated method stub
        return currentBattleModel;
    }
    
    
    
    
    
    
}
