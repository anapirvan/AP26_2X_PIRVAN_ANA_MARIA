package compulsory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Random;
@Getter

public class Bunny {
    private Cell position;
    private final Maze maze;
    private boolean foundExit = false;
    private final Random random = new Random();

    public Bunny(Maze maze, Cell position) {
        this.maze = maze;
        this.position = position;
    }

    public void move() {
        if (foundExit) return;
        List<Cell> neighbors = maze.getFreeNeighbors(position);
        if (neighbors.isEmpty()) return;
        position = neighbors.get(random.nextInt(neighbors.size()));
        if (position.isExit()) foundExit = true;
    }

    public boolean hasFoundExit() { return foundExit; }
}