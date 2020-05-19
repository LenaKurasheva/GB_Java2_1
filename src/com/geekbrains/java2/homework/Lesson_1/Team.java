package com.geekbrains.java2.homework.Lesson_1;

public class Team {
    String name;
    Runnable[] members;
    StringBuilder[] results;

    public Team(String name, Runnable[] members){
        this.name = name;
        this.members = members;
        this.results = new StringBuilder[members.length];
    }

    public void showMembersInfo(){
        for(Runnable member: members){
            if(member instanceof Cat) member = (Cat)member;
            if(member instanceof Robot) member = (Robot)member;
            if(member instanceof Person) member = (Person)member;

            System.out.println(member.toString());
        }
    }

    public void showResults(){
        for(StringBuilder str: results){
            System.out.println(str);
        }

    }
}
