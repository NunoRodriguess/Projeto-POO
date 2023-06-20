package app;

/**
 * The InvalidCommand class is an exception that is thrown when an
 * invalid command is encountered during execution.
 * It provides information about the command and the line number where the error
 * occurred.
 */
public class InvalidCommand extends Exception {

    /**
     * Constructs a new InvalidCommand object with the specified command and
     * line number.
     *
     * @param command    the invalid command
     * @param lineNumber the line number where the error occurred
     */
    public InvalidCommand(String command, int lineNumber) {
        super("Error executing " + command + " in line " + lineNumber);
    }

}
