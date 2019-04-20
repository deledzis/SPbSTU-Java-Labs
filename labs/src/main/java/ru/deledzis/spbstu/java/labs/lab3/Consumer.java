package ru.deledzis.spbstu.java.labs.lab3;

import static ru.deledzis.spbstu.java.labs.utils.UtilsKt.log;

public class Consumer extends Thread {

    private final Producer mProducer;

    Consumer(Producer producer) {
        this.mProducer = producer;
    }

    @Override
    public void run() {
        while (!mProducer.isFinish()) {
            synchronized (mProducer) {
                int value = Storage.getNumber();
                log("Consumer" + " " + Thread.currentThread().getName() + " " +
                        "consumed number" + " " + value);
                mProducer.notify();
                try {
                    mProducer.wait();
                } catch (InterruptedException e) {
                    e.getMessage();
                }
            }
        }
    }
}
