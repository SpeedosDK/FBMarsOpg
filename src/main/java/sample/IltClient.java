package sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class IltClient {
    public static void main(String[] args) {
        int port = 5555;
        String host = "localhost";

        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            out.println("OxygenClient");

            int min = 0;
            int max = 100;

            try {
                while (true) {
                    int ilt = (int) (Math.random() * (max - min)) + min;
                    out.println("O2: " + ilt + "%");
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
