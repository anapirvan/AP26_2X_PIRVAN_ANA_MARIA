package homework;

import compulsory.Bunny;
import compulsory.Cell;
import compulsory.Maze;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class Robot {
    private final String name;
    private Cell position;
    private final Maze maze;
    private final SharedMemory sharedMemory;
    private final Set<Cell> visited = new HashSet<>();

    public Robot(String name, Cell position, Maze maze, SharedMemory sharedMemory) {
        this.name = name;
        this.position = position;
        this.maze = maze;
        this.sharedMemory = sharedMemory;
    }

    public void move(List<Robot> robots, Bunny bunny) {
        visited.add(position);

        if (calculateDistance(position, bunny.getPosition()) <= 2) {
            sharedMemory.reportBunny(bunny.getPosition());
        }

        if (sharedMemory.getBunnyPosition().isPresent()) {
            if (bfs(sharedMemory.getBunnyPosition().get(), robots) != null) {
                position = bfs(sharedMemory.getBunnyPosition().get(), robots);
            }
            visited.add(position);
        } else {
            List<Cell> available = maze.getFreeNeighbors(position)
                    .stream()
                    .filter(c -> robots.stream()
                            .noneMatch(other -> other != this && other.getPosition().equals(c)))
                    .collect(Collectors.toList());
            if (!available.isEmpty())
                position = available.get(new Random().nextInt(available.size()));
        }
    }

    private int calculateDistance(Cell position1, Cell position2) {
        return Math.abs(position1.getRow() - position2.getRow()) + Math.abs(position1.getCol() - position2.getCol());
    }


    private Cell bfs(Cell target, List<Robot> robots) {
        if (position.equals(target)) return position;

        Map<Cell, Cell> parent = new HashMap<>();
        Queue<Cell> queue = new LinkedList<>();
        queue.add(position);
        parent.put(position, null);

        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            if (current.equals(target)) {
                return reconstructFirstStep(parent, current);
            }
            for (Cell neighbor : maze.getFreeNeighbors(current)) {
                if (!parent.containsKey(neighbor)) {
                    parent.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
        return null;
    }

    private Cell reconstructFirstStep(Map<Cell, Cell> parent, Cell target) {
        Cell current = target;
        while (parent.get(current) != null && !parent.get(current).equals(position)) {
            current = parent.get(current);
        }
        return current;
    }
}
