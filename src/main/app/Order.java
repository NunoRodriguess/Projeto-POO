package app;

import java.time.LocalDate;
import java.util.List;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Represents an order containing a collection of items, associated users, and
 * order details.
 */
public class Order implements Serializable, Comparable<Order> {
    private List<Item> collection;
    private HashMap<String, Integer> carrierHelper;
    private List<User> sellers;
    private User buyer;
    private TypeOfSize dimension;
    private double itemPrice;
    private double satisfactionPrice;
    private OrderState state;
    private LocalDate date;
    private int id;
    private double endPrice;

    private static int currentID = 1;

    /**
     * Enumeration representing the size of an order.
     */
    public enum TypeOfSize {
        Little, Medium, Big;
    }

    /**
     * Enumeration representing the state of an order.
     */
    public enum OrderState {
        Pending, Finished, Dispatched;
    }

    /**
     * Constructs an empty Order object with default values for its properties.
     */
    public Order() {
        this.collection = new LinkedList<Item>();
        this.carrierHelper = new HashMap<String, Integer>();
        this.dimension = TypeOfSize.Little;
        this.itemPrice = 0;
        this.satisfactionPrice = 0;
        this.state = OrderState.Pending;
        this.date = LocalDate.now();
        this.id = currentID++;
        this.endPrice = 0;
        this.buyer = null;
        this.sellers = new LinkedList<User>();

    }

    /**
     * Constructs an Order object with the specified collection, carrier helper,
     * dimension, satisfaction price,
     * item price, order state, date, end price, buyer, and sellers.
     *
     * @param collection        the collection of items in the order
     * @param carrierHelper     the helper map for carrier information
     * @param dimension         the size dimension of the order
     * @param satisfactionPrice the satisfaction price of the order
     * @param itemPrice         the item price of the order
     * @param state             the state of the order
     * @param date              the date of the order
     * @param endPrice          the end price of the order
     * @param buyer             the buyer associated with the order
     * @param sellers           the sellers associated with the order
     */

    public Order(List<Item> collection, HashMap<String, Integer> carrierHelper, TypeOfSize dimension,
            double satisfactionPrice, double itemPrice, OrderState state, LocalDate date, double endPrice, User buyer,
            List<User> sellers) {
        this.collection = collection;
        this.dimension = dimension;
        this.carrierHelper = carrierHelper;
        this.itemPrice = itemPrice;
        this.satisfactionPrice = satisfactionPrice;
        this.state = state;
        this.date = date;
        this.id = currentID++;
        this.endPrice = endPrice;
        this.buyer = buyer;
        this.sellers = sellers;
    }

    /**
     * Constructs an Order object as a copy of the provided order.
     *
     * @param oneOrder the order to be copied
     */
    public Order(Order oneOrder) {
        this.collection = oneOrder.getCollection();
        this.dimension = oneOrder.getDimension();
        this.itemPrice = oneOrder.getItemPrice();
        this.carrierHelper = oneOrder.getCarrierHelper();
        this.satisfactionPrice = oneOrder.getSatisfactionPrice();
        this.state = oneOrder.getState();
        this.date = oneOrder.getDate();
        this.id = oneOrder.getID();
        this.endPrice = oneOrder.getEndPrice();
        this.buyer = oneOrder.getBuyer();
        this.sellers = oneOrder.getSellers();
    }

    /**
     * Retrieves the end price of the order.
     *
     * @return The end price of the order.
     */
    public double getEndPrice() {
        return this.endPrice;
    }

    /**
     * Retrieves the satisfaction price of the order.
     *
     * @return The satisfaction price of the order.
     */
    public double getSatisfactionPrice() {
        return this.satisfactionPrice;
    }

    /**
     * Retrieves the buyer of the order.
     *
     * @return The buyer of the order.
     */
    public User getBuyer() {
        return this.buyer;
    }

    /**
     * Retrieves the list of sellers associated with the order.
     *
     * @return The list of sellers associated with the order.
     */
    public List<User> getSellers() {
        return this.sellers;
    }

    /**
     * Retrieves the carrier helper, which maps carrier names to the number of items
     * associated with each carrier.
     *
     * @return The carrier helper map.
     */
    public HashMap<String, Integer> getCarrierHelper() {
        return this.carrierHelper;
    }

    /**
     * Sets the order state to "Dispatched".
     */
    public void setDispatched() {
        this.state = OrderState.Dispatched;
    }

    /**
     * Sets the order state to "Finished" and returns the collection of items in the
     * order.
     *
     * @return The collection of items in the order.
     */
    public List<Item> setFinished() {
        // Generate invoices
        this.state = OrderState.Finished;
        return this.collection;
    }

