package ru.deledzis.spbstu.labs;

public class Toy implements Present {

    @Override
    public String itCanBePresented() {
        return "yes";
    }

    @Override
    public String whoAmI() {

        return "Toy";
    }

}
