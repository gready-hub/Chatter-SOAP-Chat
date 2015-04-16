package chat.server;

import java.util.ArrayList;

/**
 * User class, represents data that a user should hold. All fields are public
 *
 * @author Gary
 */
public class User {
    /* name for the user, should be made unique */

    public String username;

    /* List of all messages that this user has accumilated */
    public final ArrayList<Message> messages = new ArrayList<>();

    /* boolean used to check if user has been made an admin */
    public boolean isAdmin;
}
