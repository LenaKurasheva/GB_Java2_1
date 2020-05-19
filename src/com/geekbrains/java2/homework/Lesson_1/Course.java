package com.geekbrains.java2.homework.Lesson_1;

public class Course {
    Barrier[] barriers;

    public Course(Barrier[] barriers){
        this.barriers = barriers;
    }
    void doIt(Team team){
        for ( int i = 0; i < team.members.length; i ++) {

            if(team.members[i] instanceof Cat) team.members[i] = (Cat)team.members[i];
            if(team.members[i] instanceof Person) team.members[i] = (Person)team.members[i];
            if(team.members[i] instanceof Robot) team.members[i] = (Robot)team.members[i];

            team.results[i] = new StringBuilder("");
            team.results[i].append(team.members[i].toString());

            for (int j = 0; j < barriers.length; j++) {

                if (barriers[j] instanceof Treadmill) {
                    if (!team.members[i].run((Treadmill) barriers[j])) {
                        team.results[i].append(barriers[j].getClass().getSimpleName() + " FAILED ");
                        break;
                    } else team.results[i].append(barriers[j].getClass().getSimpleName() + " PASSED; ");
                }
                if (barriers[j] instanceof Wall) {
                    if(!((Jumpable) team.members[i]).jump((Wall) barriers[j])){
                        team.results[i].append(barriers[j].getClass().getSimpleName() + " FAILED");
                    } else team.results[i].append(barriers[j].getClass().getSimpleName() + " PASSED");
                }
            }
        }
    }
}
