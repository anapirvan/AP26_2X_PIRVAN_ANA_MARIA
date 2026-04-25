package compulsory;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;

public class MazeApp extends Application {

    private Cell[][] cells;
    private Canvas canvas = null;
    private final double cellSize = 40;
    private final double distantaMax = 6;
    private Timeline timeline;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Maze Creator");

        BorderPane root = new BorderPane();

        //top
        HBox configPanel = new HBox(10);
        TextField rowsField = new TextField("10");
        TextField colsField = new TextField("10");
        Button drawButton = new Button("Draw Maze");
        drawButton.setOnAction(e -> {
            try {
                int rows = Integer.parseInt(rowsField.getText());
                int cols = Integer.parseInt(colsField.getText());
                initCells(rows, cols);
                canvas = new Canvas(cols * cellSize, rows * cellSize);
                canvas.setOnMouseClicked(event -> {
                    handleClick(event.getX(), event.getY());
                    drawCells(canvas.getGraphicsContext2D());
                });
                root.setCenter(canvas);
                drawCells(canvas.getGraphicsContext2D());
            } catch (NumberFormatException ex) {
                System.err.println("Invalid input");
            }
        });
        configPanel.getChildren().addAll(
                new Label("Rows:"), rowsField,
                new Label("Cols:"), colsField,
                drawButton
        );
        root.setTop(configPanel);

        //bottom
        HBox controlPanel = new HBox(10);

        Button createButton = new Button("Create");
        createButton.setOnAction(e -> {
            if (canvas != null) {
                createRandom();
                drawCells(canvas.getGraphicsContext2D());
            }
        });

        Button generateButton = new Button("Generate");
        Slider speedSlider = new Slider(1, 10, 5);

        generateButton.setOnAction(e -> {
            if (canvas != null) {
                if (timeline != null) timeline.stop();
                resetCells();
                drawCells(canvas.getGraphicsContext2D());
                generate(speedSlider);
            }

        });

        Button validateButton = new Button("Validate");
        validateButton.setOnAction(e -> {
            if (canvas != null) {
                boolean reachable = isReachable();
                drawCells(canvas.getGraphicsContext2D());
                if (reachable) {
                    System.out.println("Maze is traversable!");
                    highlightCell(canvas.getGraphicsContext2D(), 0, 0, Color.GREEN);
                    highlightCell(canvas.getGraphicsContext2D(), cells.length - 1, cells[0].length - 1, Color.GREEN);
                } else {
                    System.out.println("Maze is NOT traversable!");
                    highlightCell(canvas.getGraphicsContext2D(), 0, 0, Color.RED);
                    highlightCell(canvas.getGraphicsContext2D(), cells.length - 1, cells[0].length - 1, Color.RED);
                }
            }
        });

        Button resetButton = new Button("Reset");
        resetButton.setOnAction(e -> {
            if (canvas != null) {
                resetCells();
                drawCells(canvas.getGraphicsContext2D());
            }
        });