    /**
     * Retrieves the collection of items in the order.
     *
     * @return The collection of items in the order.
     */
    public List<Item> getCollection() {
        return this.collection;
    }

    /**
     * Retrieves the dimension (size) of the order.
     *
     * @return The dimension of the order.
     */
    public TypeOfSize getDimension() {
        return this.dimension;
    }

    /**
     * Checks if the order is in the "Pending" state.
     *
     * @return True if the order is pending, false otherwise.
     */
    public boolean isPending() {
        return this.state.equals(OrderState.Pending);
    }

    /**
     * Checks if the order is in the "Finished" state.
     *
     * @return True if the order is finished, false otherwise.
     */
    public boolean isFinished() {
        return this.state.equals(OrderState.Finished);
    }

    /**
     * Checks if the order is in the "Dispatched" state.
     *
     * @return True if the order is dispatched, false otherwise.
     */
    public boolean isDispatched() {
        return this.state.equals(OrderState.Dispatched);
    }

    /**
     * Retrieves the total item price of the order.
     *
     * @return The total item price of the order.
     */
    public double getItemPrice() {
        return this.itemPrice;
    }

    /**
     * Retrieves the state of the order.
     *
     * @return The state of the order.
     */
    public OrderState getState() {
        return this.state;
    }

    /**
     * Retrieves the date of the order.
     *
     * @return The date of the order.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Retrieves the ID of the order.
     *
     * @return The ID of the order.
     */
    public int getID() {
        return this.id;
    }

    /**
     * Sets the end price of the order.
     *
     * @param endPrice The end price to be set for the order.
     */
    public void setEndPrice(double endPrice) {
        this.endPrice = endPrice;
    }

    /**
     * Sets the collection of items for the order.
     *
     * @param collection The collection of items to be set for the order.
     */
    public void setCollection(List<Item> collection) {
        this.collection = collection;
    }

    /**
     * Sets the dimension (size) of the order.
     *
     * @param dimension The dimension to be set for the order.
     */
    public void setDimension(TypeOfSize dimension) {
        this.dimension = dimension;
    }

    /**
     * Sets the item price of the order.
     *
     * @param itemPrice The item price to be set for the order.
     */
    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    /**
     * Sets the satisfaction price of the order.
     *
     * @param price The satisfaction price to be set for the order.
     */
    public void setSatisfactionPrice(double price) {
        this.satisfactionPrice = price;
    }

    /**
     * Sets the state of the order.
     *
     * @param state The state to be set for the order.
     *              If the state is set to "Finished", the final price is calculated
     *              and updated accordingly.
     */
    public void setState(OrderState state) {
        if (state == OrderState.Finished) {
            this.endPrice = calculateFinalPrice();
        }

        this.state = state;
    }

    /**
     * Sets the date of the order.
     *
     * @param date The date to be set for the order.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Sets the buyer of the order.
     *
     * @param buyer The buyer to be set for the order.
     */
    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    /**
     * Sets the sellers associated with the order.
     *
     * @param sellers The sellers to be set for the order.
     */
    public void setSeller(LinkedList<User> sellers) {
        this.sellers = sellers;
    }

    /**
     * Retrieves a list of items owned by a specific user within the order.
     *
     * @param user The user whose items are to be retrieved.
     * @return A list of items owned by the specified user within the order.
     */
    public List<Item> itemPerUser(User user) {
        List<Item> list = new LinkedList<Item>();

        for (Item i : this.collection) {
            if (i.getonePreviousOwners() == user.getId()) {
                list.add(i);
            }
        }

        return list;
    }

    /**
     * Returns a string representation of the order.
     *
     * @return A string representation of the order.
     */
    @Override
    public String toString() {
        return "Order{" +
                "collection='" + this.collection.toString() + "\'" +
                " dimension='" + this.dimension +
                " Final Price='" + this.itemPrice +
                " Satisfaction Price='" + this.satisfactionPrice +
                " State='" + this.state +
                " Date='" + this.date.toString() + "\'" +
                " Iva= 13%'" + "\'" +
                "ID='" + this.id + "}";
    }

    /**
     * Returns a formatted string representation of the order, showing the items,
     * ID, final price, state, and date.
     *
     * @return A formatted string representation of the order.
     */
    public String showOrder() {
        String ret = "Items= \n";

        for (Item i : this.collection) {
            ret += i.showItem();
        }
        ret = ret + '\n' + " ID= " + this.id + ", " + " Final Price= " + this.itemPrice + ", " + " State= " + this.state
                + ", " + " Date= "
                + this.date.toString();

        return ret;
    }

