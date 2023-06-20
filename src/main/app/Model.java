package app;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import java.io.*;

/**
 * This class represents a module that manages items, users, orders, and
 * carriers.
 */
public class Model implements Serializable {

    private ItemManager itemManager;
    private UserManager userManager;
    private OrderManager orderManager;
    private CarrierManager carrierManager;
    private double vintageProfit;
    private User currentUser;

    /**
     * Constructs a new Module object with Managers.
     */
    Model() {
        this.itemManager = new ItemManager();
        this.userManager = new UserManager();
        this.orderManager = new OrderManager();
        this.carrierManager = new CarrierManager();
        SystemDate.setDate(LocalDate.now());
        this.vintageProfit = 0;
    }

    /**
     * Copy of the User Manager
     * 
     * @return Map<Integer, User>
     */
    public Map<Integer, User> getUserManagerCopy() {
        return this.userManager.getUserMapCopy();
    }

    /**
     * Current Date
     * 
     * @return String
     */
    public String getDate() {
        return SystemDate.getDate().toString();
    }

    /**
     * Current Date
     * 
     * @return String
     */
    private LocalDate getSystemDate() {
        return SystemDate.getDate();
    }

    /**
     * Copy of the Carrier Manager
     * 
     * @return List<Carrier>
     */
    public List<Carrier> getCarrierManagerList() {
        return carrierManager.getCarriers();
    }

    public List<Item> getListedItemsManagerList() {

        return itemManager.getListedItems();

    }

    /// -------------------
    /**
     * Returns the currently logged-in user.
     *
     * @return The currently logged-in user.
     * @throws NullPointerException if no user is currently logged in.
     * @throws UserIsAdminException if the currently logged-in user is an admin.
     */
    private User getCurrentUser() throws NullPointerException, UserIsAdminException {

        if (this.currentUser.getEmail().equals("admin")) {
            throw new UserIsAdminException(this.currentUser);
        }
        if (this.currentUser == null) {
            throw new NullPointerException();
        }

        return this.currentUser;
    }

    /**
     * Returns a copy of the currently logged-in user.
     *
     * @return Copy of the currently logged-in user.
     * @throws NullPointerException if no user is currently logged in.
     * @throws UserIsAdminException if the currently logged-in user is an admin.
     */
    public User CurrentUser() throws NullPointerException, UserIsAdminException {

        if (this.currentUser.getEmail().equals("admin")) {
            throw new UserIsAdminException(this.currentUser);
        }
        if (this.currentUser == null) {
            throw new NullPointerException();
        }

        return this.currentUser.clone();
    }

    /**
     * Retrieves and returns the system items of the current user.
     *
     * @return A string representation of the current user's system items.
     * @throws UserIsAdminException if the current user is an admin.
     */
    public String currentUserSystemItems() throws UserIsAdminException {

        User u = getCurrentUser();
        String ret = "";
        for (Item i : u.getSystemItems()) {

            ret += i.showItem();

        }
        ret += '\n';
        return ret;
    }

    /**
     * Retrieves and returns the listed items of the current user.
     *
     * @return A string representation of the current user's listed items.
     * @throws UserIsAdminException if the current user is an admin.
     */
    public String currentUserListedItems() throws UserIsAdminException {

        User u = getCurrentUser();
        String ret = "";
        for (Item i : u.getSellingItems()) {

            ret += i.showItem();

        }
        ret += '\n';
        return ret;
    }

    /**
     * Retrieves and returns the orders of the current user.
     *
     * @return A string representation of the current user's orders.
     */
    public String checkThisUserOrders() {

        int userId = this.currentUser.getId();
        List<Order> pointers = this.orderManager.getThisUserOrders(userId);

        String ret = "";

        for (Order order : pointers) {

            ret += order.showOrder();
        }
        return ret;
    }

    /**
     * Sets the current user based on the provided user ID.
     *
     * @param userId The ID of the user to set as the current user.
     */
    private void setCurrentUser(int id) {
        this.currentUser = this.userManager.getUser(id);
    }

