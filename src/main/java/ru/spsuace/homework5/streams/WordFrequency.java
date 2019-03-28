package ru.spsuace.homework5.streams;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Написать программу, которая из текста (стрим строк), возвращает 10 самых популярных слов (В порядке убывания частоты).
 * Словом считается последовательность символов из букв и цифр от пробела до пробела. (Посмотрите статические методы
 * в классе Character)
 * <p>
 * В исходном стриме строка - это строка из киниги, которая может содержать в себе много слов.
 * <p>
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

        Stream<String> test = lines.flatMap(s -> Arrays.stream(s.split("\\s")))
                .filter(w -> w.length() > 0)
                .map(String::toLowerCase);

        Map<String, Long> map = test.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> {
                            throw new IllegalStateException();
                        },
                        LinkedHashMap::new
                ));
        List<String> wordFrequenc = new ArrayList<>();
        map.forEach((k, v) -> wordFrequenc.add(k));
        return wordFrequenc;
    }
}
