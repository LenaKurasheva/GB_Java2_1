package com.geekbrains.java2.homework.Lesson_2;

public class MyArrayDataException extends Exception {

    public MyArrayDataException(int x, int y){
        System.out.printf("There is no number in the cell: %d:%d \n", x , y);
    }
}
