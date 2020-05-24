package com.geekbrains.java2.homework.Lesson_3;

public class TestTelephoneBook {
    public static void main(String[] args) {
        TelephoneBook telephoneBook = new TelephoneBook();
        telephoneBook.add("Ivanov", "8-911-815-12-12");
        telephoneBook.add("Ivanov", "8-921-345-54-23");
        telephoneBook.add("Ivanov", "8-955-617-45-65");
        telephoneBook.add("Kurasheva", "8-911-815-26-03");
        telephoneBook.get("Ivanov");
        telephoneBook.get("Kurasheva");
    }
}
