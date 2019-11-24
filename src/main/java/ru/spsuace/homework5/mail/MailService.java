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
public class MailService<T> implements Consumer<Message<T>> {

    /**
     * С помощью этого метода почтовый сервис обрабатывает письма и зарплаты
     * 1 балл
     */
    private List<Message<T>> Messages = new ArrayList<Message<T>>();

    @Override
    public void accept(Message<T> message) {
        Messages.add(message);
    }

    /**
     * Метод возвращает мапу получатель -> все объекты которые пришли к этому получателю через данный почтовый сервис
     */
    public Map<String, List<T>> getMailBox() {
        return Messages.stream().collect(Collectors.toMap(
                message -> message.getReceiver(),
                message -> Arrays.asList(message.getMessageBody()),
                (existing, replacement) -> {
                    existing.addAll(replacement);
                    return existing;
                }
        ));
    }


    /**
     * Метод должен заставить обработать service все mails.
     * 1 балл за любое решение (2 балла за красивое)
     */
    public static void process(MailService service, List<Message> mails) {
        mails.stream().forEach(mail -> service.accept(mail));
    }

}
