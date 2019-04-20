package ru.deledzis.labs.lab3;

/**
 * Class representing some Storage with a main field mNumber,
 * which is used by two threads (Producer & Consumer) as a
 * shared static field storage.
 */
class Storage {
    private static int mNumber = 0;

    static int getNumber() {
        return mNumber;
    }

    static void setNumber(int number) {
        Storage.mNumber = number;
    }
}
