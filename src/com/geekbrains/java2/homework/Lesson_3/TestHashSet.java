//1.Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
//Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
//Посчитать, сколько раз встречается каждое слово.

package com.geekbrains.java2.homework.Lesson_3;

import java.util.Arrays;
import java.util.HashSet;

public class TestHashSet {

    public static void countRepeatsInStringArray(String[] array) {
        HashSet<String> uniqueWords = new HashSet<>(Arrays.asList(array));
        String[] uniqueArray = new String[uniqueWords.size()];
        uniqueWords.toArray(uniqueArray);

        System.out.println("Уникальные слова: " + uniqueWords);

        int count = 0;
        String currentWord = "";

        for (int i = 0; i < uniqueArray.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[j].equals(uniqueArray[i])) {
                    count++;
                    currentWord = uniqueArray[i];
                }
            }
            System.out.println("Слово [" + currentWord + "] встречается " + count + " раз.");
            currentWord = "";
            count = 0;
        }
    }

    public static void main(String[] args) {
        String[] numbers = {"One", "Two", "Three", "Four",
                "Five", "Five", "Five", "Five", "Five", "Two", "Three", "Three", "Four", "Four", "Four"};

        System.out.println("Заданный массив: " + Arrays.toString(numbers));
        countRepeatsInStringArray(numbers);
    }
}
