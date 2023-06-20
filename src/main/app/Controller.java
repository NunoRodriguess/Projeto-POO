package app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.time.format.DateTimeParseException;

public class Controller {

    private Model m;

    /**
     * Constructs a Controller object.
     *
     * @param m the Model instance
     */
    public Controller(Model m) {
        this.m = m;
    }

    /**
     * Retrieves the current user.
     *
     * @return the current user
     * @throws NullPointerException if the current user is null
     * @throws UserIsAdminException if the current user is an admin
     */
    public User getCurrentUser() throws NullPointerException, UserIsAdminException {
        return this.m.CurrentUser();
    }

    /**
     * Logs in the user with the given email and password.
     *
     * @param email    the email of the user
     * @param password the password of the user
     * @throws NullPointerException if the email or password is null
     * @throws MissedIdException    if the login fails
     */
    public void login(String email, String password) throws NullPointerException, MissedIdException {

        m.loginModel(email, password);
    }

    /**
     * Logs out the current user.
     */
    public void logout() {
        m.nullCurrentUser();
    }

    /**
     * Executes the parsing of a command and performs the corresponding actions
     * based on the input.
     *
     * @param buffer the command to parse
     * @param line   the line number of the command in the input file
     * @throws InvalidCommand           if the command is invalid or malformed
     * @throws IllegalArgumentException if an argument is invalid
     */
    private void parserExecuter(String buffer, int line) throws InvalidCommand, IllegalArgumentException {
        try {
            String[] substrings = buffer.split(",");
            if (substrings[0].equals("SetupDate")) {
                m.setCurrentDate(Util.toDate(substrings[1]));
                return;
            }

            LocalDate dateParse = Util.toDate(substrings[0]);
            if (dateParse.isAfter(Util.toDate(m.getDate()))) {
                m.TimeSkip(dateParse);
            }

            switch (substrings[1]) {

                case "RegistarUtilizador":
                    String[] arguments = substrings[2].split(";");
                    try {
                        m.registsUser(arguments[0], arguments[1], arguments[2], Integer.parseInt(arguments[3]),
                                arguments[4]);
                    } catch (UserAlreadyExistsException e) {
                        throw new InvalidCommand(substrings[1], line);
                    }
                    break;

                case "Login":
                    String[] arguments1 = substrings[2].split(";");
                    try {
                        m.loginModel(arguments1[0], arguments1[1]);
                    } catch (MissedIdException e) {
                        throw new InvalidCommand(substrings[1], line);
                    } catch (NullPointerException e) {
                        throw new InvalidCommand(substrings[1], line);
                    }
                    break;

                case "RegistarItem":
                    String[] arguments2 = substrings[2].split(";");
                    try {
                        switch (arguments2[0]) {
                            case "Bag":

                                if (arguments2[10].equals("No")) {
                                    double dimension = Double.parseDouble(arguments2[5])
                                            * Double.parseDouble(arguments2[6]) * Double.parseDouble(arguments2[7]);
                                    m.registBag(arguments2[1], arguments2[2], Double.parseDouble(arguments2[3]),
                                            arguments2[11], Double.parseDouble(arguments2[4]) / 5,
                                            dimension, arguments2[8], Util.toDate(arguments2[9]), "n");
                                } else {
                                    double dimension = Double.parseDouble(arguments2[5])
                                            * Double.parseDouble(arguments2[6]) * Double.parseDouble(arguments2[7]);
                                    m.registBag(arguments2[1], arguments2[2], Double.parseDouble(arguments2[3]),
                                            arguments2[10], Double.parseDouble(arguments2[4]) / 5,
                                            dimension, arguments2[8], Util.toDate(arguments2[9]), "y");
                                }
                                break;
                            case "Sneaker":

                                if (arguments2[9].equals("No")) {

                                    m.registSneaker(arguments2[1], arguments2[2], Double.parseDouble(arguments2[3]),
                                            arguments2[10], Double.parseDouble(arguments2[4]) / 5,
                                            Double.parseDouble(arguments2[5]), Util.toSneakerType(arguments2[6]),
                                            arguments2[7], Util.toDate(arguments2[8]), "n");
                                } else {
                                    m.registSneaker(arguments2[1], arguments2[2], Double.parseDouble(arguments2[3]),
                                            arguments2[10], Double.parseDouble(arguments2[4]) / 5,
                                            Double.parseDouble(arguments2[5]), Util.toSneakerType(arguments2[6]),
                                            arguments2[7], Util.toDate(arguments2[8]), "y");
                                }

                                break;
                            case "Tshirt":

                                m.registTshirt(arguments2[1], arguments2[2], Double.parseDouble(arguments2[3]),
                                        arguments2[7], Double.parseDouble(arguments2[4]) / 5,
                                        Util.toTshirtSize(arguments2[5]), Util.toTshirtPattern(arguments2[6]));

                                break;

                            default:
                                throw new InvalidCommand(substrings[1], line);

                        }
                    } catch (NullPointerException e) {
                        throw new InvalidCommand(substrings[1], line);
                    }
                    break;
                case "RegistarTransportadora":
                    String[] arguments3 = substrings[2].split(";");
                    try {
                        if (arguments3[1].equals("No"))
                            m.addCarrier(arguments3[0], Double.parseDouble(arguments3[2]),
                                    Double.parseDouble(arguments3[3]), Double.parseDouble(arguments3[4]), "n");
                        else {
                            m.addCarrier(arguments3[0], Double.parseDouble(arguments3[2]),
                                    Double.parseDouble(arguments3[3]), Double.parseDouble(arguments3[4]), "y");
                        }

                    } catch (CarrierAlreadyExistsException e) {

                        throw new InvalidCommand(substrings[1], line);
                    }

                    break;
                case "FazerEncomenda":
                    try {
                        m.makeOrder(Util.toLinkedListParser(substrings[2]));
                    } catch (InvalidId e) {
                        throw new InvalidCommand(substrings[1], line);
                    }
                    break;
                case "AlterarTransportadora":
                    String[] arguments4 = substrings[2].split(";");
                    try {

                        changeCarrier(arguments4[0], Double.parseDouble(arguments4[1]),
                                Double.parseDouble(arguments4[2]), Double.parseDouble(arguments4[3]));

                    } catch (NullPointerException e) {

                        throw new InvalidCommand(substrings[1], line);
                    }

                    break;
                case "PassarTempo":

                    break;

                default:
                    throw new InvalidCommand(substrings[1], line);

            }
        } catch (IllegalArgumentException e) {
            throw new InvalidCommand("Unidentified", line);
        } catch (DateTimeParseException e) {
            throw new InvalidCommand("Unidentified", line);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommand("Unidentified", line);
        }

    }