    /**
     * Adds a new item to a user's item list based on the provided user ID and item
     * ID.
     *
     * @param userId The ID of the user.
     * @param itemId The ID of the item.
     * @throws NullPointerException if the user or item is not found.
     */
    private void addNewItemToUsers(int id_user, int id_item) throws NullPointerException {

        this.userManager.getUser(id_user).addItem(this.itemManager.getItem(id_item));
    }

    /**
     * Performs the login process based on the provided email and password.
     *
     * @param email    The email of the user.
     * @param password The password of the user.
     * @throws NullPointerException if the user is not found.
     * @throws MissedIdException    if the password is incorrect.
     */
    public void loginModel(String email, String password) throws NullPointerException, MissedIdException {

        User u = lookupUser(email);

        if (u.getPassword().equals(password)) {
            setCurrentUser(u.getId());
        } else {
            throw new MissedIdException();
        }

    }

    /**
     * Displays the normal carriers (non-premium carriers).
     *
     * @return A string representation of the normal carriers.
     */
    private String displayNormalCarriers() {
        String result = "\n";
        for (Carrier c : getCarrierManagerList()) {
            if (c instanceof Premium) {

            } else {
                result += c.showCarrier();
            }

        }
        return result;

    }

    /**
     * Displays the premium carriers.
     *
     * @return A string representation of the premium carriers.
     */

    private String displayPremiumCarriers() {
        String result = "";
        for (Carrier c : getCarrierManagerList()) {
            if (c instanceof Premium) {
                PremiumCarrier c1 = (PremiumCarrier) c;
                result += c1.showCarrier();
            }

        }
        return result;

    }

    /**
     * Shows carriers based on the premium status.
     *
     * @param premium A string indicating if premium carriers should be shown ("y"
     *                for yes, otherwise no).
     * @return A string representation of the carriers.
     */

    public String showCarriers(String premium) {
        if (premium.equals("y"))
            return displayPremiumCarriers();
        else
            return displayNormalCarriers();
    }

    /**
     * Displays the listed items, excluding items listed by the current user.
     *
     * @return A string representation of the listed items.
     * @throws UserIsAdminException if the current user is an admin.
     */
    public String displayListedItems() throws UserIsAdminException {
        List<Item> items = getListedItemsManagerList();
        String ret = "";
        for (Item item : items) {

            if (item.getUserId() != getCurrentUser().getId())
                ret += item.showItem();
        }
        return ret;
    }

    /**
     * Displays all carriers, including premium and normal carriers.
     *
     * @return A string representation of all carriers.
     */
    public String displayAllCarriers() {
        String result = "\n";
        for (Carrier c : getCarrierManagerList()) {
            System.out.println(c instanceof Premium);
            if (c instanceof Premium) {
                PremiumCarrier c1 = (PremiumCarrier) c;
                result += c1.toString() + "\n";
            } else {
                result += c.toString() + "\n";
            }
        }
        return result;
    }

    /**
     * Sets the current date to the given date.
     *
     * @param dateNew The new current date.
     */
    public void setCurrentDate(LocalDate dateNew) {
        SystemDate.setDate(dateNew);
    }

    /**
     * Makes an order for the specified items.
     *
     * @param items_keys A list of item keys to be ordered.
     * @return The created order.
     * @throws InvalidId if the item IDs are invalid.
     */
    public Order makeOrder(List<Integer> items_keys) throws InvalidId {

        if (this.currentUser.oneOfHis(items_keys) || !this.itemManager.areAllThisForSale(items_keys)
                || items_keys.isEmpty())
            throw new InvalidId();

        Order order = new Order();
        for (Integer current_key : items_keys) {
            Item i = this.itemManager.getItem(current_key);
            User u = this.userManager.getUser(i.getUserId());
            order.addItem(i, u);
            this.itemManager.updateItem(i.getID());
        }
        User buyer = this.userManager.getUser(this.currentUser.getId());
        order.setBuyer(buyer);
        order.setDate(this.getSystemDate());
        this.orderManager.addOrder(order);
        return order.clone();
    }

