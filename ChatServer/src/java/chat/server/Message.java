package chat.server;

/**
 * Message class, this represents data that a message should contain. Sender,
 * receiver, message, image.
 *
 * @author Gary
 */
public class Message {
    /* username of the sender */

    public String sender;

    /* username of the receiver */
    public String receiver;

    /* message from the sender to the receiver */
    public String message;

    /* Image bundled with the message */
    public byte[] image;
}