    /**
     * Executes the simulation based on the commands in the given file.
     *
     * @param path the path of the file containing the commands
     * @throws FileNotFoundException    if the file is not found
     * @throws IOException              if an I/O error occurs
     * @throws InvalidCommand           if an invalid command is encountered
     * @throws IllegalArgumentException if an argument is invalid
     */

    public void simulation(String path)
            throws FileNotFoundException, IOException, InvalidCommand, IllegalArgumentException {
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        int line = 1;
        while ((st = br.readLine()) != null) {
            if (Util.checkIgnore(st)) {
                parserExecuter(st, line);
            }
            line++;
        }
        br.close();

    }

    /**
     * Registers a bag item.
     *
     * @param description    the description of the bag
     * @param brand          the brand of the bag
     * @param basePrice      the base price of the bag
     * @param carrier        the carrier of the bag
     * @param conditionScore the condition score of the bag
     * @param dimension      the dimension of the bag
     * @param material       the material of the bag
     * @param releaseDate    the release date of the bag
     * @param premium        the premium status of the bag
     * @throws UserIsAdminException if the user is an admin
     * @throws NullPointerException if any argument is null
     */
    public void registItemBag(String description, String brand, double basePrice,
            String carrier, double conditionScore, double dimension,
            String material, LocalDate releaseDate, String premium) throws UserIsAdminException, NullPointerException {

        m.registBag(description, brand, basePrice,
                carrier, conditionScore, dimension, material, releaseDate,
                premium);
    }

    /**
     * Registers a T-shirt item.
     *
     * @param description    the description of the T-shirt
     * @param brand          the brand of the T-shirt
     * @param basePrice      the base price of the T-shirt
     * @param carrier        the carrier of the T-shirt
     * @param conditionScore the condition score of the T-shirt
     * @param size           the size of the T-shirt
     * @param pattern        the pattern of the T-shirt
     * @throws UserIsAdminException if the user is an admin
     * @throws NullPointerException if any argument is null
     */
    public void registItemTshirt(String description, String brand, double basePrice,
            String carrier, double conditionScore,
            Tshirt.TshirtSize size, Tshirt.TshirtPattern pattern) throws UserIsAdminException, NullPointerException {
        m.registTshirt(description, brand, basePrice, carrier,
                conditionScore, size, pattern);
    }

