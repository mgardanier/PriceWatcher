package UserIO;


/**
 * Created by michael.gardanier on 6/5/17.
 */
public class UserInputManager implements IUserInputManager {

    @Override
    public UserOperation getUserOperation() {
        System.out.println("Please choose one of the following options:");
        System.out.println("1. Add a product to watch list");
        System.out.println("2. List your current product watches");
        System.out.println("3. Remove a product from watch list");
        System.out.println("4. Quit");

        int input = 0;

        switch(input){
            case 1:
                return UserOperation.ADD;
            case 2:
                return UserOperation.LIST;
            case 3:
                return UserOperation.REMOVE;
            default:
                return UserOperation.QUIT;
        }
    }

    /**
     * Method to validate a user's choice.
     * @param input the input recieved from the user
     * @return a value indicating the
     */
    public int validateMenuInput(String input){
        if(input.equals(null))
            return -1;
        int inputInt = -1;
        try{
            inputInt = Integer.parseInt(input);
        } catch (Exception e){
            return -1;
        }
        if(inputInt < 1 || inputInt > 4)
            return -1;
        return inputInt;
    }

    @Override
    public void displayToUser(String output) {

    }

    @Override
    public String getUserInput() {
        return null;
    }
}
