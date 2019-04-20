package ru.deledzis.labs.lab3;

import static ru.deledzis.labs.utils.UtilsKt.getRandomInt;

/**
 * [Runnable] interface implementer working as a Producer in a Producer-Consumer multithreading pattern
 */
public class Producer extends Thread {

    private final Lab3Controller controller;

    private int steps;
    private int minValue;
    private int maxValue;
    private boolean mFinish = false;

    Producer(Lab3Controller controller, int steps, int minValue, int maxValue) {
        this.controller = controller;
        this.steps = steps;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public void run() {
        for (int i = 0; i < steps; i++) {
            synchronized (this) {
                int value = getRandomInt(minValue, maxValue);
                Storage.setNumber(value);
                synchronized (controller) {
                    controller.handleNewMessage("Producer produced number" + " " + value + "\n");
                }
                this.notify();
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.getMessage();
                }
            }
        }
        mFinish = true;
    }

    boolean isFinish() {
        return mFinish;
    }
}