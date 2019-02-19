package ru.deledzis.spbstu.java.labs.lab3;

import static ru.deledzis.spbstu.java.labs.lab3.Main.LOOPS_COUNT;

/**
 * [Runnable] interface implementer working as a Consumer in a Producer-Consumer multithreading pattern
 */
public class Consumer implements Runnable {

    private Storage mStorage;

    Consumer(Storage storage) {
        this.mStorage = storage;
    }

    @Override
    public void run() {
        // for finite work
        for (int i = 0; i < LOOPS_COUNT; i++) {
            mStorage.consume();
        }

        // for infinite work
        /*while(true) {
            mStorage.consume();
        }*/
    }
}
