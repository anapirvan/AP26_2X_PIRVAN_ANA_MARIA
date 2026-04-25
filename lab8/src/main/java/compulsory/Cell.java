package compulsory;

import java.io.Serializable;

public class Cell implements Serializable {
    private int row;
    private int col;
    private boolean topWall = true;
    private boolean bottomWall = true;
    private boolean leftWall = true;
    private boolean rightWall = true;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean isTopWall()    { return topWall; }
    public boolean isBottomWall() { return bottomWall; }
    public boolean isLeftWall()   { return leftWall; }
    public boolean isRightWall()  { return rightWall; }

    public void setTopWall(boolean v)    { topWall = v; }
    public void setBottomWall(boolean v) { bottomWall = v; }
    public void setLeftWall(boolean v)   { leftWall = v; }
    public void setRightWall(boolean v)  { rightWall = v; }
}