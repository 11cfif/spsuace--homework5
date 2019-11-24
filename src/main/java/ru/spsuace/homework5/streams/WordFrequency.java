package ru.spsuace.homework5.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Написать программу, которая из текста (стрим строк), возвращает 10 самых популярных слов (В порядке убывания частоты).
 * Словом считается последовательность символов из букв и цифр от пробела до пробела или знака препинания (.,!:-?;).
     * (Посмотрите статические методы в классе Character)
 *
 * В исходном стриме строка - это строка из книги, которая может содержать в себе много слов.
 *
 * Если слов в стриме меньше 10, то вывести все слова. Если слова имеют одинаковое количество упоминаний, то выводить
 * в лексикографическом порядеке.
 * Слова надо сравнивать без учета регистра.
 */
public class WordFrequency {

    /**
     * Задачу можно решить без единого условного оператора, только с помощью стримов.
     * Если будут использоваться условные операторы, то оценка максимальная оценка 2 балла.
     * Без них - 4 балла
     */
    public static List<String> wordFrequency(Stream<String> lines) {
        List<String> list = lines
                .map(line -> line.split("\\W+"))
                .flatMap(arr -> Stream.of(arr))
                .map(w -> w.toLowerCase())
                .collect(Collectors.toList());

        List<String> copy = new ArrayList<>(list);

        return list
                .stream()
                .distinct()
                .sorted((w1, w2) -> (int)copy.stream().filter(w -> w.equals(w2)).count()
                        - (int)copy.stream().filter(w -> w.equals(w1)).count())
                .limit(10)
                .collect(Collectors.toList());
    }


}
