package br.com.letscode.navalbatle;

import br.com.letscode.navalbatle.exceptions.InvalidCoordsException;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Random;

public class GameControl {

    private final String[][] table = new String[10][10];

    public String[][] getTable() {
        return this.table;
    }

    private final Random rand = new Random();

    public String[][] placeShip(String coords) throws InvalidCoordsException{
        if (coords.length() > 2) throw new InvalidCoordsException("Coordenadas inválidas");

        int coordsX = this.switchLetterToCorrespondentInt(coords.toUpperCase().charAt(0));
        if (coordsX == 10) throw new InvalidCoordsException("Coordenada X inválida");

        try{
            int coordsY = Integer.parseInt(Character.toString(coords.charAt(1)));
            if (this.table[coordsX][coordsY] == "N") throw new InvalidCoordsException("Você já tem um navio nessas coordenadas");

            this.table[coordsX][coordsY] = "N";
        }catch (NumberFormatException err){
            throw new InvalidCoordsException("Coordenada Y inválida");
        }

        return this.table;
    }

//    public String[][] placeShips(String[] unconvertedCoords) throws InvalidCoordsException{
//        int[][] allShipsCoords = convertCoords(unconvertedCoords);
//
//        this.placePlayerShips(allShipsCoords);
//        this.placeComputerShips();
//
//        return this.table;
//    }
//
//    private void placePlayerShips(int[][] allShipsCoords){
//
//        for (int i = 0; i < allShipsCoords.length; i++){
//            try {
//                this.table[allShipsCoords[i][0]][allShipsCoords[i][1]] = "N";
//            }catch (InputMismatchException err){
//                throw new InvalidCoordsException();
//            }
//        }
//
//    }

    public void placeComputerShips(){
        for (int i = 0; i < 10; i++){
            int[] shipCoords = new int[2];

            boolean computerShipAlreadyInTheseCoords;

            do{
                shipCoords[0] = rand.nextInt(10);
                shipCoords[1] = rand.nextInt(10);
                computerShipAlreadyInTheseCoords = Objects.equals(this.table[shipCoords[0]][shipCoords[1]], "C");
            }
            while (computerShipAlreadyInTheseCoords);

            boolean playerShipAlreadyInTheseCoords = Objects.equals(this.table[shipCoords[0]][shipCoords[1]], "N");

            if (playerShipAlreadyInTheseCoords){
                this.table[shipCoords[0]][shipCoords[1]] = "2";
            }else {
                this.table[shipCoords[0]][shipCoords[1]] = "C";
            }
        }
    }

    private int[][] convertCoords(String[] shipsCoords) {
        int[][] convertedCoords = new int[10][2];

        for(int i = 0; i < shipsCoords.length; i++){

            char shipsCoordsFirstChar = shipsCoords[i].toUpperCase().charAt(0);
            convertedCoords[i][0] = this.switchLetterToCorrespondentInt(shipsCoordsFirstChar);

            convertedCoords[i][1] = Integer.parseInt(Character.toString(shipsCoords[i].charAt(1)));
        }
        return convertedCoords;
    }

    public String[][] play(String shipCoords){
        int coordsX = this.switchLetterToCorrespondentInt(shipCoords.charAt(0));
        int coordsY = Integer.parseInt(Character.toString(shipCoords.charAt(1)));

        // playMethod(coordsX, coordsY);

        computerPlay();

        return this.table;
    }

    private void computerPlay(){
        int coordsX;
        int coordsY;
        do{
            coordsX = rand.nextInt(10);
            coordsY = rand.nextInt(10);
        }while (Objects.equals(this.table[coordsX][coordsY], "_"));

        // computerPlayMethod(coordsX, coordsY);
    }

    private int switchLetterToCorrespondentInt(char letter){

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
}
