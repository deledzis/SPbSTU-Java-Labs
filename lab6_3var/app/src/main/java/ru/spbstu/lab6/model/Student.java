package ru.spbstu.lab6.model;

import androidx.annotation.NonNull;

public class Student {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private boolean isHeadman;

    public Student ( ) {
    }

    public Student (
            String firstName,
            String lastName,
            String phone,
            String email,
            boolean isHeadman
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.isHeadman = isHeadman;
    }

    public String getFirstName ( ) {
        return firstName;
    }

    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }

    public String getLastName ( ) {
        return lastName;
    }

    public void setLastName (String lastName) {
        this.lastName = lastName;
    }

    public String getPhone ( ) {
        return phone;
    }

    public void setPhone (String phone) {
        this.phone = phone;
    }

    public String getEmail ( ) {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public boolean isHeadman ( ) {
        return isHeadman;
    }

    public void setHeadman (boolean headman) {
        isHeadman = headman;
    }

    @NonNull
    @Override
    public String toString ( ) {
        return "Student {\n" +
                "\tfirstName='" + firstName + '\'' + '\n' +
                "\tlastName='" + lastName + '\'' + '\n' +
                "\tphone='" + phone + '\'' + '\n' +
                "\temail='" + email + '\'' + '\n' +
                "\tisHeadman=" + isHeadman + '\n' +
                '}';
    }
}
