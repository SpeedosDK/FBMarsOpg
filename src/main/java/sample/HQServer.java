package sample;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class HQServer {
    public static void main(String[] args) {
        //Opretter trådpøl, med diverse indstillinger
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        int port = 5555;

        try (ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Server started on port " + port);


            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                   pool.submit(new HQClienthandler(clientSocket)); //Tilføjer clienter til pølen
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
