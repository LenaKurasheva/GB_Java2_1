package com.geekbrains.java2.homework.Lesson_1;

public class Robot implements Runnable, Jumpable {
    static int id = 0;
    int maxRun;
    double maxJump;

    public Robot(int maxRun, double maxJump){
        this.maxRun = maxRun;
        this.maxJump = maxJump;
        id++;
    }

    public int getId() {
        return id;
    }
    private int getMaxRun(){
        return maxRun;
    }
    private double getmaxJump(){
        return maxJump;
    }

    @Override
    public void jump() {
        System.out.println("Robot runs!");
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
        System.out.println("Robot jumped!");

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
        return "Robot " + id + ": ";
    }
}
