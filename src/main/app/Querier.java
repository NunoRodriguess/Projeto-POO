package app;

/**
 * An interface representing a Querier, which executes a query and returns a
 * result.
 */
public interface Querier {

    /**
     * Executes the query and returns the result.
     *
     * @return the result of the query
     * @throws NullPointerException if the query execution encounters a null value
     */
    public Object execute() throws NullPointerException;

}
