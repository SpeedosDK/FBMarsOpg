package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class HQClienthandler implements Runnable{
    private final Socket clientSocket;
    private String clientName;
    private final List<BufferedReader> clientList;


    public HQClienthandler(Socket clientSocket, List<BufferedReader> clientList){
        this.clientSocket = clientSocket;
        this.clientList = clientList;
    }

    @Override
    public void run(){
        try (Socket socket = clientSocket;
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"))){
        clientList.add(in);
        String clientName = in.readLine();
            while (true){
                String message = in.readLine();

                int value = Integer.parseInt(message);

                if (message != null){
                    System.out.println(message);
                }
                if (clientName.equals("CarbonClient") && value > 36 || value < -15 ){
                    System.out.println("test");
                }

            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
