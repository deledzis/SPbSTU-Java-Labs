package ru.deledzis.spbstu.java.labs.lab3;

/**
 * Implementation of the third lab, 6th variant.
 * It is a modified version of a classic multithreading problem called "Producer-Consumer Problem"
 *
 * @author Alexander Styagov, SPbSTU
 */

public class Main {
    private static final int STEPS = 5;

    public static void main(String[] args) {
        Producer producer = new Producer(STEPS);
        Consumer consumer = new Consumer(producer);

        producer.start();
        consumer.start();
    }
}
