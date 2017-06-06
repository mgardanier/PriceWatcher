package UserIO;

/**
 * Created by michael.gardanier on 6/5/17.
 */
public interface IUserInputManager {


    enum UserOperation{
        ADD, LIST, REMOVE, QUIT
    }
    public UserOperation getUserOperation();
    public void displayToUser(String output);
    public String getUserInput();
    public int validateMenuInput(String s);

}
