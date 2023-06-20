package app;

import java.util.Map;

/**
 * Query that returns the User who has profited the most.
 * It implements the Querier interface.
 */
public class BiggestEarnerAllTime implements Querier {

    private Map<Integer, User> hm;

    /**
     * Constructs a BiggestEarnerAllTime object.
     *
     * @param mapcopy a copy of the user map
     */
    public BiggestEarnerAllTime(Map<Integer, User> mapcopy) {
        hm = mapcopy;
    }

    /**
     * Executes the query to find the user who has profited the most.
     *
     * @return the user object who has profited the most
     * @throws NullPointerException if the user map is empty
     */
    @Override
    public User execute() throws NullPointerException {
        if (hm.isEmpty())
            throw new NullPointerException("No user is in the Model");

        User biggestEarner = null;

        for (int user_key : hm.keySet()) {
            User u = hm.get(user_key);
            if (biggestEarner == null || biggestEarner.soldItemsValue() < u.soldItemsValue()) {
                biggestEarner = u;
            }
        }
        return biggestEarner;
    }
}
