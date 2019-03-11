package ru.deledzis.spbstu.java.labs.lab2;

import org.jetbrains.annotations.NotNull;

public class Toy implements Present {

    @NotNull
    @Override
    public String itCanBePresented() {
        return "Yes";
    }

    @NotNull
    @Override
    public String whoAmI() {
        return "Toy";
    }

}