    /**
     * Registers an item for a user.
     *
     * @param item    The item to be registered.
     * @param id_user The ID of the user.
     * @throws NullPointerException if the user is not found.
     */
    private void registsItem(Item item, int id_user) throws NullPointerException, IllegalArgumentException {
        if (item instanceof Premium && !(item.getCarrier() instanceof Premium)
                || !(item instanceof Premium) && (item.getCarrier() instanceof Premium)) {
            throw new IllegalArgumentException();
        } else {
            User u = this.userManager.getUser(id_user);
            this.itemManager.addListedItem(item); // clone no manager
            Item i = this.itemManager.searchItem(item.getID());
            u.addItem(i);
            i.setUserId(u.getId());
        }

    }

    /**
     * Registers a new user with the given credentials.
     *
     * @param email    The email of the user.
     * @param name     The name of the user.
     * @param address  The address of the user.
     * @param nif      The NIF of the user.
     * @param password The password of the user.
     * @throws NullPointerException       if any of the parameters is null.
     * @throws UserAlreadyExistsException if a user with the same email already
     *                                    exists.
     */

    public void registsUser(String email, String name, String address, int nif, String password)
            throws NullPointerException, UserAlreadyExistsException {

        if (reviewCredentials(email) != true) {
            throw new UserAlreadyExistsException();
        }
        User u = new User(email, name, address, nif, password);
        this.userManager.addUser(u);

    }

    /**
     * Registers a new bag with the provided information.
     *
     * @param description    The description of the bag.
     * @param brand          The brand of the bag.
     * @param basePrice      The base price of the bag.
     * @param carrier        The carrier of the bag.
     * @param conditionScore The condition score of the bag.
     * @param dimension      The dimension of the bag.
     * @param material       The material of the bag.
     * @param releaseDate    The release date of the bag.
     * @param premium        The premium status of the bag.
     * @throws NullPointerException     if any of the parameters is null.
     * @throws IllegalArgumentException if the condition score is greater than 5.
     */
    public void registBag(String description, String brand, double basePrice,
            String carrier, double conditionScore, double dimension,
            String material, LocalDate releaseDate, String premium)
            throws NullPointerException, IllegalArgumentException {
        Stack<Integer> previousOwners = new Stack<Integer>();

        if (currentUser == null)
            throw new NullPointerException();

        this.carrierManager.getCarrier(carrier);

        if (conditionScore > 5 || releaseDate.isAfter(getSystemDate()))
            throw new IllegalArgumentException();

        if (premium.equals("y")) {
            PremiumBag bag = new PremiumBag(description, brand, basePrice,
                    this.carrierManager.getCarrier(carrier),
                    conditionScore / 5, previousOwners, dimension, material, releaseDate, this.currentUser.getId());

            registsItem(bag, this.currentUser.getId());
        } else {
            Bag bag = new Bag(description, brand, basePrice,
                    this.carrierManager.getCarrier(carrier),
                    conditionScore / 5, previousOwners, dimension, material, releaseDate, this.currentUser.getId());
            registsItem(bag, this.currentUser.getId());
        }

    }

    /**
     * Registers a new T-shirt with the provided information.
     *
     * @param description    The description of the T-shirt.
     * @param brand          The brand of the T-shirt.
     * @param basePrice      The base price of the T-shirt.
     * @param carrier        The carrier of the T-shirt.
     * @param conditionScore The condition score of the T-shirt.
     * @param size           The size of the T-shirt.
     * @param pattern        The pattern of the T-shirt.
     * @throws NullPointerException     if any of the parameters is null.
     * @throws IllegalArgumentException if the condition score is greater than 5.
     */
    public void registTshirt(String description, String brand, double basePrice,
            String carrier, double conditionScore, Tshirt.TshirtSize size,
            Tshirt.TshirtPattern pattern) throws NullPointerException, IllegalArgumentException {

        if (currentUser == null)
            throw new NullPointerException();

        if (conditionScore > 5)
            throw new IllegalArgumentException();

        Stack<Integer> previousOwners = new Stack<Integer>();
        Tshirt tshirt = new Tshirt(description, brand, basePrice,
                this.carrierManager.getCarrier(carrier),
                conditionScore / 5, previousOwners, size, pattern, this.currentUser.getId());

        registsItem(tshirt, this.currentUser.getId());
    }

