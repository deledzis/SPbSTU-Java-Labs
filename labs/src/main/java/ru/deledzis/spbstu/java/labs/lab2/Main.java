package ru.deledzis.spbstu.java.labs.lab2;

import static ru.deledzis.spbstu.java.labs.utils.UtilsKt.*;

/**
 * Implementation of the second lab, 6th variant.
 * Simple and stupid calculator of one operation (+, -, * or /) with two operands.
 *
 * @author Alexander Styagov, SPbSTU
 */

public class Main {

    /***** ERRORS *****/
    private static final int ERROR_CODE_WRONG_ARGUMENT  = 1;
    private static final int ERROR_CODE_UNKNOWN         = 2;

    /***** CLASSES *****/
    private static final int BOOK                       = 1;
    private static final int SHOE                       = 2;
    private static final int TOY                        = 3;
    private static final int PICTURE                    = 4;

    /***** OTHERS *****/
    private static final int ARGUMENTS_COUNT            = 1;

    //private static final ResourceBundle mResources = ResourcesAccessor.INSTANCE.getResources();

    public static void main(String[] args) {
        // initial check the count of arguments given
        if (args.length != ARGUMENTS_COUNT) {
            logError("wrong arguments. Use following format: [operand] [operator] [operand]",
                    ERROR_CODE_WRONG_ARGUMENT);
        }

        // printing entire list of enter arguments
        log("Given next arguments set:");
        for (String argument : args) {
            log("[ " + argument + " ]");
        }

        // parsing array size from commandline arguments
        int arraySize = 0;
        try {
            arraySize = Integer.parseInt(args[0]);
        } catch (NumberFormatException ex) {
            logError("wrong argument. Should be an integer", ERROR_CODE_WRONG_ARGUMENT);
        }

        // this will be used to store interesting us entities from array
        StringBuilder implementsPresentProducts = new StringBuilder("Products that implements Present interface:")
                .append("\n");

        Product[] products = new Product[arraySize];
        for (int i = 0; i < products.length; i++) {
            Product product = null;
            // the result class of each product in array will be chosen by the great random
            switch (getRandomInt(1, 4)) {
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
                    logError("some internal error in random function work", ERROR_CODE_UNKNOWN);
                    break;
            }
            // at this point, if product is null, program would be terminated already
            assert product != null;

            if (product instanceof Present) {
                // instantly executing method
                ((Present) product).itCanBePresented();
                // but not instantly printing its name, only storing in variable
                implementsPresentProducts.append("Product #").append(i).append(" ")
                        .append("is a").append(" ").append(product.whoAmI()).append('\n');
            }
            products[i] = product;
            log("Product #" + i + " " + "is a" +  " " + product.whoAmI());
        }

        log(implementsPresentProducts.toString());
    }
}
