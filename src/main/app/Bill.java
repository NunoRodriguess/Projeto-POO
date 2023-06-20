package app;

import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Represents a bill that contains information about the type, items, total
 * cost, ports tax, and associated order.
 */
public class Bill implements Serializable {

    /**
     * Enum representing the type of bill: BOUGHT or SOLD.
     */
    enum TypeBill {
        BOUGHT,
        SOLD
    }

    private int billNumber;
    private TypeBill type;
    private Map<Integer, Item> items;
    private double totalCost;
    private double portsTax;
    private Order order;

    private static int bill_count = 1;

    /**
     * Default constructor for the Bill class.
     * Initializes the bill with default values.
     */
    public Bill() {
        this.billNumber = bill_count++;
        this.type = null;
        this.items = new HashMap<>();
        this.totalCost = 0;
        this.order = null;
    }

    /**
     * Parameterized constructor for the Bill class.
     * Initializes the bill with the provided values.
     *
     * @param type      The type of the bill (BOUGHT or SOLD).
     * @param article   The map of items associated with the bill.
     * @param totalCost The total cost of the bill.
     * @param o         The associated order.
     */
    public Bill(TypeBill type, Map<Integer, Item> article, double totalCost, Order o) {
        this.billNumber = bill_count++;
        this.type = type;
        this.items = new HashMap<>();
        for (Map.Entry<Integer, Item> e : article.entrySet()) {
            this.items.put(e.getKey(), e.getValue());
        }
        this.totalCost = totalCost;
        this.order = o;
    }

    /**
     * Copy constructor for the Bill class.
     *
     * @param f The bill object to be copied.
     */
    public Bill(Bill f) {
        this.billNumber = f.getbillNumber();
        this.type = f.gettype();
        this.items = f.getitems();
        this.totalCost = f.gettotalCost();
        this.portsTax = f.getportsTax();
        this.order = f.getOrder();
    }

    /**
     * Retrieves the bill number.
     *
     * @return The bill number.
     */
    public int getbillNumber() {
        return this.billNumber;
    }

    /**
     * Retrieves the type of the bill.
     *
     * @return The type of the bill.
     */
    public TypeBill gettype() {
        return this.type;
    }

    /**
     * Retrieves the associated order.
     *
     * @return The associated order.
     */
    public Order getOrder() {
        return this.order;
    }

    /**
     * Retrieves the ports tax.
     *
     * @return The ports tax. Returns 0.0 if the bill type is SOLD.
     */
    public double getportsTax() {
        if (this.type.equals(TypeBill.SOLD))
            return 0.0;
        else
            return this.portsTax;
    }

    /**
     * Retrieves the Map with the items
     *
     * @return A map with the Items
     */

    public Map<Integer, Item> getitems() {
        Map<Integer, Item> map = new HashMap<Integer, Item>();
        for (Map.Entry<Integer, Item> e : this.items.entrySet()) {
            map.put(e.getKey(), e.getValue());
        }
        return map;
    }

    /**
     * Retrives total cost of the purchase
     * 
     * @return double
     */
    public double gettotalCost() {
        return this.totalCost;
    }

    /**
     * Set the item Map
     * 
     * @param Map<Integer,Item> art
     */
    public void setitems(Map<Integer, Item> art) {
        Map<Integer, Item> map = new HashMap<Integer, Item>();
        for (Map.Entry<Integer, Item> e : art.entrySet()) {
            map.put(e.getKey(), e.getValue());
        }
        this.items = map;
    }

    /**
     * Set total cost of the bill
     * 
     * @param valor
     */
    public void settotalCost(double valor) {
        this.totalCost = valor;
    }

    /**
     * Set state of the bill as Sold
     * 
     * @param oneOrder
     */

    public void setSold() {
        this.type = TypeBill.SOLD;
    }

    /**
     * Set state of the bill as Bought
     * 
     * @param oneOrder
     */
    public void setBought() {
        this.type = TypeBill.BOUGHT;
    }

    /**
     * Associate bill with an order
     * 
     * @param oneOrder
     */
    public void setOrder(Order oneOrder) {
        this.order = oneOrder;
    }

    /**
     * Clone method
     * 
     * @return a new Bill
     */
    @Override
    public Bill clone() {
        return new Bill(this);
    }

