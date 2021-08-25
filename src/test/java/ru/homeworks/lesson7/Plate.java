package ru.homeworks.lesson7;

public class Plate {
    private int food;
    public Plate(int food) {
        this.food = food;
    }

    public void addFood(int food) {
        System.out.println("в тарелку добавили "+food+" еды");
        this.food += food;
    }

    public int decreaseFood(int n) {
//        условие для недоедания кота
//        if (food==0){
//            return 0;
//        }else if (n>food){
//            int a = n-food;
//            food=-food;
//            return a;
//        }
        if (food<n) {
            return 1;
        }
        food -= n;
        return -1;
    }

    public void info() {
        System.out.println("plate: " + food);
    }

}
