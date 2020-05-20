//1.Напишите метод, на вход которого подаётся двумерный строковый массив  размером 4х4.
//При подаче массива другого размера необходимо бросить исключение MyArraySizeException.

//2.Далее метод должен пройтись по всем элементам массива, преобразовать в int и просуммировать.
//Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ
//или текст вместо числа), должно быть брошено исключение MyArrayDataException с детализацией,
//в какой именно ячейке лежат неверные данные.

//3.В методе main() вызвать полученный метод, обработать возможные исключения MySizeArrayException
//и MyArrayDataException и вывести результат расчета.

package com.geekbrains.java2.homework.Lesson_2;


public class CheckExceptions {


    public static void main(String[] args) {
        String [][] strings = new String[][]{
                {"1", "2", "3", "4"},
                {"11", "23", "32", "4"},
                {"6", "234", "x", "4"},
                {"0", "2", "12", "54"}
        };
        try {
            Integer sum = summarizeMembers(strings);
            System.out.println("Sum of array's members: " + sum);
        } catch (MyArrayDataException | MyArraySizeException e){
            e.printStackTrace();
        }
    }
    
    public static Integer summarizeMembers (String[][] strings) throws MyArrayDataException, MyArraySizeException {
        int intMember = 0;
        int summ = 0;
        int x = 0;
        int y = 0;

        if(strings.length != 4) {
            throw new MyArraySizeException();
        }

        for (int i = 0; i <strings.length ; i++) {
            if (strings[i].length != 4){
                throw new MyArraySizeException();
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                char[] charsFromString = strings[i][j].toCharArray();
                for (int k = 0; k < charsFromString.length; k++) {
                    Character checkingChar = (Character) charsFromString[k];
                    if (!Character.isDigit(checkingChar)){
                        throw new MyArrayDataException(x, y);
                    }
                }
                x = i;
                y = j + 1;
                summ += Integer.parseInt(strings[i][j]);;
            }
        }
        return summ;
    }
}
