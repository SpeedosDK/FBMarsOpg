package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TempClient {

    public static void main(String[] args) {
        int port = 5555;
        String host = "localhost";


        try(Socket socket = new Socket(host, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            int min = -100;
            int max = 100;


            try {
                while (true) {
                    int temp = (int) (Math.random() * (max - min)) + min;
                    out.println("Temperatur " + temp + "C");
                    Thread.sleep(5000);
                }
            } catch (InterruptedException e) {
                System.out.println("Fejl " + e.getMessage());
            }

        } catch (IOException e) {
            System.out.println("Client fejl" + e.getMessage());
        }
    }
}
