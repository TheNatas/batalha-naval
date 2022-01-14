package br.com.letscode.navalbatle;

public class Attack extends Game {

    public boolean computerRightShot = false;

    public Coords coords;

    public Attack(Coords coords, TableCells target){
        this.coords = coords;

        if (target == TableCells.COMPUTER_SHIP)
            playerPlay();
        else
            computerPlay();
    }

    private void playerPlay(){
        TableCells coordsContent = getTable()[this.coords.X][this.coords.Y];

        if (coordsContent == TableCells.COMPUTER_SHIP){
            table[this.coords.X][this.coords.Y] = TableCells.CRITICAL_ATTACK;
            numberOfComputerShips--;
        }else if (coordsContent == TableCells.BOTH_SHIPS){
            table[this.coords.X][this.coords.Y] = TableCells.SHIP_AND_CRITICAL_ATTACK;
            numberOfComputerShips--;
        }else if (coordsContent == TableCells.PLAYER_SHIP){
            table[this.coords.X][this.coords.Y] = TableCells.SHIP_AND_MISSED_ATTACK;
        }else{
            table[this.coords.X][this.coords.Y] = TableCells.MISSED_ATTACK;
        }
    }

    private void computerPlay(){
        TableCells coordsContent = getTable()[this.coords.X][this.coords.Y];
        if (coordsContent == TableCells.PLAYER_SHIP){
            table[this.coords.X][this.coords.Y] = TableCells.WATER;
            numberOfPlayerShips--;
            this.computerRightShot = true;
        }else if (coordsContent == TableCells.BOTH_SHIPS){
            table[this.coords.X][this.coords.Y] = TableCells.COMPUTER_SHIP;
            numberOfPlayerShips--;
            this.computerRightShot = true;
        }
    }

}
