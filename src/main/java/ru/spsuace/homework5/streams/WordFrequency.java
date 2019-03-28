package ru.spsuace.homework5.streams;

import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.function.*;

/**
 * Написать программу, которая из текста (стрим строк), возвращает 10 самых популярных слов (В порядке убывания частоты).
 * Словом считается последовательность символов из букв и цифр от пробела до пробела. (Посмотрите статические методы
 * в классе Character)
 *
 * В исходном стриме строка - это строка из киниги, которая может содержать в себе много слов.
 *
 * Если слов в стриме меньше 10, то вывести все слова.
 * Слова надо сравнивать без учета регистра.
 */
public class WordFrequency {

    /**
     * Задачу можно решить без единого условного оператора, только с помощью стримов.
     * Если будут использоваться условные операторы, то оценка максимальная оценка 2 балла.
     * Без них - 4 балла
     */
    public static List<String> wordFrequency(Stream<String> lines) {

        return lines.map(s -> Stream.of(s.split(" "))
                .collect(Collectors.toList()))
                .flatMap(str -> str.stream())
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .map(key -> key.getKey())
                .collect(Collectors.toList());
    }
}
