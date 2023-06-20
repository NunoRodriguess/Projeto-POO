package app;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    /**
     * The main entry point of the application.
     * It creates an instance of the Model and Controller classes,
     * loads the saved state from a file if available,
     * and initializes the View to display the main menu.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Model m = new Model();
        Controller c = new Controller(m);

        try {
            c.load();
        } catch (FileNotFoundException e) {
            System.out.println("No Saved state!");
        } catch (IOException e) {
            System.out.println("Could Not reach file!");
        } catch (ClassNotFoundException e) {
        }

        View menu = new View(c);
        menu.mainMenu();
    }
}