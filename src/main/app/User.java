package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * The User class represents a user of an online marketplace. It stores
 * information
 * such as the user's ID, email, name, address, NIF, password, as well as lists
 * of orders
 * and items associated with the user.
 */
public class User implements Serializable, Comparable<User> {

    private int id;
    private String email;
    private String name;
    private String address;
    private int nif;
    private String password;

    private Map<Integer, Bill> bills;
    private List<Item> systemItems;
    private List<Item> sellingItems;

    private static int currentID = 1;

    /**
     * Constructs a new user with default values for all fields.
     */
    public User() {

        this.id = currentID++;
        this.email = "n/d";
        this.name = "n/d";
        this.address = "n/d";
        this.nif = 0;
        this.password = "n/d";
        this.bills = new HashMap<Integer, Bill>();
        this.systemItems = new ArrayList<Item>();
        this.sellingItems = new ArrayList<Item>();

    }

    /**
     * Constructs a new user with the specified values for all fields.
     *
     * @param email          the user's email address
     * @param name           the user's name
     * @param address        the user's address
     * @param nif            the user's tax identification number
     * @param soldItemsValue the total value of items sold by the user
     * @param password       the user's password
     * @param bills          the orders acquired by the user
     * @param emittedOrder   the orders emitted by the user
     * @param sellingItems   the items being sold by the user
     */
    public User(String email, String name, String address, int nif, HashMap<Integer, Bill> bills, String password,
            ArrayList<Item> systemItems, ArrayList<Item> sellingItems) {

        this.id = currentID++;
        this.email = email;
        this.name = name;
        this.bills = bills;
        this.address = address;
        this.nif = nif;
        this.password = password;
        this.bills = bills;
        this.systemItems = new ArrayList<>(systemItems);
        this.sellingItems = new ArrayList<>(sellingItems);

    }

    /**
     * Constructs a new user with the specified values for a few fields
     *
     * @param email    the user's email address
     * @param name     the user's name
     * @param address  the user's address
     * @param nif      the user's tax identification number
     * @param password the user's password
     */
    public User(String email, String name, String address, int nif, String password) {

        this.id = currentID++;
        this.email = email;
        this.name = name;
        this.address = address;
        this.nif = nif;
        this.password = password;
        this.bills = new HashMap<>();
        this.systemItems = new ArrayList<>();
        this.bills = new HashMap<>();
        this.sellingItems = new ArrayList<>();

    }

    /**
     * Constructs a new user that is a copy of the specified user.
     *
     * @param oneUser the user to copy
     */
    public User(User oneUser) {

        this.id = oneUser.getId();
        this.email = oneUser.getEmail();
        this.name = oneUser.getName();
        this.address = oneUser.getAddress();
        this.nif = oneUser.getNif();
        this.password = oneUser.getPassword();
        this.bills = oneUser.getBillsCopy();
        this.systemItems = oneUser.getSystemItems();
        this.sellingItems = oneUser.getSellingItems();

    }

    /**
     * Gets the ID of this user.
     *
     * @return the user's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the email address of this user.
     *
     * @return the user's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the name of this user.
     *
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the user's address.
     *
     * @return the user's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns copy of the users Emmited Orders
     *
     * @return copy of the users Emmited Orders
     */
    public List<Order> getEmmitedOrder() {
        List<Order> orders = new LinkedList<Order>();
        for (Integer b_id : this.bills.keySet()) {
            Bill b = this.bills.get(b_id);
            if (b.isSold()) {
                orders.add(b.getOrder().clone());
            }
        }
        return orders;
    }

    /**
     * Returns the user's NIF (tax identification number).
     *
     * @return the user's NIF
     */
    public int getNif() {
        return nif;
    }

    /**
     * Returns the user's bills
     *
     * @return the user's bills
     */
    public Map<Integer, Bill> getBills() {
        return this.bills;
    }

    /**
     * Returns the copy of user's bills
     *
     * @return the copy of the user's bills
     */
    public Map<Integer, Bill> getBillsCopy() {
        Map<Integer, Bill> copiedBills = new HashMap<>();

        for (Map.Entry<Integer, Bill> entry : this.bills.entrySet()) {

            copiedBills.put(entry.getKey(), (Bill) entry.getValue().clone());

        }

        return copiedBills;
    }

    /**
     * Add's a bill to the User
     * 
     * @param bill the bill to add
     */

    public void addBills(Bill bill) {

        this.bills.put(bill.getbillNumber(), bill.clone());

    }

    /**
     * Returns the user's password.
     *
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the user's system items
     *
     * @return the user's system items
     */
    public List<Item> getSystemItems() {
        return new ArrayList<>(this.systemItems);
    }

    /**
     * Returns the user's listed items.
     *
     * @return the user's listed items
     */
    public List<Item> getSellingItems() {

        return new ArrayList<>(this.sellingItems);
    }

    /**
     * @param email
     *              Set the user's email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param name
     *             Set the user's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param address
     *                Set the user's address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @param nif
     *            Set the user's nif.
     */
    public void setNif(int nif) {
        this.nif = nif;
    }

    /**
     * @param soldItemsValue
     *                       Set the user's total sold items value.
     */

    public double soldItemsValueFrame(LocalDate date1, LocalDate date2) {
        double sum = 0;
        for (Integer i : bills.keySet()) {
            Bill b = this.bills.get(i);
            if (b.isSold() && b.getOrder().getDate().isAfter(date1) && b.getOrder().getDate().isBefore(date2))
                sum += b.gettotalCost();
        }
        return sum;
    }

