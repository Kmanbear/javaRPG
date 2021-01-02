import java.util.List;

/*
 * new model created everytime there is a battle
 */

public class BattleModel extends Model{
    
    private List<Entity> enemies;
    private Player player;

    BattleModel(Player playerFighting, List<Entity> enemiesToFight) {
        player = playerFighting;
        enemies = enemiesToFight;
    }
    
    public int getDim() {
        // TODO Auto-generated method stub
        return 10;
    }
    
}
