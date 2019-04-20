package ru.deledzis.labs.lab3;

/**
 * [Runnable] interface implementer working as a Consumer in a Producer-Consumer multithreading pattern
 */
public class Consumer extends Thread {

    private final Lab3Controller controller;
    private final Producer producer;

    Consumer(Lab3Controller controller, Producer producer) {
        this.controller = controller;
        this.producer = producer;
    }

    @Override
    public void run() {
        while (!producer.isFinish()) {
            synchronized (producer) {
                int value = Storage.getNumber();
                synchronized (controller) {
                    controller.handleNewMessage("Consumer consumed number" + " " + value + "\n");
                }
                producer.notify();
                try {
                    producer.wait();
                } catch (InterruptedException e) {
                    e.getMessage();
                }
            }
        }
        controller.handleFinish();
    }
}