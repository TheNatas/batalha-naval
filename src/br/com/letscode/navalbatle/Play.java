package br.com.letscode.navalbatle;

public class Play extends Table {

    public int coordsX;
    public int coordsY;
    public boolean computerRightShot = false;

    public Play(int coordsX, int coordsY, TableCells target){
        this.coordsX = coordsX;
        this.coordsY = coordsY;

        if (target == TableCells.COMPUTER_SHIP)
            playerPlay(coordsX, coordsY);
        else
            computerPlay(coordsX, coordsY);
    }

    private void playerPlay(int coordsX, int coordsY){
        TableCells coordsContent = getTable()[coordsX][coordsY];

        if (coordsContent == TableCells.COMPUTER_SHIP){
            table[coordsX][coordsY] = TableCells.CRITICAL_ATTACK;
            numberOfComputerShips--;
        }else if (coordsContent == TableCells.BOTH_SHIPS){
            table[coordsX][coordsY] = TableCells.SHIP_AND_CRITICAL_ATTACK;
            numberOfComputerShips--;
        }else if (coordsContent == TableCells.PLAYER_SHIP){
            table[coordsX][coordsY] = TableCells.SHIP_AND_MISSED_ATTACK;
        }else{
            table[coordsX][coordsY] = TableCells.MISSED_ATTACK;
        }
    }

    private void computerPlay(int coordsX, int coordsY){
        TableCells coordsContent = getTable()[coordsX][coordsY];
        if (coordsContent == TableCells.PLAYER_SHIP){
            table[coordsX][coordsY] = TableCells.WATER;
            numberOfPlayerShips--;
            this.computerRightShot = true;
        }else if (coordsContent == TableCells.BOTH_SHIPS){
            table[coordsX][coordsY] = TableCells.COMPUTER_SHIP;
            numberOfPlayerShips--;
            this.computerRightShot = true;
        }
    }

}
