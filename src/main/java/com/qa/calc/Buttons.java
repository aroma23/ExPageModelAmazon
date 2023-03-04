package com.qa.calc;

public enum Buttons {
    eight("8"),
    three("3"),
    multiply("x"),
    equals("=");
    final String buttonState;
    Buttons(String s) {
        this.buttonState = s;
    }

    public String getButtonState() {
        return this.buttonState;
    }
}
