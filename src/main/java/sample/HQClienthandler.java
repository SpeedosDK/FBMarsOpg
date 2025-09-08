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


    public HQClienthandler(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    @Override
    public void run(){
        try (Socket socket = clientSocket;
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"))){
        String clientName = in.readLine();
            while (true){
                String message = in.readLine();
                String number = message.replaceAll("[^0-9]", "");

                int value = Integer.parseInt(number);

                if (message != null){
                    System.out.println(message);
                }
                switch (clientName){
                    case "CarbonClient" -> {
                        if (value > 2000){
                            System.out.println("CarbonAlarm: " + number);
                        }
                    }
                    case "OxygenClient" -> {
                        if (value < 19 || value > 23) {
                            System.out.println("OxygenAlarm: " + number);
                        }
                    }
                    case "PressureClient" -> {
                        if (value < 800 || value > 1100){
                            System.out.println("PressureAlarm: " + number);
                        }
                    }
                    case "TemperatureClient" -> {
                        if (value < -15 || value > 35){
                            System.out.println("TemperatureAlarm: " + number);
                        }
                    }
                }
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
