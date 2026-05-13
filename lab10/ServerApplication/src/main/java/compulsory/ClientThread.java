package compulsory;

import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

@AllArgsConstructor
class ClientThread extends Thread {
    private final Socket socket;
    private final ServerSocket serverSocket;

    public void run() {
        try (socket) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            String request;

            while ((request = in.readLine()) != null) {
                System.out.println("Received: '" + request + "'");
                if (request.equals("stop")) {
                    out.println("Server stopped");
                    out.flush();
                    serverSocket.close();
                    System.out.println("Closing server...");
                    break;
                } else {
                    out.println("Server received the request..");
                    out.flush();
                }
            }

        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        }
    }
}

