package app;

import java.time.LocalDate;
import java.time.Period;
import java.util.Stack;

/**
 * A class representing a premium bag that extends the Bag class and implements
 * the Premium interface.
 */
public class PremiumBag extends Bag implements Premium {

    /**
     * Constructs a new PremiumBag with default values.
     */
    PremiumBag() {
        super();
    }

    /**
     * Constructs a new PremiumBag with the specified properties.
     *
     * @param description    the description of the bag
     * @param brand          the brand of the bag
     * @param basePrice      the base price of the bag
     * @param carrier        the carrier of the bag
     * @param conditionScore the condition score of the bag
     * @param previousOwners the stack of previous owners of the bag
     * @param dimension      the dimension of the bag
     * @param material       the material of the bag
     * @param releaseDate    the release date of the bag
     * @param userId         the ID of the user
     */
    public PremiumBag(String description, String brand, double basePrice,
            Carrier carrier, double conditionScore, Stack<Integer> previousOwners, double dimension,
            String material, LocalDate releaseDate, int userId) {
        super(description, brand, basePrice,
                carrier, conditionScore, previousOwners, dimension,
                material, releaseDate, userId);
    }

    /**
     * Copy constructor for the Bag class.
     *
     * @param oneBag The Bag object to be copied.
     */
    public PremiumBag(Bag oneBag) {
        super(oneBag);
    }

    /**
     * Creates a clone of the PremiumBag.
     *
     * @return a clone of the PremiumBag
     */
    @Override
    public PremiumBag clone() {
        return new PremiumBag(this);
    }

    /**
     * Returns a string representation of the PremiumBag object.
     *
     * @return a string representation of the PremiumBag
     */
    @Override
    public String toString() {
        return "Bag{" +
                "ID=" + this.getID() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", brand='" + getBrand() + '\'' +
                ", basePrice=" + getBasePrice() +
                ", priceCorrection=" + getPriceCorrection() +
                ", carrier='" + getCarrier().getName() + '\'' +
                ", conditionScore=" + getConditionScore() +
                ", previousOwners=" + getPreviousOwners() +
                ", dimension=" + getDimension() +
                ", material=" + getMaterial() +
                ", releaseDate=" + getReleaseDate() + '\'' +
                ", Price=" + getPrice() + '\'' +
                ", Premium Status" +
                '}';
    }

    /**
     * Returns a string representation of the bag for display purposes.
     *
     * @return a string representation of the bag
     */
    @Override
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
        sb.append("|" + Util.formatCell("Material: " + getMaterial(), boxWidth) + "|\n");
        sb.append("|" + Util.formatCell("Dimension: " + getDimension(), boxWidth) + "|\n");
        sb.append("|" + Util.formatCell("Carrier: " + getCarrier().getName(), boxWidth) + "|\n");
        sb.append("|" + Util.formatCell("This is a Premium Item", boxWidth) + "|\n");

        // Create the bottom border
        sb.append("+" + "-".repeat(boxWidth - 2) + "+\n");

        return sb.toString();
    }

    /**
     * Returns the price of a PremiumBag
     *
     * @return the price of a PremiumBag
     */
    @Override
    public double getPrice() {
        LocalDate now = SystemDate.getDate();
        int yearDiff = Period.between(this.getReleaseDate(), now).getYears();

        return this.getBasePrice() * (1 + (yearDiff * 0.025));
    }
}