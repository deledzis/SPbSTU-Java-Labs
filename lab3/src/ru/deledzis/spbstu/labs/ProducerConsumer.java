package ru.deledzis.spbstu.labs;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

class ProducerConsumer {
    // Create a list shared by producer and consumer
    // Size of list is 2.
    private LinkedList<Integer> list = new LinkedList<>();

    // Function called by producer thread
    void produce() throws InterruptedException {
        int value = getRandomIntInRange(0, 100);
        while (true) {
            synchronized (this) {
                // producer thread waits while list is full
                /*while (list.size() == capacity) {
                    wait();
                }*/

                System.out.println("Producer produced - " + value);

                // to insert the jobs in the list
                list.add(value);

                // notifies the consumer thread that now it can start consuming
                notify();

                // makes the working of program easier to  understand
                //Thread.sleep(1000);
            }
        }
    }

    // Function called by consumer thread
    void consume() throws InterruptedException {
        while (true) {
            synchronized (this)
            {
                // consumer thread waits while list is empty
                /*while (list.size() == 0)
                    wait();*/

                //to retrieve the first job in the list
                //int val = list.removeFirst();
                int value = list.getLast();

                System.out.println("Consumer consumed - " + value);

                // Wake up producer thread
                notify();

                // and sleep
                //Thread.sleep(1000);
            }
        }
    }

    /**
     * An auxiliary function to get some random integer in given range
     *
     * @param min is a lowest available integer
     * @param max is a biggest available integer
     * @return random integer between [min] and [max] inclusively
     */
    private static int getRandomIntInRange(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
