package ru.deledzis.spbstu.java.labs.lab3;

import static ru.deledzis.spbstu.java.labs.utils.UtilsKt.getRandomInt;
import static ru.deledzis.spbstu.java.labs.utils.UtilsKt.log;

public class Producer extends Thread {

    private static final int RANGE_MIN_VALUE = 0;
    private static final int RANGE_MAX_VALUE = 100;

    private int mCurrentStep;
    private int mSteps;
    private boolean mFinish = false;

    Producer(int steps) {
        this.mCurrentStep = 0;
        this.mSteps = steps;
    }

    @Override
    public void run() {
        while (mCurrentStep < mSteps) {
            synchronized (this) {
                mCurrentStep++;
                int value = getRandomInt(RANGE_MIN_VALUE, RANGE_MAX_VALUE);
                Storage.setNumber(value);
                log("Producer" + " " + Thread.currentThread().getName() + " " +
                        "produced number" + " " + value);
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
