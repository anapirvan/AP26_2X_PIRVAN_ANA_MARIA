package compulsory;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Maze {
    private final Cell[][] grid;
    private final int rows, cols;
    private Cell exit;

    private static final String[] DEFAULT = {
            "#######",
            "#     #",
            "# ### #",
            "#   # E",
            "#######"
    };

    public Maze(String[] layout) {
        rows = layout.length;
        cols = layout[0].length();
        grid = new Cell[rows][cols];
        for (int row = 0; row < rows; row++)
            for (int column = 0; column < cols; column++) {
                char ch = layout[row].charAt(column);
                boolean isExit = (ch == 'E');
                boolean isWall = (ch == '#');
                Cell cell = new Cell(row, column, isWall, isExit);
                grid[row][column] = cell;
                if (isExit) {
                    exit = cell;
                }
            }
    }

    public static Maze defaultMaze() {
        return new Maze(DEFAULT);
    }

    public Cell getCell(int r, int c) {
        return grid[r][c];
    }

    public List<Cell> getFreeNeighbors(Cell cell) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        List<Cell> result = new ArrayList<>();
        for (int[] direction : directions) {
            int rowNumber = cell.getRow() + direction[0], columnNumber = cell.getCol() + direction[1];
            if (rowNumber >= 0 && rowNumber < rows && columnNumber >= 0 && columnNumber < cols && !grid[rowNumber][columnNumber].isWall())
                result.add(grid[rowNumber][columnNumber]);
        }
        return result;
    }
}