    /**
     * Returns the amount spend on a time frame
     * 
     * @return the amount spent on a time frame
     */
    public double boughtValueFrame(LocalDate date1, LocalDate date2) {
        double sum = 0;
        for (Integer i : bills.keySet()) {
            Bill b = this.bills.get(i);
            if (!b.isSold() && b.getOrder().getDate().isAfter(date1) && b.getOrder().getDate().isBefore(date2))
                sum += b.getAmount();
        }
        return sum;
    }

    /**
     * @param password
     *                 Set the user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param item_id
     *                change item to a system item
     */
    public void listASystemItem(int item_id) {

        Item i = this.searchItem(item_id);
        if (i == null)
            throw new NullPointerException();
        this.systemItems.remove(i);
        this.sellingItems.add(i);

    }

    /**
     * @param emittedOrder
     *                     Set the user's emitted orders.
     */
    public void setEmittedOrder(List<Item> systemItems) {
        this.systemItems = new ArrayList<>(systemItems);
    }

    /**
     * @param sellingItems
     *                     Set the user's selling items.
     */
    public void setSellingItems(List<Item> sellingItems) {
        this.sellingItems = new ArrayList<>(sellingItems);
    }

    /**
     * Compares user´s by the total amount of sales.
     * 
     * @param otherUser
     * @return result of comparation
     */
    @Override
    public int compareTo(User otherUser) {
        return Double.compare(this.soldItemsValue(), otherUser.soldItemsValue());
    }

    /**
     * Clone a user
     * 
     * @return new user object
     */
    @Override
    public User clone() {
        return new User(this);
    }

    /**
     * See´s if two users are equal
     * 
     * @param o
     * @return return result of comparation
     */
    @Override
    public boolean equals(Object o) {

        if (o == null)
            return false;

        if (o.getClass() != this.getClass())
            return false;

        User u = (User) o;

        return (u.getId() == this.getId()) && u.getEmail().equals(this.getEmail()) && u.getName().equals(this.getName())
                && u.getAddress().equals(this.getAddress()) && u.getNif() == this.getNif()
                && u.getBills() == this.getBills()
                && u.getPassword().equals(this.getPassword())
                && u.getSystemItems().equals(this.getSystemItems())
                && u.getSellingItems().equals(this.getSellingItems());

    }

    /**
     * Makes a string out of a user
     * 
     * @return String conversion of object user
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", nif=" + nif +
                ", password='" + password + '\'' +
                ", systemItems=" + systemItems +
                ", sellingItems=" + sellingItems +
                ", Bills=" + bills +
                ", Total Earned=" + soldItemsValue() +
                '}';
    }

    /**
     * @param oneItem
     *                Add a listed item to the user
     */
    public void addItem(Item oneItem) {
        this.sellingItems.add(oneItem);
    }

    /**
     * @param oneItem
     *                Add a system item to the user
     */
    public void addSystemItem(Item oneItem) {
        this.systemItems.add(oneItem);
    }

    /**
     * @param oneItem
     *                Removes an item from the user
     */
    public void removeItem(Item oneItem) {
        this.sellingItems.remove(oneItem);
        oneItem.addPreviousOwner(this.id);
    }

    /**
     * @param oneItem
     *                Removes a system item from the user
     */

    public void removeSystemItem(Item oneItem) {
        this.systemItems.remove(oneItem);
    }

    /**
     * Check´s if user has the item
     * 
     * @param oneItem
     * @return returns true if it has the item
     */
    public boolean containsItem(Item oneItem) {
        return this.sellingItems.contains(oneItem);
    }

    /**
     * Check´s if user has the item
     * 
     * @param oneItem
     * @return returns true if it has the item
     */
    public boolean hasItem(int item_id) {

        for (Item oneItem : this.systemItems) {
            if (oneItem.getID() == item_id) {
                return true;
            }
        }
        return false;

    }

    /**
     * Looks for the Item
     * 
     * @param item_id id of the item
     * @return returns the Item or Null
     */
    public Item searchItem(int item_id) throws NullPointerException {
        for (Item i : this.sellingItems) {
            if (i.getID() == item_id)
                return i;
        }
        for (Item i : this.systemItems) {
            if (i.getID() == item_id)
                return i;
        }
        return null;
    }

    /**
     * Calculates the amount earned
     * 
     * @return returns the amount earned
     */
    public double soldItemsValue() {
        double ret = 0;
        for (int b_key : bills.keySet()) {
            Bill b = bills.get(b_key);
            if (b.isSold())
                ret += b.getAmount();
        }
        return ret;
    }

    /**
     * Calculates the amount spend
     * 
     * @return returns the amount spend
     */
    public double spendValue() {
        double ret = 0;
        for (int b_key : bills.keySet()) {
            Bill b = bills.get(b_key);
            if (!b.isSold())
                ret += b.getAmount();
        }
        return ret;
    }

    /**
     * Custom serialization method to write the object's state to the
     * ObjectOutputStream.
     *
     * @param out ObjectOutputStream to write the object's state to
     * @throws IOException If an I/O error occurs while writing the object
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); // default serialization
        out.writeInt(currentID); // save static variable
    }

    /**
     * Custom deserialization method to read the object's state from the
     * ObjectInputStream.
     *
     * @param in ObjectInputStream to read the object's state from
     * @throws IOException            If an I/O error occurs while reading the
     *                                object
     * @throws ClassNotFoundException If the class of a serialized object could not
     *                                be found
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // default deserialization
        currentID = in.readInt(); // load static variable
    }

    /**
     * Checks if the YourClass object contains any of the given item keys.
     *
     * @param items_keys List of item keys to check
     * @return true if at least one item key is found in the sellingItems list,
     *         false otherwise
     */
    public boolean oneOfHis(List<Integer> items_keys) {

        for (Item i : this.sellingItems) {

            int id = i.getID();

            for (int buyId : items_keys) {

                if (id == buyId)
                    return true;
            }

        }

        return false;
    }

}