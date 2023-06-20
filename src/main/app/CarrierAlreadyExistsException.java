package app;

/**
 * Represents a exception in case a User tries to
 * add to the system a carrier with the same name as
 * another carrie already in the system
 */
public class CarrierAlreadyExistsException extends Exception {

    public CarrierAlreadyExistsException() {
        super();
    }

}