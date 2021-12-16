package br.com.letscode.navalbatle;

public class GameControl {

    private static String[][] table = new String[10][10];

    public String[][] getTable() {
        return this.table;
    }

    public String[][] placeShips(String[] unconvertedCoords){
        int[][] allShipsCoords = convertCoords(unconvertedCoords);

        this.placePlayerShips(allShipsCoords);
        this.placeComputerShips();

        return this.table;
    }

    private void placePlayerShips(int[][] allShipsCoords){

        for (int[] shipCoords : allShipsCoords){
            table[shipCoords[0]][shipCoords[1]] = "\uD83D\uDEA2";
        }

    }

    private void placeComputerShips(){
        for (int i = 0; i < 10; i++){
            int[] shipCoords = new int[2];

            boolean computerShipAlreadyInTheseCoords = this.table[shipCoords[0]][shipCoords[1]] == "C";
            boolean playerShipAlreadyInTheseCoords = this.table[shipCoords[0]][shipCoords[1]] == "\uD83D\uDEA2";

            do{
                shipCoords[0] = (int) (Math.random() * 10);
                shipCoords[1] = (int) (Math.random() * 10);
            }
            while (computerShipAlreadyInTheseCoords);

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

    public void play(String shipCoords){
        int coordsX = this.switchLetterToCorrespondentInt(shipCoords.charAt(0));
        int coordsY = Integer.parseInt(Character.toString(shipCoords.charAt(1)));

        // return new Play(new int[]{coordsX , coordsY});
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
