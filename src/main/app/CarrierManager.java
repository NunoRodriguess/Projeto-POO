package app;

import java.util.TreeMap;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The CarrierManager class manages a collection of carriers.
 */
public class CarrierManager implements Serializable {

    private TreeMap<String, Carrier> carrierMap;

    /**
     * Constructs a new CarrierManager object.
     */
    public CarrierManager() {
        this.carrierMap = new TreeMap<String, Carrier>();
    }

    /**
     * Retrieves a carrier with the specified name.
     *
     * @param carrierName the name of the carrier
     * @return the carrier object, or null if not found
     * @throws NullPointerException if carrierName is null
     */
    public Carrier getCarrier(String carrierName) throws NullPointerException {
        if (this.carrierMap.containsKey(carrierName)) {
            return this.carrierMap.get(carrierName);
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Adds a carrier to the carrier map.
     *
     * @param carrier the carrier object to add
     * @throws CarrierAlreadyExistsException if the carrier with the same name
     *                                       already exists
     */
    public void addCarrier(Carrier carrier) throws CarrierAlreadyExistsException {
        if (this.carrierMap.containsKey(carrier.getName()))
            throw new CarrierAlreadyExistsException();

        this.carrierMap.put(carrier.getName(), carrier.clone());
    }

    /**
     * Retrieves a list of all carriers.
     *
     * @return a list of all carriers
     */
    public List<Carrier> getCarriers() {
        List<Carrier> carriers = new LinkedList<Carrier>();
        for (String key : this.carrierMap.keySet()) {
            Carrier value = this.carrierMap.get(key);
            carriers.add(value.clone());
        }
        return carriers;
    }

    /**
     * Removes a carrier with the specified name.
     *
     * @param name the name of the carrier to remove
     */
    public void removeCarrier(String name) {
        this.carrierMap.remove(name);
    }

    /**
     * Creates and returns a copy of the carrier map.
     *
     * @return a copy of the carrier map
     */
    public Map<String, Carrier> mapCopy() {
        Map<String, Carrier> copy = new TreeMap<>();
        for (Map.Entry<String, Carrier> entry : carrierMap.entrySet()) {
            String id = entry.getKey();
            Carrier carrier = entry.getValue();
            copy.put(id, carrier.clone());
        }
        return copy;
    }
}
