package ru.deledzis.spbstu.java.labs.lab2;

import org.jetbrains.annotations.NotNull;

public class Book implements Product {

    @NotNull
    @Override
    public String whoAmI() {
        return "Book";
    }

}
