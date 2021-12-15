package br.com.letscode.navalbatle;

public class Tests {

    public static void main(String[] args) {

        GameControl ctrl = new GameControl();

        // test
        String[][] table = ctrl.placeShips(new String[]{"A2", "C5", "F6", "B2", "J1", "H6", "C3", "D0", "E9", "A5"});
        for (String[] row : table){
            for (String cell : row){
                System.out.printf("| %s ", cell);
            }
            System.out.println("");
        }
    }
}
