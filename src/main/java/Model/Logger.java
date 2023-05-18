package Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class that logs events during the game and saves then all for later inspection.
 * @Author Mikael Nilsson
 */
public class Logger {

    /**
     * String of match events.
     */
    private ArrayList<String> matchHistory;
    /**
     * Name of the file for when it's saved to the hard-drive.
     */
    private String fileName;

    /**
     * Constructor creating an unique filename for traceability.
     * @param whitePlayer name of the white player.
     * @param blackPlayer name of the black player.
     */
    public Logger(String whitePlayer, String blackPlayer) {
        this.matchHistory = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd HHmm");
        Date date = new Date();
        this.fileName = "files/" +  whitePlayer + " " + blackPlayer + " " + formatter.format(date) +".txt.";
    }

    /**
     *
     * @param event Match event like movement of what piece, taking of a piece or game-ending event.
     */
    public void addEvent(String event) {
        matchHistory.add(event);
    }

    /**
     * Method for saving the match-history to the hard-drive.
     */
    public void writeHistoryToFile() {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(String event : matchHistory) {
                bufferedWriter.write(event+"\n");
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        }
        catch (IOException e) {}
    }
}