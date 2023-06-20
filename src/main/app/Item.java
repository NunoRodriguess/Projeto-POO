package app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Stack;

/**
 * Represents an item with a description, brand, reference, base price,
 * price correction, carrier, condition score, previous owners,
 * premium status and ID.
 */
public abstract class Item implements Serializable {
    private String description;
    private String brand;
    private double basePrice;
    private Carrier carrier;
    private double conditionScore;
    Stack<Integer> previousOwners;
    private int id;
    private int userId;

    private static int currentID = 1;

    /**
     * Default constructor for Item class.
     * Initializes all fields to default values.
     */
    public Item() {
        this.description = "n/d";
        this.brand = "n/d";
        this.basePrice = 0;
        this.conditionScore = 0;
        this.previousOwners = new Stack<Integer>();
        this.id = currentID;
        currentID++;
        this.userId = 0; // admin id
    }

    /**
     * Constructor for Item class with parameters.
     *
     * @param description     the description of the item
     * @param brand           the brand of the item
     * @param basePrice       the base price of the item
     * @param conditionScore  the condition score of the item
     * @param carrier         the carrier of the item
     * @param userId          the id of the owner
     * @param previousOwners  the number of previous owners of the item
     */
    public Item(String description, String brand, double basePrice,
            Carrier carrier, double conditionScore, int userId, Stack<Integer> previousOwners) {
        this.description = description;
        this.brand = brand;
        this.basePrice = basePrice;
        this.carrier = carrier;
        this.conditionScore = conditionScore;
        this.previousOwners = previousOwners;
        this.id = currentID++;
        this.userId = userId;
    }

    /**
     * Copy constructor for Item class.
     *
     * @param oneItem an instance of Item to copy from
     */
    public Item(Item oneItem) {
        this.description = oneItem.getDescription();
        this.brand = oneItem.getBrand();
        this.basePrice = oneItem.getBasePrice();
        this.carrier = oneItem.getCarrier();
        this.conditionScore = oneItem.getConditionScore();
        this.previousOwners = oneItem.getPreviousOwners();
        this.id = oneItem.getID();
        this.userId = oneItem.getUserId();
    }

    /**
     * Returns the id of the user listing the item.
     *
     * @return the id of the user listing the item.
     */
    public int getUserId() {
        return this.userId;
    }

    /**
     * Returns the description of the item.
     *
     * @return the description of the item
     */
    public String getDescription() {
        return this.description;
    }

    public int getonePreviousOwners() {
        return this.previousOwners.peek();
    }

    /**
     * Returns the brand of the item.
     *
     * @return the brand of the item
     */
    public String getBrand() {
        return this.brand;
    }


    /**
     * Returns the base price of the item.
     *
     * @return the base price of the item
     */
    public double getBasePrice() {
        return this.basePrice;
    }

    /**
     * Returns the price correction of the item.
     *
     * @return the price correction of the item
     */
    public double getPriceCorrection() {
        return 1 - this.conditionScore;
    }

    /**
     * Returns the final price of the item.
     *
     * @return the final price of the item
     */
    public abstract double getPrice();

    /**
     * Returns the carrier of the item.
     *
     * @return the carrier of the item
     */
    public Carrier getCarrier() {
        return this.carrier;
    }

    /**
     * Returns the condition score of the item.
     *
     * @return the condition score of the item
     */
    public double getConditionScore() {
        return this.conditionScore;
    }

    /**
     * Returns the number of previous owners of the item.
     *
     * @return the number of previous owners of the item
     */
    public Stack<Integer> getPreviousOwners() {
        return this.previousOwners;
    }

    /**
     * Returns the ID of the item.
     *
     * @return the ID of the item
     */
    public int getID() {
        return this.id;
    }

    /**
     * Sets the description of the item.
     *
     * @param description the new description of the item
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the description of the item.
     *
     * @param description the new description of the item
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Sets the brand of the item.
     *
     * @param brand the new brand of the item
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }


    /**
     * Sets the base price of the item.
     *
     * @param basePrice the new base price of the item
     */
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * Sets the carrier of the item.
     *
     * @param carrier the new carrier of the item
     */
    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    /**
     * Sets the condition score of the item.
     *
     * @param conditionScore the new condition score of the item
     */
    public void setConditionScore(double conditionScore) {
        this.conditionScore = conditionScore;
    }

    /**
     * Sets the number of previous owners of the item.
     *
     * @param previousOwners the new number of previous owners of the item
     */
    public void setPreviousOwners(Stack<Integer> previousOwners) {
        this.previousOwners = previousOwners;
    }

    public void addPreviousOwner(int user_id2) {
        this.previousOwners.push(user_id2);
    }

    public void returnOwnership() {
        this.userId = this.previousOwners.pop();

    }

    /**
     * Returns a string representation of the item.
     *
     * @return a string representation of the item
     */
    public abstract String toString();

    /**
     * This method checks if the object o is equal to the current object.
     * 
     * @param o the object to compare with the current object
     * @return true if the objects are equal and false otherwise
     */
    public abstract boolean equals(Object o);

    /**
     * This method creates and returns a copy of the current object.
     * 
     * @return a copy of the current object
     */
    public abstract Item clone();

    /**
     * Returns a string representation of the item.
     *
     * @return a string representation of the item
     */
    public abstract String showItem();

    /**
     * Writes the static variable
     *
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); // default serialization
        out.writeInt(currentID); // save static variable
    }

    /**
     * Reads the static variable
     *
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // default deserialization
        currentID = in.readInt(); // load static variable
    }

}