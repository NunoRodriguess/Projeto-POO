package app;

import java.time.LocalDate;
import java.time.Period;
import java.util.Stack;

/**
 * A class representing a premium sneaker that extends the Sneaker class and
 * implements the Premium interface.
 */
public class PremiumSneaker extends Sneaker implements Premium {

  /**
   * Constructs a new PremiumSneaker with default values.
   */
  public PremiumSneaker() {
    super();
  }

  /**
   * Constructs a new PremiumSneaker with the specified properties.
   *
   * @param description    the description of the sneaker
   * @param brand          the brand of the sneaker
   * @param basePrice      the base price of the sneaker
   * @param carrier        the carrier of the sneaker
   * @param conditionScore the condition score of the sneaker
   * @param previousOwners the stack of previous owners of the sneaker
   * @param size           the size of the sneaker
   * @param type           the type of the sneaker
   * @param color          the color of the sneaker
   * @param releaseDate    the release date of the sneaker
   * @param userId         the ID of the user associated with the sneaker
   */
  public PremiumSneaker(String description, String brand, double basePrice,
      Carrier carrier, double conditionScore, Stack<Integer> previousOwners, double size,
      SneakerType type, String color, LocalDate releaseDate, int userId) {
    super(description, brand, basePrice,
        carrier, conditionScore, previousOwners, size,
        type, color, releaseDate, userId);
  }

  /**
   * Constructs a new PremiumSneaker from an existing Sneaker object.
   *
   * @param oneSneaker the existing Sneaker object to be used
   */
  public PremiumSneaker(Sneaker oneSneaker) {
    super(oneSneaker);
  }

  /**
   * Creates a clone of the PremiumSneaker.
   *
   * @return a clone of the PremiumSneaker
   */
  @Override
  public PremiumSneaker clone() {
    return new PremiumSneaker(this);
  }

  /**
   * Calculates and returns the price of the premium sneaker.
   * The price is based on the base price and the number of years since the
   * release date.
   *
   * @return the price of the premium sneaker
   */
  @Override
  public double getPrice() {
    LocalDate now = SystemDate.getDate();
    int yearDiff = Period.between(this.getReleaseDate(), now).getYears();

    return this.getBasePrice() * (1 + (yearDiff * 0.025));
  }

  /**
   * Returns a string representation of the PremiumSneaker object.
   *
   * @return a string representation of the PremiumSneaker
   */
  @Override
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
        ", size=" + getSize() +
        ", type=" + getType() +
        ", color='" + getColor() + '\'' +
        ", releaseDate=" + this.getReleaseDate() + '\'' +
        ", Price=" + getPrice() +
        ", Premium Status" +
        '}';
  }

  /**
   * Returns a string representation of the PremiumSneaker object.
   *
   * @return a string representation of the PremiumSneaker
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
    sb.append("|" + Util.formatCell("Type: " + getType(), boxWidth) + "|\n");
    sb.append("|" + Util.formatCell("Size: " + getSize(), boxWidth) + "|\n");
    sb.append("|" + Util.formatCell("Color: " + getColor(), boxWidth) + "|\n");
    sb.append("|" + Util.formatCell("Carrier: " + getCarrier().getName(), boxWidth) + "|\n");
    sb.append("|" + Util.formatCell("This is a Premium Item", boxWidth) + "|\n");

    // Create the bottom border
    sb.append("+" + "-".repeat(boxWidth - 2) + "+\n");

    return sb.toString();
}
}