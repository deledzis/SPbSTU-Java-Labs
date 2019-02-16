package ru.deledzis.spbstu.labs.lab2;

import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of second lab, 6th variant.
 * Simple and stupid calculator of one operation (+, -, * or /) with two operands.
 *
 * @author Alexander Styagov, SPbSTU
 */

public class Main {

    private static final int ERROR_CODE_WRONG_ARGUMENT = 1;
    private static final int ERROR_CODE_UNKNOWN = 2;

    private static final int BOOK = 1;
    private static final int SHOE = 2;
    private static final int TOY = 3;
    private static final int PICTURE = 4;

    public static void main(String[] args) {
        // printing entire list of enter arguments
        System.out.println("Given next arguments set: ");
        for (String argument : args) {
            System.out.println("[ " + argument + " ]");
        }

        // parsing array size from commandline arguments
        int arraySize = 0;
        try {
            arraySize = Integer.parseInt(args[0]);
        } catch (NumberFormatException ex) {
            handleError("Wrong argument",
                    "Error: wrong argument. Should be an integer.",
                    ERROR_CODE_WRONG_ARGUMENT);
        }

        // this will be used to store interesting us entities from array
        StringBuilder implementsPresentProducts = new StringBuilder();

        Product[] products = new Product[arraySize];
        for (int i = 0; i < products.length; i++) {
            Product product = null;
            // the result class of each product in array will be chosen by the great random
            switch (getRandomIntInRange(1, 4)) {
                case BOOK:
                    product = new Book();
                    break;
                case SHOE:
                    product = new Shoe();
                    break;
                case TOY:
                    product = new Toy();
                    break;
                case PICTURE:
                    product = new Picture();
                    break;
                default:
                    handleError("Random function error",
                            "Some internal error in random function work",
                            ERROR_CODE_UNKNOWN);
                    break;
            }

            if (product != null) {
                if (product instanceof Present) {
                    // instantly executing method
                    ((Present) product).itCanBePresented();
                    // but not instantly printing its name, only storing in variable
                    implementsPresentProducts.append("Product #").append(i).append(" is a ")
                            .append(product.whoAmI()).append('\n');
                }
                products[i] = product;
                System.out.println("Product #" + i + " is a " + product.whoAmI());
            }
        }

        System.out.println("Products that implements Present interface:");
        System.out.println(implementsPresentProducts.toString());
    }

    /**
     * An auxiliary function to get some random integer in given range
     *
     * @param min is a lowest available integer
     * @param max is a biggest available integer
     * @return random integer between [min] and [max] inclusively
     */
    private static int getRandomIntInRange(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * An auxiliary function to log some message and print message to user
     *
     * @param log message that will be logged with @{link Level.WARNING} level
     * @param printMessage is a message that will be printed to user's console
     * @param exitCode is an error code that will be used to exit the application
     */
    private static void handleError(String log, String printMessage, int exitCode) {
        Logger.getGlobal().log(Level.WARNING, log);
        System.out.println(printMessage);
        System.exit(exitCode);
    }
}
