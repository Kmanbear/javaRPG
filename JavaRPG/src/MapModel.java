import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class MapModel {

    private int dimension;
    private Tile[][] worldMap;
    private Player player;
    private List<Entity> enemies = new ArrayList<>();

    /**
     * Constructor sets up game state.
     */
    public MapModel() {
        dimension = Game.MAP_DIM;
        reset();
    }

    /**
     * playerMovement
     * 
     * player clicks on square within playerMap
     * x is x-coordinate clicked
     * y is y-coordinate clicked
     * 
     * @param c column to play in
     * @param r row to play in
     * @return whether the turn was successful
     */
    public boolean playerMovement(int x, int y) {
        Point newLocation = new Point(player.getLocation().x + x, player.getLocation().y + y);
        if (inWorldMap(newLocation) && getWorldMapCell(newLocation).getAccessible() && 
                player.calculatePossibleMoves().contains(newLocation)) { 
            player.setLocation(newLocation);
            Tile[][] newPlayerMap = getPlayerMap(player.getLocation(), player.getPlayerMapRange());
            player.setPlayerMap(newPlayerMap);
            return true;
        } else { //when player tries to travel somewhere illegal
            return false;
        }
    }

    /* returns section of map player can see at new location
     * 
     * @param x x-coordinate of player
     * @param y y-coordinate of player
     * @return section of map player can see at location x,y
     */
    private Tile[][] getPlayerMap(Point location, int range) {
        int dim = getPlayerMapDim();
        Tile[][] newPlayerMap = new Tile[dim][dim];
        for (int i = 0; i < newPlayerMap.length; i++) {
            for (int j = 0; j < newPlayerMap.length; j++) {
                newPlayerMap[j][i] = new Tile(new Point(j, i));
                newPlayerMap[j][i].setTerrain(TerrainType.EMPTY);//sets anything not in map to empty
                Point worldMapLocation = new Point(i + location.x - range, j + location.y - range);
                if (inWorldMap(worldMapLocation)) {
                    newPlayerMap[j][i] = worldMap[worldMapLocation.y][worldMapLocation.x];//TODO: figure out if this is shallow vs deep copy
                }
            }
        }
        return newPlayerMap;
    }

    private boolean inWorldMap(Point newLocation) {
        return  newLocation.x >= 0 && newLocation.x < dimension &&//checks if in world map, otherwise it is zero
                 newLocation.y >= 0 &&  newLocation.y < dimension;
    }

    /**
     * reset (re-)sets the game state to start a new game.
     */
    public void reset() {
        initializeWorldMap();
        player = new Player(0 ,1, 3);//starting location, and range
        playerMovement(0, 0); //quick fix to initialize player TODO: replace quick fix
    }
    
    private void initializeWorldMap() {
        // TODO Auto-generated method stub
        worldMap = new Tile[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                worldMap[i][j] = new Tile(new Point(i, j));
                worldMap[i][j].setTerrain(TerrainType.GRASS);
            }
        }
        enemies.clear();
        addEnemy(new Rat(3, 3));
    }

    private void addEnemy(Entity enemy) {
        enemies.add(enemy);
        worldMap[enemy.location.x][enemy.location.y].addEntity(enemy);
    }

    /**
     * getCell is a getter for the contents of the
     * cell specified by the method arguments.
     * 
     * @param c column to retrieve
     * @param r row to retrieve
     * @return an integer denoting the contents
     *         of the corresponding cell on the 
     *         game board.  //TODO: implement hierarchy, what numbers stand for what
     */
    public Tile getWorldMapCell(Point newLocation) {
        int x =  newLocation.x;
        int y =  newLocation.y;
        return worldMap[y][x];
    }
    
    /**
     * getBoard is a getter for the contents of the
     * board
     * 
     * @return copy of the game board as a 2D array
     *         where values of array are: 0 = empty,
     *         1 = Player 1, 2 = Player 2
     */
    public Tile[][] getWorldMap() {
        Tile[][] boardCopy = new Tile[worldMap.length][];
        for (int i = 0; i < worldMap.length; i++) {
            boardCopy[i] = worldMap[i].clone();
        }
        return boardCopy;
    }

    /**
     * converts player range (how far player can see)
     * to dimension of displayed player map
     * 
     * @return player map dimension
     */
    public int getPlayerMapDim() {
        return 2 * player.getPlayerMapRange() + 1;//range on both sides, plus the row/col the player stands in
    }

    public Tile getPlayerMapCell(int i, int j) {
        Tile[][] playerMap = player.getPlayerMap();
        return playerMap[j][i];
    }
    /*
     * returns list of enemies that fight player
     */
    public List<Entity> enemyMovement() {
        //TODO: sort enemies list so stronger enemy comes first
        List<Entity> enemiesToBattle = new ArrayList<Entity>();
        for (Entity enemy : enemies) {           
            List<Point> enemyPossibleMoves = enemy.calculatePossibleMoves();
            Point closestMove = new Point(enemy.location.x, enemy.location.y); //default: set to where enemy is already
            for (Point move : enemyPossibleMoves) {
                if (inWorldMap(move) && getWorldMapCell(move).getAccessible()) {
                    if (move.distance(player.location) < closestMove.distance(player.location)) {
                        closestMove.setLocation(move);
                    }
                }
            }
            worldMap[enemy.location.y][enemy.location.x].removeEntity(enemy);
            if (closestMove.distance(player.location) == 0) { //don't add enemy when on top of player
                enemiesToBattle.add(enemy);
            } else {
                worldMap[closestMove.y][closestMove.x].addEntity(enemy);
                enemy.setLocation(new Point(closestMove.x, closestMove.y));
            }   
        }
        enemies.removeAll(enemiesToBattle);//all enemies that fight player are no longer on map or in map model
        return enemiesToBattle;
    }

    public Phase update(int xMovement, int yMovement) {
        playerMovement(xMovement, yMovement);
        if (!enemyMovement().isEmpty()) {
            //TODO: gameModel.switchToPhase(Phase.BATTLE);
            return Phase.BATTLE;
        } else {
            return Phase.MAP;
        }
    }

}
