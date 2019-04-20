package ru.deledzis.spbstu.java.labs.lab3;

public class Main {
    private static final int STEPS = 100;

    public static void main(String[] args) {
        Producer producer = new Producer(STEPS);
        Consumer consumer = new Consumer(producer);

        producer.start();
        consumer.start();
    }
}
