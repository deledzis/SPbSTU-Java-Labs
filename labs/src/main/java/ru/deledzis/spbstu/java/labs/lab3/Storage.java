package ru.deledzis.spbstu.java.labs.lab3;

import static ru.deledzis.spbstu.java.labs.lab3.Main.ERROR_CODE_INTERRUPTED_EXC;
import static ru.deledzis.spbstu.java.labs.utils.UtilsKt.*;

/**
 * Class representing some Storage with a main field mNumber, which is used by two threads (Producer & Consumer) as a
 * shared variable. Defines two main methods - produce() and consume()
 */

class Storage {

    private static final int NOT_SET = -1;

    private static final int MIN_AVAILABLE_VALUE = 0;
    private static final int MAX_AVAILABLE_VALUE = 100;

    private static final int STEPS = 100;

    // this default value is used to detect unset state of field
    private int mNumber = NOT_SET;

    private int mCurrentStep = 0;

    //private static final ResourceBundle mResources = ResourcesAccessor.INSTANCE.getResources();

    /**
     * a synchronized method used to set a [mNumber] field if it's not set. If it is set (differs from -1),
     * the thread control goes to another waiting [Thread] (it would be the Consumer, which will awake
     * and start [consume()]. If it is not set (equals to -1), then the thread control remains in the current Thread,
     * and if [value] meet the conditions, new value for field [mNumber] will be set and control goes to the Consumer.
     *
     * @param value is an input integer number that [Producer] generated and trying to set to field [mNumber]
     */
    synchronized void produce(int value) {
        while (mNumber != NOT_SET) {
            try {
                // field is already produced, transfer control to Consumer thread
                wait();
            } catch (InterruptedException e) {
                logError(e.getMessage(), ERROR_CODE_INTERRUPTED_EXC);
            }
        }

        if (value >= MIN_AVAILABLE_VALUE && value <= MAX_AVAILABLE_VALUE) {
            mNumber = value;

            log("Producer" + " " + Thread.currentThread().getName() + " " +
                            "produced number" + " " + mNumber);
            // uncomment to see how consumer is waiting for producer to notify about new item added
            /*try {
                sleep(1000 * 10); // 10 sec
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            notify();
        } /*else {
            print("Producer" + " " + getThreadName() + " " +
                    "produced bad number" + " " + value);
        }*/
    }

    /**
     * a synchronized method used to get a [mNumber] field if it's set. If it isn't set (equals to -1),
     * the thread control goes to another waiting [Thread] (it would be the Producer, which will awake
     * and start [produce()]. If it is set (differs from -1), then the thread control remains in the current Thread,
     * and after consuming, [mNumber] will be reset to default [NOT_SET] value.
     */
    synchronized void consume() {
        while (mNumber == NOT_SET) {
            // nothing to consume yet, field is not produced, awaiting
            try {
                wait();
            } catch (InterruptedException e) {
                logError(e.getMessage(), ERROR_CODE_INTERRUPTED_EXC);
            }
        }
        // field is produced by now, can consume
        System.out.println("Consumer" + " " + getThreadName() + " " +
                "consumed number" + " " +  mNumber);
        // resetting field
        mNumber = NOT_SET;
        mCurrentStep++;
        // awake blocked awaiting thread(s) on this object's monitor
        notify();
    }

    synchronized private String getThreadName() {
        return Thread.currentThread().getName();
    }

    boolean isRunning() {
        return mCurrentStep != STEPS;
    }
}
