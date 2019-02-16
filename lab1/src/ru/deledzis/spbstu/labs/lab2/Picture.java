package ru.deledzis.spbstu.labs.lab2;

public class Picture implements Present {

    @Override
    public String itCanBePresented() {
        return "yes";
    }

    @Override
    public String whoAmI() {
        return "Picture";
    }

}
