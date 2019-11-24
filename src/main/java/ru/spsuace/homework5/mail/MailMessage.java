package ru.spsuace.homework5.mail;

public class MailMessage extends Message<String> {

    public MailMessage(String sender, String receiver, String messageBody) {
        super(sender, receiver, messageBody);
    }
}