    /**
     * Registers a new sneaker with the provided information.
     *
     * @param description    The description of the sneaker.
     * @param brand          The brand of the sneaker.
     * @param basePrice      The base price of the sneaker.
     * @param carrier        The carrier of the sneaker.
     * @param conditionScore The condition score of the sneaker.
     * @param size           The size of the sneaker.
     * @param type           The type of the sneaker.
     * @param color          The color of the sneaker.
     * @param releaseDate    The release date of the sneaker.
     * @param premium        The premium status of the sneaker.
     * @throws NullPointerException     if any of the parameters is null.
     * @throws IllegalArgumentException if the condition score is greater than 5.
     */
    public void registSneaker(String description, String brand, double basePrice,
            String carrier, double conditionScore, double size,
            Sneaker.SneakerType type, String color, LocalDate releaseDate, String premium)
            throws NullPointerException, IllegalArgumentException {

        if (currentUser == null)
            throw new NullPointerException();

        Stack<Integer> previousOwners = new Stack<Integer>();

        if (conditionScore > 5 || releaseDate.isAfter(getSystemDate()))
            throw new IllegalArgumentException();

        if (premium.equals("y")) {
            PremiumSneaker sneaker = new PremiumSneaker(description, brand, basePrice,
                    this.carrierManager.getCarrier(carrier),
                    conditionScore / 5, previousOwners, size, type, color, releaseDate, this.currentUser.getId());

            registsItem(sneaker, this.currentUser.getId());

        } else {
            Sneaker sneaker = new Sneaker(description, brand, basePrice,
                    this.carrierManager.getCarrier(carrier),
                    conditionScore / 5, previousOwners, size, type, color, releaseDate, this.currentUser.getId());

            registsItem(sneaker, this.currentUser.getId());
        }
    }

    /**
     * ToString method
     *
     * @return a string representation of the Model
     */

    @Override
    public String toString() {
        return "Module{" +
                "Current User" + this.currentUser +
                "soldItemsMap=" + this.itemManager.getSoldItems() +
                ", listedItemsMap=" + this.itemManager.getListedItems() +
                ", userMap=" + this.userManager.getUsers() +
                ", orderMap=" + this.orderManager.getOrders() +
                ", carrierMap=" + this.carrierManager.getCarriers() +
                ", date=" + getDate() +
                ", vintageProfit=" + vintageProfit +
                '}';
    }

    /**
     * Looks up a user by their email.
     *
     * @param email The email of the user to look up.
     * @return The found user if exists, or null otherwise.
     */
    public User lookupUser(String email) {

        User u = userManager.findUserByEmail(email);
        if (u == null)
            return null;
        return u.clone();
    }

    /**
     * Reviews the credentials to determine if a user with the given email already
     * exists.
     *
     * @param email The email to review.
     * @return true if the user does not exist, false otherwise.
     */
    private boolean reviewCredentials(String email) {
        User u = this.userManager.findUserByEmail(email);
        return u == null;
    }

    /**
     * Updates the model for an item with the given item ID.
     *
     * @param item_id The ID of the item to update.
     */
    private void updateItemModel(int item_id) {

        Item i = this.itemManager.getItem(item_id);
        if (i != null) {

            this.vintageProfit += i.getPrice() * 0.112; // 0.122 Comissão por item da vintage

            if (i.getConditionScore() == 1)
                this.vintageProfit += 0.5;
            else {
                this.vintageProfit += 0.25;
            }

            this.itemManager.updateItem(item_id);
        }

    }

