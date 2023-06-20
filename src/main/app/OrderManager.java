package app;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.io.Serializable;

/**
 * Manages orders by storing and providing access to order information.
 */
public class OrderManager implements Serializable {
    private HashMap<Integer, Order> orderMap;

    /**
     * Constructs an OrderManager object with an empty order map.
     */
    public OrderManager() {
        this.orderMap = new HashMap<Integer, Order>();
    }

    /**
     * Retrieves the order with the specified ID.
     *
     * @param id the ID of the order
     * @return the order with the specified ID, or null if not found
     */
    public Order getOrder(int id) {
        if (this.orderMap.containsKey(id))
            return this.orderMap.get(id);
        return null;
    }

    /**
     * Retrieves all orders associated with the specified user ID.
     *
     * @param userId the ID of the user
     * @return a list of orders associated with the specified user ID
     */
    public List<Order> getThisUserOrders(int userId) {
        List<Order> orders = new LinkedList<Order>();

        for (Integer key : this.orderMap.keySet()) {
            Order o = this.orderMap.get(key);
            if (o.getBuyer().getId() == userId) {
                orders.add(o);
            }
        }
        return orders;
    }

    /**
     * Retrieves a specific order by its ID.
     *
     * @param id the ID of the order
     * @return a copy of the order with the specified ID
     */
    public Order searchOrder(int id) {
        return this.orderMap.get(id);
    }

    /**
     * Adds an order to the order map.
     *
     * @param order the order to be added
     */
    public void addOrder(Order order) {
        this.orderMap.put(order.getID(), order.clone());
    }

    /**
     * Removes an order from the order map.
     *
     * @param id the ID of the order to be removed
     * @return the removed order, or null if not found
     */
    public Order removeOrder(int id) {
        return this.orderMap.remove(id);
    }

    /**
     * Retrieves all orders stored in the order manager.
     *
     * @return a list of all orders
     */
    public List<Order> getOrders() {
        List<Order> orders = new LinkedList<Order>();
        for (Integer key : this.orderMap.keySet()) {
            Order value = this.orderMap.get(key);
            orders.add(value);
        }
        return orders;
    }
}
