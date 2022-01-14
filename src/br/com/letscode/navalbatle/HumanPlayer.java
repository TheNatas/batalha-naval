package br.com.letscode.navalbatle;

import br.com.letscode.navalbatle.exceptions.InvalidCoordsException;

public class HumanPlayer extends Player implements Attack{

    public HumanPlayer(){
        this.shipSymbol = TableCells.PLAYER_SHIP;
    }

    public TableCells[][] attack(TableCells[][] table, Coords coords){

        boolean alreadyAttackedHere = table[coords.X][coords.Y] == TableCells.MISSED_ATTACK || table[coords.X][coords.Y] == TableCells.CRITICAL_ATTACK || table[coords.X][coords.Y] == TableCells.SHIP_AND_MISSED_ATTACK || table[coords.X][coords.Y] == TableCells.SHIP_AND_CRITICAL_ATTACK;

        if (alreadyAttackedHere){
            throw new InvalidCoordsException("Você já atacou aqui");
        }

        TableCells coordsContent = table[coords.X][coords.Y];

        if (coordsContent == TableCells.COMPUTER_SHIP){
            table[coords.X][coords.Y] = TableCells.CRITICAL_ATTACK;
        }
        else if (coordsContent == TableCells.BOTH_SHIPS){
            table[coords.X][coords.Y] = TableCells.SHIP_AND_CRITICAL_ATTACK;
        }
        else if (coordsContent == TableCells.PLAYER_SHIP){
            table[coords.X][coords.Y] = TableCells.SHIP_AND_MISSED_ATTACK;
        }
        else{
            table[coords.X][coords.Y] = TableCells.MISSED_ATTACK;
        }

        return table;
    }
}
