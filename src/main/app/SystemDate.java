package app;

import java.io.*;
import java.time.LocalDate;

public class SystemDate implements Serializable {
    private static LocalDate date;

    public static LocalDate getDate() {
        return date;
    }

    public static void setDate(LocalDate newDate) {
        date = newDate;
    }

    /**
     * Saves the current state of the date to a file.
     *
     * @param fileName the name of the file to save the date to
     * @throws FileNotFoundException if the specified file cannot be found
     * @throws IOException           if an I/O error occurs while writing to the
     *                               file
     */
    public static void save(String fileName) throws FileNotFoundException, IOException {
        try (FileOutputStream fs = new FileOutputStream(fileName);
                ObjectOutputStream os = new ObjectOutputStream(fs)) {
            os.writeObject(date);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a previously saved date from a file.
     *
     * @param fileName the name of the file to load the date from
     * @return the loaded date object
     * @throws FileNotFoundException  if the specified file cannot be found
     * @throws IOException            if an I/O error occurs while reading the file
     * @throws ClassNotFoundException if the class of the serialized object cannot
     *                                be found
     */
    public static void load(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        try (FileInputStream fs = new FileInputStream(fileName);
                ObjectInputStream os = new ObjectInputStream(fs)) {
            date = (LocalDate) os.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
