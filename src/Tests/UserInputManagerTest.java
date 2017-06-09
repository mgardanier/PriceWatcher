package Tests;

import UserIO.IUserInputManager;
import UserIO.UserInputManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by michael.gardanier on 6/5/17.
 */
class UserInputManagerTest {

    IUserInputManager manager = new UserInputManager();

    @Test
    void validateMenuInput(){
        String testInput = "1";

        /** Valid Tests **/
        assertEquals(1, manager.validateMenuInput(testInput));
        testInput = "2";
        assertEquals(2, manager.validateMenuInput(testInput));
        testInput = "3";
        assertEquals(3, manager.validateMenuInput(testInput));

        /** Invalid Tests **/
        testInput = "One";
        assertEquals(-1, manager.validateMenuInput(testInput));
        testInput = "@#$*";
        assertEquals(-1, manager.validateMenuInput(testInput));
        testInput = "9999999999999999999";
        assertEquals(-1, manager.validateMenuInput(testInput));
        testInput = "1111111111111111111111111111111111111111111111111111111";
        assertEquals(-1, manager.validateMenuInput(testInput));
        testInput = "0";
        assertEquals(-1, manager.validateMenuInput(testInput));
        testInput = "7";
        assertEquals(-1, manager.validateMenuInput(testInput));

    }

}