package br.com.letscode.navalbatle;

public class Tests {

    public static void main(String[] args) {

        GameControl ctrl = new GameControl();

        printTable(ctrl.getTable());

        // test
        String[][] table = ctrl.placeShips(new String[]{"A2", "C5", "F6", "B2", "J1", "H6", "C3", "D0", "E9", "A5"});
        printTable(table);

    }

    private static void printTable(String[][] table){
        System.out.println("---------------------------------------------");
        System.out.println("                    JOGADOR");
        System.out.println("---------------------------------------------");
        System.out.println("|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");

        for (int i = 0; i < table.length; i++){
            System.out.printf("| %s |", switchIntToCorrespondentLetter(i));
            for (String cell : table[i]){
                System.out.printf(" %s |", cell);
            }
            System.out.println("");
        }
    }

    private static String switchIntToCorrespondentLetter(int i){
        switch (i){
            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
            case 3:
                return "D";
            case 4:
                return "E";
            case 5:
                return "F";
            case 6:
                return "G";
            case 7:
                return "H";
            case 8:
                return "I";
            case 9:
                return "J";
            default:
                return "K";
        }
    }
}
