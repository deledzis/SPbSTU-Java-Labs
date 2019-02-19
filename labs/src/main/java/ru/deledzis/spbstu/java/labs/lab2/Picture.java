package ru.deledzis.spbstu.java.labs.lab2;

import org.jetbrains.annotations.NotNull;
import ru.deledzis.spbstu.java.labs.utils.ResourcesAccessor;

public class Picture implements Present {

    @NotNull
    @Override
    public String itCanBePresented() {
        return ResourcesAccessor.INSTANCE.getResources().getString("yes");
    }

    @NotNull
    @Override
    public String whoAmI() {
        return ResourcesAccessor.INSTANCE.getResources().getString("picture");
    }

}
