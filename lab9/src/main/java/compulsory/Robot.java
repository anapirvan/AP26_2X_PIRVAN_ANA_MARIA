package compulsory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor

public class Robot {
    private final String name;
    private Cell position;
    private final Maze maze;
    private final Random random = new Random();

    public Robot(String name, Cell position, Maze maze) {
        this.name = name;
        this.position = position;
        this.maze = maze;
    }

    public void move(List<Robot> allRobots) {
        List<Cell> available = maze.getFreeNeighbors(position)
                .stream()
                .filter(c -> allRobots.stream()
                        .noneMatch(other -> other != this && other.getPosition().equals(c)))
                .collect(Collectors.toList());

        if (available.isEmpty()) return;
        position = available.get(random.nextInt(available.size()));
    }

}
