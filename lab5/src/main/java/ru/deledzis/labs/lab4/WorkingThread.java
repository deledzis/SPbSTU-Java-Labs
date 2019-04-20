package ru.deledzis.labs.lab4;

final class WorkingThread implements Runnable {

    private final Lab4Controller controller;

    private WorkingThread lock;

    private int stringsCount;

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

    WorkingThread(Lab4Controller controller, int stringsCount) {
        this.controller = controller;
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

                synchronized (controller) {
                    String print = Thread.currentThread().getName() + " ";
                    if (isLast) {
                        print += '\n';
                    }
                    controller.handleNewMessage(print);
                    System.out.print(print);
                }

                synchronized (this) {
                    this.notify();
                }
            }
            if (isLast) {
                controller.handleFinish();
            }
        }
    }
}
