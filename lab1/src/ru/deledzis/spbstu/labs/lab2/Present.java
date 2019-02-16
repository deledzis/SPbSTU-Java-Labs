package ru.deledzis.spbstu.labs.lab2;

import com.sun.istack.internal.NotNull;

public interface Present extends Product {
    @NotNull
    String itCanBePresented();
}
