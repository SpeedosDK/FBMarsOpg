package sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

// De forskellige Clients ligner hinanden
public class CarbonClient {
    public static void main(String[] args) {
        int port = 5555;
        String host = "localhost";

        try (Socket socket = new Socket(host, port); // Opretter forbindelse til port og host.
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) { // Skriver til server
            out.println("CarbonClient"); // Giver navnet på Client til server
            int min = 0;
            int max = 2500; // Interval den skal give tal i mellem.

            try {
                while (true) {
                    int carbon = (int) (Math.random() * (max - min)) + min; // Laver Math.random for at få tilfældigt tal
                    out.println("Carbondioxide: " + carbon + "ppm"); // Skriver til serveren
                    Thread.sleep(5000); // Venter 5 sek og gør det samme igen.
                }
            } catch (InterruptedException e) {
                System.out.println("Fejl " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Client fejl " + e.getMessage()); // Udskriver client fejl
        }
    }
}
