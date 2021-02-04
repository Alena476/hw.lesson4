package lesson4;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static char[][] map;
    public static final int SIZE = 3;

    public static final char DOT_EMPTY = '*';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    public static void main(String[] args) {

        initMap();
        printMap();

        while (true) {
            humanTurn();
            printMap();
            if(isEndGame(DOT_X, "Игрок победил!")) {
                break;
            };

            aiTurn();
            printMap();
            if(isEndGame(DOT_O, "Компьютер победил!")) {
                break;
            };

        }
        System.out.println("Игра окончена");
    }

    private static void initMap() {
        map = new char[SIZE][SIZE];

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map.length; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    private static void printMap() {
        for (int i = 0; i <= map.length; i++) {
            if( i == 0) {
                System.out.print("\t");
            } else {
                System.out.print(i + "\t");
            }
        }

        System.out.println();
        for (int i = 0; i < map.length; i++) {
            System.out.print((i + 1) +  "\t");
            for (int j = 0; j < map.length; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите, пожалуйста, координаты в формате X, Y");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while(!isCellValid(x, y));
        map[x][y] = DOT_X;
    }

    private static void aiTurn() {
        int x, y;
        do {
            x = random.nextInt(map.length);
            y = random.nextInt(map.length);
        } while(!isCellValid(x, y));
        System.out.println("Компьютер занял ячейку " + (x + 1) + " " + (y + 1));
        map[x][y] = DOT_O;
    }

    private static boolean isCellValid(int x, int y) {
        if(x < 0 || x >= map.length|| y < 0 || y >= map.length) {
            return false;
        }
        return map[x][y] == DOT_EMPTY;
    }

    private static boolean checkWin(char dotValue) {
        for(int i = 0; i < map.length; i++) {
            int countV = 0;

            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == dotValue) {
                    countV++;
                }
                if (countV == map.length) {
                    return true;
                }
            }
        }

        for(int c = 0; c < map.length; c++) {
            int countH = 0;

            for (int j = 0; j < map.length; j++) {
                if (map[j][c] == dotValue) {
                    countH++;
                }
                if (countH == map.length) {
                    return true;
                }
            }
        }
        for(int i = 0; i < map.length; i++) {
            int countD = 0;

            for (int j = 0; j < map.length; j++) {
                if (map[j][j] == dotValue) {
                    countD++;
                }
                if (countD == map.length) {
                    return true;
                }
            }
        }
        for(int i = 0; i < map.length; i++) {
            int countD = 0;

            for (int j = 0; j < map.length; j++) {
                if (map[j][map.length - (j+1)] == dotValue) {
                    countD++;
                }
                if (countD == map.length) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isEndGame(char dotValue, String message) {
        if (checkWin(dotValue)) {
            System.out.println(message);
            return true;
        } else if (isFullMap()) {
            System.out.println("Ничья");
            return true;
        }
        return false;
    }

    private static boolean isFullMap() {

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if(map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

}
