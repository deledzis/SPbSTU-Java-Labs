package ru.deledzis.spbstu.java.labs.lab2;

import org.jetbrains.annotations.NotNull;
import ru.deledzis.spbstu.java.labs.utils.ResourcesAccessor;

public class Shoe implements Product {

    @NotNull
    @Override
    public String whoAmI() {
        return ResourcesAccessor.INSTANCE.getResources().getString("shoe");
    }

}
