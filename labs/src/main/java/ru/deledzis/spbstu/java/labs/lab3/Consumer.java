package ru.deledzis.spbstu.java.labs.lab3;

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
        while (mStorage.isRunning()) {
            mStorage.consume();
        }

        // for infinite work
        /*while(true) {
            mStorage.consume();
        }*/
    }
}
