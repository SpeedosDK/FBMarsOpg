package sample;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class HQServer {
    public static void main(String[] args) {
        int port = 5555;
        final Logger logger = Logger.getLogger("Server");
        final List<BufferedReader> clients = Collections.synchronizedList(new ArrayList<>());

        try (ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Server started on port " + port);


            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                   new Thread(new HQClienthandler(clientSocket, clients)).start();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
