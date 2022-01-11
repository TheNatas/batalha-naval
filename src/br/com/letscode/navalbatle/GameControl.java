package br.com.letscode.navalbatle;

import br.com.letscode.navalbatle.exceptions.InvalidCoordsException;

import java.util.Objects;
import java.util.Random;

public class GameControl {

    private final String[][] table = new String[10][10];
    private int numberOfPlayerShips = 0;
    private int numberOfComputerShips = 0;
    private String armyName;
    private String winner;

    public String[][] getTable() {
        return this.table;
    }
    public int getNumberOfPlayerShips() {
        return numberOfPlayerShips;
    }
    public int getNumberOfComputerShips() {
        return numberOfComputerShips;
    }
    public String getArmyName() {
        return armyName;
    }
    public String getWinner() {
        return winner;
    }

    public void setArmyName(String armyName) {
        this.armyName = armyName;
    }

    private final Random rand = new Random();

    public String[][] placeShip(String coords) throws InvalidCoordsException{
        int[] checkedCoords = this.checkCoordsValidity(coords);

        if (this.table[checkedCoords[0]][checkedCoords[1]] == "N") throw new InvalidCoordsException("Você já tem um navio nessas coordenadas");

        this.table[checkedCoords[0]][checkedCoords[1]] = "N";
        this.numberOfPlayerShips++;

        return this.table;
    }

    private int[] checkCoordsValidity(String coords) throws InvalidCoordsException{
        if (coords.length() != 2) throw new InvalidCoordsException("Coordenadas inválidas");

        int coordsX = this.switchLetterToCorrespondentInt(coords.toUpperCase().charAt(0));
        if (coordsX == 10) throw new InvalidCoordsException("Coordenada X inválida");
        int coordsY;
        try{
            coordsY = Integer.parseInt(Character.toString(coords.charAt(1)));
        }catch (NumberFormatException err){
            throw new InvalidCoordsException("Coordenada Y inválida");
        }catch (StringIndexOutOfBoundsException err){
            throw new InvalidCoordsException("Coordenadas inválidas");
        }

        return new int[]{coordsX, coordsY};
    }

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

            this.numberOfComputerShips++;
        }
    }

    public boolean play(String shipCoords) throws InvalidCoordsException{
        int[] checkedCoords = this.checkCoordsValidity(shipCoords);

        if (this.table[checkedCoords[0]][checkedCoords[1]] == "-" || this.table[checkedCoords[0]][checkedCoords[1]] == "*"){
            throw new InvalidCoordsException("Você já atacou aqui");
        }

        // this.winner = playMethod(checkedCoords);

        computerPlay();

        return rand.nextBoolean(); // TODO: Deve retornar true quando o jogo estiver terminado e false para o contrário
    }

    private void computerPlay(){
        boolean alreadyAttackedHere;

        int coordsX;
        int coordsY;
        do{
            coordsX = rand.nextInt(10);
            coordsY = rand.nextInt(10);
            alreadyAttackedHere = Objects.equals(this.table[coordsX][coordsY], "_") || Objects.equals(this.table[coordsX][coordsY], "#") || Objects.equals(this.table[coordsX][coordsY], "c") || Objects.equals(this.table[coordsX][coordsY], "Z");
        }while (alreadyAttackedHere);

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
