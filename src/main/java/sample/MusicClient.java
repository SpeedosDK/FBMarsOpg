package sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MusicClient {
    public static void main(String[] args) {
        int port = 5555;
        String host = "localhost";

        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            out.println("MusicClient");

            try {
                while (true) {
                    int random = (int) ((Math.random() * (10)) + 1);
                    if (random < 10){
                        out.println(random + " ZZTop");
                    }
                    else if (random == 10) {
                        out.println(random + " Brazilian Funk");
                    }
                    Thread.sleep(5000);
                }
            } catch (InterruptedException e) {
                System.out.println("Fejl " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Client fejl " + e.getMessage());
        }
    }
}
