package compulsory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class GameClient {
    public static void main(String[] args) throws IOException {
        String serverAddress = "127.0.0.1";
        int PORT = 8100;
        try (Socket socket = new Socket(serverAddress, PORT);
             Scanner scanner = new Scanner(System.in))
        {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                System.out.println("Enter command:");
                String request = scanner.nextLine();
                if (request.equals("exit")) return;
                out.println(request);

                try {
                    String response = in.readLine();
                    System.out.println(response);
                } catch (SocketException e) {
                    System.out.println("Server stopped.");
                }
            }
        }

    }

}
