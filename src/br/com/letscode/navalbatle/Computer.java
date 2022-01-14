package br.com.letscode.navalbatle;

import br.com.letscode.navalbatle.helpers.AutoGenerator;

import java.util.Objects;

public class Computer extends Player implements Attack{

    public Computer(){
        this.shipSymbol = TableCells.COMPUTER_SHIP;
    }

    public TableCells[][] attack(TableCells[][] table, Coords coords){

        for (int i = 0; true; i++){
            if (Objects.isNull(this.attacks[i])) {
                this.attacks[i] = coords;
                break;
            }
        }

        TableCells coordsContent = table[coords.X][coords.Y];
        if (coordsContent == TableCells.PLAYER_SHIP){
            table[coords.X][coords.Y] = TableCells.WATER;
        }
        else if (coordsContent == TableCells.SHIP_AND_MISSED_ATTACK){
            table[coords.X][coords.Y] = TableCells.MISSED_ATTACK;
        }
        else if (coordsContent == TableCells.SHIP_AND_CRITICAL_ATTACK){
            table[coords.X][coords.Y] = TableCells.CRITICAL_ATTACK;
        }
        else if (coordsContent == TableCells.BOTH_SHIPS){
            table[coords.X][coords.Y] = TableCells.COMPUTER_SHIP;
        }

        return table;
    }
}
