package ru.deledzis.spbstu.java.labs.lab3;

import static ru.deledzis.spbstu.java.labs.utils.UtilsKt.getRandomInt;

/**
 * [Runnable] interface implementer working as a Producer in a Producer-Consumer multithreading pattern
 */
public class Producer implements Runnable {

    private static final int RANGE_MIN_VALUE = -100;
    private static final int RANGE_MAX_VALUE = 100;

    private Storage mStorage;

    Producer(Storage storage) {
        this.mStorage = storage;
    }

    @Override
    public void run() {
        // for finite work
        while (mStorage.isRunning()) {
            mStorage.produce(getRandomInt(RANGE_MIN_VALUE, RANGE_MAX_VALUE));
        }

        // for infinite work
        /*while (true) {
            mStorage.produce(getRandomInt(RANGE_MIN_VALUE, RANGE_MAX_VALUE));
        }*/
    }

}
