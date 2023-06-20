package app;

import java.util.Stack;

/**
 * Represents a Tshirt item that extends the Item class.
 * It has instance variables such as size and pattern.
 * It also has two inner enums named TshirtSize and TshirtPattern. TshirtSize
 * has four values: S,M,L and XL. TshirtPattern has three values: Smooth,
 * Stripes
 * and
 * PalmTrees
 * The class has constructors, getters, and setters for its instance
 * variables.
 */

public class Tshirt extends Item {
    private TshirtSize size;
    private TshirtPattern pattern;

    /**
     * This enum represents the size of the Tshirt: S,M,L or XL
     */
    public enum TshirtSize {
        S, M, L, XL;
    }

    /**
     * This enum represents the pattern of the Tshirt: Smooth,Stripes or Palm trees
     */
    public enum TshirtPattern {
        Smooth, Stripes, PalmTrees;
    }

    /**
     * Default constructor for the Tshirt class.
     */
    public Tshirt() {
        super();
        this.size = null;
        this.pattern = null;
    }

    /**
     * Constructor for the Tshirt class with all parameters.
     *
     * @param description     The description of the tshirt.
     * @param brand           The brand of the tshirt.
     * @param basePrice       The base price of the tshirt.
     * @param priceCorrection The price correction of the tshirt.
     * @param carrier         The carrier of the tshirt.
     * @param conditionScore  The condition score of the tshirt.
     * @param previousOwners  The number of previous owners of the tshirt.
     * @param premiumStat     Whether or not the tshirt is premium status.
     * @param size            The size of the tshirt (S, M, L or XL).
     * @param pattern         The pattern of the tshirt (Smooth, Stripes or
     *                        PalmTrees).
     */
    public Tshirt(String description, String brand, double basePrice,
            Carrier carrier, double conditionScore, Stack<Integer> previousOwners, TshirtSize size,
            TshirtPattern pattern, int userId) {
        super(description, brand, basePrice, carrier, conditionScore, userId, previousOwners);
        this.size = size;
        this.pattern = pattern;
    }

    /**
     * Copy constructor for the Tshirt class.
     * 
     * @param oneTshirt The Tshirt object to be copied.
     */
    public Tshirt(Tshirt oneTshirt) {
        super(oneTshirt);
        this.size = oneTshirt.getSize();
        this.pattern = oneTshirt.getPattern();
    }

    /**
     * Returns the size of the tshirt.
     * 
     * @return The size of the tshirt.
     */
    public TshirtSize getSize() {
        return this.size;
    }

    /**
     * Returns the pattern of the tshirt.
     * 
     * @return The pattern of the tshirt.
     */
    public TshirtPattern getPattern() {
        return this.pattern;
    }

    /**
     * Returns the price of the tshirt based on its base price and
     * previous owners.
     * 
     * @return The price of the tshirt.
     */
    public double getPrice() {
        if (this.pattern == TshirtPattern.Smooth)
            return this.getBasePrice();
        else {
            if (this.getConditionScore() != 1)
                return 0.5 * this.getBasePrice();
            else {
                return this.getBasePrice();
            }
        }
    }

    /**
     * Sets the size of the tshirt.
     * 
     * @param size The size to set for the tshirt.
     */
    public void setSize(TshirtSize size) {
        this.size = size;
    }

    /**
     * Sets the pattern of the tshirt.
     * 
     * @param pattern The pattern to set for the tshirt.
     */
    public void setPattern(TshirtPattern pattern) {
        this.pattern = pattern;
    }

    /**
     * Returns a string representation of the Tshirt.
     *
     * @return a string representation of the Tshirt
     */
    public String toString() {
        return "Tshirt{" +
                "ID=" + this.getID() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", brand='" + getBrand() + '\'' +
                ", basePrice=" + getBasePrice() +
                ", priceCorrection=" + getPriceCorrection() +
                ", carrier='" + getCarrier().getName() + '\'' +
                ", conditionScore=" + getConditionScore() +
                ", previousOwners=" + getPreviousOwners() +
                ", size=" + this.size +
                ", pattern=" + this.pattern + '\'' +
                ", Price=" + getPrice() +
                '}';
    }

    /**
     * Returns a string representation of the Tshirt.
     *
     * @return a string representation of the Tshirt
     */
    public String showItem() {

        StringBuilder sb = new StringBuilder();
        int boxWidth = 50;

        // Create the top border
        sb.append("+" + "-".repeat(boxWidth - 2) + "+\n");

        // Append the student information
        sb.append("|" + Util.formatCell("Id: " + getID(), boxWidth) + "|\n");
        sb.append("|" + Util.formatCell("Description: " + getDescription(), boxWidth) + "|\n");
        sb.append("|" + Util.formatCell("Brand: " + getBrand(), boxWidth) + "|\n");
        sb.append("|" + Util.formatCell("Price: " + getPrice(), boxWidth) + "|\n");
        sb.append("|" + Util.formatCell("Pattern: " + getPattern(), boxWidth) + "|\n");
        sb.append("|" + Util.formatCell("Size: " + getSize(), boxWidth) + "|\n");
        sb.append("|" + Util.formatCell("Pattern: " + getPattern(), boxWidth) + "|\n");
        sb.append("|" + Util.formatCell("Carrier: " + getCarrier().getName(), boxWidth) + "|\n");

        // Create the bottom border
        sb.append("+" + "-".repeat(boxWidth - 2) + "+\n");

        return sb.toString();
    }

    /**
     * This method checks if the object o is equal to the current object.
     * 
     * @param o the object to compare with the current object
     * @return true if the objects are equal and false otherwise
     */
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null || o.getClass() != this.getClass())
            return false;
        Tshirt s = (Tshirt) o;
        return this.getDescription().equals(s.getDescription()) && this.getBrand().equals(s.getBrand())
                && this.getBasePrice() == s.getBasePrice()
                && this.getPriceCorrection() == s.getPriceCorrection() && this.getCarrier().equals(s.getCarrier())
                && this.getConditionScore() == s.getConditionScore()
                && this.getPreviousOwners() == s.getPreviousOwners()
                && this.size == s.getSize() && this.pattern == s.getPattern();
    }

    /**
     * This method creates and returns a copy of the current object.
     * 
     * @return a copy of the current object
     */
    public Tshirt clone() {
        return new Tshirt(this);
    }
}
