package corrida.services;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogService {

    private static final String LOG_FILE = "system_activity.log";

    public static void registrarLog(String mensagem) {

        try (FileWriter fw = new FileWriter(LOG_FILE, true); PrintWriter pw = new PrintWriter(fw)){
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            pw.println(timestamp + " - " + mensagem);
        } catch (IOException e){
            System.err.println("Falha CRÍTICA ao registrar log: " + e.getMessage());
        }
    }
}