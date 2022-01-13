package br.com.letscode.navalbatle;

public class Play extends GameControl{

    public int coordsX;
    public int coordsY;
    public boolean computerRightShot = false;

    public Play(int coordsX, int coordsY, String target){
        this.coordsX = coordsX;
        this.coordsY = coordsY;

        if (target == "C")
            playerPlay(coordsX, coordsY);
        else
            computerPlay(coordsX, coordsY);
    }

    private void playerPlay(int coordsX, int coordsY){
        String coordsContent = getTable()[coordsX][coordsY];

        if (coordsContent == "C"){
            table[coordsX][coordsY] = "*";
            numberOfComputerShips--;
        }else if (coordsContent == "2"){
            table[coordsX][coordsY] = "X";
            numberOfComputerShips--;
        }else if (coordsContent == "N"){
            table[coordsX][coordsY] = "n";
        }else{
            table[coordsX][coordsY] = "-";
        }
    }

    private void computerPlay(int coordsX, int coordsY){
        String coordsContent = getTable()[coordsX][coordsY];
        if (coordsContent == "N"){
            table[coordsX][coordsY] = " ";
            numberOfPlayerShips--;
            this.computerRightShot = true;
        }else if (coordsContent == "2"){
            table[coordsX][coordsY] = "C";
            numberOfPlayerShips--;
            this.computerRightShot = true;
        }
    }

}
