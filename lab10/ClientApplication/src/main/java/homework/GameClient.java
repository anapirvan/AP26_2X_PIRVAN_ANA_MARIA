package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GameClient {
    public static void main(String[] args) throws IOException {
        String serverAddress = "127.0.0.1";
        int PORT = 8101;

        try (Socket socket = new Socket(serverAddress, PORT);
             Scanner scanner = new Scanner(System.in))
        {

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //thread separat care asculta serverul
            new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        handleMessage(message);
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected");
                }
            }).start();


            System.out.print("Enter your name: ");
            out.println("JOIN|" + scanner.nextLine());

            while (scanner.hasNextLine()) {
                String input = scanner.nextLine().trim().toUpperCase();
                if (input.equals("EXIT")) break;
                if (input.matches("[A-D]")) out.println("ANSWER|" + input);
                else System.out.println("Type A, B, C or D.");
            }
        }
    }

    private static void handleMessage(String message) {
        String[] p = message.split("\\|");
        switch (p[0]) {
            case "TIME_UP"   -> System.out.println("\n" + p[1]);
            case "GAME_STARTING" -> System.out.println("\nGame starting");
            case "QUESTION"      -> {
                System.out.println("\nQuestion " + p[1] + "/" + p[2]);
                System.out.println(p[3]);
                System.out.println("A) " + p[4]);
                System.out.println("B) " + p[5]);
                System.out.println("C) " + p[6]);
                System.out.println("D) " + p[7]);
                System.out.println("Timp: " + p[8]);
                System.out.print("Answer: ");
            }
            case "CORRECT"   -> System.out.println("Correct! Score: " + p[1]);
            case "WRONG"     -> System.out.println("Wrong! Correct: " + p[1] + " | Score: " + p[2]);
            case "SCORES"    -> {
                for (int i = 1; i < p.length; i++) System.out.println(p[i]);
            }
            case "GAME_OVER" -> System.out.println("\nWinner: " + p[1]);
            case "ERROR"     -> System.out.println("Eroare: " + p[1]);
        }
    }
}
