package app;

import java.time.LocalDate;
import java.util.Stack;

/**
 * Represents a Sneaker item that extends the Item class.
 * It has instance variables such as size, type, color, and releaseDate.
 * It also has an inner enum named SneakerType with two values: LACES and
 * NOLACES.
 * The class has constructors, getters, and setters for its instance
 * variables.
 */
public class Sneaker extends Item {
    private double size;
    private SneakerType type;
    private String color;
    private LocalDate releaseDate;

    /**
     * This enum represents the type of sneaker: LACES or NOLACES.
     */
    public enum SneakerType {
        LACES, NOLACES;
    }

    /**
     * Default constructor for the Sneaker class.
     */
    public Sneaker() {
        super();
        this.size = 0;
        this.type = null;
        this.color = "n/d";

    }

    /**
     * Constructor for the Sneaker class with all parameters.
     *
     * @param description     The description of the sneaker.
     * @param brand           The brand of the sneaker.
     * @param basePrice       The base price of the sneaker.
     * @param priceCorrection The price correction of the sneaker.
     * @param carrier         The carrier of the sneaker.
     * @param conditionScore  The condition score of the sneaker.
     * @param previousOwners  The number of previous owners of the sneaker.
     * @param premiumStat     Whether or not the sneaker is premium status.
     * @param size            The size of the sneaker.
     * @param type            The type of the sneaker (LACES or NOLACES).
     * @param color           The color of the sneaker.
     * @param releaseDate     The release date of the sneaker.
     */
    public Sneaker(String description, String brand, double basePrice,
            Carrier carrier, double conditionScore, Stack<Integer> previousOwners, double size,
            SneakerType type, String color, LocalDate releaseDate, int userId) {
        super(description, brand, basePrice, carrier, conditionScore, userId, previousOwners);
        this.size = size;
        this.type = type;
        this.color = color;
        this.releaseDate = releaseDate;
    }

    /**
     * Copy constructor for the Sneaker class.
     * 
     * @param oneSneaker The Sneaker object to be copied.
     */
    public Sneaker(Sneaker oneSneaker) {
        super(oneSneaker);
        this.size = oneSneaker.getSize();
        this.type = oneSneaker.getType();
        this.color = oneSneaker.getColor();
        this.releaseDate = oneSneaker.getReleaseDate();
    }

    /**
     * Returns the size of the sneaker.
     * 
     * @return The size of the sneaker.
     */
    public double getSize() {
        return this.size;
    }

    /**
     * Returns the type of the sneaker.
     * 
     * @return The type of the sneaker.
     */
    public SneakerType getType() {
        return this.type;
    }

    /**
     * Returns the color of the sneaker.
     * 
     * @return The color of the sneaker.
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Returns the release date of the sneaker.
     * 
     * @return The release date of the sneaker.
     */
    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    /**
     * Returns the price of the sneaker based on its base price,
     * previous owners, and condition score.
     * 
     * @return The price of the sneaker.
     */
    public double getPrice() {
        if (getConditionScore() == 1 && getSize() < 45) {
            return this.getBasePrice();
        }
        return (this.getBasePrice() * this.getConditionScore()
                - (this.getBasePrice() * this.getPriceCorrection() / (this.getPreviousOwners().size() + 5)));
    }

    /**
     * Sets the size of the sneaker.
     * 
     * @param size The size to set for the sneaker.
     */
    public void setSize(double size) {
        this.size = size;
    }

    /**
     * Sets the type of the sneaker.
     * 
     * @param type The type to set for the sneaker.
     */
    public void setType(SneakerType type) {
        this.type = type;
    }

    /**
     * Sets the color of the sneaker.
     * 
     * @param color The color to set for the sneaker.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Sets the release date of the sneaker.
     * 
     * @param releaseDate The release date to set for the sneaker.
     */
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Returns a string representation of the Sneaker.
     *
     * @return a string representation of the Sneaker
     */
    public String toString() {
        return "Sneaker{" +
                "ID=" + this.getID() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", brand='" + getBrand() + '\'' +
                ", basePrice=" + getBasePrice() +
                ", priceCorrection=" + getPriceCorrection() +
                ", carrier='" + getCarrier().getName() + '\'' +
                ", conditionScore=" + getConditionScore() +
                ", previousOwners=" + getPreviousOwners() +
                ", size=" + this.size +
                ", type=" + this.type +
                ", color='" + this.color + '\'' +
                ", releaseDate=" + this.releaseDate + '\'' +
                ", Price=" + getPrice() +
                '}';
    }

    /**
     * Returns a string representation of the Sneaker.
     *
     * @return a string representation of the Sneaker
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
        sb.append("|" + Util.formatCell("Type: " + getType(), boxWidth) + "|\n");
        sb.append("|" + Util.formatCell("Size: " + getSize(), boxWidth) + "|\n");
        sb.append("|" + Util.formatCell("Color: " + getColor(), boxWidth) + "|\n");
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
        Sneaker s = (Sneaker) o;
        return this.getDescription().equals(s.getDescription()) && this.getBrand().equals(s.getBrand())
                && this.getBasePrice() == s.getBasePrice()
                && this.getPriceCorrection() == s.getPriceCorrection() && this.getCarrier().equals(s.getCarrier())
                && this.getConditionScore() == s.getConditionScore()
                && this.getPreviousOwners() == s.getPreviousOwners()
                && this.size == s.getSize() && this.type.equals(s.getType()) && this.color.equals(s.getColor())
                && this.releaseDate == s.getReleaseDate();
    }

    /**
     * This method creates and returns a copy of the current object.
     * 
     * @return a copy of the current object
     */
    public Sneaker clone() {
        return new Sneaker(this);
    }

}