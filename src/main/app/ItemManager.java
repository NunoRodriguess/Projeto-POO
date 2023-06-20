package app;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.io.Serializable;

/**
 * The {@code ItemManager} class is responsible for managing items in the
 * system.
 * It keeps track of sold and listed items, allows adding and removing items,
 * updating their status, and provides methods for retrieving items.
 */
public class ItemManager implements Serializable {
    private HashMap<Integer, Item> soldItemsMap;
    private HashMap<Integer, Item> listedItemsMap;

    /**
     * Constructs a new {@code ItemManager} object.
     * Initializes the soldItemsMap and listedItemsMap.
     */
    public ItemManager() {
        this.soldItemsMap = new HashMap<Integer, Item>();
        this.listedItemsMap = new HashMap<Integer, Item>();
    }

    /**
     * Retrieves an item with the specified ID.
     *
     * @param id the ID of the item to retrieve
     * @return the item with the specified ID, or null if it is not found
     */
    public Item getItem(int id) {
        if (this.soldItemsMap.containsKey(id)) {
            return this.soldItemsMap.get(id);
        } else if (this.listedItemsMap.containsKey(id)) {
            return this.listedItemsMap.get(id);
        } else {
            return null;
        }
    }

    /**
     * Adds a sold item to the soldItemsMap.
     *
     * @param item the item to add
     */
    public void addSoldItem(Item item) {
        this.soldItemsMap.put(item.getID(), item);
    }

    /**
     * Adds a listed item to the listedItemsMap.
     *
     * @param item the item to add
     */
    public void addListedItem(Item item) {
        this.listedItemsMap.put(item.getID(), item.clone());
    }

    /**
     * Removes a listed item with the specified ID from the listedItemsMap.
     *
     * @param id the ID of the item to remove
     * @return the removed item, or null if it is not found
     */
    public Item removeListedItem(int id) {
        return this.listedItemsMap.remove(id);
    }

    /**
     * Removes a sold item with the specified ID from the soldItemsMap.
     *
     * @param id the ID of the item to remove
     * @return the removed item, or null if it is not found
     */
    public Item removeSoldItem(int id) {
        return this.soldItemsMap.remove(id).clone();
    }

    /**
     * Updates the status of an item with the specified ID.
     * If the item is listed, it will be moved to the soldItemsMap.
     *
     * @param id the ID of the item to update
     */
    public void updateItem(int id) {
        if (this.listedItemsMap.containsKey(id)) {
            Item item = removeListedItem(id);
            addSoldItem(item);
        }
    }

    /**
     * Retrieves a list of sold items.
     *
     * @return the list of sold items
     */
    public List<Item> getSoldItems() {
        List<Item> items = new LinkedList<Item>();
        for (Integer key : this.soldItemsMap.keySet()) {
            Item value = this.soldItemsMap.get(key);
            items.add(value.clone());
        }
        return items;
    }

    /**
     * Retrieves a list of listed items.
     *
     * @return the list of listed items
     */

    public List<Item> getListedItems() {
        List<Item> items = new LinkedList<Item>();
        for (Integer key : this.listedItemsMap.keySet()) {
            Item value = this.listedItemsMap.get(key);
            items.add(value.clone());
        }
        return items;
    }

    /**
     * Searches for an item with the specified ID.
     *
     * @param id the ID of the item to search for
     * @return the item with the specified ID, or null if it is not found
     */
    public Item searchItem(int id) {
        Item item = this.getItem(id);
        if (item != null) {
            return item;
        }
        return null;
    }

    /**
     * Moves a sold item to the listed items map.
     * The item with the specified ID is retrieved from the soldItemsMap,
     * removed from it, and added to the listedItemsMap.
     *
     * @param id the ID of the item to move from sold to listed
     */
    public void soldToListed(int id) {
        Item item = this.soldItemsMap.get(id);
        this.soldItemsMap.remove(item.getID());
        this.listedItemsMap.put(item.getID(), item);
    }

    /**
     * Checks if all the items with the given keys are listed for sale.
     *
     * @param items_keys the list of item keys to check
     * @return {@code true} if all the items are listed for sale, {@code false}
     *         otherwise
     */
    public boolean areAllThisForSale(List<Integer> items_keys) {
        for (int key : items_keys) {
            Item item = this.listedItemsMap.get(key);
            if (item == null) {
                return false;
            }
        }
        return true;
    }
}
