package homework;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;

public class GameServer {
    public static final int PORT = 8101;

    private static final int MIN_PLAYERS = 2;

    //punem synchronized pe fiecare operatie din lista
    private final List<ClientThread> clients = Collections.synchronizedList(new ArrayList<>());
    private final List<Question> questions;
    private int currentQuestionIndex = 0;
    private int answersReceived = 0;
    private String currentCorrectLetter;
    private boolean gameStarted = false;

    private volatile boolean gameFinished = false;
    private final ExecutorService threadPool = Executors.newFixedThreadPool(10);

    private static final int TIME_PER_QUESTION = 10;
    private ScheduledFuture<?> currentTimer;
    private final ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();

    public GameServer() throws IOException {
        questions = Question.loadFromFile("src/main/resources/questions.txt");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (!gameFinished) {
                Socket socket = serverSocket.accept();
                ClientThread client = new ClientThread(socket, this);

                if(!gameStarted) clients.add(client);
                threadPool.execute(client);
            }
        }
        finally {
            threadPool.shutdown();
        }
    }


    public synchronized void playerJoined() {
        broadcast("WAITING|" + clients.size() + " players");

        //incepem jocul cand se aduna nr minim de clienti
        if (!gameStarted && clients.size() >= MIN_PLAYERS) {
            gameStarted = true;
            broadcast("GAME_STARTING");
            sendQuestion();
        }
    }

    private void sendQuestion() {
        if (currentQuestionIndex >= questions.size()) {
            endGame();
            return;
        }
        //resetam tot pt intrebarea noua
        answersReceived = 0;
        clients.forEach(c -> c.answered = false);

        Question question = questions.get(currentQuestionIndex);
        List<String> options = question.shuffleOptions();
        currentCorrectLetter = question.getCorrectLetter(options);

        //construieste intrebarea noua si o trimite la toti jucatorii
        broadcast(String.format("QUESTION|%d|%d|%s|%s|%s|%s|%s|%d",
                currentQuestionIndex + 1, questions.size(),
                question.getText(),
                options.get(0), options.get(1), options.get(2), options.get(3),
                TIME_PER_QUESTION));

        currentTimer = timer.schedule(() -> {
            synchronized (this) {
                broadcast("TIME_UP|Timpul a expirat!");
                broadcastScores();
                currentQuestionIndex++;
                sendQuestion();
            }
        }, TIME_PER_QUESTION, TimeUnit.SECONDS);
    }

    public synchronized void submitAnswer(ClientThread client, String answer) {
        if (!gameStarted || client.answered) return;

        client.answered = true;
        boolean correct = answer.equals(currentCorrectLetter);
        if (correct) {
            client.score += 10;
            client.send("CORRECT|" + client.score);
        } else {
            client.send("WRONG|" + currentCorrectLetter + "|" + client.score);
        }

        answersReceived++;
        if (answersReceived >= clients.size()) {
            currentTimer.cancel(false);
            broadcastScores();
            currentQuestionIndex++;
            sendQuestion();
        }
    }

    private void endGame() {
        timer.shutdown();
        broadcastScores();
        ClientThread winner = clients.stream()
                .max(Comparator.comparingInt(c -> c.score))
                .orElse(null);
        broadcast("GAME_OVER|" + (winner != null ? winner.name : "Nobody"));

        gameFinished=true;
    }

    private void broadcastScores() {
        StringBuilder stringBuilder = new StringBuilder("SCORES");
        clients.forEach(c -> stringBuilder.append("|").append(c.name).append(":").append(c.score));
        broadcast(stringBuilder.toString());
    }

    private void broadcast(String message) {
        clients.forEach(clientThread -> clientThread.send(message));
    }
    public static void main(String[] args) throws IOException { new GameServer(); }
}
