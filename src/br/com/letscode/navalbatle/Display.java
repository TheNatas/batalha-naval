package br.com.letscode.navalbatle;

import java.util.Scanner;

public class Display {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        printHeader();

    }

    private static void printHeader() {
        System.out.println("---------------------------------------------");
        System.out.println("                    JOGADOR");
        System.out.println("---------------------------------------------");
        System.out.println("|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");
    }


}
