package com.geekbrains.java2.homework.Lesson_1;

public class Cat implements Runnable, Jumpable {
    private String name;
    private int maxRun;
    private double maxJump;


    public Cat(String name, int maxRun, double maxJump){
        this.name= name;
        this.maxRun = maxRun;
        this.maxJump = maxJump;
    }

    public String getName() {
        return name;
    }
    private int getMaxRun(){
        return maxRun;
    }
    private double getmaxJump(){
        return maxJump;
    }

    @Override
    public void jump() {
        System.out.println("Cat jumped!");
    }

    public boolean jump(Wall wall) {
        if (wall.height > maxJump) {
            //System.out.println("Too high! Wall not passed.");
            return false;
        } else {
            //System.out.println("Jumped over the wall!");
            return true;
        }
    }

    @Override
    public void run() {
        System.out.println("Cat jumped!");

    }

    public boolean run(Treadmill treadmill) {

        if (treadmill.length > maxRun) {
            //System.out.println("Too long! Treadmill not passed.");
            return false;
        } else {
            //System.out.printf("Ran %d m.! Treadmill passed! ", treadmill.length);
            return true;
        }
    }

    @Override
    public String toString() {
        return "Cat " + name + ": ";
    }
}
