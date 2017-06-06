package FileIO;

import FileIO.Impl.FileManager;
import FileIO.Interfaces.IFileManager;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by michael.gardanier on 6/6/17.
 */
public class StatusLogger {

    private static StatusLogger ourInstance = new StatusLogger();

    private String filePath = "log.txt";

    public static StatusLogger getInstance() {
        return ourInstance;
    }

    private StatusLogger(){

    }

    public void logError(String error){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("***Error***: " + error);
        this.writeToLogFile(stringBuilder.toString());
    }

    public void logInfo(String info){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("---Info---: " + info);
        this.writeToLogFile(stringBuilder.toString());
    }

    public void logUserEvent(String userEvent){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("User Event: " + userEvent);
        this.writeToLogFile(stringBuilder.toString());
    }

    private void writeToLogFile(String output){
        IFileManager fileManager = new FileManager();
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        if(!fileManager.writeStringToFile(timeStamp + output, filePath)){
            System.out.println("Error opening/writing to log file");
        }
    }

    public void setLogFilePath(String filepath){
        this.filePath = filepath;
    }
}
