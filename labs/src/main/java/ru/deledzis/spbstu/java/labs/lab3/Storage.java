package ru.deledzis.spbstu.java.labs.lab3;

class Storage {
    private static int mNumber = 0;

    public static int getNumber() {
        return mNumber;
    }

    public static void setNumber(int number) {
        Storage.mNumber = number;
    }
}
