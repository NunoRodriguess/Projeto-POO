package app;

/**
 * The InvalidId class is an exception that is thrown when an invalid ID
 * is encountered.
 * It indicates that the ID provided is not valid or does not exist.
 */
public class InvalidId extends Exception {

    /**
     * Constructs a new InvalidId object with no detail message.
     */
    public InvalidId() {
        super();
    }

}
