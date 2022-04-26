package com.pablogormi.entraditas.util;

public class StringWrapper {
    private String data;

    public StringWrapper(String s) {
        this.data = s;
    }
    public StringWrapper() {
        this.data = "";
    }

    public String getString() {
        return this.data;
    }
    public void setString(String s) {
        this.data = s;
    }
}
