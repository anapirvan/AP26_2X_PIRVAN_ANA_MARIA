package compulsory;
import java.io.*;
import java.net.*;

public class ClientThread extends Thread {
    private final Socket socket;
    private final GameServer server;
    private PrintWriter out;

    // campuri player, direct aici
    String name = "Unknown";
    int score = 0;
    boolean answered = false;

    public ClientThread(Socket socket, GameServer server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try (socket) {
            out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                String[] parts = line.split("\\|");
                switch (parts[0]) {
                    case "JOIN"   -> { name = parts[1]; server.playerJoined(); }
                    case "ANSWER" -> server.submitAnswer(this, parts[1]);
                }
            }
        } catch (IOException e) {
            System.err.println("Communication error: " + e.getMessage());
        }
    }

    public void send(String message) { if (out != null) out.println(message); }
}
