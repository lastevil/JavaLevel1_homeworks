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
        System.out.println();
        System.out.println("Задание 7**");
        System.out.println(checkBalance(1, 1, 1, 2, 1));
        System.out.println();
        System.out.println("Задание 8***");
        int[] mas = {1,2,3,4,5,6};
        System.out.println(Arrays.toString(arrayShift(mas,-2)));
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
    //Задание 5
    private static int[] arraysMaker(int len,int initValue){
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=initValue;
        }
        return arr;
    }
    //Задание 6*
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
    //Задание 7**
    private static boolean checkBalance(int... arr){
        int []a= new int[arr.length-1];
        int []b= new int[arr.length-1];
        int summLeft=arr[0];
        int summRight=arr[arr.length-1];

        //массив сумм слева
        for (int i = 0; i < arr.length-1; i++) {
            summLeft=summLeft+arr[i+1];
            a[i]=summLeft;
        }
     //   System.out.println(Arrays.toString(a));

        //массив сумм справа
        for (int i=arr.length-1;i>0; i--) {
            summRight=summRight+arr[i-1];
            b[i-1]=summRight;
        }
        int index_i=0;
                int index_j=0;
    //    System.out.println(Arrays.toString(b));

        //проверка равенства сумм и их соседство
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (a[i]==b[j]&&Math.abs(i-j)!=a.length-1){
                    index_i=i;
                    index_j=j;
                    if(index_i+1==index_j-1){
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }
    //задание 8***
    private static int[] arrayShift(int[] arr,int n){
        System.out.print(Arrays.toString(arr)+" => ");
        if (n<0){
        do{
            int a=arr[0];
            for (int i = 0; i < arr.length-1; i++) {
                arr[i]=arr[i+1];
            }
            arr[arr.length-1]=a;
           n++;
        }while (n<0);
        }

        if (n>0){
            do {
                int a=arr[arr.length-1];
                for (int i = arr.length-1; i > 0; i--) {
                arr[i]=arr[i-1];
            }
                arr[0]=a;
             n--;
            }while (n>0);
        }
        return arr;
    }
}
