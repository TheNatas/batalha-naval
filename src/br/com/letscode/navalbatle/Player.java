package br.com.letscode.navalbatle;

import java.util.Objects;

public class Player {

    public TableCells shipSymbol;
    public String armyName;
    public Ship[] ships = new Ship[10];
    public Coords[] attacks = new Coords[100];

    public Ship addShip(Coords coords){
        for (int i = 0; i < this.ships.length; i++){
            if (Objects.isNull(this.ships[i])){
                this.ships[i] = new Ship(coords);
                return this.ships[i];
            }
        }

        return this.ships[this.ships.length-1];
    }

    public int getNumberOfRemainingShips(){
        int numberOfRemainingShips = 10;

        for (Ship ship : this.ships){
            if (ship.isDestroyed){
                numberOfRemainingShips--;
            }
        }

        return numberOfRemainingShips;
    }

}
