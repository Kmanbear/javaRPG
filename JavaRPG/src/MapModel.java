import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is a model for TicTacToe.  
 * 
 * This game adheres to a Model-View-Controller design framework.
 * This framework is very effective for turn-based games.  We
 * STRONGLY recommend you review these lecture slides, starting at
 * slide 8, for more details on Model-View-Controller:  
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec37.pdf
 * 
 * This model is completely independent of the view and controller.
 * This is in keeping with the concept of modularity! We can play
 * the whole game from start to finish without ever drawing anything
 * on a screen or instantiating a Java Swing object.
 * 
 * Run this file to see the main method play a game of TicTacToe,
 * visualized with Strings printed to the console.
 */
public class MapModel {

    private int dimension;
    private int[][] worldMap;
    private boolean gameOver;
    private Player player;
    private List<Enemy> enemies = new ArrayList<>();
    private Map<Integer, Terrain> terrainDictionary = new HashMap<>();

    /**
     * Constructor sets up game state.
     */
    public MapModel(int dim) {
        dimension = dim;
        reset();
    }
    
    public MapModel(int[][] mapVals) { //constructor to set board for testing
        dimension = mapVals.length;
        reset();
        for (int i = 0; i < mapVals.length; i++) { //copies all values of input board
            worldMap[i] = mapVals[i].clone();
        }
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
        int newPlayerX = (int) player.getLocation().getX() + x;
        int newPlayerY = (int) player.getLocation().getY() + y;
        if (inWorldMap(newPlayerX, newPlayerY) && terrainDictionary.get(getWorldMapCell(newPlayerX, newPlayerY)).getAccesible()) { 
            player.setLocation(newPlayerX, newPlayerY);
            int[][] newPlayerMap = getPlayerMap(player.getLocation(), player.getPlayerMapRange());
            player.setPlayerMap(newPlayerMap);
            System.out.println(Arrays.deepToString(newPlayerMap));
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
    private int[][] getPlayerMap(Point2D location, int range) {
        int dim = getPlayerMapDim();
        int[][] newPlayerMap = new int[dim][dim];
        for (int i = 0; i < newPlayerMap.length; i++) {
            for (int j = 0; j < newPlayerMap.length; j++) {
                int worldMapI = i + (int) location.getX() - range;
                int worldMapJ = j + (int) location.getY() - range;
                if (inWorldMap(worldMapI, worldMapJ)) {
                    newPlayerMap[j][i] = worldMap[worldMapJ][worldMapI];
                }
            }
        }
        return newPlayerMap;
    }

    private boolean inWorldMap(int worldMapI, int worldMapJ) {
        return worldMapI >= 0 && worldMapI < dimension &&//checks if in world map, otherwise it is zero
                worldMapJ >= 0 && worldMapJ < dimension;
    }

    /**
     * reset (re-)sets the game state to start a new game.
     */
    public void reset() {
        worldMap = new int[][] {//hard coded map
            {2, 1, 1, 1, 1, 1, 1, 1, 1, 2},
            {1, 1, 1, 2, 2, 2, 1, 1, 1, 1},
            {1, 1, 2, 1, 1, 1, 2, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 2, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 2, 1, 1, 1},
            {1, 2, 1, 1, 1, 2, 1, 1, 1, 2},
            {1, 1, 1, 1, 1, 2, 1, 1, 1, 1},
            {1, 1, 2, 1, 1, 2, 1, 2, 1, 1},
            {1, 1, 2, 1, 2, 1, 1, 1, 1, 1},
            {2, 1, 1, 1, 2, 1, 1, 1, 1, 2},
        };
        player = new Player(0 ,1, 3);//starting location, and range
        enemies.add(new Rat(3, 3));
        initializeTerrainDictionary();
        gameOver = false;
        playerMovement(0, 0); //quick fix to initalize player TODO: replace quick fix
    }
    
    private void initializeTerrainDictionary() {
        terrainDictionary.put(0, new EmptyTerrain());
        terrainDictionary.put(2, new EmptyTerrain());
        terrainDictionary.put(1, new FilledTerrain());
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
    public int getWorldMapCell(int x, int y) {
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
    public int[][] getWorldMap() {
        int [][] boardCopy = new int[worldMap.length][];
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

    public int getPlayerMapCell(int i, int j) {
        int[][] playerMap = player.getPlayerMap();
        return playerMap[j][i];
    }

    public void enemyMovement() {
        for (Enemy enemy : enemies) {
            List<Point2D> enemyPossibleMoves = enemy.calculatePossibleMoves();
            for (Point2D move : enemyPossibleMoves) {
                //TODO: check if move works
                //if move works, then enter new location of enemy there
            }
        }
        
    }

}
