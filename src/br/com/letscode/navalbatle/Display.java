package br.com.letscode.navalbatle;

import br.com.letscode.navalbatle.exceptions.InvalidCoordsException;

import java.util.Objects;
import java.util.Scanner;

public class Display {

    private static final GameControl ctrl = new GameControl();

    public static void main(String[] args) {

        System.out.println("---------------------------------------------");
        System.out.println("         BEM-VINDO À BATALHA NAVAL!");
        System.out.println("---------------------------------------------");

        String[][] table = ctrl.getTable();

        try(Scanner in = new Scanner(System.in)){

            System.out.print("# Dê um nome à sua tropa de navios: ");
            ctrl.setArmyName(in.nextLine());

            printTable(table, false);

            for (int i = 0; i < 10; i++){
                do {
                    try {
                        System.out.printf("Posicione seu %d° navio: ", i + 1);
                        table = ctrl.placeShip(in.next());
                        printTable(table, false);
                        break;
                    } catch (InvalidCoordsException err) {
                        System.out.println(err.message);
                    }
                }while (true);
            }

            System.out.println("Posicionando navios do adversário");
            ctrl.placeComputerShips();

            boolean finished = false;
            while(!finished){
                printTable(table, false);
                System.out.printf("%s: %d%n", ctrl.getArmyName(), ctrl.getNumberOfPlayerShips());
                System.out.printf("Number of computer ships: %d%n", ctrl.getNumberOfComputerShips());

                do {
                    try {
                        System.out.println("");
                        System.out.print("Onde você deseja atacar? ");
                        finished = ctrl.play(in.next());
                        break;
                    } catch (InvalidCoordsException err) {
                        System.out.println(err.message);
                    }
                }while (true);
            }

            printTable(ctrl.getTable(), true);

        }

    }

    private static void printTable(String[][] table, boolean gameEnded){
        printTableHeader(gameEnded);
        System.out.println("|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");

        for (int i = 0; i < table.length; i++){
            System.out.printf("| %s |", switchIntToCorrespondentLetter(i));
            for (String cell : table[i]){

                String printable;
                if (Objects.isNull(cell))
                    printable = "~";
                else
                    printable = gameEnded ? cell : hideGameControls(cell);

                System.out.printf(" %s |", printable);
            }
            System.out.println("");
        }
    }

    private static void printTableHeader(boolean gameEnded) {
        System.out.println("---------------------------------------------");

        if (gameEnded){
            if (ctrl.getWinner() != "computer") {
                System.out.println("           PARABÉNS! VOCÊ VENCEU!");
            }
            else {
                System.out.println("     FIM DE JOGO. MELHOR SORTE NA PRÓXIMA!");
            }
        }else {
            System.out.println("                    JOGADOR");
        }

        System.out.println("---------------------------------------------");
    }

    private static String hideGameControls(String cell) {
        String printable;
        if (cell == "C" || cell == "_" || cell == "c" || cell == "#" || cell == "Z"){
            printable = "~";
        }else if (cell == "2"){
            printable = "N";
        }else{
            printable = cell;
        }
        return printable;
    }

    private static String switchIntToCorrespondentLetter(int i){
        switch (i){
            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
            case 3:
                return "D";
            case 4:
                return "E";
            case 5:
                return "F";
            case 6:
                return "G";
            case 7:
                return "H";
            case 8:
                return "I";
            case 9:
                return "J";
            default:
                return "K";
        }
    }
}
