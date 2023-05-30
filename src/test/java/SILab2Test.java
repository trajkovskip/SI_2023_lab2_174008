import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;

public class SILab2Test {

    @Test
    public void testFunctionWithMultiConditions() {
        List<User> allUsers = new ArrayList<>(Arrays.asList(
                new User("user1", "password1", "user1@example.com"),
                new User("user2", "password2", "user2@example.com")
        ));

        // Test Case 1: User object is null
        User nullUser = null;
        try {
            SILab2.function(nullUser, allUsers);
            fail("Expected RuntimeException to be thrown");
        } catch (RuntimeException e) {
            assertEquals("Mandatory information missing!", e.getMessage());
        }

        // Test Case 2: User password is null
        User nullPasswordUser = new User("user3", null, "user3@example.com");
        try {
            SILab2.function(nullPasswordUser, allUsers);
            fail("Expected RuntimeException to be thrown");
        } catch (RuntimeException e) {
            assertEquals("Mandatory information missing!", e.getMessage());
        }

        // Test Case 3: User email is null
        User nullEmailUser = new User("user4", "password4", null);
        try {
            SILab2.function(nullEmailUser, allUsers);
            fail("Expected RuntimeException to be thrown");
        } catch (RuntimeException e) {
            assertEquals("Mandatory information missing!", e.getMessage());
        }

        // Test Case 4: User object is not null, password is not null, and email is not null
        User validUser = new User("user5", "password5", "user5@example.com");
        boolean result = SILab2.function(validUser, allUsers);
        assertTrue(result);

        // Test Case 5: User with the same email already exists
        User existingEmailUser = new User("user6", "password6", "user1@example.com");
        result = SILab2.function(existingEmailUser, allUsers);
        assertFalse(result);

        // Test Case 6: User with the same username already exists
        User existingUsernameUser = new User("user1", "password7", "user7@example.com");
        result = SILab2.function(existingUsernameUser, allUsers);
        assertFalse(result);

        // Test Case 7: User password contains username
        User passwordContainsUsernameUser = new User("user8", "user8pass", "user8@example.com");
        result = SILab2.function(passwordContainsUsernameUser, allUsers);
        assertFalse(result);

        // Test Case 8: User password is less than 8 characters
        User shortPasswordUser = new User("user9", "pass", "user9@example.com");
        result = SILab2.function(shortPasswordUser, allUsers);
        assertFalse(result);

        // Test Case 9: User password contains a special character
        User specialCharPasswordUser = new User("user10", "password!@#", "user10@example.com");
        result = SILab2.function(specialCharPasswordUser, allUsers);
        assertTrue(result);

        // Test Case 10: User password contains a space
        User spacePasswordUser = new User("user11", "pass word", "user11@example.com");
        result = SILab2.function(spacePasswordUser, allUsers);
        assertFalse(result);
    }

    @Test
    public void testFunctionWithEveryBranch(User validUsernameUser) {
        List<User> allUsers = new ArrayList<>(Arrays.asList(
                new User("user1", "password1", "user1@example.com"),
                new User("user2", "password2", "user2@example.com")
        ));

        // Test Case 1: User object is null
        User nullUser = null;
        try {
            SILab2.function(nullUser, allUsers);
            fail("Expected RuntimeException to be thrown");
        } catch (RuntimeException e) {
            assertEquals("Mandatory information missing!", e.getMessage());
        }

        // Test Case 2: User object is not null
        User validUser = new User("user3", "password3", "user3@example.com");
        boolean result = SILab2.function(validUser, allUsers);
        assertTrue(result);

        // Test Case 3: User password is null
        User nullPasswordUser = new User("user4", null, "user4@example.com");
        try {
            SILab2.function(nullPasswordUser, allUsers);
            fail("Expected RuntimeException to be thrown");
        } catch (RuntimeException e) {
            assertEquals("Mandatory information missing!", e.getMessage());
        }

        // Test Case 4: User password is not null
        User validPasswordUser = new User("user5", "password5", "user5@example.com");
        result = SILab2.function(validPasswordUser, allUsers);
        assertTrue(result);

        // Test Case 5: User email is null
        User nullEmailUser = new User("user6", "password6", null);
        try {
            SILab2.function(nullEmailUser, allUsers);
            fail("Expected RuntimeException to be thrown");
        } catch (RuntimeException e) {
            assertEquals("Mandatory information missing!", e.getMessage());
        }

        // Test Case 6: User email is not null
        User validEmailUser = new User("user7", "password7", "user7@example.com");
        result = SILab2.function(validEmailUser, allUsers);
        assertTrue(result);

        // Test Case 7: User username is null
        User nullUsernameUser = new User(null, "password8", "user8@example.com");
        validUsernameUser.setEmail("user8@example.com");
        result = SILab2.function(validUsernameUser, allUsers);
        assertTrue(result);

        // Test Case 8: User username is not null
        validUsernameUser = new User("user9", "password9", "user9@example.com");
        result = SILab2.function(validUsernameUser, allUsers);
        assertTrue(result);

        // Test Case 9: User email is invalid (does not contain "@" or ".")
        User invalidEmailUser = new User("user10", "password10", "user10examplecom");
        result = SILab2.function(invalidEmailUser, allUsers);
        assertFalse(result);

        // Test Case 10: User email is valid (contains "@" and ".")
        validEmailUser = new User("user11", "password11", "user11@example.com");
        result = SILab2.function(validEmailUser, allUsers);
        assertTrue(result);
    }
}
