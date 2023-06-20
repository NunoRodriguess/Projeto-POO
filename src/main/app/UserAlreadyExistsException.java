package app;

/**
 * Exception thrown when trying to add a user that already exists.
 */
public class UserAlreadyExistsException extends Exception {

    /**
     * Constructs a new UserAlreadyExistsException.
     */
    public UserAlreadyExistsException() {
        super();
    }
}
