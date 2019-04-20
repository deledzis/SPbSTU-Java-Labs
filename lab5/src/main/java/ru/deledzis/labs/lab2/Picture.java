package ru.deledzis.labs.lab2;

import org.jetbrains.annotations.NotNull;

public class Picture implements Present {

    @NotNull
    @Override
    public String itCanBePresented() {
        return "Yes";
    }

    @NotNull
    @Override
    public String whoAmI() {
        return "Picture";
    }

}
