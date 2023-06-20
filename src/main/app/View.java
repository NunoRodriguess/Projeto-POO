package app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class View {
    private Controller _cont = null;
    Scanner scanner = null;

    /**
     * Constructs a View object with the specified controller.
     *
     * @param c The controller associated with the view.
     */
    public View(Controller c) {
        _cont = c;
        scanner = new Scanner(System.in);
    }

    /**
     * Registers a new user by taking input from the user.
     * The user is prompted to enter their name, email, password, address, and NIF.
     * The entered information is then passed to the controller for user
     * registration.
     * If the email is already in use, an error message is displayed.
     * If there is an input mismatch exception, it is caught and not handled.
     */
    private void registerUser() {

        try {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            System.out.print("Enter your email: ");
            String email = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
            System.out.print("Enter your address: ");
            String address = scanner.nextLine();
            System.out.print("Enter your NIF: ");
            int nif = scanner.nextInt();
            scanner.nextLine(); // consume the newline character
            _cont.registerUser(email, name, address, nif, password);

        } catch (UserAlreadyExistsException e) {
            System.out.print("Email already in use.\n");
            scanner.nextLine(); // consume the newline character
        } catch (InputMismatchException e) {
            System.out.print("Wrong Input.\n");
            scanner.nextLine(); // consume the newline character
        }

    }

    /**
     * Performs the login process by taking input from the user.
     * The user is prompted to enter their email and password.
     * The entered information is then passed to the controller for login
     * authentication.
     * If the user's password is incorrect, a corresponding error message is
     * displayed.
     * If no user with the provided credentials is found, an error message is
     * displayed.
     * Exceptions related to login authentication are caught and handled.
     */
    public void doLogin() {
        System.out.flush();
        System.out.print("Login page\n");
        System.out.print("\n");
        System.out.print("\n");

        try {
            System.out.print("Enter your email: ");
            String email_login = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password_login = scanner.nextLine();
            _cont.login(email_login, password_login);

        } catch (MissedIdException e) {
            System.out.print("This User's password is incorrect.\n");
            scanner.nextLine();
        } catch (NullPointerException e) {
            System.out.print("No user with those credentials!\n");
            scanner.nextLine();
        }

    }

    /**
     * Performs the registration of an item by taking input from the user.
     * The user is prompted to choose the type of item they want to register (bag,
     * t-shirt, sneaker, or store-purchased item).
     * Based on the user's choice, specific information related to the chosen item
     * type is requested.
     * The entered information is then passed to the controller for item
     * registration.
     * Exceptions related to item registration are caught and handled.
     */

    private void registerItem() {
        String des, brand, carrier, date, type, color, Tsizes, pattern, material, premium;
        double price, score;
        int size, itemId, width, height, depth, dimension;

        try {
            _cont.getCurrentUser();

            // ....
            System.out.flush();
            System.out.print("Choose the Item you want to register.\n");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print("b: Bag:\n");
            System.out.print("t: Tshirt:\n");
            System.out.print("s: Sneaker:\n");
            System.out.print("a: If the item was purchased at the store:\n");
            String item_regist = scanner.nextLine();
            switch (item_regist) {
                case "b":
                    System.out.print("Enter Bag description:");
                    des = scanner.nextLine();
                    System.out.print("Enter Bag brand:");
                    brand = scanner.nextLine();
                    System.out.print("Enter Bag Base Price:");
                    price = scanner.nextDouble();
                    System.out.print(
                            "Please rate the condition of the item on a scale of 1 to 5, where 5 means the item is still in its original packaging:");
                    score = scanner.nextDouble();
                    System.out.print("Enter Bag dimension:");
                    System.out.print("Width in cm:");
                    width = scanner.nextInt();
                    System.out.print("Height in cm:");
                    height = scanner.nextInt();
                    System.out.print("Depth in cm:");
                    depth = scanner.nextInt();
                    material = scanner.nextLine();
                    dimension = width * height * depth;
                    System.out.print("Enter Bag material:");
                    material = scanner.nextLine();
                    System.out.print("Enter Bag Release Date (yyyy-mm-dd):");
                    date = scanner.nextLine();
                    System.out.print("Is this a Premium Item ? ('y' for yes or 'n' for no)");
                    premium = scanner.nextLine();
                    System.out.print("Choose one of the following Carrier's:");
                    System.out.print("\n");
                    System.out.print(_cont.displayCarriers(premium));
                    System.out.print("\n");
                    carrier = scanner.nextLine();
                    System.out.print("\n");
                    System.out.print("\n");
                    System.out.print("\n");
                    _cont.registItemBag(des, brand, price,
                            carrier, score,
                            dimension, material, Util.toDate(date), premium);
                    break;
                // ------------------------------------------------------------------------------------------
                case "t":
                    System.out.print("Enter Tshirt description:");
                    des = scanner.nextLine();
                    System.out.print("Enter Tshirt brand:");
                    brand = scanner.nextLine();
                    System.out.print("Enter Tshirt Base Price:");
                    price = scanner.nextDouble();
                    System.out.print(
                            "Please rate the condition of the item on a scale of 1 to 5, where 5 means the item is still in its original packaging:");
                    score = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Tshirt Size(S, M, L, XL):");
                    Tsizes = scanner.nextLine();
                    System.out.print("Enter Tshirt Pattern (Smooth, Stripes, PalmTrees):");
                    pattern = scanner.nextLine();
                    System.out.print("Choose one of the following Carrier's:");
                    System.out.print(_cont.displayCarriers("n"));
                    carrier = scanner.nextLine();
                    System.out.print("\n");
                    System.out.print("\n");
                    System.out.print("\n");
                    _cont.registItemTshirt(des, brand, price,
                            carrier, score,
                            Util.toTshirtSize(Tsizes), Util.toTshirtPattern(pattern));
                    break;
                // ------------------------------------------------------------------------------------------
                case "s":
                    System.out.print("Enter Sneaker description:");
                    des = scanner.nextLine();
                    System.out.print("Enter Sneaker brand:");
                    brand = scanner.nextLine();
                    System.out.print("Enter Sneaker Base Price:");
                    price = scanner.nextDouble();
                    System.out.print(
                            "Please rate the condition of the item on a scale of 1 to 5, where 5 means the item is still in its original packaging:");
                    score = scanner.nextDouble();
                    System.out.print("Enter Sneaker Size (Eur):");
                    size = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Sneaker Type(LACES, NOLACES):");
                    type = scanner.nextLine();
                    System.out.print("Enter Sneaker Color:");
                    color = scanner.nextLine();
                    System.out.print("Enter Sneaker Release Date (yyyy-mm-dd):");
                    date = scanner.nextLine();
                    System.out.print("Is this a Premium Item ? ('y' for yes or 'n' for no)");
                    premium = scanner.nextLine();
                    System.out.print("Choose one of the following Carrier's:");
                    System.out.print("\n");
                    System.out.print(_cont.displayCarriers(premium));
                    carrier = scanner.nextLine();
                    System.out.print("\n");
                    System.out.print("\n");
                    System.out.print("\n");
                    _cont.registItemSneaker(des, brand, price,
                            carrier, score,
                            size, Util.toSneakerType(type), color, Util.toDate(date), premium);
                    break;
                case "a":
                    System.out.print("System Items:");
                    System.out.print("\n");
                    System.out.print(this._cont.getCurrentUserSystemItems());
                    System.out.print("\n");
                    System.out.print("\n");
                    System.out.print("Choose the id of the item you wish to list:\n");
                    itemId = scanner.nextInt();
                    _cont.listSystemItem(itemId);
                    break;
            }
        } catch (NullPointerException e) {
            System.out.print("No such Carrier!\n");
            scanner.nextLine();
        } catch (UserIsAdminException e) {
            System.out.print("Admin cannot register a item!\n");
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.print("The input was not valid!\n");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.print("The input was not valid!\n");
            scanner.nextLine();
        } catch (DateTimeParseException e) {
            System.out.print("invalid Date Format!\n");
            scanner.nextLine();
        }
    }

    /**
     * Performs the registration of an order by taking input from the user.
     * The user is prompted to choose the items they want to order by entering their
     * IDs.
     * The entered item IDs are then passed to the controller for order placement.
     * Exceptions related to order registration are caught and handled.
     */
    private void registerOrder() {
        try {
            System.out.print("Choose the Item you want to register.\n");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print(this._cont.showListedItems());
            System.out.print("\n");
            System.out.print("Type the ID's of the items you wish to order separated by a comma\n");
            System.out.print("\n");
            System.out.print("\n");
            String items_list = scanner.nextLine();
            this._cont.placeOrder(Util.toLinkedList(items_list));
        } catch (UserIsAdminException e) {
            System.out.println("Admin cannot make an order!\n");
            scanner.nextLine();
        } catch (InvalidId e) {
            System.out.println("Invalid Id of the item\n");
            scanner.nextLine();
        } catch (NumberFormatException e) {
            System.out.println("Id numbers only!\n");
            scanner.nextLine();
        }

    }

    /**
     * Displays a menu to change an existing carrier's information.
     * Prompts the user to enter the carrier name, commission for small, medium, and
     * big orders,
     * and passes the values to the controller to update the carrier.
     * Handles exceptions related to carrier changes.
     */
    private void changeCarrierMenu() {
        try {
            System.out.print("Let´s change an existing carrier!\n");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print(_cont.showAllCarriers());
            System.out.print("Insert the carrier Name: ");
            String name = scanner.nextLine();
            System.out.print("\n");
            System.out.print("Insert the carrier commission for small orders:");
            double taxSmall = scanner.nextDouble();
            System.out.print("\n");
            System.out.print("Insert the carrier commission for medium orders:");
            double taxMedium = scanner.nextDouble();
            System.out.print("\n");
            System.out.print("Insert the carrier commission for big orders:");
            double taxBig = scanner.nextDouble();

            _cont.changeCarrier(name, taxSmall, taxMedium, taxBig);
        } catch (NullPointerException e) {
            System.out.println("Carrier is not in the database!\n");
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.print("Wrong Input type\n");
            scanner.nextLine();
        }

    }

    /**
     * Displays a menu to add a new carrier to the system.
     * Prompts the user to enter the carrier name, whether it is a premium carrier,
     * and commission for small, medium, and big orders.
     * Passes the entered values to the controller to register the new carrier.
     * Handles exceptions related to carrier registration.
     */
    private void addCarrierMenu() {
        try {
            System.out.print("Let´s add a carrier to the system!\n");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print("Insert the carrier Name: ");
            String name = scanner.nextLine();
            System.out.print("Is it a premium Carrier ? ('y' for yes): ");
            String premium = scanner.nextLine();
            System.out.print("\n");
            System.out.print("Insert the carrier commission for small orders:");
            double taxSmall = scanner.nextDouble();
            System.out.print("\n");
            System.out.print("Insert the carrier commission for medium orders:");
            double taxMedium = scanner.nextDouble();
            System.out.print("\n");
            System.out.print("Insert the carrier commission for big orders:");
            double taxBig = scanner.nextDouble();
            _cont.registCarrier(name, taxSmall, taxMedium, taxBig, premium);

        } catch (InputMismatchException e) {
            System.out.print("Wrong Input type\n");
            scanner.nextLine();
        } catch (CarrierAlreadyExistsException e) {
            System.out.println("Carrier already in the database!\n");
            scanner.nextLine();
        }

    }

    /**
     * Displays a menu for executing queries.
     * Prompts the user to choose a query option and input any required parameters.
     * Passes the query and parameters to the controller for execution.
     * Displays the query results.
     */
    private void querierMenu() {
        try {
            System.out.print("Querier Menu! Type one of the following numbers to execute a query!\n");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print("1.Check which user has made the highest amount of money of all time\n");
            System.out.print("2.Check which user made the highest amount of money within a specific time frame\n");
            System.out.print("3.Check which carrier has made the highest amount of money\n");
            System.out.print("4.Check a User's Emitted Orders\n");
            System.out.print("5.Check the list of Sellers in a time frame\n");
            System.out.print("6.Check the list of Spenders in a time frame\n");
            System.out.print("7.Check Vintage's Profit\n");
            int query = scanner.nextInt();
            scanner.nextLine();
            int id = 0;
            String date1, date2;
            date1 = date2 = "1999-01-01";
            if (query == 4) {
                System.out.print("Insert User's ID: ");
                id = scanner.nextInt();
                scanner.nextLine();
            }
            if (query == 2) {
                System.out.print("Choose a Time Frame\n");
                System.out.print("First date:");
                date1 = scanner.nextLine();
                System.out.print("\n");
                System.out.print("Second date:");
                date2 = scanner.nextLine();
            }
            if (query == 5) {
                System.out.print("Choose a Time Frame\n");
                System.out.print("First date:");
                date1 = scanner.nextLine();
                System.out.print("\n");
                System.out.print("Second date:");
                date2 = scanner.nextLine();
            }
            if (query == 6) {
                System.out.print("Choose a Time Frame\n");
                System.out.print("First date:");
                date1 = scanner.nextLine();
                System.out.print("\n");
                System.out.print("Second date:");
                date2 = scanner.nextLine();
            }
            String out = _cont.querrierExecution(query, Util.toDate(date1), Util.toDate(date2), id);
            System.out.println(out);
            scanner.nextLine();
        } catch (NullPointerException e) {
        }
    }

    /**
     * Displays the current orders of the logged-in user.
     * Prompts the user if they wish to return an order.
     * Takes the order ID as input and passes it to the controller to process the
     * return.
     */
    private void checkOrder() {
        try {
            System.out.print(_cont.getCurrentUser().getName() + "'s current Orders");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print(this._cont.getCurrentUserAllOrders());
            System.out.print("\n");
            System.out.print("\n");
            System.out.print("Do you wish to return an order? (y for yes)\n");
            String option = scanner.nextLine();
            if (option.equals("y")) {

                System.out.print("Enter the Order's ID:");
                int orderId = scanner.nextInt();
                _cont.returnOrderId(orderId);
                scanner.nextLine();
            }
        } catch (UserIsAdminException e) {
            System.out.println("Admin dont have orders!\n");
            scanner.nextLine();

        } catch (OrderNotReturnable e) {
            System.out.print("This Order cannot be returned!\n");
            scanner.nextLine();

        } catch (InputMismatchException e) {
            System.out.print("Enter the Order's ID!\n");
            scanner.nextLine();

        }
    }

    /**
     * Displays the listed and system items of the logged-in user.
     * Note: This method assumes the existence of a `getCurrentUser()` method in the
     * controller.
     */
    private void checkMyItems() {
        try {
            System.out.print(_cont.getCurrentUser().getName() + "'s Listed Items");
            System.out.print("\n");
            System.out.print(this._cont.getCurrentUserListedItems());
            System.out.print("\n");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print(_cont.getCurrentUser().getName() + "'s System Items");
            System.out.print("\n");
            System.out.print(this._cont.getCurrentUserSystemItems());
            System.out.print("\n");
            scanner.nextLine();
        } catch (UserIsAdminException e) {
            System.out.println("Admin doesn't have items\n");
        }
    }

    /**
     * Displays the current bills of the logged-in user.
     * Prints the user's name followed by their current bills.
     * If the logged-in user is an admin, it displays a message indicating that
     * admins don't have bills.
     */
    private void checkBills() {
        try {
            System.out.print(_cont.getCurrentUser().getName() + "' current Bills");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print(this._cont.currentUserBills()); //
            System.out.print("\n");
            System.out.print("\n");
            scanner.nextLine();
        } catch (UserIsAdminException e) {
            System.out.println("Admin doesn't have bills!\n");
        }
    }

    /**
     * Allows the user to skip time in the simulation by advancing the date to a
     * future date.
     * Prompts the user to enter a date in the format yyyy-mm-dd.
     * Passes the date to the controller to update the simulation date.
     */
    private void skipTime() {
        try {
            System.out.print("This is the current date of the Simulation:" + _cont.accessDate() + "\n");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print("\n");
            System.out.print("Choose a date in the future to advance the simulation!\n");
            System.out.print("Format: yyyy-mm-dd\n");
            String date = scanner.nextLine();
            _cont.advanceTime(Util.toDate(date));
            System.out.print("\n");
            System.out.print("Date updated!\n");
        } catch (IllegalArgumentException e) {
            System.out.print("Invalid Date!\n");
        } catch (DateTimeParseException e) {
            System.out.print("Invalid Date!\n");
        }
    }

    /**
     * Displays the main menu and handles user input and navigation through the
     * Vintage system.
     * The behavior of the menu depends on whether a user is logged in or not, and
     * whether the logged-in user is an admin or not.
     */
    public void mainMenu() {
        boolean quit = false;

        while (!quit) {
            System.out.flush();
            try {
                _cont.getCurrentUser();
                System.out.print("Welcome to Vintage!\n");
                System.out.print("\n");
                System.out.print("\n");
                System.out.print("\n");
                System.out.print("\n");
                System.out.print("l: Login to another User:\n");
                System.out.print("u: Register a User:\n");
                System.out.print("o: Make a order:\n");
                System.out.print("i: Register a Item:\n");
                System.out.print("c: To check orders:\n");
                System.out.print("t: To skip time:\n");
                System.out.print("b: To check bills:\n");
                System.out.print("m: To check my items:\n");
                System.out.print("x: Logout:\n");
                System.out.print("\n");
                System.out.print("\n");
                System.out.print("Press 'q' to Quit\n");
                System.out.print("User: " + _cont.getCurrentUser().getName());
                System.out.print("\n");
                System.out.print("\n");
                String option = scanner.nextLine();

                switch (option) {
                    case "q":

                        try {
                            _cont.save();
                        } catch (FileNotFoundException e) {
                            System.out.println("Save File not Found");
                        } catch (IOException e) {
                            System.out.println("Error Accessing File");
                        }

                        quit = true;
                        break;

                    case "u":
                        registerUser();
                        break;

                    case "l":
                        doLogin();
                        break;

                    case "i":
                        registerItem();
                        break;

                    case "o":
                        registerOrder();
                        break;

                    case "c":
                        checkOrder();
                        break;
                    case "t":
                        skipTime();
                        break;

                    case "b":
                        checkBills();
                        break;
                    case "m":
                        checkMyItems();
                        break;
                    case "x":
                        _cont.logout();

                        break;
                }
            } catch (NullPointerException e) {
                System.out.print("Welcome to Vintage!\n");
                System.out.print("\n");
                System.out.print("\n");
                System.out.print("\n");
                System.out.print("\n");
                System.out.print("l: Login:\n");
                System.out.print("u: Register a User:\n");
                System.out.print("t: To skip time:\n");
                System.out.print("s: To execute a simulation (will require a .txt file and a path):\n");
                System.out.print("\n");
                System.out.print("No user logged in.");
                System.out.print("\n");
                System.out.print("Press 'q' to Quit\n");
                String option = scanner.nextLine();

                switch (option) {
                    case "q":

                        try {
                            _cont.save();
                        } catch (FileNotFoundException esc) {
                            System.out.println("Save File not Found!");
                        } catch (IOException esc) {
                            System.out.println("Error Acessing File!");
                        }

                        quit = true;
                        break;

                    case "u":
                        registerUser();
                        break;

                    case "l":
                        doLogin();
                        break;
                    case "t":
                        skipTime();
                        break;
                    case "s":
                        try {
                            System.out.print("Write the path for the simulation file: ");
                            String path = scanner.nextLine();
                            _cont.simulation(path);
                        } catch (FileNotFoundException esc) {
                            System.out.println("Error reaching .txt File!");
                        } catch (IOException esc) {
                            System.out.println("Error reading .txt File!");
                        } catch (InvalidCommand esc) {
                            System.out.println(esc.getMessage()); // prints the custom error message
                        } catch (DateTimeParseException esc) {
                            System.out.print("Invalid Date!\n");
                        }
                        break;

                }
            } catch (UserIsAdminException e) {
                System.out.print("Welcome to Vintage Special Admin Menu!\n");
                System.out.print("\n");
                System.out.print("\n");
                System.out.print("\n");
                System.out.print("\n");
                System.out.print("l: Login to a diffrent User:\n");
                System.out.print("u: Register a User:\n");
                System.out.print("t: To skip time:\n");
                System.out.print("r: To add Carriers:\n");
                System.out.print("c: To change Carriers:\n");
                System.out.print("a: To access the system Queries:\n");
                System.out.print("x: Logout:\n");
                System.out.print("\n");
                System.out.print("No user logged in.");
                System.out.print("\n");
                System.out.print("Press 'q' to Quit\n");
                String option = scanner.nextLine();
                switch (option) {
                    case "q":

                        try {
                            _cont.save();
                        } catch (FileNotFoundException esc) {
                            System.out.println("Save File not Found!");
                        } catch (IOException esc) {
                            System.out.println("Error Acessing File!");
                        }

                        quit = true;
                        break;

                    case "u":

                        registerUser();
                        break;

                    case "l":

                        doLogin();

                        break;

                    case "t":

                        skipTime();

                        break;

                    case "r":

                        addCarrierMenu();

                        break;
                    case "c":

                        changeCarrierMenu();

                        break;
                    case "x":
                        _cont.logout();

                        break;
                    case "a":

                        querierMenu();

                        break;
                }

            }

        }
    }

}
