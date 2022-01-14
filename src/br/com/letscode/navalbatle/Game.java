package br.com.letscode.navalbatle;

import br.com.letscode.navalbatle.exceptions.InvalidCoordsException;

import java.util.Objects;
import java.util.Random;

public class Game {

    protected static TableCells[][] table = new TableCells[10][10];
    protected static int numberOfPlayerShips = 0;
    protected static int numberOfComputerShips = 0;
    public Attack[] computerAttacks = new Attack[100];
    private String armyName;
    private String winner;

    public TableCells[][] getTable() {
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

    public TableCells[][] placeShip(String coords) throws InvalidCoordsException{
        Coords checkedCoords = this.checkCoordsValidity(coords);

        if (table[checkedCoords.X][checkedCoords.Y] == TableCells.PLAYER_SHIP) throw new InvalidCoordsException("Você já tem um navio nessas coordenadas");

        table[checkedCoords.X][checkedCoords.Y] = TableCells.PLAYER_SHIP;
        numberOfPlayerShips++;

        return table;
    }

    public TableCells[][] autoPlaceShip(){
        int[] coords = generateFreeCoords(TableCells.PLAYER_SHIP);
        table[coords[0]][coords[1]] = TableCells.PLAYER_SHIP;
        numberOfPlayerShips++;

        return table;
    }

    private int[] generateFreeCoords(TableCells coordsContentToAvoid){
        int shipCoordsX;
        int shipCoordsY;

        boolean shipAlreadyInTheseCoords;

        do{
            shipCoordsX = rand.nextInt(10);
            shipCoordsY = rand.nextInt(10);
            shipAlreadyInTheseCoords = Objects.equals(this.table[shipCoordsX][shipCoordsY], coordsContentToAvoid);
        }
        while (shipAlreadyInTheseCoords);

        return new int[]{shipCoordsX, shipCoordsY};
    }

    private Coords checkCoordsValidity(String coords) throws InvalidCoordsException{
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

        return new Coords(coordsX, coordsY);
    }

    public void placeComputerShips(){
        for (int i = 0; i < 10; i++){
            int[] shipCoords = generateFreeCoords(TableCells.COMPUTER_SHIP);

            boolean playerShipAlreadyInTheseCoords = Objects.equals(this.table[shipCoords[0]][shipCoords[1]], TableCells.PLAYER_SHIP);

            if (playerShipAlreadyInTheseCoords){
                this.table[shipCoords[0]][shipCoords[1]] = TableCells.BOTH_SHIPS;
            }else {
                this.table[shipCoords[0]][shipCoords[1]] = TableCells.COMPUTER_SHIP;
            }

            this.numberOfComputerShips++;
        }
    }

    public boolean play(String shipCoords) throws InvalidCoordsException{
        Coords checkedCoords = this.checkCoordsValidity(shipCoords);
        boolean alreadyAttackedHere = this.table[checkedCoords.X][checkedCoords.Y] == TableCells.MISSED_ATTACK || this.table[checkedCoords.X][checkedCoords.Y] == TableCells.CRITICAL_ATTACK || this.table[checkedCoords.X][checkedCoords.Y] == TableCells.SHIP_AND_MISSED_ATTACK || this.table[checkedCoords.X][checkedCoords.Y] == TableCells.SHIP_AND_CRITICAL_ATTACK;

        if (alreadyAttackedHere){
            throw new InvalidCoordsException("Você já atacou aqui");
        }

        new Attack(checkedCoords, TableCells.COMPUTER_SHIP);

        if (this.numberOfComputerShips == 0) {
            this.winner = this.armyName;
            return true;
        }

        return computerPlay();
    }

    private boolean computerPlay(){
        boolean alreadyAttackedHere = false;

        int coordsX;
        int coordsY;
        do{
            coordsX = rand.nextInt(10);
            coordsY = rand.nextInt(10);

            for (Attack attack : computerAttacks){
                if (Objects.isNull(attack))
                    break;
                alreadyAttackedHere = attack.coords.X == coordsX && attack.coords.Y == coordsY;
            }
        }while (alreadyAttackedHere);

        for (int i = 0; true; i++){
            if (Objects.isNull(computerAttacks[i])) {
                computerAttacks[i] = new Attack(new Coords(coordsX, coordsY), TableCells.PLAYER_SHIP);
                break;
            }
        }

        if (this.numberOfPlayerShips == 0) {
            this.winner = "computer";
            return true;
        }

        return false;
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