    /**
     * Registers a sneaker item.
     *
     * @param description    the description of the sneaker
     * @param brand          the brand of the sneaker
     * @param basePrice      the base price of the sneaker
     * @param carrier        the carrier of the sneaker
     * @param conditionScore the condition score of the sneaker
     * @param size           the size of the sneaker
     * @param type           the type of the sneaker
     * @param color          the color of the sneaker
     * @param releaseDate    the release date of the sneaker
     * @param premium        the premium status of the sneaker
     * @throws UserIsAdminException if the user is an admin
     * @throws NullPointerException if any argument is null
     */
    public void registItemSneaker(String description, String brand, double basePrice,
            String carrier, double conditionScore,
            double size, Sneaker.SneakerType type, String color, LocalDate releaseDate, String premium)
            throws UserIsAdminException, NullPointerException {
        m.registSneaker(description, brand, basePrice,
                carrier, conditionScore, size, type, color, releaseDate, premium);
    }

    /**
     * Registers a new user.
     *
     * @param email    The email of the user.
     * @param name     The name of the user.
     * @param address  The address of the user.
     * @param nif      The NIF (tax identification number) of the user.
     * @param password The password of the user.
     * @throws UserAlreadyExistsException If the user already exists.
     */
    public void registerUser(String email, String name, String address, int nif, String password)
            throws UserAlreadyExistsException {

        this.m.registsUser(email, name, address, nif, password);

    }

    /**
     * Advances the time in the system to the specified date.
     *
     * @param date The new date to set.
     * @throws IllegalArgumentException If the date is invalid or in the past.
     */
    public void advanceTime(LocalDate date) throws IllegalArgumentException {

        m.TimeSkip(date);
    }

    /**
     * Places an order with the given list of item IDs.
     *
     * @param order The list of item IDs to include in the order.
     * @throws UserIsAdminException If the user is an admin and cannot place orders.
     * @throws InvalidId            If any of the item IDs is invalid.
     */
    public void placeOrder(List<Integer> order) throws UserIsAdminException, InvalidId {

        m.makeOrder(order);

    }

    /**
     * Displays carriers based on the premium status.
     *
     * @param premium The premium status of the carriers to display.
     * @return A string representation of the carriers.
     */
    public String displayCarriers(String premium) {
        return m.showCarriers(premium);
    }

    /**
     * Displays all carriers.
     *
     * @return A string representation of all carriers.
     */
    public String showAllCarriers() {
        return m.displayAllCarriers();
    }

    /**
     * Displays the listed items.
     *
     * @return A string representation of the listed items.
     * @throws UserIsAdminException If the user is an admin and cannot view the
     *                              listed items.
     */
    public String showListedItems() throws UserIsAdminException {

        return m.displayListedItems();
    }

    /**
     * Displays the listed items of the current user.
     *
     * @return A string representation of the listed items of the current user.
     * @throws UserIsAdminException If the user is an admin and cannot view their
     *                              listed items.
     */
    public String getCurrentUserListedItems() throws UserIsAdminException {

        return m.currentUserListedItems();
    }

    /**
     * Displays the system items of the current user.
     *
     * @return A string representation of the system items of the current user.
     * @throws UserIsAdminException If the user is an admin and cannot view their
     *                              system items.
     */
    public String getCurrentUserSystemItems() throws UserIsAdminException {

        return m.currentUserSystemItems();
    }

    /**
     * Lists a system item with the given item ID.
     *
     * @param item_id The ID of the item to list.
     * @throws UserIsAdminException If the user is an admin and cannot list system
     *                              items.
     * @throws NullPointerException If the item ID is null.
     */
    public void listSystemItem(int item_id) throws UserIsAdminException, NullPointerException {

        m.alterItemState(item_id);

    }

    /**
     * Displays all orders of the current user.
     *
     * @return A string representation of all orders of the current user.
     * @throws UserIsAdminException If the user is an admin and cannot view their
     *                              orders.
     */
    public String getCurrentUserAllOrders() throws UserIsAdminException {

        return m.checkThisUserOrders();

    }

    /**
     * Retrieves the current system date.
     *
     * @return The current system date as a string.
     */
    public String accessDate() {
        return m.getDate();
    }

