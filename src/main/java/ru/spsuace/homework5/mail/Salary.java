package ru.spsuace.homework5.mail;

public class Salary extends Message<Double> {
    public Salary(String sender, String receiver, Double messageBody) {
        super(sender, receiver, messageBody);
    }
}

