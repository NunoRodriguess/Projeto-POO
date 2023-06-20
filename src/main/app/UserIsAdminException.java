package app;

/**
 * Exception thrown when an operation is not allowed or diffrent for an admin
 * user.
 */
public class UserIsAdminException extends Exception {

    /**
     * Constructs a new UserIsAdminException with the specified user.
     *
     * @param user the user causing the exception
     */
    public UserIsAdminException(User user) {
        super();
    }

}
