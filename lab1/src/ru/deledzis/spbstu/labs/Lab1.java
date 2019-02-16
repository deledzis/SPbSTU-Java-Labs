package ru.deledzis.spbstu.labs;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of first lab, 6th variant.
 * Simple and stupid calculator of one operation (+, -, * or /) with two operands.
 *
 * @author Alexander Styagov, SPbSTU
 */
public class Lab1 {

    private static final int ARGUMENTS_COUNT = 3;

    private static final int ERROR_CODE_WRONG_ARGUMENTS = 1;
    private static final int ERROR_CODE_WRONG_OPERANDS = 2;
    private static final int ERROR_CODE_WRONG_OPERATOR = 3;

    private static final Map mOperatorsAndOperationsMap = new HashMap<Character, String>() {{
        put('+', "Сумма");
        put('-', "Разность");
        put('x', "Произведение");
        put('/', "Частное");
    }};

    public static void main(String[] args) {
        // printing entire list of enter arguments
        System.out.println("Given next arguments set: ");
        for (String argument : args) {
            System.out.println("[ " + argument + " ]");
        }

        // initial check the count of arguments given
	    if (args.length != ARGUMENTS_COUNT) {
            handleError("Wrong arguments count",
                    "Error: wrong arguments. Use following format: [operand] [operator] [operand]",
                    ERROR_CODE_WRONG_ARGUMENTS);
        }

	    // parsing operands
        double firstOperand = 0;
        double secondOperand = 0;
        // handling possible number format exception
        try {
            firstOperand = Double.parseDouble(args[0]);
            secondOperand = Double.parseDouble(args[2]);
        } catch (NumberFormatException ex) {
            handleError("Wrong operands",
                    "Error: wrong arguments. Operands must be integer or float numbers.",
                    ERROR_CODE_WRONG_OPERANDS);
        }

        // now can check the operator
        if (args[1].length() == 1 && mOperatorsAndOperationsMap.containsKey(args[1].charAt(0))) {
            char operator = args[1].charAt(0);
            // correct operator
            double result = 0;
            try {
                result = doMathOperation(operator, firstOperand, secondOperand);
            } catch (WrongOperatorException e) {
                handleError("Wrong operator",
                        "Error: " + e.getMessage(),
                        ERROR_CODE_WRONG_OPERATOR);
            }

            // everything's fine, printing results
            Logger.getGlobal().log(Level.FINE, "Done successfully");
            System.out.println("Операнд 1 = " + firstOperand);
            System.out.println("Операция = " + operator);
            System.out.println("Операнд 2 = " + secondOperand);
            System.out.println(mOperatorsAndOperationsMap.get(operator) + " = " + result);
        } else {
            // bad operator
            handleError("Wrong operator",
                    "Error: wrong operator. Should be '+', '-', 'x' or '/'.",
                    ERROR_CODE_WRONG_OPERATOR);
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
            case '+': return first + second;
            case '-': return first - second;
            case 'x': return first * second;
            case '/': return first / second;
            default: throw new WrongOperatorException("wrong operator. Should be '+', '-', 'x' or '/'.");
        }
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

    /**
     * Custom exception class representing not supportable character symbol for operation
     */
    private static class WrongOperatorException extends Exception {
        WrongOperatorException(String message) {
            super(message);
        }
    }
}