    /**
     * Retrieves the bills of the current user.
     *
     * @return A string representation of the bills of the current user.
     */
    public String currentUserBills() {
        return m.userBills();
    }

    /**
     * Returns an order with the given order ID.
     *
     * @param orderId The ID of the order to return.
     * @throws UserIsAdminException If the user is an admin and cannot return
     *                              orders.
     * @throws OrderNotReturnable   If the order is not returnable.
     */
    public void returnOrderId(int orderId) throws UserIsAdminException, OrderNotReturnable {

        m.deleteOrder(orderId);

    }

    /**
     * Saves the current state of the system to a file.
     *
     * @throws FileNotFoundException If the file cannot be found.
     * @throws IOException           If an I/O error occurs.
     */
    public void save() throws FileNotFoundException, IOException {
        this.m.save("data.ser");
        SystemDate.save("date.ser");
    }

    /**
     * Loads the system state from a file.
     *
     * @throws FileNotFoundException  If the file cannot be found.
     * @throws IOException            If an I/O error occurs.
     * @throws ClassNotFoundException If the class of a serialized object cannot be
     *                                found.
     */
    public void load() throws FileNotFoundException, IOException, ClassNotFoundException {
        this.m = Model.load("data.ser");
        SystemDate.load("date.ser");
    }

    /**
     * Returns a string representation of the controller.
     *
     * @return A string representation of the controller.
     */
    @Override
    public String toString() {
        return m.toString();
    }

    /**
     * Registers a new carrier.
     *
     * @param name      The name of the carrier.
     * @param taxSmall  The tax rate for small packages.
     * @param taxMedium The tax rate for medium packages.
     * @param taxBig    The tax rate for big packages.
     * @param premium   The premium status of the carrier.
     * @throws CarrierAlreadyExistsException If the carrier already exists.
     */
    public void registCarrier(String name, double taxSmall, double taxMedium, double taxBig, String premium)
            throws CarrierAlreadyExistsException {

        m.addCarrier(name, taxSmall, taxMedium, taxBig, premium);

    }

    /**
     * Changes the tax rates for a carrier.
     *
     * @param name      The name of the carrier.
     * @param taxSmall  The new tax rate for small packages.
     * @param taxMedium The new tax rate for medium packages.
     * @param taxBig    The new tax rate for big packages.
     * @throws NullPointerException     If the carrier name is null.
     * @throws CarrierNotFoundException If the carrier is not found.
     */
    public void changeCarrier(String name, double taxSmall, double taxMedium, double taxBig)
            throws NullPointerException {
        m.changeCarrier(name, taxSmall, taxMedium, taxBig);
    }

    /**
     * Executes the specified querrier query.
     *
     * @param query  The query number.
     * @param date1  The start date for time frame queries.
     * @param date2  The end date for time frame queries.
     * @param userID The user ID for user-related queries.
     * @return The result of the querrier query.
     * @throws NullPointerException If there is a null reference while executing the
     *                              query.
     */
    @SuppressWarnings("unchecked")
    public String querrierExecution(int query, LocalDate date1, LocalDate date2, int userID)
            throws NullPointerException {
        String result = "";
        Querier querier;
        switch (query) {
            case (1):
                querier = new BiggestEarnerAllTime(m.getUserManagerCopy());
                User u = (User) querier.execute();
                result = u.toString();
                break;
            case (2):
                querier = new BiggestEarnerAllTimeFrame(m.getUserManagerCopy(), date1, date2);
                User u2 = (User) querier.execute();
                result = u2.toString();
                break;
            case (3):
                querier = new BiggestCarrier(m.getCarrierManagerCopy());
                Carrier c = (Carrier) querier.execute();
                result = c.toString();
                break;
            case (4):
                querier = new EmmitedOrderList(m.getUserManagerCopy(), userID);
                List<Order> l = (LinkedList<Order>) querier.execute();
                result = l.toString();
                break;
            case (5):
                querier = new PodiumSeller(m.getUserManagerCopy(), date1, date2);
                List<User> l1 = (ArrayList<User>) querier.execute();
                result = l1.toString();
                break;
            case (6):
                querier = new PodiumSpenders(m.getUserManagerCopy(), date1, date2);
                List<User> l2 = (ArrayList<User>) querier.execute();
                result = l2.toString();
                break;
            case (7):
                querier = new VintageProfit(m.getVintageProfit());
                double d = (double) querier.execute();
                result = String.format("%f", d);
                break;
        }
        return result;
    }

}