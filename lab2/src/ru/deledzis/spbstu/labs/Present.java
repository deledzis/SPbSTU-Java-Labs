package ru.deledzis.spbstu.labs;

import com.sun.istack.internal.NotNull;

public interface Present extends Product {
    @NotNull
    String itCanBePresented();
}
