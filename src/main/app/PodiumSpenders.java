package app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A class that calculates the podium spenders based on a given date range.
 * Implements the Querier interface for executing the query.
 */
public class PodiumSpenders implements Querier {

    private Map<Integer, User> hm;
    private LocalDate date1;
    private LocalDate date2;

    /**
     * Constructs a PodiumSpenders object with the given user map and date range.
     *
     * @param mapcopy The user map.
     * @param date1   The start date of the range.
     * @param date2   The end date of the range.
     */
    public PodiumSpenders(Map<Integer, User> mapcopy, LocalDate date1, LocalDate date2) {
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
     * Executes the query to calculate the podium spenders.
     *
     * @return The list of top spenders.
     * @throws NullPointerException if no user is in the model.
     */
    public Object execute() throws NullPointerException {
        if (hm.isEmpty()) {
            throw new NullPointerException("No user is in the Model");
        }

        List<User> topSpenders = new ArrayList<>(hm.values());

        for (int i = 0; i < topSpenders.size() - 1; i++) {
            for (int j = i + 1; j < topSpenders.size(); j++) {
                User user1 = topSpenders.get(i);
                User user2 = topSpenders.get(j);
                if (user2.boughtValueFrame(date1, date2) > user1.boughtValueFrame(date1, date2)) {
                    topSpenders.set(i, user2);
                    topSpenders.set(j, user1);
                }
            }
        }
        topSpenders.removeIf(user -> user.getEmail().equals("admin"));
        return topSpenders;
    }
}
