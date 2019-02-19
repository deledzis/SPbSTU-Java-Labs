package ru.deledzis.spbstu.java.labs.lab2;

import ru.deledzis.spbstu.java.labs.utils.ResourcesAccessor;

import java.util.ResourceBundle;

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

    private static final ResourceBundle mResources = ResourcesAccessor.INSTANCE.getResources();

    public static void main(String[] args) {
        // printing entire list of enter arguments
        print(mResources.getString("arg_set"));
        for (String argument : args) {
            print("[ " + argument + " ]");
        }

        // parsing array size from commandline arguments
        int arraySize = 0;
        try {
            arraySize = Integer.parseInt(args[0]);
        } catch (NumberFormatException ex) {
            logError(mResources.getString("error_argument"), ERROR_CODE_WRONG_ARGUMENT);
        }

        // this will be used to store interesting us entities from array
        StringBuilder implementsPresentProducts = new StringBuilder(mResources.getString("prod_impl_present"))
                .append("\n");

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
                    logError(mResources.getString("error_random"), ERROR_CODE_UNKNOWN);
                    break;
            }
            // at this point, if product is null, program would be terminated already
            assert product != null;

            if (product instanceof Present) {
                // instantly executing method
                ((Present) product).itCanBePresented();
                // but not instantly printing its name, only storing in variable
                implementsPresentProducts.append(mResources.getString("prod")).append(i).append(" ")
                        .append(mResources.getString("is_a")).append(" ").append(product.whoAmI()).append('\n');
            }
            products[i] = product;
            print(mResources.getString("prod") + i + " " + mResources.getString("is_a") +  " " + product.whoAmI());
        }

        print(implementsPresentProducts.toString());
    }
}
