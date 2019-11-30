package ru.spsuace.homework5.mail;


import javafx.concurrent.Service;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Нужно создать сервис, который умеет обрабатывать обрабатывать письма и зарплату.
 * Письма состоят из получателя, отправителя, текста сообщения
 * Зарплата состоит из получателя, отправителя и суммы.
 *
 * В реализации нигде не должно быть классов Object и коллекций без типа. Используйте дженерики.
 *
 * Оценка за задание 4 балла (еще 2 балла можно получить дополнительно)
 */
public class MailService<T extends MailMessage> implements Consumer<Message<T>> {

    /**
     * С помощью этого метода почтовый сервис обрабатывает письма и зарплаты
     * 1 балл
     */
    private List<Message<T>> Messages = new ArrayList<Message<T>>();
    private Map<String, List<T>> MailBox = new HashMap<>();


    @Override
    public void accept(Message<T> message) {
        List<T> mails = MailBox.get(message.getReceiver());
        if (mails == null) {
            List<T> list = new ArrayList<>();
            list.add(message.getMessageBody());
            MailBox.put(message.getReceiver(), list);
        } else {
            mails.add(message.getMessageBody());
        }
        Messages.add(message);
    }

    /**
     * Метод возвращает мапу получатель -> все объекты которые пришли к этому получателю через данный почтовый сервис
     */
    public Map<String, List<T>> getMailBox() {
        return MailBox;
    }


    /**
     * Метод должен заставить обработать service все mails.
     * 1 балл за любое решение (2 балла за красивое)
     */
    public static void process(MailService service, List<Message> mails) {
        mails.stream().forEach(mail -> service.accept(mail));
    }

}
