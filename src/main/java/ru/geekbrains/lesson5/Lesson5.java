package ru.geekbrains.lesson5;

public class Lesson5 {
    public static void main(String[] args) {
        Employee[] persons = {
                new Employee("Васильев Егор", "Безработный",
                        "msil@cec.ru", "3543345343", 435, 30),
                new Employee("Пернай Иван", "Безработный",
                        "msil@cec.ru", "1234567876", 12753, 40),
                new Employee("Щекатилов Алексей", "Безработный",
                        "msil@cec.ru", "3453462626", 123423, 27),
                new Employee("Полонская Алина", "Безработный",
                        "msil@cec.ru", "4215346325", 175473, 45),
                new Employee("Завьялова Мария", "Безработный",
                        "msil@cec.ru", "987634567876", 7542, 50)
        };

        for (int i = 0; i < persons.length; i++) {
           if (persons[i].getAge()>=40) {
           persons[i].info();
           }
        }
    }
}
