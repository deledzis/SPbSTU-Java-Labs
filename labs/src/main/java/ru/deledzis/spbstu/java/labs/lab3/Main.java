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

    public static void main(String[] args) {
        // class with shared field
        Storage storage = new Storage();
        Thread producer = new Thread(new Producer(storage));
        Thread consumer = new Thread(new Consumer(storage));

        producer.start();
        consumer.start();

        /*try
        {
            producer.join();
            consumer.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }*/
    }
}
