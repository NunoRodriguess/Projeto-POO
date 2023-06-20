package app;

import java.util.Map;

/**
 * Query that returns the Carrier that has profited the most.
 * It implements the Querier interface.
 */
public class BiggestCarrier implements Querier {

    private Map<String, Carrier> hm;

    /**
     * Constructs a BiggestCarrier object.
     *
     * @param mapcopy a copy of the carrier map
     */
    public BiggestCarrier(Map<String, Carrier> mapcopy) {
        hm = mapcopy;
    }

    /**
     * Executes the query to find the carrier that has profited the most.
     *
     * @return the carrier object that has profited the most
     * @throws NullPointerException if the carrier map is empty
     */
    @Override
    public Carrier execute() throws NullPointerException {

        if (hm.isEmpty())
            throw new NullPointerException("No Carrier is in the Model");

        Carrier biggestEarner = null;

        for (String key : hm.keySet()) {

            Carrier c = hm.get(key);
            if (biggestEarner == null || biggestEarner.getTotalEarning() < c.getTotalEarning()) {
                biggestEarner = c;
            }
        }
        return biggestEarner;
    }
}
