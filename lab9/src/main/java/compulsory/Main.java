package compulsory;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Maze maze = Maze.defaultMaze();

        Bunny bunny = new Bunny(maze,maze.getCell(1, 1));

        List<Robot> robots = List.of(
                new Robot("robot1", maze.getCell(1, 5), maze),
                new Robot("robot2", maze.getCell(3, 1), maze)
        );

        Game game = new Game(maze, bunny, robots);
        game.start();
    }
}
