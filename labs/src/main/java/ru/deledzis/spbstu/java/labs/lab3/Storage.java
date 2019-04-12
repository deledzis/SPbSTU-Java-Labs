package ru.deledzis.spbstu.java.labs.lab3;

/**
 * Class representing some Storage with a main field mNumber,
 * which is used by two threads (Producer & Consumer) as a
 * shared static field storage.
 */

class Storage {
    private static int mNumber = 0;

    public static int getNumber() {
        return mNumber;
    }

    public static void setNumber(int number) {
        Storage.mNumber = number;
    }
}
