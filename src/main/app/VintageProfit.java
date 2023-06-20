package app;

/**
 * Represents a VintageProfit object that implements the Querier interface.
 * It calculates and returns the vintage profit.
 */
public class VintageProfit implements Querier {

    private double vp;

    /**
     * Constructs a VintageProfit object with the specified value.
     *
     * @param m the value representing the vintage profit
     */
    public VintageProfit(Double m) {
        vp = m;
    }

    /**
     * Executes the calculation of the vintage profit.
     *
     * @return the vintage profit value
     * @throws NullPointerException if the vintage profit value is null
     */
    @Override
    public Double execute() throws NullPointerException {
        return vp;
    }
}
