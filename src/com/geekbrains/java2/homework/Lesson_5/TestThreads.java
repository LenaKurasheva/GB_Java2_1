//Необходимо написать два метода, которые делают следующее:
//1) Создают одномерный длинный массив.
//2) Заполняют этот массив единицами.
//3) Засекают время выполнения: long a = System.currentTimeMillis().
//4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
//arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
//5) Проверяется время окончания метода System.currentTimeMillis().
//6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a).
//
//Отличие первого метода от второго:
// Первый просто бежит по массиву и вычисляет значения.
// Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.

//По замерам времени:
//Для первого метода надо считать время только на цикл расчета:
//Для второго метода замеряете время разбивки массива на 2, просчета каждого из двух массивов и склейки.

package com.geekbrains.java2.homework.Lesson_5;

public class TestThreads {
    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;

    public static void method1(){
        float[] arr = new float[SIZE];
        for (int i = 0; i < SIZE; i++)  arr[i] = 1f;

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        long finishTime = System.currentTimeMillis();
        System.out.println("Время работы с одни потоком: " + (finishTime - startTime) + " ms." );

    }

    public static void method2() {
        float[] arr = new float[SIZE];
        for (int i = 0; i < SIZE; i++)  arr[i] = 1f;

        long startTime = System.currentTimeMillis();

        float[] firstHalfArr = new float[HALF];
        float[] secondHalfArr = new float[HALF];
        System.arraycopy(arr,0, firstHalfArr, 0, HALF );
        System.arraycopy(arr, HALF, secondHalfArr, 0, HALF );

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < HALF; i++) {
                firstHalfArr[i] = (float)(firstHalfArr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < HALF; i++) {
                secondHalfArr[i] = (float)(secondHalfArr[i] * Math.sin(0.2f + (i + HALF) / 5) * Math.cos(0.2f + (i + HALF) / 5) * Math.cos(0.4f + (i + HALF) / 2));
            }
        });
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(firstHalfArr,0, arr, 0, HALF );
        System.arraycopy(secondHalfArr, 0, arr, HALF, HALF );

        long finishTime = System.currentTimeMillis();
        System.out.println("Время работы с двумя потоками: " + (finishTime - startTime) + " ms.");
    }

    public static void main(String[] args) {
        method1();
        method2();
    }
}
