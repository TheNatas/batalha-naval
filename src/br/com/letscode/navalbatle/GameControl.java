package br.com.letscode.navalbatle;

public class GameControl {

    public static String[][] table = new String[10][10];

    public static String[][] getTable() {
        return table;
    }

    public static void placePlayerShips(String[] unconvertedCoords){
        int[][] allShipsCoords = convertCoords(unconvertedCoords);

        for (int[] shipCoords : allShipsCoords){
            table[shipCoords[0]][shipCoords[1]] = "N";
        }
    }

    public static void placeComputerShips(){
        int[][] allShipsCoords = new int[10][2];

        for (int i = 0; i < allShipsCoords.length; i++){
            for (int j = 0; j < allShipsCoords[i].length; j++){
                allShipsCoords[i][j] = (int) (Math.random() * 10);
            }
            if (table[allShipsCoords[i][0]][allShipsCoords[i][1]] == "C"){

            }else{
                table[allShipsCoords[i][0]][allShipsCoords[i][1]] = "C";
            }
        }

        for (int[] shipCoords : allShipsCoords){
            table[shipCoords[0]][shipCoords[1]] = "C";
        }
    }

    private static int[][] convertCoords(String[] shipsCoords) {
        int[][] convertedCoords = new int[10][2];

        for(int i = 0; i < shipsCoords.length; i++){
            int coordsY = Integer.parseInt(Character.toString(shipsCoords[i].charAt(1)));

            switch (shipsCoords[i].toUpperCase().charAt(0)){
                case 'A':
                    convertedCoords[i][0] = 0;
                    break;
                case 'B':
                    convertedCoords[i][0] = 1;
                    break;
                case 'C':
                    convertedCoords[i][0] = 2;
                    break;
                case 'D':
                    convertedCoords[i][0] = 3;
                    break;
                case 'E':
                    convertedCoords[i][0] = 4;
                    break;
                case 'F':
                    convertedCoords[i][0] = 5;
                    break;
                case 'G':
                    convertedCoords[i][0] = 6;
                    break;
                case 'H':
                    convertedCoords[i][0] = 7;
                    break;
                case 'I':
                    convertedCoords[i][0] = 8;
                    break;
                case 'J':
                    convertedCoords[i][0] = 9;
                    break;
            }
            convertedCoords[i][1] = coordsY;
        }
        return convertedCoords;
    }
}
