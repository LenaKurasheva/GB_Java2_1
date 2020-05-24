//2.Написать простой класс Телефонный Справочник, который хранит в себе список фамилий и телефонных номеров.
//В этот телефонный справочник с помощью метода add() можно добавлять записи, а с помощью метода get()
//искать номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов
//(в случае однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны.
//Желательно не добавлять лишний функционал (дополнительные поля (имя, отчество, адрес), взаимодействие
//с пользователем через консоль и т.д). Консоль использовать только для вывода результатов проверки телефонного
//справочника.

package com.geekbrains.java2.homework.Lesson_3;

import java.util.ArrayList;
import java.util.HashMap;

public class TelephoneBook {

    HashMap<String, ArrayList<String>> book;

    public TelephoneBook() {
        book = new HashMap<>();
    }

    public void add(String name, String phone) {
        if (book.containsKey(name)) {
            book.get(name).add(phone);
        } else {
            ArrayList<String> numbers = new ArrayList<>();
            numbers.add(phone);
            book.put(name, numbers);
        }
    }

    public void get(String name) {
        System.out.printf("Имя: %S | Номер: %S \n", name, book.get(name).toString());
    }

}

//Вариант реализации с более медленным поиском по value:
//public class TelephoneBook {

//    HashMap<String,String> book;
//
//    public TelephoneBook(){
//        book = new HashMap<>();
//    }
//    public void add (String name, String phone){
//        book.put(phone, name);
//    }
//    public void get (String name){
//     for(Map.Entry<String,String> o : book.entrySet()){
//        if (o.getValue().equals(name)) System.out.printf("Имя: %S | Номер: %S \n", name, o.getKey());
//     }
//    }
//}
