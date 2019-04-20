package ru.deledzis.spbstu.java.labs.lab4;

import static ru.deledzis.spbstu.java.labs.utils.UtilsKt.log;

final class WorkingThread implements Runnable {

    private WorkingThread lock; // object (another thread from array) on which current thread will be coupled via wait/notify

    private int stringsCount;   // number of string lines threads should log

    private boolean isFirst = false;
    private boolean isLast = false;

    void setLock(WorkingThread lock) {
        this.lock = lock;
    }

    void setFirst() {
        isFirst = true;
    }

    void setLast() {
        this.isLast = true;
    }

    WorkingThread(int stringsCount) {
        this.stringsCount = stringsCount;
    }

    @Override
    public void run() {
        synchronized (lock) {
            for (int i = 0; i < stringsCount; i++) {
                if (!isFirst || i != 0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.print(Thread.currentThread().getName() + " ");

                if (isLast) {
                    System.out.println();
                }

                synchronized (this) {
                    this.notify();
                }
            }
        }
    }
}