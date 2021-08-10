package ru.homeworks.lesson3;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Lesson_3 {
    public static void main(String[] args) {
        System.out.println("Задание 1");
        arraysReverse(8);
        System.out.println();
        System.out.println("Задание 2");
        arrayCreate();
        System.out.println();
        System.out.println("Задание 3");
        arraysChekSix( 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 );
        System.out.println();
        System.out.println("Задание 4");
        arraysDiag(5);
        System.out.println();
        System.out.println("Задание 5");
        System.out.println(Arrays.toString(arraysMaker(6,7)));
        System.out.println();
        System.out.println("Задание 6*");
        arrayMaxMin(10);
    }

    //Задание 1
    private static void arraysReverse(int a){
        int[] mas = new int[a];
        for (int i = 0; i < mas.length; i++) {
            mas[i]=(int)Math.round(Math.random());
        }
        System.out.println(Arrays.toString(mas));
        for (int i = 0; i < mas.length; i++) {
            if (mas[i]==0){
                mas[i]=1;
            } else{
                mas[i]=0;
            }
        }
        System.out.println(Arrays.toString(mas));
    }
    //Задание 2
    private static void arrayCreate(){
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=i+1;
        }
        System.out.println(Arrays.toString(arr));
    }
    //Задание 3
    private static void arraysChekSix(int... arr){
        for (int i = 0; i <arr.length ; i++) {
            if (arr[i]<6){
                arr[i]=arr[i]*2;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
    //Задание 4
    private static void arraysDiag(int a){
        int[][] arr = new int[a][a];
        int j =arr.length-1;
        for (int i = 0; i < arr.length; i++) {
            arr[i][i]=1;
            arr[i][j]=1;
            j--;
            System.out.println(Arrays.toString(arr[i]));
        }
    }

    private static int[] arraysMaker(int len,int initValue){
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=initValue;
        }
        return arr;
    }
    private static void arrayMaxMin(int len){
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=(int)Math.round(Math.random()*30-15);
        }
        System.out.println("Задан массив:\n"+Arrays.toString(arr));
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i <arr.length; i++) {
            if(max<=arr[i]){
                max = arr[i];
            }
            if (min>=arr[i]){
                min=arr[i];
            }
        }
        System.out.println("Максимум массива: "+max+"\nМинимум массива: "+min);
    }
}
