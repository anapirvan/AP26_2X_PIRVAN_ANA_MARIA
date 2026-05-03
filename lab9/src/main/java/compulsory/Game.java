package compulsory;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor


public class Game {
    private final Maze maze;
    private final Bunny bunny;
    private final List<Robot> robots;
    private volatile boolean gameOver = false;


    public void start() {
        display();
        Thread bunnyThread = new Thread(() -> {
            while (!gameOver) {
                synchronized (this) {
                    if (gameOver) return;
                    bunny.move();
                    if (bunny.hasFoundExit()) {
                        System.out.println("Bunny has found the exit!");
                        gameOver = true;
                    }
                    if(!gameOver) display();
                }
                sleep(200);
            }
        });

        List<Thread> robotThreads = robots.stream().map(r -> new Thread(() -> {
            while (!gameOver) {
                synchronized (this) {
                    if (gameOver) return;
                    r.move(robots);
                    if (r.getPosition().equals(bunny.getPosition())) {
                        System.out.println("Robot " + r.getName() + " has caught the bunny!");
                        gameOver = true;
                    }
                    if (!gameOver) display();
                }
                sleep(200);
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
        for (int r = 0; r < maze.getRows(); r++) {
            for (int c = 0; c < maze.getCols(); c++) {
                Cell cell = maze.getCell(r, c);
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
}
