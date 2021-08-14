package ru.geekbrains.lesson4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Lesson4 {
    private static char[][] map;

    public static final char DOT_EMPTY = '_';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';

    //координаты последнего хода игрока
    public static int lastTurnX;
    public static int lastTurnY;
    //счетчик ходов
    public static int counterPlayer;
    public static final int SIZE = 5;
    public static final int DOTS_TO_WIN = 4;

    public static Scanner scanner;
    public static Random random;


    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        counterPlayer = 0;


        initMap();
        printMap();
        if (whoFirst() == false) {
            turnAI();
            printMap();
        }
        while (true) {
            turnPlayer();
            printMap();
            if (checkWin(DOT_X) == true) {
                System.out.println("Вы выйграли!");
                break;
            }
            if (isMapFull() == true) {
                System.out.println("Ничья!");
                break;
            }
            turnAI();
            printMap();
            if (checkWin(DOT_O) == true) {
                System.out.println("Вы проиграли...");
                break;
            }
            if (isMapFull() == true) {
                System.out.println("Ничья!");
                break;
            }
        }


    }


    //создание поля размеров SIZExSIZE
    private static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }
    //Вывод в консоль поля
    private static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            if (i == 0) {
                System.out.print("  ");
            } else System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    //ход игрока
    private static void turnPlayer() {
        int x, y;
        do {
            System.out.println("Введите координаты хода:");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!checkTurn(x, y));
        map[x][y] = DOT_X;
        lastTurnX = x;
        lastTurnY = y;
        counterPlayer++;
    }
    //ход машины
    private static void turnAI() {
        int x, y;
        int j3 = SIZE - 1;
        random = new Random();
        int[] max = new int[SIZE*2 + 2];
        do {
            //для первого хода
            //точка Х
            //          if (counterPlayer<=1) {
            if (lastTurnX == 0) {
                x = Math.round(random.nextInt(2));
            } else if (lastTurnX == 2) {
                x = 1 + random.nextInt(lastTurnX);
            } else
                x = random.nextInt(SIZE);
            //точка Y
            if (lastTurnY == 0) {
                y = Math.round(random.nextInt(2));
            } else if (lastTurnY == 2) {
                y = 1 + random.nextInt(lastTurnY);
            } else
                y = random.nextInt(SIZE);
            //  }
            //         else {
            //считаем колличество Х по строкам и столбцам с диагоналями
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (map[i][j] == DOT_X) {
                        max[i] += 1;
                    }
                    if (map[j][i] == DOT_X) {
                        max[i+SIZE] += 1;
                    }
                }

                if (map[i][i] == DOT_X) {
                    max[max.length - 2] += 1;
                }
                if (map[i][j3] == DOT_X) {
                    max[max.length - 1] += 1;
                    j3--;
                }
            }
        System.out.println(Arrays.toString(max));

        } while (!checkTurnAI(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[x][y] = DOT_O;

    }
    //проверка хода
    private static boolean checkTurn(int x, int y) {
        if (x < 0 | y < 0 | x >= SIZE | y >= SIZE) {
            System.out.println("Неверные координаты.");
            return false;
        }
        if (map[x][y] == DOT_EMPTY) {
            return true;
        }
        System.out.println("Клетка занята.");
        return false;
    }
    //проверка хода компьютера (без вывода ошибок ввода)
    private static boolean checkTurnAI(int x, int y) {
        if (x < 0 | y < 0 | x >= SIZE | y >= SIZE) {
            return false;
        }
        if (map[x][y] == DOT_EMPTY) {
            return true;
        }
        return false;
    }
    //проверка победы
    private static boolean checkWin(char symb) {
        int checkColumn = 0;
        int checkLine = 0;
        int checkDiag1 = 0;
        int checkDiag2 = 0;
        int y = SIZE - 1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == symb) {
                    //проверка столбца
                    for (int k = 0; k < SIZE; k++) {
                        if (map[i][k] == symb) {
                            checkColumn++;
                            if (checkColumn == DOTS_TO_WIN) {
                                return true;
                            }
                        } else checkColumn = 0;
                    }
                    checkColumn = 0;
                    //проверка строки
                    for (int k = 0; k < SIZE; k++) {
                        if (map[k][i] == symb) {
                            checkLine++;
                            if (checkLine == DOTS_TO_WIN) {
                                return true;
                            }
                        } else checkLine = 0;
                    }
                    checkLine = 0;
                }
            }
            //проверка диагоналей
            if (map[i][i] == symb)
                checkDiag1++;
            if (checkDiag1 == DOTS_TO_WIN) {
                return true;
            }


            if (map[i][y] == symb) {
                checkDiag2++;
                if (checkDiag2 == DOTS_TO_WIN) {
                    return true;
                }
                y--;
            }
        }
        return false;
    }
    //кто ходит первый
    private static boolean whoFirst() {
        int x = (int) Math.round(Math.random() * 10);
        int y = (int) Math.round(Math.random() * 10);
        if (x < y) {
            System.out.println("Первый ход компьютера");
            return false;
        }
        System.out.println("Вы ходите первым");
        return true;
    }
    //проверка на ничью
    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;

    }

}
