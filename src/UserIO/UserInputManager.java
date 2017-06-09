package UserIO;


import FileIO.StatusLogger;

import java.util.Scanner;

/**
 * Created by michael.gardanier on 6/5/17.
 */
public class UserInputManager implements IUserInputManager {

    @Override
    public UserOperation getUserOperation() {
        while(true) {
            System.out.println("\n***Menu***\nPlease choose one of the following options:");
            System.out.println("1. Add a product to watch list");
            System.out.println("2. View your watch list");
            System.out.println("3. Remove a product from watch list");
            System.out.println("4. Get updated prices");
            System.out.println("5. Options");
            System.out.print("6. Save and quit\n% ");

            int input;
            Scanner scanner = new Scanner(System.in);
            input = validateMenuInput(scanner.next());
            if(input == -1){
                System.out.println("Invalid input, try again");
                StatusLogger.getInstance().logError("Invalid user input");
                continue;
            }
            StatusLogger.getInstance().logUserEvent("User selected menu choice " + input);
            switch (input) {
                case 1:
                    return UserOperation.ADD;
                case 2:
                    return UserOperation.LIST;
                case 3:
                    return UserOperation.REMOVE;
                case 4:
                    return UserOperation.REFRESH;
                case 5:
                    return UserOperation.OPTIONS;
                default:
                    return UserOperation.QUIT;
            }
        }
    }

    @Override
    public int validateMenuInput(String input){
        return this.validateMenuInput(input, 6);
    }

    @Override
    public int validateMenuInput(String s, int max) {
        if(s.equals(null))
            return -1;
        int inputInt = -1;
        try{
            inputInt = Integer.parseInt(s);
        } catch (Exception e){
            return -1;
        }
        if(inputInt < 1 || inputInt > max)
            return -1;
        return inputInt;
    }

    @Override
    public void displayToUser(String output) {
        System.out.print(output);
    }

    @Override
    public String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }
}
