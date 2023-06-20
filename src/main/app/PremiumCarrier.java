package app;

/**
 * A class representing a premium carrier that extends the Carrier class and
 * implements the Premium interface.
 */
public class PremiumCarrier extends Carrier implements Premium {

    /**
     * Constructs a new PremiumCarrier with the specified properties.
     *
     * @param name      the name of the carrier
     * @param taxSmall  the tax value for small items
     * @param taxMedium the tax value for medium items
     * @param taxBig    the tax value for big items
     * @param profit    the profit of the carrier
     */
    public PremiumCarrier(String name, double taxSmall, double taxMedium, double taxBig, int profit) {
        super(name, taxSmall, taxMedium, taxBig, profit);
    }

    /**
     * Constructs a new PremiumCarrier from an existing PremiumCarrier object.
     *
     * @param oneCarrier the existing PremiumCarrier object to be used
     */
    public PremiumCarrier(PremiumCarrier oneCarrier) {
        super(oneCarrier);
    }

    /**
     * Constructs a new PremiumCarrier with default values.
     */
    public PremiumCarrier() {
        super();
    }

    /**
     * Creates a clone of the PremiumCarrier.
     *
     * @return a clone of the PremiumCarrier
     */
    @Override
    public PremiumCarrier clone() {
        return new PremiumCarrier(this);
    }

    /**
     * Returns a string representation of the PremiumCarrier object.
     *
     * @return a string representation of the PremiumCarrier
     */
    @Override
    public String toString() {
        return "Carrier{" +
                "name='" + getName() + '\'' +
                ", Small tax value='" + getTaxSmall() + '\'' +
                ", Medium tax value='" + getTaxMedium() + '\'' +
                ", Big tax value=" + getTaxBig() +
                ", Total Earning=" + getTotalEarning() +
                ", Premium Status }";
    }

    /**
     * Returns a string representation of the carrier for display purposes.
     *
     * @return a string representation of the carrier
     */
    @Override
    public String showCarrier() {
        StringBuilder sb = new StringBuilder();
        int boxWidth = 30;

        // Create the top border
        sb.append("+" + "-".repeat(boxWidth - 2) + "+\n");

        // Append the carrier information
        sb.append("|" + Util.formatCell("Name: " + getName(), boxWidth) + "|\n");
        sb.append("|" + Util.formatCell("Small tax value: " + getTaxSmall(), boxWidth) + "|\n");
        sb.append("|" + Util.formatCell("Medium tax value: " + getTaxMedium(), boxWidth) + "|\n");
        sb.append("|" + Util.formatCell("Big tax value: " + getTaxBig(), boxWidth) + "|\n");
        String Iva = String.format("%.2f", getIva());
        sb.append("|" + Util.formatCell("Iva: " + Iva, boxWidth) + "|\n");

        // Create the bottom border
        sb.append("+" + "-".repeat(boxWidth - 2) + "+\n");

        return sb.toString();
    }
}