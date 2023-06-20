package app;

import java.util.Map;
import java.util.List;

/**
 * The EmmitedOrderList class implements the Querier interface
 * and provides functionality to execute
 * a query for retrieving the emitted orders of a specific user from a map of
 * users.
 */
public class EmmitedOrderList implements Querier {

    private Map<Integer, User> hm;
    private int id;

    /**
     * Constructs a new EmmitedOrderList object with the specified map of
     * users and user ID.
     *
     * @param mapcopy    the map of users
     * @param userIdcopy the ID of the user
     */
    public EmmitedOrderList(Map<Integer, User> mapcopy, int userIdcopy) {
        hm = mapcopy;
        id = userIdcopy;
    }

    /**
     * Executes the query to retrieve the emitted orders of the user with the
     * specified ID.
     *
     * @return a list of the emitted orders of the user
     * @throws NullPointerException if no user is present in the map or the user
     *                              with the specified ID is not found
     */
    @Override
    public List<Order> execute() throws NullPointerException {
        if (hm.isEmpty())
            throw new NullPointerException("No user is in the Model");

        User u = hm.get(id);
        if (u == null)
            throw new NullPointerException("User not in the system");

        return u.getEmmitedOrder();
    }
}
