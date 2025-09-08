package sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class CarbonClient {
    public static void main(String[] args) {
        int port = 5555;
        String host = "localhost";

        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            out.println("CarbonClient");
            int min = 0;
            int max = 2500;

            try {
                while (true) {
                    int carbon = (int) (Math.random() * (max - min)) + min;
                    out.println("Carbondioxide: " + carbon + "ppm");
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
