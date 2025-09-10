package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.logging.*;

public class HQClienthandler implements Runnable{
    private final Socket clientSocket;
    private String clientName;
    private static final Logger logger = Logger.getLogger("HQClienthandler");
    //Opretter log - først en til alle beskeder og derefter en til advarsler
    static {
        try {
            FileHandler fh = new FileHandler("HQClienthandler.log", true); //Opretter tekstfilen
            fh.setFormatter(new SimpleFormatter()); //Sætter formatet på loggen, så man får dato, tid og besked
            logger.addHandler(fh); //Tilføjer fh til loggeren
        } catch (IOException e) {
            System.out.println("Log error");
        }

        try {
            //Opretter log til advarsler
            FileHandler warningHandler = new FileHandler("Warnings.log", true);
            warningHandler.setFormatter(new SimpleFormatter());
            warningHandler.setLevel(Level.WARNING); //Sætter niveauet til kun at tage advarsler
            logger.addHandler(warningHandler);
        } catch (IOException e) {
            System.out.println("WarningLog error");
        }
        logger.setUseParentHandlers(false); //Gør så man enten kan få udskrevet loggen i terminal også eller kun i tekstfilen
        logger.setLevel(Level.ALL);
    }

    public HQClienthandler(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    @Override
    public void run(){
        try (Socket socket = clientSocket;
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"))){
        String clientName = in.readLine(); // Client får et navn, så vi kan kende forskel på dem
            while (true){
                String message = in.readLine(); // Får beskeden fra client
                String number = message.replaceAll("[^0-9]", ""); // Vi skal få tallet ud af String med tekst også, så vi replacer en ny string med kun tallet

                int value = Integer.parseInt(number); // Laver det om til en int

                if (message != null){
                    logger.info(message); // Udskriver beskeden ud til logger
                }
                switch (clientName){ // Laver switch case med alle clienter og udskriver en warning hvis det uden for intervallet
                    case "CarbonClient" -> {
                        if (value > 2000){
                            logger.warning("CarbonAlarm: " + number + " ppm");
                        }
                    }
                    case "OxygenClient" -> {
                        if (value < 19 || value > 23) {
                            logger.warning("OxygenAlarm: " + number + "%");
                        }
                    }
                    case "PressureClient" -> {
                        if (value < 800 || value > 1100){
                            logger.warning("PressureAlarm: " + number + "hPa");
                        }
                    }
                    case "TemperatureClient" -> {
                        if (value < -15 || value > 35){
                            logger.warning("TemperatureAlarm: " + number + "C");
                        }
                    }
                    case "MusicClient" -> {
                        if (value == 10){
                            logger.warning("MusicAlarm: Brazilian Funk");
                        }
                    }
                }
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
