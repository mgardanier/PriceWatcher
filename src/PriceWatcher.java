import UserIO.IUserInputManager;
import UserIO.UserInputManager;
import FileIO.Impl.ConfigurationManager;
import FileIO.Interfaces.IConfigurationManager;

/**
 * Created by michael.gardanier on 6/5/17.
 */

public class PriceWatcher {

    private static IConfigurationManager configManager = new ConfigurationManager();
    private static boolean initialize(){

        return configManager.loadConfigFiles();
    }

    private static void quit(){
        configManager.saveConfiguration();
    }

    public static void main(String[] args){

        if(initialize()){
            IUserInputManager.UserOperation op = IUserInputManager.UserOperation.LIST;
            while(! op.equals(IUserInputManager.UserOperation.QUIT)) {
                IUserInputManager userInputManager = new UserInputManager();
                op = userInputManager.getUserOperation();
                switch (op){
                    case ADD:
                    case LIST:
                    case REMOVE:
                    case QUIT: default:
                }
            }
        }
        else{
            UserInputManager inputManager = new UserInputManager();
            inputManager.displayToUser("***Error: Initialization failure. Review log files for more details.");
        }
    }

}
