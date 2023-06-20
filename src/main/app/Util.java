package app;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class containing various static methods for common operations.
 */
public class Util {

    /**
     * Converts a string value to a LocalDate object.
     *
     * @param value the string representing the date
     * @return the converted LocalDate object
     * @throws IllegalArgumentException if the value is invalid
     * @throws DateTimeParseException   if the value cannot be parsed into a
     *                                  LocalDate
     */
    public static LocalDate toDate(String value) throws IllegalArgumentException, DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(value, formatter);
        return date;
    }

    /**
     * Converts a string value to a TshirtSize enum.
     *
     * @param value the string representing the Tshirt size
     * @return the converted TshirtSize enum
     * @throws IllegalArgumentException if the value is invalid
     */
    public static Tshirt.TshirtSize toTshirtSize(String value) throws IllegalArgumentException {
        Tshirt.TshirtSize result = null;
        try {
            result = Tshirt.TshirtSize.valueOf(value);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Tshirt Size must be one of the following: S, M, L, XL\n");
        }
        return result;
    }

    /**
     * Converts a string value to a TshirtPattern enum.
     *
     * @param value the string representing the Tshirt pattern
     * @return the converted TshirtPattern enum
     * @throws IllegalArgumentException if the value is invalid
     */
    public static Tshirt.TshirtPattern toTshirtPattern(String value) throws IllegalArgumentException {
        Tshirt.TshirtPattern result = null;
        try {
            result = Tshirt.TshirtPattern.valueOf(value);
        } catch (Exception ex) {
            throw new IllegalArgumentException(
                    "Tshirt Pattern must be one of the following: Smooth, Stripes, PalmTrees\n");
        }
        return result;
    }

    /**
     * Converts a string value to a SneakerType enum.
     *
     * @param value the string representing the sneaker type
     * @return the converted SneakerType enum
     * @throws IllegalArgumentException if the value is invalid
     */
    public static Sneaker.SneakerType toSneakerType(String value) throws IllegalArgumentException {
        Sneaker.SneakerType result = null;
        try {
            result = Sneaker.SneakerType.valueOf(value);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Sneaker must be one of the following: LACES, NOLACES\n");
        }
        return result;
    }

    /**
     * Converts a comma-separated string to a LinkedList of integers.
     *
     * @param input the comma-separated string
     * @return the converted LinkedList of integers
     */
    public static List<Integer> toLinkedList(String input) {
        String[] parts = input.split(",");
        List<Integer> list = new LinkedList<>();
        for (String part : parts) {
            Integer value = Integer.parseInt(part.trim());
            list.add(value);
        }
        return list;
    }

    /**
     * Converts a semicolon-separated string to a LinkedList of integers.
     *
     * @param input the semicolon-separated string
     * @return the converted LinkedList of integers
     */
    public static List<Integer> toLinkedListParser(String input) {
        String[] parts = input.split(";");
        List<Integer> list = new LinkedList<>();
        for (String part : parts) {
            Integer value = Integer.parseInt(part.trim());
            list.add(value);
        }
        return list;
    }

    /**
     * Checks if a string should be onsidered for ignoring.
     *
     * @param str the string to check
     * @return true if the string should be considered, false otherwise
     */

    public static boolean checkIgnore(String str) {
        if (str == null)
            return false;

        if (str.equals("")) {
            return false;
        }
        if (str.startsWith("//")) {
            return false;
        }

        return true;
    }

    /**
     * Formats the content of a cell with a specified width.
     * The content will be centered within the cell, with padding on both sides.
     *
     * @param content the content to be formatted
     * @param width   the width of the cell
     * @return the formatted content
     */
    public static String formatCell(String content, int width) {
        int contentWidth = content.length();
        int paddingWidth = (width - 2 - contentWidth) / 2; // Subtracting 2 for the left and right borders
        String padding = " ".repeat(paddingWidth);
        return padding + content + padding;
    }

}