        Button exportButton = new Button("Export PNG");
        exportButton.setOnAction(e -> {
            if (canvas != null) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save Maze as PNG");
                fileChooser.setInitialFileName("maze.png");
                fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("PNG files", "*.png")
                );
                File file = fileChooser.showSaveDialog(primaryStage);
                if (file != null) {
                    WritableImage image = canvas.snapshot(null, null);
                    try {
                        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
                        System.out.println("Saved to: " + file.getAbsolutePath());
                    } catch (IOException ex) {
                        System.err.println("Error saving image: " + ex.getMessage());
                    }
                }
            }
        });

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            if (canvas != null) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save Maze");
                fileChooser.setInitialFileName("maze.ser");
                fileChooser.getExtensionFilters().add(
                        new FileChooser.ExtensionFilter("Maze files", "*.ser"));
                File file = fileChooser.showSaveDialog(primaryStage);
                if (file != null) {
                    try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
                        objectOutputStream.writeObject(cells);
                        System.out.println("Maze salvat la: " + file.getAbsolutePath());
                    } catch (IOException ex) {
                        System.err.println("Eroare la salvare: " + ex.getMessage());
                    }
                }
            }
        });

        Button loadButton = new Button("Load");
        loadButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Load Maze");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Maze files", "*.ser"));
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                    cells = (Cell[][]) objectInputStream.readObject();
                    int rows = cells.length;
                    int cols = cells[0].length;
                    canvas = new Canvas(cols * cellSize, rows * cellSize);
                    canvas.setOnMouseClicked(event -> {
                        handleClick(event.getX(), event.getY());
                        drawCells(canvas.getGraphicsContext2D());
                    });
                    root.setCenter(canvas);
                    drawCells(canvas.getGraphicsContext2D());
                    System.out.println("Maze incarcat din: " + file.getAbsolutePath());
                } catch (IOException | ClassNotFoundException ex) {
                    System.err.println("Eroare la incarcare: " + ex.getMessage());
                }
            }
        });

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> primaryStage.close());

        controlPanel.getChildren().addAll(createButton, generateButton, validateButton, resetButton, exportButton, saveButton, loadButton, exitButton);
        root.setBottom(controlPanel);

        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }

    private void handleClick(double mouseX, double mouseY) {

        //calculeaza celula in care s-a dat cick
        int c = (int) (mouseX / cellSize);
        int r = (int) (mouseY / cellSize);

        //verifica sa nu fie in afara
        if (r < 0 || r >= cells.length || c < 0 || c >= cells[0].length) return;

        //calculeaza coordonatele click ului in celula
        double localX = mouseX - c * cellSize;
        double localY = mouseY - r * cellSize;

        //detectam ce perete e cel mai aproape de click
        if (localY < distantaMax && r > 0) {
            //sus
            cells[r][c].setTopWall(!cells[r][c].isTopWall());
            cells[r - 1][c].setBottomWall(!cells[r - 1][c].isBottomWall());
        } else if (localY > cellSize - distantaMax && r < cells.length - 1) {
            //jos
            cells[r][c].setBottomWall(!cells[r][c].isBottomWall());
            cells[r + 1][c].setTopWall(!cells[r + 1][c].isTopWall());
        } else if (localX < distantaMax && c > 0) {
            //stanga
            cells[r][c].setLeftWall(!cells[r][c].isLeftWall());
            cells[r][c - 1].setRightWall(!cells[r][c - 1].isRightWall());
        } else if (localX > cellSize - distantaMax && c < cells[0].length - 1) {
            //dreapta
            cells[r][c].setRightWall(!cells[r][c].isRightWall());
            cells[r][c + 1].setLeftWall(!cells[r][c + 1].isLeftWall());
        }
    }

    private void initCells(int rows, int cols) {
        this.cells = new Cell[rows][cols];
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                cells[r][c] = new Cell(r, c);
    }

    private void resetCells() {
        for (int r = 0; r < cells.length; r++)
            for (int c = 0; c < cells[0].length; c++) {
                cells[r][c].setTopWall(true);
                cells[r][c].setBottomWall(true);
                cells[r][c].setLeftWall(true);
                cells[r][c].setRightWall(true);
            }
    }

    private void createRandom() {
        Random random = new Random();
        int rows = cells.length;
        int cols = cells[0].length;
        int repetitions = random.nextInt(rows * cols / 2);

        for (int i = 0; i < repetitions; i++) {
            int r = random.nextInt(rows);
            int c = random.nextInt(cols);
            int side = random.nextInt(4);

            switch (side) {
                case 0 -> {//sus
                    if (r > 0) {
                        cells[r][c].setTopWall(false);
                        cells[r - 1][c].setBottomWall(false);
                    }
                }
                case 1 -> {//jos
                    if (r < rows - 1) {
                        cells[r][c].setBottomWall(false);
                        cells[r + 1][c].setTopWall(false);
                    }
                }
                case 2 -> {//stanga
                    if (c > 0) {
                        cells[r][c].setLeftWall(false);
                        cells[r][c - 1].setRightWall(false);
                    }
                }
                case 3 -> {//dreapta
                    if (c < cols - 1) {
                        cells[r][c].setRightWall(false);
                        cells[r][c + 1].setLeftWall(false);
                    }
                }
            }
        }
    }

    private void drawCells(GraphicsContext gc) {
        int rows = cells.length;
        int cols = cells[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                double x = c * cellSize;
                double y = r * cellSize;

                gc.setFill(Color.LIGHTBLUE);
                gc.fillRect(x, y, cellSize, cellSize);

                gc.setStroke(Color.BLACK);
                gc.setLineWidth(2);
                if (cells[r][c].isTopWall())
                    gc.strokeLine(x, y, x + cellSize, y);
                if (cells[r][c].isBottomWall())
                    gc.strokeLine(x, y + cellSize, x + cellSize, y + cellSize);
                if (cells[r][c].isLeftWall())
                    gc.strokeLine(x, y, x, y + cellSize);
                if (cells[r][c].isRightWall())
                    gc.strokeLine(x + cellSize, y, x + cellSize, y + cellSize);
            }
        }
    }

    private boolean isReachable() {
        int rows = cells.length;
        int cols = cells[0].length;
        boolean[][] visited = new boolean[rows][cols];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];

            if (r == rows - 1 && c == cols - 1) return true;

            //sus
            if (r > 0 && !cells[r][c].isTopWall() && !visited[r - 1][c]) {
                visited[r - 1][c] = true;
                queue.add(new int[]{r - 1, c});
            }
            //jos
            if (r < rows - 1 && !cells[r][c].isBottomWall() && !visited[r + 1][c]) {
                visited[r + 1][c] = true;
                queue.add(new int[]{r + 1, c});
            }
            //stanga
            if (c > 0 && !cells[r][c].isLeftWall() && !visited[r][c - 1]) {
                visited[r][c - 1] = true;
                queue.add(new int[]{r, c - 1});
            }
            //dreapta
            if (c < cols - 1 && !cells[r][c].isRightWall() && !visited[r][c + 1]) {
                visited[r][c + 1] = true;
                queue.add(new int[]{r, c + 1});
            }
        }

        return false;
    }

    private void highlightCell(GraphicsContext gc, int r, int c, Color color) {
        double x = c * cellSize;
        double y = r * cellSize;
        gc.setFill(color);
        gc.fillRect(x + 2, y + 2, cellSize - 4, cellSize - 4);
    }


    private void removeWall(int r, int c, int nr, int nc) {
        if (nr == r - 1) {//sus
            cells[r][c].setTopWall(false);
            cells[nr][nc].setBottomWall(false);
        }
        if (nr == r + 1) {//jos
            cells[r][c].setBottomWall(false);
            cells[nr][nc].setTopWall(false);
        }
        if (nc == c - 1) {//stanga
            cells[r][c].setLeftWall(false);
            cells[nr][nc].setRightWall(false);
        }
        if (nc == c + 1) {//dreapta
            cells[r][c].setRightWall(false);
            cells[nr][nc].setLeftWall(false);
        }
    }


    private boolean isValid() {
        int rows = cells.length;
        int cols = cells[0].length;
        int totalCells = rows * cols;

        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1];

            if (r > 0 && !cells[r][c].isTopWall() && !visited[r - 1][c]) {
                visited[r - 1][c] = true;
                count++;
                queue.add(new int[]{r - 1, c});
            }
            if (r < rows - 1 && !cells[r][c].isBottomWall() && !visited[r + 1][c]) {
                visited[r + 1][c] = true;
                count++;
                queue.add(new int[]{r + 1, c});
            }
            if (c > 0 && !cells[r][c].isLeftWall() && !visited[r][c - 1]) {
                visited[r][c - 1] = true;
                count++;
                queue.add(new int[]{r, c - 1});
            }
            if (c < cols - 1 && !cells[r][c].isRightWall() && !visited[r][c + 1]) {
                visited[r][c + 1] = true;
                count++;
                queue.add(new int[]{r, c + 1});
            }
        }

        return count == totalCells;
    }

    private void generate (Slider speedSlider) {
        Stack<int[]> stack = new Stack<>();
        boolean[][] visited = new boolean[cells.length][cells[0].length];

        stack.push(new int[]{0, 0});
        visited[0][0] = true;

        timeline = new Timeline();//executa cod la intervale regulate de timp
        timeline.setCycleCount(Timeline.INDEFINITE);

        KeyFrame keyFrame = new KeyFrame(
                Duration.millis(200 / speedSlider.getValue()), //cat dureaza o iteratie a algoritmului
                e -> {
                    if (stack.isEmpty()) {
                        timeline.stop();
                        System.out.println("Valid: " + isValid());
                        return;
                    }

                    int[] current = stack.peek();
                    int r = current[0];
                    int c = current[1];

                    List<int[]> neighbors = getUnvisitedNeighbors(r, c, visited);

                    if (!neighbors.isEmpty()) {
                        //alegem un vecin nevizitat random
                        int[] next = neighbors.get(new Random().nextInt(neighbors.size()));
                        int nr = next[0];
                        int nc = next[1];

                        removeWall(r, c, nr, nc);
                        visited[nr][nc] = true;
                        stack.push(new int[]{nr, nc});
                    } else {
                        stack.pop();
                    }

                    drawCells(canvas.getGraphicsContext2D());
                    if (!stack.isEmpty()) {
                        int[] top = stack.peek();
                        highlightCell(canvas.getGraphicsContext2D(), top[0], top[1], Color.ORANGE);
                    }
                }
        );

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    private List<int[]> getUnvisitedNeighbors(int r, int c, boolean[][] visited) {
        List<int[]> neighbors = new ArrayList<>();
        if (r > 0 && !visited[r-1][c]) neighbors.add(new int[]{r-1, c});
        if (r < cells.length-1 && !visited[r+1][c]) neighbors.add(new int[]{r+1, c});
        if (c > 0 && !visited[r][c-1]) neighbors.add(new int[]{r, c-1});
        if (c < cells[0].length-1 && !visited[r][c+1]) neighbors.add(new int[]{r, c+1});
        return neighbors;
    }
}