    /**
     * Alters the state of an item with the given item ID.
     *
     * @param item_id The ID of the item to alter.
     * @throws NullPointerException if the current user is null.
     */
    public void alterItemState(int item_id) throws NullPointerException {
        int user_id = currentUser.getId();
        User u = this.userManager.getUser(user_id);
        u.listASystemItem(item_id);
        this.itemManager.soldToListed(item_id);
    }

    /**
     * Advances the current date to the specified new date, processing pending
     * orders and updating relevant information.
     *
     * @param newDate the new date to which the current date will be advanced
     * @throws IllegalArgumentException if the new date is before the current date
     */

    public void TimeSkip(LocalDate newDate) throws IllegalArgumentException {

        if (newDate.isBefore(getSystemDate())) {
            throw new IllegalArgumentException();
        }

        while (getSystemDate().isBefore(newDate)) {

            List<Order> orders = this.orderManager.getOrders();
            for (Order o : orders) {
                if (o.isPending() && o.getDate().isBefore(newDate)) {
                    List<Item> items = o.setFinished();
                    for (Item i : items) {

                        User u = this.userManager.getUser(i.getUserId());
                        u.removeItem(i); // o dinheiro só cai no utilizador apartir do momento que a encomenda é
                                         // dispatched
                        User uBuy = o.getBuyer();
                        uBuy.addSystemItem(i);
                        updateItemModel(i.getID());
                        i.setUserId(uBuy.getId());

                    }
                    o.setFinished();
                }
                long daysBetween = ChronoUnit.DAYS.between(getSystemDate(), newDate);
                if (o.isFinished() && daysBetween >= 3) {

                    HashMap<String, Integer> carrierHelper = o.getCarrierHelper();
                    for (String carrier_name : carrierHelper.keySet()) {

                        Carrier c = this.carrierManager.getCarrier(carrier_name);
                        c.updateEarnings(carrierHelper.get(carrier_name), o.getItemPricePerCarrier(carrier_name));

                    }
                    HashMap<String, Integer> carrierHashMap = o.getCarrierHelper();

                    for (User u : o.getSellers()) {
                        List<Item> items = o.itemPerUser(u);

                        for (Item item : items) {
                            Bill bill = new Bill();
                            bill.addItem(item, carrierHashMap.get(item.getCarrier().getName()));
                            bill.setSold();
                            bill.setOrder(o);
                            u.addBills(bill.clone());
                        }

                    }

                    Bill billBuyer = new Bill();
                    for (Item item : o.getCollection()) {

                        billBuyer.addItem(item, carrierHashMap.get(item.getCarrier().getName()));

                    }
                    billBuyer.setBought();
                    billBuyer.setOrder(o);
                    o.getBuyer().addBills(billBuyer.clone());
                    o.setDispatched();

                }

            }

            setCurrentDate(getSystemDate().plusDays(1));
        }
        setCurrentDate(newDate);
    }

    /**
     * Undoes the processing of items from a canceled or returned order, reverting
     * relevant information and ownership.
     *
     * @param o the order for which items need to be undone
     */
    private void undoItem(Order o) {

        List<Item> col = o.getCollection();

        for (Item item : col) {
            User u = this.userManager.getUser(item.getUserId());
            u.removeSystemItem(item);

            item.returnOwnership();
            this.vintageProfit -= item.getPrice() * 0.112;
            this.addNewItemToUsers(item.getUserId(), item.getID());
            this.itemManager.soldToListed(item.getID());
            Carrier c = item.getCarrier();
            c.revertProfit(item.getPrice(), o.getCarrierHelper().get(c.getName()));

        }
    }

    /**
     * Deletes an order and performs necessary actions related to the deletion, such
     * as returning items and removing associated bills.
     *
     * @param orderId the ID of the order to be deleted
     * @throws OrderNotReturnable if the order cannot be returned
     */
    public void deleteOrder(int orderId) throws OrderNotReturnable { // Para uma order ser returend, nenhum dos items
                                                                     // que foram comprados podem estar listados!

        Order o = this.orderManager.getOrder(orderId);
        long daysBetween = ChronoUnit.DAYS.between(o.getDate(), getSystemDate());
        User u = o.getBuyer();
        for (Item i : o.getCollection()) {
            if (u.hasItem(i.getID()) == false) {
                throw new OrderNotReturnable();
            }

        }

        if (daysBetween >= 16) {
            throw new OrderNotReturnable();
        }

        this.orderManager.removeOrder(orderId);
        this.userManager.deleteBills(o);
        this.undoItem(o);

    }

