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
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++) {
                char ch = layout[r].charAt(c);
                boolean isExit = (ch == 'E');
                boolean isWall = (ch == '#');
                Cell cell = new Cell(r, c, isWall, isExit);
                grid[r][c] = cell;
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
        for (int[] d : directions) {
            int nr = cell.getRow() + d[0], nc = cell.getCol() + d[1];
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && !grid[nr][nc].isWall())
                result.add(grid[nr][nc]);
        }
        return result;
    }
}