    /**
     * toString method
     * 
     * @return a string representation of the bill
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bill Number: ").append(billNumber).append("\n");
        sb.append("Type: ").append(type).append("\n");
        sb.append("Items:\n");
        for (Map.Entry<Integer, Item> entry : items.entrySet()) {
            int itemId = entry.getKey();
            Item item = entry.getValue();
            sb.append("  Item ID: ").append(itemId).append("\n");
            sb.append("    - Name: ").append(item.getDescription()).append("\n");
            sb.append("    - Price: ").append(item.getPrice()).append("\n");
            // Add other item details as needed
        }
        sb.append("Total Cost: ").append(totalCost).append("\n");
        sb.append("Ports Tax: ").append(portsTax).append("\n");
        sb.append("Order: ").append(order.getID()).append("\n");
        return sb.toString();
    }

    /**
     * Represantation of a bill for normal user
     * 
     * @return a string representation of the bill
     */

    public String showBill() {

        String ret = "";
        for (Integer i_key : this.items.keySet()) {
            Item i = this.items.get(i_key);
            ret += i.showItem();
        }
        ret = ret + '\n' + " ID= " + this.billNumber + ", " + " Total Cost= " + this.totalCost + ", " + " Ports Tax= "
                + this.portsTax
                + ", " + " Order= "
                + this.order.getID()
                + "Type= " + this.type
                + "Amount= " + getAmount();

        return ret;

    }

    /**
     * Equals method.
     * 
     * @params one object
     * @return true if this object equals to the object in the parameter
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if ((obj == null) || (this.getClass() != obj.getClass()))
            return false;
        Bill f = (Bill) obj;
        return this.getbillNumber() == f.getbillNumber() &&
                this.gettype().equals(f.gettype()) &&
                this.getitems().equals(f.getitems()) &&
                this.gettotalCost() == f.gettotalCost();
    }

    /**
     * Add an item to the bill.
     * 
     * @params one Item and the number to consider to aply taxes
     */
    public void addItem(Item item, int many_tax) {
        this.items.put(item.getID(), item);
        Carrier c = item.getCarrier();
        double tax = 0;
        if (many_tax == 1)
            tax = c.getTaxSmallWithIva();
        if (many_tax >= 2 && many_tax <= 5)
            tax = c.getTaxMediumWithIva();
        if (many_tax > 5)
            tax = c.getTaxBigWithIva();

        this.portsTax += tax * item.getBasePrice();
        calculateTotalCostItems();
    }

    /**
     * Remove an item to the bill.
     * 
     * @params one Item and the number to consider to aply taxes
     */
    public void removeItem(Item item, int many_tax) {
        this.items.remove(item.getID());
        Carrier c = item.getCarrier();
        double tax = 0;
        if (many_tax == 1)
            tax = c.getTaxSmallWithIva();
        if (many_tax >= 2 && many_tax <= 5)
            tax = c.getTaxMediumWithIva();
        if (many_tax > 5)
            tax = c.getTaxBigWithIva();

        this.portsTax -= tax * item.getBasePrice();
        if (many_tax == 2)
            this.portsTax = (this.portsTax * c.getTaxSmallWithIva()) / c.getTaxMediumWithIva();
        if (many_tax == 6)
            this.portsTax = (this.portsTax * c.getTaxMediumWithIva()) / c.getTaxBigWithIva();
        calculateTotalCostItems();
    }

    /**
     * Calculate the summation of the Items in the Bill
     * 
     */
    public void calculateTotalCostItems() {
        double sum = 0;
        for (Integer key : this.items.keySet()) {

            Item i = this.items.get(key);
            sum += i.getPrice();

        }
        this.totalCost = sum;
    }

    /**
     * Amount spend or earned
     * 
     * @return double value representing the price
     */
    public double getAmount() {

        if (this.type.equals(TypeBill.SOLD))
            return this.totalCost * 0.988;
        else
            return this.portsTax + this.totalCost;

    }

    /**
     * Checks if bill is Sold
     * 
     * @return true if the bill was sold
     */
    public boolean isSold() {
        return this.type.equals(TypeBill.SOLD);
    }

    /**
     * Writes the static variable responsible to give the bill number
     * 
     * @throws IoException if occured any problem writing
     */

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); // default serialization
        out.writeInt(bill_count); // save static variable
    }

    /**
     * Reads the static variable responsible to give the bill number
     * 
     * @throws IoException            if occured any problem reading
     * @throws ClassNotFoundException if occured any problem reading
     */

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // default deserialization
        bill_count = in.readInt(); // load static variable
    }

    /**
     * @return TypeBill return the type
     */
    public TypeBill getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(TypeBill type) {
        this.type = type;
    }

    /**
     * @return Map<Integer, Item> return the items
     */
    public Map<Integer, Item> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(Map<Integer, Item> items) {
        this.items = items;
    }

    /**
     * @return double return the totalCost
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * @param totalCost the totalCost to set
     */
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * @return double return the portsTax
     */
    public double getPortsTax() {
        return portsTax;
    }

    /**
     * @param portsTax the portsTax to set
     */
    public void setPortsTax(double portsTax) {
        this.portsTax = portsTax;
    }

}