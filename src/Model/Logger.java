package Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Logger {

    private ArrayList<String> matchHistory;
    private String fileName;

    public Logger(String whitePlayer, String blackPlayer) {
        this.matchHistory = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd HHmm");
        Date date = new Date();
        this.fileName = "files/" +  whitePlayer + " " + blackPlayer + " " + formatter.format(date) +".txt.";
    }

    public void addEvent(String event) {
        matchHistory.add(event);
    }

    public void writeHistoryToFile() {
        try {
            FileWriter fr = new FileWriter(fileName);
            BufferedWriter br = new BufferedWriter(fr);

            for(String event : matchHistory) {
                br.write(event+"\n");
            }
            br.flush();
            br.close();
        }
        catch (IOException e) {}
    }
}
