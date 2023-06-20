package app;

import java.io.Serializable;

/**
 * Represents a Carrier class.
 * It has instance variables such as name, type, taxSmall, taxMedium, taxBig and
 * totalEarning.
 * The class has constructors, getters, and setters for its instance
 * variables.
 */
public class Carrier implements Serializable, Comparable<Carrier> {
    private String name;
    private double taxSmall;
    private double taxMedium;
    private double taxBig;
    private double totalEarning;

    private static double iva = 0.13;

    /**
     * Default constructor for the Carrier class.
     */
    public Carrier() {
        this.name = "n/d";
        this.taxSmall = 0;
        this.taxMedium = 0;
        this.taxBig = 0;
        this.totalEarning = 0;
    }

    /**
     * Constructor for the Carrier class with all parameters.
     *
     * @param name         The name of the carrier.
     * @param taxSmall     The small tax value of the carrier.
     * @param taxMedium    The medium tax value of the carrier.
     * @param taxBig       The big tax value of the carrier.
     * @param totalEarning The total earning value of the carrier.
     * 
     */
    public Carrier(String name, double taxSmall, double taxMedium, double taxBig, double totalEarning) {
        this.name = name;
        this.taxSmall = taxSmall;
        this.taxMedium = taxMedium;
        this.taxBig = taxBig;
        this.totalEarning = totalEarning;
    }

    /**
     * Copy constructor for the Carrier class.
     * 
     * @param oneCarrier The Carrier object to be copied.
     */
    public Carrier(Carrier oneCarrier) {
        this.name = oneCarrier.getName();
        this.taxSmall = oneCarrier.getTaxSmall();
        this.taxMedium = oneCarrier.getTaxMedium();
        this.taxBig = oneCarrier.getTaxBig();
        this.totalEarning = oneCarrier.getTotalEarning();
    }

    /**
     * Returns the name of the carrier.
     * 
     * @return The name of the carrier.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the small tax value of the carrier.
     * 
     * @return The small tax value of the carrier.
     */
    public double getTaxSmall() {
        return this.taxSmall;
    }

    /**
     * Returns the medium tax value of the carrier.
     * 
     * @return The medium tax value of the carrier.
     */
    public double getTaxMedium() {
        return this.taxMedium;
    }

    /**
     * Returns the big tax value of the carrier.
     * 
     * @return The big tax value of the carrier.
     */
    public double getTaxBigWithIva() {
        return this.taxBig + iva;
    }

    public double getIva() {
        return iva;
    }

    /**
     * Returns the small tax value of the carrier.
     * 
     * @return The small tax value of the carrier.
     */
    public double getTaxSmallWithIva() {
        return this.taxSmall + iva;
    }

    /**
     * Returns the medium tax value of the carrier.
     * 
     * @return The medium tax value of the carrier.
     */
    public double getTaxMediumWithIva() {
        return this.taxMedium + iva;
    }

    /**
     * Returns the big tax value of the carrier.
     * 
     * @return The big tax value of the carrier.
     */
    public double getTaxBig() {
        return this.taxBig;
    }

    /**
     * Returns the total earning value of the carrier.
     * 
     * @return The total earning value of the carrier.
     */
    public double getTotalEarning() {
        return this.totalEarning;
    }

    /**
     * Sets the name of the carrier.
     * 
     * @param name The name to set for the carrier.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the small tax value of the carrier.
     * 
     * @param taxSmall The small tax value to set for the carrier.
     */
    public void setTaxSmall(double taxSmall) {
        this.taxSmall = taxSmall;
    }

    /**
     * Sets the medium tax value of the carrier.
     * 
     * @param taxMedium The medium tax value to set for the carrier.
     */
    public void setTaxMedium(double taxMedium) {
        this.taxMedium = taxMedium;
    }

    /**
     * Sets the big tax value of the carrier.
     * 
     * @param taxBig The big tax value to set for the carrier.
     */
    public void setTaxBig(double taxBig) {
        this.taxBig = taxBig;
    }

    /**
     * Sets the total earning value of the carrier.
     * 
     * @param totalEarning The total earning to set for the carrier.
     */
    public void setTotalEarning(double totalEarning) {
        this.totalEarning = totalEarning;
    }

    /**
     * Compares carriers based on the total earning.
     * 
     * @return Result of the comparison
     */
    public int compareTo(Carrier anotherCarrier) {
        return Double.compare(this.totalEarning, anotherCarrier.getTotalEarning());
    }

    /**
     * Returns a string representation of the Carrier.
     *
     * @return a string representation of the Carrier
     */
    public String toString() {

        return "Carrier{" +
                "name='" + getName() + '\'' +
                ", Small tax value='" + getTaxSmall() + '\'' +
                ", Medium tax value='" + getTaxMedium() + '\'' +
                ", Big tax value=" + getTaxBig() +
                ", Total Earning=" + getTotalEarning() +
                '}';
    }

    /**
     * Returns a string representation of the Carrier.
     *
     * @return a string representation of the Carrier
     */
    public String showCarrier() {

        StringBuilder sb = new StringBuilder();
        int boxWidth = 50;

        // Create the top border
        sb.append("+" + "-".repeat(boxWidth - 2) + "+\n");

        // Append the student information
        sb.append("|" + Util.formatCell("Name: " + getName(), boxWidth) + "|\n");
        sb.append("|" + Util.formatCell("Small tax value: " + getTaxSmall(), boxWidth) + "|\n");
        sb.append("|" + Util.formatCell("Medium tax value: " + getTaxMedium(), boxWidth) + "|\n");
        sb.append("|" + Util.formatCell("Big tax value: " + getTaxBig(), boxWidth) + "|\n");
        String Iva = String.format("%.2f", Carrier.iva);
        sb.append("|" + Util.formatCell("Iva: " + Iva, boxWidth) + "|\n");
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
        Carrier s = (Carrier) o;
        return this.name.equals(s.getName()) && this.taxSmall == s.getTaxSmall() && this.taxMedium == s.getTaxMedium()
                && this.taxBig == s.getTaxBig() && this.totalEarning == s.getTotalEarning();
    }

    /**
     * This method creates and returns a copy of the current object.
     * 
     * @return a copy of the current object
     */
    public Carrier clone() {
        return new Carrier(this);
    }

    /**
     * This method add's to the earnigs of the Carrier
     * 
     * @param number of items and the price of the items
     */
    public void updateEarnings(int total_items, double final_price) {

        if (total_items == 1)
            this.totalEarning += final_price * this.taxSmall;
        if (total_items >= 2 && total_items <= 5)
            this.totalEarning += final_price * this.taxMedium;
        if (total_items > 5)
            this.totalEarning += final_price * this.taxBig;

    }

    /**
     * This method removes the earnigs of the Carrier
     * 
     * @param number of items and the price of the items
     */
    public void revertProfit(double final_price, int total_items) {

        if (total_items == 1)
            this.totalEarning -= final_price * this.taxSmall;
        if (total_items == 2) {
            this.totalEarning -= final_price * this.taxMedium;
            this.totalEarning = (this.totalEarning / this.taxMedium) * this.taxSmall;
        }
        if (total_items > 2 && total_items <= 5)
            this.totalEarning -= final_price * this.taxMedium;
        if (total_items == 6) {
            this.totalEarning -= final_price * this.taxBig;
            this.totalEarning = (this.totalEarning / this.taxBig) * this.taxMedium;
        }
        if (total_items > 6)
            this.totalEarning -= final_price * this.taxBig;

    }
}