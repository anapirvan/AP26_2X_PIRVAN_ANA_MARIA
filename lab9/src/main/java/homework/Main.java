package homework;
import compulsory.Bunny;
import compulsory.Maze;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Maze maze = Maze.defaultMaze();
        SharedMemory sharedMemory = new SharedMemory();

        Bunny bunny = new Bunny(maze, maze.getCell(1, 1));

        List<Robot> robots = List.of(
                new Robot("robot1", maze.getCell(1, 5), maze, sharedMemory),
                new Robot("robot2", maze.getCell(3, 1), maze, sharedMemory)
        );

        Game game = new Game(maze, bunny, robots);
        game.start();
    }
}
