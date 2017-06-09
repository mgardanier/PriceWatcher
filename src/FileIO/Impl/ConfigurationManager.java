package FileIO.Impl;

import FileIO.Interfaces.IConfigurationManager;
import FileIO.Interfaces.IFileManager;
import FileIO.StatusLogger;
import Model.ItemList;
import Model.ItemListLoader;
import UserIO.IUserInputManager;
import UserIO.UserInputManager;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by michael.gardanier on 6/5/17.
 */
public class ConfigurationManager implements IConfigurationManager {

    private final String configFilePath = "Config.json";
    @Override
    public boolean loadConfigFiles() {
        File file = new File(configFilePath);
        IUserInputManager inputManager = new UserInputManager();
        if(!file.exists()) {
            StatusLogger.getInstance().logInfo("No config file...creating new one");
            try {
                file.createNewFile();
            } catch (IOException e) {
                StatusLogger.getInstance().logError("Unable to create config file!");
            }
        }
        try {
            inputManager.displayToUser("Loading data...\n");
            Scanner scanner = new Scanner(file);
            if(scanner.hasNext()) {
                String content = scanner.useDelimiter("\\Z").next();
                Gson gson = new Gson();
                ItemListLoader loader = gson.fromJson(content, ItemListLoader.class);
                loader.loadItemList();
            }
        } catch (FileNotFoundException e){
            StatusLogger.getInstance().logError("No config file available");
        }
        inputManager.displayToUser("Done!\n");
        return true;
    }

    @Override
    public boolean saveConfiguration() {
        if(!restoreConfigFiles())
            return false;
        StatusLogger.getInstance().logInfo("Saving config");
        Gson gson = new Gson();
        String output = gson.toJson(ItemList.getInstance());
        IFileManager fileManager = new FileManager();
        return fileManager.writeStringToFile(output, configFilePath);
    }

    @Override
    public boolean restoreConfigFiles() {
        File file = new File(configFilePath);
        return file.delete();
    }

}
