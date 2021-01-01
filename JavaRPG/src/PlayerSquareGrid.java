import java.awt.Rectangle;

public class PlayerSquareGrid extends Rectangle {
    private static final long serialVersionUID = 1L; //auto-generated
    private int xCoordinate; //row of square
    private int yCoordinate; //col of square
    
    public PlayerSquareGrid(int rowVal, int colVal, int lengthVal) {
        xCoordinate = rowVal;
        yCoordinate = colVal;
        this.x = xCoordinate * lengthVal;
        this.y = yCoordinate * lengthVal;
        this.height = lengthVal;
        this.width = lengthVal;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }
}
