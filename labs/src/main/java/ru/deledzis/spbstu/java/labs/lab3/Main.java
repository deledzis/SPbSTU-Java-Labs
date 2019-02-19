package ru.deledzis.spbstu.java.labs.lab3;

/**
 * Implementation of the third lab, 6th variant.
 * It is a modified version of a classic multithreading problem called "Producer-Consumer Problem"
 *
 * @author Alexander Styagov, SPbSTU
 */

public class Main {

    /***** ERRORS *****/
    static final int ERROR_CODE_INTERRUPTED_EXC = 1;

    /***** OTHERS *****/
    static final int LOOPS_COUNT = 10;

    public static void main(String[] args) {
        // class with shared field
        Storage storage = new Storage();
        Producer producer = new Producer(storage);
        Consumer consumer = new Consumer(storage);

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
