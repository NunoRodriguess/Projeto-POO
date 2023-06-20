package app;

import java.time.LocalDate;
import java.util.Map;

/**
 * Query that returns the User that has profited the most within a specified
 * time frame.
 * It implements the Querier interface.
 */
public class BiggestEarnerAllTimeFrame implements Querier {

    private Map<Integer, User> hm;
    private LocalDate date1;
    private LocalDate date2;

    /**
     * Constructs a BiggestEarnerAllTimeFrame object.
     *
     * @param mapcopy a copy of the user map
     * @param date1   the start date of the time frame
     * @param date2   the end date of the time frame
     */
    public BiggestEarnerAllTimeFrame(Map<Integer, User> mapcopy, LocalDate date1, LocalDate date2) {

        hm = mapcopy;

        if (date1.isBefore(date2)) {
            this.date1 = date1;
            this.date2 = date2;
        } else {
            this.date1 = date2;
            this.date2 = date1;
        }
    }

    /**
     * Executes the query to find the user that has profited the most within the
     * specified time frame.
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
            if (biggestEarner == null
                    || biggestEarner.soldItemsValueFrame(date1, date2) < u.soldItemsValueFrame(date1, date2)) {
                biggestEarner = u;
            }

        }
        if (biggestEarner.getEmail().equals("admin")) {
            throw new NullPointerException("No user sold items in the time frame");
        }
        return biggestEarner;
    }
}
