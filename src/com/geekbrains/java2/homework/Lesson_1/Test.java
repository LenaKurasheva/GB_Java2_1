package com.geekbrains.java2.homework.Lesson_1;

public class Test {
    public static void main(String[] args) {
        
        Treadmill treadmill = new Treadmill(20);
        Wall wall = new Wall(3);
        Barrier[] barriers = {treadmill, wall};
        
        Cat cat = new Cat("Vasiliy",5,3);
        Cat cat2 = new Cat("Myrzik", 25, 3);
        Robot robot = new Robot(100000, 5);
        Person person = new Person("Ivan",2000,2);
        Runnable[] participants = {cat , robot, person, cat2};

        Course c = new Course(barriers); // Создаем полосу препятствий
        Team team = new Team("WINNERS", participants); // Создаем команду
        c.doIt(team); // Просим команду пройти полосу
        team.showResults(); // Показываем результаты



//    Задание из методички:Создайте два массива: с участниками и препятствиями,
//    и заставьте всех участников пройти этот набор препятствий. Если участник
//    не смог пройти одно из препятствий, то дальше по списку он препятствий не идет:

//    for (Runnable participant : participants) {
//
//            if(participant instanceof Cat) participant = (Cat)participant;
//            if(participant instanceof Robot) participant = (Robot)participant;
//            if(participant instanceof Person) participant = (Person)participant;
//
//            System.out.print(participant.toString());
//
//            for (int j = 0; j < barriers.length; j++) {
//
//                if (barriers[j] instanceof Treadmill) {
//                    if (!participant.run((Treadmill) barriers[j])) {
//                        break;
//                    }
//                }
//                if (barriers[j] instanceof Wall) {
//                    ((Jumpable) participant).jump((Wall) barriers[j]);
//
//                }
//            }
//
//        }
        
    }
}
