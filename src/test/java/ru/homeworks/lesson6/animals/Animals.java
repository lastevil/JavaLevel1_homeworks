package ru.homeworks.lesson6.animals;

public class Animals {
    protected String name;
    private static int number;

   Animals(String name){
       this.name=name;
       number++;
    }

    protected void run(int a){
        System.out.println("Животное "+name+" пробежало"+a+" метров.");
    }

    protected void swim(int a){
        System.out.println("Животное "+name+" проплыло"+a+" метров.");
    }
    public static int getCount(){
       return number;
    }
}
