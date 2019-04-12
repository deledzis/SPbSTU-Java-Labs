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
        log("[" + Thread.currentThread().getName() + "] starting run...");

        for (int i = 0; i < stringsCount; i++) {
            log("[" + Thread.currentThread().getName() + "] i = " + i);

            // for the first iteration (i == 0) for all threads in array
            // the lock.wait() method will be called, starting from the isLast thread towards the 0-th one.
            // When the first thread starting work, it prints its name first, then awakes the last thread. And so on...
            if (!isFirst || i != 0) {
                // TODO: There is a problem with direct order of starting threads:
                //  there is a big chance that current thread, after printing its name
                //  and notifying other threads will enter this block and set itself wait
                //  faster than the thread that is locked over current thread will wake up and
                //  print its name, notify next and so on...
                //  Possible solution: use indirect order of launching...
                synchronized(lock) {
                    try {
                        log("[" + Thread.currentThread().getName() + "] Locking... ");

                        // blocking current thread until lock will be notified
                        // as for lock is another thread from array, this thread will wake up
                        // when another thread (which is a lock object for current thread)
                        // will call this.notify in its run() method

                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            log("[" + Thread.currentThread().getName() + "] Exit from waiting state. Print its name...");

            System.out.print(Thread.currentThread().getName() + " ");

            if (isLast) {
                System.out.println();
            }

            // here we're synchronizing on "this" because current thread is a lock object for some other thread
            // i.e. some another thread will wake up
            synchronized(this) {
                log("[" + Thread.currentThread().getName() + "] Notifying other threads.");

                this.notify();
            }
        }
    }
}