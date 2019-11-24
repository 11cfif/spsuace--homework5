package ru.spsuace.homework5.mail;

public class Message<T> {
    private String Sender;
    private String Receiver;
    private T MessageBody;


    public String getSender() {
        return Sender;
    }

    public String getReceiver() {
        return Receiver;
    }

    public T getMessageBody() {
        return MessageBody;
    }

    public Message(String sender, String receiver, T messageBody) {
        Sender = sender;
        Receiver = receiver;
        MessageBody = messageBody;
    }
}