    /**
     * Saves the current state of the model to a file.
     *
     * @param fileName the name of the file to save the model to
     * @throws FileNotFoundException if the specified file cannot be found
     * @throws IOException           if an I/O error occurs while writing to the
     *                               file
     */
    public void save(String fileName) throws FileNotFoundException, IOException {

        FileOutputStream fs = new FileOutputStream(fileName);
        ObjectOutputStream os = new ObjectOutputStream(fs);
        os.writeObject(this);
        os.flush();
        os.close();

    }

    /**
     * Loads a previously saved model from a file.
     *
     * @param fileName the name of the file to load the model from
     * @return the loaded Model object
     * @throws FileNotFoundException  if the specified file cannot be found
     * @throws IOException            if an I/O error occurs while reading the file
     * @throws ClassNotFoundException if the class of the serialized object cannot
     *                                be found
     */
    public static Model load(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {

        FileInputStream fs = new FileInputStream(fileName);
        ObjectInputStream os = new ObjectInputStream(fs);
        Model model = (Model) os.readObject();
        os.close();

        return model;
    }

    /**
     * Adds a carrier to the model.
     *
     * @param name      the name of the carrier
     * @param taxSmall  the tax rate for small items
     * @param taxMedium the tax rate for medium items
     * @param taxBig    the tax rate for big items
     * @param premium   specifies whether the carrier is premium or not (accepts "y"
     *                  for premium, any other value for non-premium)
     * @throws CarrierAlreadyExistsException if a carrier with the same name already
     *                                       exists
     */
    public void addCarrier(String name, double taxSmall, double taxMedium, double taxBig, String premium)
            throws CarrierAlreadyExistsException {

        if (premium.equals("y")) {

            this.carrierManager.addCarrier(new PremiumCarrier(name, taxSmall, taxMedium, taxBig, 0));
        } else {
            this.carrierManager.addCarrier(new Carrier(name, taxSmall, taxMedium, taxBig, 0));
        }

    }

    /**
     * Changes the tax rates for a carrier.
     *
     * @param name      the name of the carrier
     * @param taxSmall  the new tax rate for small items
     * @param taxMedium the new tax rate for medium items
     * @param taxBig    the new tax rate for big items
     * @throws NullPointerException if the carrier with the specified name is not
     *                              found
     */
    public void changeCarrier(String name, double taxSmall, double taxMedium, double taxBig)
            throws NullPointerException {
        Carrier c = this.carrierManager.getCarrier(name);
        this.carrierManager.removeCarrier(name);
        c.setTaxSmall(taxSmall);
        c.setTaxMedium(taxMedium);
        c.setTaxBig(taxBig);
        try {
            this.carrierManager.addCarrier(c);
        } catch (CarrierAlreadyExistsException e) {
        }

    }

    /**
     * Returns a copy of the carrier manager map.
     *
     * @return a copy of the carrier manager map
     */
    public Map<String, Carrier> getCarrierManagerCopy() {
        return this.carrierManager.mapCopy();
    }

    /**
     * Returns the total vintage profit.
     *
     * @return the vintage profit
     */
    public Double getVintageProfit() {
        return this.vintageProfit;
    }

    /**
     * Sets the current user to null.
     */
    public void nullCurrentUser() {
        this.currentUser = null;
    }

    /**
     * Retrieves the bills of the current user.
     *
     * @return a string representation of the user's bills
     */
    public String userBills() {
        String ret = "";
        for (int b_key : this.currentUser.getBills().keySet()) {

            Bill b = this.currentUser.getBills().get(b_key);
            ret += b.showBill();

        }
        return ret;
    }

}
