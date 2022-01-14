package br.com.letscode.navalbatle;

import br.com.letscode.navalbatle.exceptions.InvalidCoordsException;
import br.com.letscode.navalbatle.helpers.AutoGenerator;

import java.util.Objects;
import java.util.Scanner;

public class Display {

    private static final Game game = new Game();

    public static void main(String[] args) {

        System.out.println("---------------------------------------------");
        System.out.println("         BEM-VINDO À BATALHA NAVAL!");
        System.out.println("---------------------------------------------");

        try(Scanner in = new Scanner(System.in)){

            System.out.print("# Dê um nome à sua tropa de navios: ");
            game.player.armyName = in.nextLine();

            printTable(false);

            String ans;
            do{
                System.out.print("# Deseja posicionar seus navios automaticamente? (S/N) ");
                ans = in.next().toUpperCase();
            }while (!ans.equals("S") && !ans.equals("N"));

            if (ans.equals("S")){
                for (int i = 0; i < 10; i++){
                    game.placeShip(game.player, game.player.addShip(AutoGenerator.generateFreeCoords(game.table, TableCells.PLAYER_SHIP)));
                }
            }
            else{
                for (int i = 0; i < 10; i++) {
                    do {
                        try {
                            System.out.printf("# Posicione seu %d° navio: ", i + 1);
                            game.placeShip(game.player, game.player.addShip(validateCoords(in.next())));
                            printTable(false);
                            break;
                        } catch (InvalidCoordsException err) {
                            System.out.println(err.message);
                        }
                    } while (true);
                }
            }

            System.out.println("# Posicionando navios do adversário...");
            game.placeComputerShips();

            while(!game.finished){
                printTable(false);
                System.out.printf("%s: %d%n", game.player.armyName, game.player.getNumberOfRemainingShips());
                System.out.printf("Tropa inimiga: %d%n", game.computer.getNumberOfRemainingShips());

                do {
                    try {
                        System.out.println("");
                        System.out.print("# Onde você deseja atacar? ");
                        game.newPlay(validateCoords(in.next()));
                        break;
                    } catch (InvalidCoordsException err) {
                        System.out.println(err.message);
                    }
                }while (true);
            }

            printTable(true);
        }

    }

    private static Coords validateCoords(String coords) throws InvalidCoordsException{
        if (coords.length() != 2)
            throw new InvalidCoordsException("Coordenadas inválidas");

        int coordsX = switchLetterToCorrespondentInt(coords.toUpperCase().charAt(0));
        if (coordsX == 10)
            throw new InvalidCoordsException("Coordenada X inválida");

        int coordsY;

        try{
            coordsY = Integer.parseInt(Character.toString(coords.charAt(1)));
        }catch (NumberFormatException err){
            throw new InvalidCoordsException("Coordenada Y inválida");
        }catch (StringIndexOutOfBoundsException err){
            throw new InvalidCoordsException("Coordenadas inválidas");
        }

        return new Coords(coordsX, coordsY);
    }



    private static void printTable(boolean gameEnded){
        printTableHeader(gameEnded);
        System.out.println("|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");

        for (int i = 0; i < game.table.length; i++){
            System.out.printf("| %s |", switchIntToCorrespondentLetter(i));
            for (int j = 0; j < game.table[i].length; j++){

                TableCells printable;
                if (Objects.isNull(game.table[i][j]))
                    printable = TableCells.WATER;
                else
                    printable = gameEnded ? showAllShips(game.table[i][j], new Coords(i, j)) : hideComputerShips(game.table[i][j]);

                System.out.printf(" %s |", printable);
            }
            System.out.println("");
        }

    }

    private static void printTableHeader(boolean gameEnded) {
        System.out.println("---------------------------------------------");

        if (gameEnded){
            if (game.winner == game.player) {
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

    private static TableCells hideComputerShips(TableCells cell) {

        if (cell == TableCells.COMPUTER_SHIP){
            return TableCells.WATER;
        }else if (cell == TableCells.BOTH_SHIPS){
            return TableCells.PLAYER_SHIP;
        }else{
            return cell;
        }
    }

    private static TableCells showAllShips(TableCells cell, Coords coords){

        for (int i = 0; i < 10; i++){
            if (!Objects.isNull(game.coordsWithBothShips[i])) {
                if (game.coordsWithBothShips[i].X == coords.X && game.coordsWithBothShips[i].Y == coords.Y) {
                    return TableCells.BOTH_SHIPS;
                }
            }
            else if (game.player.ships[i].coords.X == coords.X && game.player.ships[i].coords.Y == coords.Y){
                return TableCells.PLAYER_SHIP;
            }
            else if (game.computer.ships[i].coords.X == coords.X && game.computer.ships[i].coords.Y == coords.Y){
                return TableCells.COMPUTER_SHIP;
            }
        }

        return cell;
    }

    private static int switchLetterToCorrespondentInt(char letter){

        switch (letter){
            case 'A':
                return 0;
            case 'B':
                return 1;
            case 'C':
                return 2;
            case 'D':
                return 3;
            case 'E':
                return 4;
            case 'F':
                return 5;
            case 'G':
                return 6;
            case 'H':
                return 7;
            case 'I':
                return 8;
            case 'J':
                return 9;
            default:
                return 10;
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
