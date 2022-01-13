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

            String ans;
            do{
                System.out.print("# Deseja posicionar seus navios automaticamente? (S/N) ");
                ans = in.next().toUpperCase();
            }while (ans.equals("S") && ans.equals("N"));

            if (ans.equals("S")){
                for (int i = 0; i < 10; i++){
                    table = ctrl.autoPlaceShip();
                }
            }
            else{
                for (int i = 0; i < 10; i++) {
                    do {
                        try {
                            System.out.printf("# Posicione seu %d° navio: ", i + 1);
                            table = ctrl.placeShip(in.next());
                            printTable(table, false);
                            break;
                        } catch (InvalidCoordsException err) {
                            System.out.println(err.message);
                        }
                    } while (true);
                }
            }

            System.out.println("Posicionando navios do adversário...");
            ctrl.placeComputerShips();

            boolean finished = false;
            while(!finished){
                printTable(table, false);
                System.out.printf("%s: %d%n", ctrl.getArmyName(), ctrl.getNumberOfPlayerShips());
                System.out.printf("Tropa inimiga: %d%n", ctrl.getNumberOfComputerShips());

                do {
                    try {
                        System.out.println("");
                        System.out.print("# Onde você deseja atacar? ");
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
            for (int j = 0; j < table[i].length; j++){

                String printable;
                if (Objects.isNull(table[i][j]))
                    printable = " ";
                else
                    printable = gameEnded ? showAllShips(table[i][j], i, j) : hideGameControls(table[i][j]);

                System.out.printf(" %s |", printable);
            }
            System.out.println("");
        }

//        System.out.println("Ataques do computador: ");
//        for (Play play : ctrl.computerAttacks){
//            if (Objects.isNull(play)) break;
//            System.out.printf("%d%d - %b%n", play.coordsX, play.coordsY, play.computerRightShot);
//        }
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
        if (cell == "C"){
            return " ";
        }else if (cell == "2"){
            return "N";
        }else{
            return cell;
        }
    }

    private static String showAllShips(String cell, int coordsX, int coordsY){
        for (Play play : ctrl.computerAttacks){ // mapear os ataques certos do computador para recolocar os navios lá
            if (Objects.isNull(play)) break;
            if (play.coordsX == coordsX && play.coordsY == coordsY) {

                if (play.computerRightShot == true) {
                    return "N";
                }
            }
        }

        if (cell == "*"){
            return "C";
        }else if (cell == "X"){
            return "2";
        }else if (cell == "n"){
            return "N";
        }else{
            return cell;
        }
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
