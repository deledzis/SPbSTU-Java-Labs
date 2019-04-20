package ru.deledzis.spbstu.java.labs.lab4;

import static ru.deledzis.spbstu.java.labs.utils.UtilsKt.*;

/**
 * Implementation of the forth lab, 6th variant.
 * Sequent and synchronized work of multiple threads.
 *
 * @author Alexander Styagov, SPbSTU
 */

public class Main {

    /***** ERRORS *****/
    private static final int ERROR_CODE_WRONG_ARGUMENT  = 1;

    /***** OTHERS *****/
    private static final int ARGUMENTS_COUNT            = 2;

    public static void main(String[] args) {
        // initial check the count of arguments given
        if (args.length != ARGUMENTS_COUNT) {
            logError("wrong arguments. Use following format: [operand] [operator] [operand]",
                    ERROR_CODE_WRONG_ARGUMENT);
        }

        // printing entire list of enter arguments
        print("Given next arguments set:");
        for (String argument : args) {
            print("[ " + argument + " ]");
        }

        // parsing number of threads and string from commandline arguments
        int threadsNumber = 0;
        int stringsNumber = 0;
        try {
            threadsNumber = Integer.parseInt(args[0]);
            stringsNumber = Integer.parseInt(args[1]);
        } catch (NumberFormatException ex) {
            logError("wrong argument. Should be an integer", ERROR_CODE_WRONG_ARGUMENT);
        }

        WorkingThread[] threads = new WorkingThread[threadsNumber];

        for (int i = 0; i < threadsNumber; i++) {
            threads[i] = new WorkingThread(stringsNumber);
            if (i > 0) {
                threads[i].setLock(threads[i - 1]);
            }
        }
        threads[0].setLock(threads[threadsNumber - 1]);
        threads[0].setFirst();
        threads[threadsNumber - 1].setLast();

        Thread[] mainThreads = new Thread[threadsNumber];
        for (int i = 0; i < threadsNumber; i++) {
            mainThreads[i] = new Thread(threads[i]);
        }

        for (int i = threadsNumber; i > 0; i--) {
            mainThreads[i - 1].start();
        }
    }
}