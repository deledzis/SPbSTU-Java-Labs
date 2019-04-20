package ru.deledzis.labs.lab2;

import org.jetbrains.annotations.NotNull;

public class Shoe implements Product {

    @NotNull
    @Override
    public String whoAmI() {
        return "Shoe";
    }

}
