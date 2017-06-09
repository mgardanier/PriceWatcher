import FileIO.StatusLogger;
import Model.ItemList;
import Model.WatchedItem;
import UserIO.IUserInputManager;
import UserIO.UserInputManager;
import FileIO.Impl.ConfigurationManager;
import FileIO.Interfaces.IConfigurationManager;
import Web.WebsiteManager;
import Web.jsoupTest;

import javax.management.OperationsException;
import java.io.IOException;
import java.util.List;

/**
 * Created by michael.gardanier on 6/5/17.
 */

public class PriceWatcher {

    private static IConfigurationManager configManager = new ConfigurationManager();

    private static boolean initialize(){
        StatusLogger.getInstance().logInfo("...Begin configuration...\n");
        return configManager.loadConfigFiles();
    }

    private static void quit() throws OperationsException {
        new UserInputManager().displayToUser("\nSaving...");
        configManager.saveConfiguration();
        new UserInputManager().displayToUser("Done!\n");
        throw new OperationsException("User quit");
    }

    private static void getItemToAdd() throws OperationsException {
       IUserInputManager inputManager = new UserInputManager();
       inputManager.displayToUser("Please Enter the Item URL or Q to return to main menu\n% ");
       String url = inputManager.getUserInput();
       if(url.toLowerCase().equals("q"))
            toMain();
       inputManager.displayToUser("Please Enter the item name\n% ");
       String name = inputManager.getUserInput();
        WatchedItem inputItem = new WatchedItem(url, name);
        WebsiteManager siteManager = new WebsiteManager();
        WatchedItem actualItem = siteManager.getItemInitial(inputItem);
        if(actualItem == null){
            inputManager.displayToUser("\nItem could not be found.\n");
            StatusLogger.getInstance().logError("Unable to locate " + name + " at " + url);
            toMain();
        }
        ItemList.getInstance().addItem(actualItem);
        getUpdatedPrices();
    }

    public static void toMain() throws OperationsException {
        menuLoop();
    }

    public static void optionsMenu()  throws OperationsException{
        IUserInputManager inputManager = new UserInputManager();
        inputManager.displayToUser("Choose an option below: \n" +
                "1. Reset watchlist (YOU WILL LOSE YOUR DATA!)\n" +
                "2. Reset log\n" +
                "3. Hard reset (YOU WILL LOSE YOUR DATA!)\n" +
                "4. Return to main menu\n% ");
        String result = inputManager.getUserInput();
        int menuChoice = inputManager.validateMenuInput(result, 4);
        if(menuChoice == -1){
            StatusLogger.getInstance().logError("Invalid user input: " + result);
            inputManager.displayToUser("Invalid input. Returning to main menu\n");
        }
        else if(menuChoice == 1){
            ItemList.getInstance().clearList();
        }
        else if(menuChoice == 2){
            StatusLogger.getInstance().clearLogFile();
        }
        else if (menuChoice == 3){
            ItemList.getInstance().clearList();
            StatusLogger.getInstance().clearLogFile();
        }
        toMain();
    }

    public static void listItems() throws OperationsException {
        IUserInputManager userInputManager = new UserInputManager();
        String header = String.format("\n\t%-25s \t%-10s \t%-12s %-15s\n", "Item Name", "Recent Price", "Best Price", "Date of Best\n");
        userInputManager.displayToUser(header + ItemList.getInstance().toString() + "\n");
        toMain();
    }

    public static void removeItem() throws OperationsException {
        IUserInputManager userInputManager = new UserInputManager();
        userInputManager.displayToUser("Enter name of item to remove from list \n% ");
        String input = userInputManager.getUserInput();
        StatusLogger.getInstance().logUserEvent("User attempting to remove " + input + " from watchlist");
        boolean success = ItemList.getInstance().deleteWatchedItem(input);
        if(!success){
            userInputManager.displayToUser("Item not in list. Returning to menu.");
            StatusLogger.getInstance().logError("Invalid item name or item not in list");
        }
        else
            StatusLogger.getInstance().logInfo(input + " successfully removed from watchlist");
        toMain();
    }

    public static void getUpdatedPrices() throws OperationsException {
        StatusLogger.getInstance().logInfo("Getting updated prices");
        new UserInputManager().displayToUser("\n Fetching most recent prices...\n\n");
        WebsiteManager web = new WebsiteManager();
        web.updateItemPrices();
        new UserInputManager().displayToUser("Updated complete. Results below\n");
        listItems();
    }

    public static void menuLoop() throws OperationsException {
        IUserInputManager userInputManager = new UserInputManager();
        IUserInputManager.UserOperation op = userInputManager.getUserOperation();
        switch (op){
            case ADD:
                getItemToAdd();
                break;
            case LIST:
                listItems();
                break;
            case REMOVE:
                removeItem();
                break;
            case TO_MAIN:
                toMain();
                break;
            case OPTIONS:
                optionsMenu();
                break;
            case REFRESH:
                getUpdatedPrices();
                break;
            case QUIT: default:
                quit();
                break;
        }
    }

    public static void main(String[] args){

        if(initialize()){
            try {
                menuLoop();
            } catch (OperationsException e){
                StatusLogger.getInstance().logInfo("Exiting program...");
            }
        }
        else{
            UserInputManager inputManager = new UserInputManager();
            inputManager.displayToUser("***Error: Initialization failure. Review log files for more details.");
        }
        jsoupTest test = new jsoupTest();
        try {
            test.getPriceFromPage("https://www.walmart.com/ip/Panasonic-Compact-Inverter-0.8-cu-ft-Microwave-Stainless-Steel/20975936?pltfm=desktop&pt=fd&athznid=ItemCarouselType_WPA&pgid=6&cmp=-1&adgrp=-1&adUid=6c042646-7eff-4372-84a4-3825c143e07e&bkt=__bkt__&athcpid=20975936&relUUID=6972dbb3-f2d6-474b-bc2e-8f7e2b5193e7&findingMethod=wpa&wpa_qs=DTAnzgGN-jQX6xnyYSPM8-Cy1YcPDn-qGctftEsxiYWVT2Ty5Ohunu6C0gIWaEiZ&itemId=20975936&adpgm=hl&plmt=1145x345_B-C-OG_TI_1-20_HL-MID-HP-ERO-BACKFILL&tgtp=1&athena=true&athpgid=WPADesktopHP&isAthAd=true&relRank=441&adiuuid=118beeef-8385-40ea-b070-453f82ca74c5");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
