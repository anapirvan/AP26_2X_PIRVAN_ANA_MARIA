package homework;

import compulsory.Bunny;
import compulsory.Cell;
import compulsory.Maze;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Game {
    private final Maze maze;
    private final Bunny bunny;
    private final List<Robot> robots;
    private volatile boolean gameOver = false;

    private volatile int bunnySpeed = 1000;
    private final Map<String, Integer> robotSpeeds = new HashMap<>();
    private volatile boolean bunnyStopped = false;
    private final Map<String, Boolean> robotStopped = new HashMap<>();

    public Game(Maze maze, Bunny bunny, List<Robot> robots) {
        this.maze = maze;
        this.bunny = bunny;
        this.robots = robots;
        robots.forEach(r -> {
            robotSpeeds.put(r.getName(), 1000);
            robotStopped.put(r.getName(), false);
        });
    }

    public void start() {
        long startTime = System.currentTimeMillis();
        long timeLimit = 30000;

        Thread managerThread = new Thread(() -> {
            while (!gameOver) {
                sleep(3000);
                if (gameOver) return;
                long timeConsumed = System.currentTimeMillis() - startTime;
                System.out.println("Timp scurs: " + timeConsumed / 1000 + "s");
                synchronized (this) {
                    display();
                }
                if (timeConsumed >= timeLimit) {
                    System.out.println("Timpul a expirat");
                    gameOver = true;
                }
            }
        });

        managerThread.setDaemon(true);
        managerThread.start();

        display();

        Thread inputThread = new Thread(this::readCommands);
        inputThread.setDaemon(true);
        inputThread.start();

        Thread bunnyThread = new Thread(() -> {
            while (!gameOver) {
                synchronized (this) {
                    if (gameOver) return;
                    bunny.move();
                    if (bunny.hasFoundExit()) {
                        System.out.println("Bunny has found the exit!");
                        gameOver = true;
                    }
                    if (!gameOver) display();
                }
                if (!bunnyStopped) sleep(bunnySpeed);
            }
        });

        List<Thread> robotThreads = robots.stream().map(r -> new Thread(() -> {
            while (!gameOver) {
                synchronized (this) {
                    if (gameOver) return;
                    r.move(robots, bunny);
                    if (r.getPosition().equals(bunny.getPosition())) {
                        System.out.println("Robot " + r.getName() + " has caught the bunny!");
                        gameOver = true;
                    }
                }
                if (!robotStopped.get(r.getName())) sleep(robotSpeeds.get(r.getName()));
            }
        })).toList();

        bunnyThread.start();
        robotThreads.forEach(Thread::start);

        try {
            bunnyThread.join();
            for (Thread t : robotThreads) t.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("The game has been stopped!");
        }
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void display() {
        for (int row = 0; row < maze.getRows(); row++) {
            for (int column = 0; column < maze.getCols(); column++) {
                Cell cell = maze.getCell(row, column);
                if (cell.equals(bunny.getPosition()))
                    System.out.print("🐰");
                else if (robots.stream().anyMatch(rb -> rb.getPosition().equals(cell)))
                    System.out.print("🤖");
                else if (cell.isWall())
                    System.out.print("# ");
                else if (cell.isExit())
                    System.out.print("E ");
                else
                    System.out.print("  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void readCommands() {
        Scanner scanner = new Scanner(System.in);
        while (!gameOver) {
            String line = scanner.nextLine().trim().toLowerCase();
            String[] parts = line.split(" ");
            if (parts.length != 2) continue;

            String command = parts[0];
            String target = parts[1];

            switch (command) {
                case "speedup":
                    changeSpeed(target, -50);
                    break;
                case "slowdown":
                    changeSpeed(target, +50);
                    break;
                case "stop":
                    setStop(target, true);
                    break;
                case "resume":
                    setStop(target, false);
                    break;
                default:
                    System.out.println("Unknown command");
            }
        }
    }

    private void changeSpeed(String target, int difference) {
        if (target.equals("bunny") || target.equals("all")) {
            bunnySpeed = Math.max(50, bunnySpeed + difference);
        }
        for (Robot r : robots) {
            if (target.equals("all") || r.getName().equals(target)) {
                int current = robotSpeeds.get(r.getName());
                robotSpeeds.put(r.getName(), Math.max(50, current + difference));
            }
        }
    }

    private void setStop(String target, boolean stopped) {
        if (target.equals("bunny") || target.equals("all")) {
            bunnyStopped = stopped;
        }
        for (Robot r : robots) {
            if (target.equals("all") || r.getName().equals(target)) {
                robotStopped.put(r.getName(), stopped);
            }
        }
    }
}
