package br.com.letscode.navalbatle;

import br.com.letscode.navalbatle.exceptions.InvalidCoordsException;
import br.com.letscode.navalbatle.helpers.AutoGenerator;

import java.util.Objects;
import java.util.Random;

public class Game {

    public TableCells[][] table = new TableCells[10][10];
    public final HumanPlayer player = new HumanPlayer();
    public final Computer computer = new Computer();
    public Coords[] coordsWithBothShips = new Coords[10];
    boolean finished = false;
    public Player winner;

    private final Random rand = new Random();

    public void placeShip(Player currentPlayer, Ship ship) throws InvalidCoordsException{

        boolean playerShipAlreadyHere = this.table[ship.coords.X][ship.coords.Y] == TableCells.PLAYER_SHIP;

        if (currentPlayer == player && playerShipAlreadyHere){
            throw new InvalidCoordsException("Você já tem um navio nessas coordenadas");
        }
        else if (currentPlayer == computer && playerShipAlreadyHere){

            table[ship.coords.X][ship.coords.Y] = TableCells.BOTH_SHIPS;

            for (int i = 0; i < 10; i++){
                if (Objects.isNull(coordsWithBothShips[i])){
                    coordsWithBothShips[i] = ship.coords;
                    break;
                }
            }
        }
        else {
            table[ship.coords.X][ship.coords.Y] = currentPlayer.shipSymbol;
        }

    }

    public void placeComputerShips(){
        for (int i = 0; i < 10; i++){
            this.placeShip(computer, this.computer.addShip(AutoGenerator.generateFreeCoords(this.table, TableCells.COMPUTER_SHIP)));
        }
    }

    public void newPlay(Coords coords) throws InvalidCoordsException{

        this.table = player.attack(this.table, coords);
        this.finished = isGameOver(computer, coords);
        if (finished) {
            this.winner = player;
            return;
        }

        this.table = computer.attack(this.table, coords);
        this.finished = isGameOver(player, coords);
        if (finished) {
            this.winner = computer;
            return;
        }

    }

    private boolean isGameOver(Player target, Coords coords) {
        for (int i = 0; i < target.ships.length; i++){

            if (target.ships[i].coords.X == coords.X && target.ships[i].coords.Y == coords.Y){
                target.ships[i].isDestroyed = true;

                return target.getNumberOfRemainingShips() == 0;
            }
        }

        return false;
    }


}
