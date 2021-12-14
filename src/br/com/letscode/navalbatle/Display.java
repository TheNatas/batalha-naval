package br.com.letscode.navalbatle;

import java.util.Scanner;

public class Display {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        printHeader();
        GameControl ctrl = new GameControl(); // Ex.: ctrl.placePlayerShips()

        // placeShips(String[] coords) => retorna a matriz atualizada
        // (filtrar para mostrar ao usuário apenas o que ele deve ver)
        // * as coordenadas podem ser passadas como letra para a linha
        // * e número para a coluna, conforme o modelo do PDF
    }

    private static void printHeader() {
        System.out.println("---------------------------------------------");
        System.out.println("                    JOGADOR");
        System.out.println("---------------------------------------------");
        System.out.println("|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");
    }

}