    /**
     * Checks if the order is equal to another object.
     *
     * @param o The object to compare with the order.
     * @return True if the order is equal to the specified object, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null || o.getClass() != this.getClass())
            return false;
        Order e = (Order) o;
        return this.getCollection().equals(e.getCollection()) &&
                this.getDimension().equals(e.getDimension()) &&
                this.getItemPrice() == e.getItemPrice() &&
                this.getSatisfactionPrice() == e.getSatisfactionPrice() &&
                this.getState().equals(e.getState()) &&
                this.getBuyer().equals(e.getBuyer()) &&
                this.getSellers().equals(e.getSellers()) &&
                this.getID() == e.getID();
    }

    /**
     * Creates and returns a clone of the order.
     *
     * @return A clone of the order.
     */
    public Order clone() {
        return new Order(this);
    }

    /**
     * Adds an item to the order.
     *
     * @param oneItem The item to be added to the order.
     * @param owner   The owner of the item.
     */
    public void addItem(Item oneItem, User owner) {
        int nmbr = this.collection.size();
        if (nmbr == 1)
            this.dimension = TypeOfSize.Medium;
        if (nmbr == 5)
            this.dimension = TypeOfSize.Big;
        this.collection.add(oneItem);
        if (!this.sellers.contains(owner))
            this.sellers.add(owner);

        this.itemPrice += oneItem.getPrice();

        if (this.carrierHelper.containsKey(oneItem.getCarrier().getName()))
            this.carrierHelper.put(oneItem.getCarrier().getName(),
                    this.carrierHelper.get(oneItem.getCarrier().getName()) + 1);
        else
            this.carrierHelper.put(oneItem.getCarrier().getName(), 1);

        if (oneItem.getConditionScore() == 1)
            this.satisfactionPrice += 0.5;
        else {
            this.satisfactionPrice += 0.25;
        }
    }

    /**
     * Removes an item from the order.
     *
     * @param oneItem The item to be removed from the order.
     * @param owner   The owner of the item.
     */
    public void removeItem(Item oneItem, User owner) {
        int nmbr = this.collection.size();
        if (nmbr == 2)
            this.dimension = TypeOfSize.Little;
        if (nmbr == 6)
            this.dimension = TypeOfSize.Medium;
        this.collection.remove(oneItem);
        this.itemPrice -= oneItem.getPrice();
        if (oneItem.getConditionScore() == 1) {
            this.satisfactionPrice -= 0.5;
        } else {
            this.satisfactionPrice -= 0.25;
        }
        int x = this.carrierHelper.get(oneItem.getCarrier().getName());

        if (x != 1) {
            this.carrierHelper.put(oneItem.getCarrier().getName(),
                    this.carrierHelper.get(oneItem.getCarrier().getName()) - 1);
        } else {
            this.carrierHelper.remove(oneItem.getCarrier().getName());
        }
        boolean flag = false;
        for (Item i : this.collection) {
            if (i.getUserId() == owner.getId()) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            this.sellers.remove(owner);
        }
    }

    /**
     * Calculates the final price of the order.
     *
     * @return The final price of the order, including taxes.
     */
    public double calculateFinalPrice() {
        double tax = 0;
        for (Item i : this.collection) {

            int many = this.carrierHelper.get(i.getCarrier().getName());

            if (many == 1)
                tax += i.getPrice() * i.getCarrier().getTaxSmallWithIva();
            if (many >= 2 && many <= 5)
                tax += i.getPrice() * i.getCarrier().getTaxMediumWithIva();
            if (many > 5)
                tax += i.getPrice() * i.getCarrier().getTaxBigWithIva();

        }
        return this.itemPrice + this.satisfactionPrice + tax;
    }

    /**
     * Compares this order with the specified order based on their dates.
     *
     * @param o the order to compare
     * @return a negative integer if this order is earlier, zero if they have the
     *         same date,
     *         or a positive integer if this order is later
     */
    @Override
    public int compareTo(Order o) {
        LocalDate date1 = this.getDate();
        LocalDate date2 = o.getDate();
        return date1.compareTo(date2);
    }

    /**
     * Custom serialization write method to save the object's state.
     *
     * @param out the output stream
     * @throws IOException if an I/O error occurs
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); // default serialization
        out.writeInt(currentID); // save static variable
    }

    /**
     * Custom serialization read method to restore the object's state.
     *
     * @param in the input stream
     * @throws IOException            if an I/O error occurs
     * @throws ClassNotFoundException if the class of a serialized object cannot be
     *                                found
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // default deserialization
        currentID = in.readInt(); // load static variable
    }

    /**
     * Calculates the total price of items per carrier in the order.
     *
     * @param carrier_name the name of the carrier
     * @return the total price of items for the specified carrier
     */
    public double getItemPricePerCarrier(String carrier_name) {

        double total = 0;
        for (Item i : this.collection) {

            if (i.getCarrier().getName().equals(carrier_name)) {
                total += i.getPrice();
            }

        }
        return total;
    }

}