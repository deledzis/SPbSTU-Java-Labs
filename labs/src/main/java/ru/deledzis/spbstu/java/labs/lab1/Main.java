package ru.deledzis.spbstu.java.labs.lab1;

import ru.deledzis.spbstu.java.labs.utils.ResourcesAccessor;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static ru.deledzis.spbstu.java.labs.utils.UtilsKt.logError;
import static ru.deledzis.spbstu.java.labs.utils.UtilsKt.print;

/**
 * Implementation of the first lab, 6th variant.
 * Simple and stupid calculator of one operation (+, -, * or /) with two operands.
 *
 * @author Alexander Styagov, SPbSTU
 */

public class Main {

    /***** ERRORS *****/
    private static final int ERROR_CODE_WRONG_ARGUMENTS = 1;
    private static final int ERROR_CODE_WRONG_OPERANDS  = 2;
    private static final int ERROR_CODE_WRONG_OPERATOR  = 3;

    /***** OTHERS *****/
    private static final int ARGUMENTS_COUNT            = 3;

    private static final ResourceBundle mResources = ResourcesAccessor.INSTANCE.getResources();

    private static final Map mOperatorsAndOperationsMap = new HashMap<Character, String>() {{
        put('+', mResources.getString("sum"));
        put('-', mResources.getString("diff"));
        put('x', mResources.getString("product"));
        put('/', mResources.getString("quot"));
    }};

    public static void main(String[] args) {
        // initial check the count of arguments given
        if (args.length != ARGUMENTS_COUNT) {
            logError(mResources.getString("error_args"), ERROR_CODE_WRONG_ARGUMENTS);
        }

        // printing entire list of enter arguments
        print(mResources.getString("arg_set"));
        for (String argument : args) {
            print("[ " + argument + " ]");
        }

        // parsing operands
        double firstOperand = 0;
        double secondOperand = 0;
        // handling possible number format exception
        try {
            firstOperand = Double.parseDouble(args[0]);
            secondOperand = Double.parseDouble(args[2]);
        } catch (NumberFormatException ex) {
            logError(mResources.getString("error_operands"), ERROR_CODE_WRONG_OPERANDS);
        }

        // now can check the operator
        if (args[1].length() == 1 && mOperatorsAndOperationsMap.containsKey(args[1].charAt(0))) {
            char operator = args[1].charAt(0);
            // correct operator
            double result = 0;
            try {
                result = doMathOperation(operator, firstOperand, secondOperand);
            } catch (WrongOperatorException e) {
                logError(e.getMessage(), ERROR_CODE_WRONG_OPERATOR);
            }

            // everything's fine, printing results
            print(mResources.getString("op1") + firstOperand);
            print(mResources.getString("oper") + operator);
            print(mResources.getString("op2") + secondOperand);
            print(mOperatorsAndOperationsMap.get(operator) + " = " + result);
        } else {
            logError(mResources.getString("error_operator"), ERROR_CODE_WRONG_OPERATOR);
        }
    }

    /**
     * An auxiliary function, that will recognize operation by [operator]
     *
     * @param operator char symbol presenting the math operation
     * @param first double number that will be the left-assigned operand
     * @param second double number that will be the right-assigned operand
     * @return double number, which is the arithmetic result from given operation and operands
     * @throws WrongOperatorException if the [operator] char isn't in the list of supportable operators
     */
    private static double doMathOperation(char operator, double first, double second) throws WrongOperatorException {
        switch (operator) {
            case '+': return first + second; // sum
            case '-': return first - second; // diff
            case 'x': return first * second; // product
            case '/': return first / second; // quotient
            default: throw new WrongOperatorException(mResources.getString("error_operator"));
        }
    }

    /**
     * Custom exception class representing not supportable character symbol for operation
     */
    private static class WrongOperatorException extends Exception {
        WrongOperatorException(String message) {
            super(message);
        }
    }
}

