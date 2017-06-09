package UserIO;

/**
 * Created by michael.gardanier on 6/5/17.
 */
public interface IUserInputManager {


    enum UserOperation{
        ADD, LIST, REMOVE, QUIT, TO_MAIN, OPTIONS, REFRESH
    }
    public UserOperation getUserOperation();
    public void displayToUser(String output);
    public String getUserInput();

    /**
     * Validate input from user at menu screen
     * @param s the input string
     * @return the value, -1 if invalid
     */
    public int validateMenuInput(String s);

    /**
     * Validate the input from a user based on a range
     * @param s the input string
     * @param max the max input value (i.e 1-max)
     * @return the input value, -1 if invalid
     */
    public int validateMenuInput(String s, int max);

}
