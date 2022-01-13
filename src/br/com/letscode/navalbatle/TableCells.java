package br.com.letscode.navalbatle;

public enum TableCells {
    WATER(" "),
    PLAYER_SHIP("N"),
    COMPUTER_SHIP("C"),
    BOTH_SHIPS("2"),
    MISSED_ATTACK("-"),
    CRITICAL_ATTACK("*"),
    SHIP_AND_MISSED_ATTACK("n"),
    SHIP_AND_CRITICAL_ATTACK("X");

    private final String name;

    TableCells(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
