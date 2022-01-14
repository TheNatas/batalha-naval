package br.com.letscode.navalbatle.helpers;

import br.com.letscode.navalbatle.Attack;
import br.com.letscode.navalbatle.Coords;
import br.com.letscode.navalbatle.Player;
import br.com.letscode.navalbatle.TableCells;

import java.util.Objects;
import java.util.Random;

public class AutoGenerator {

    private static final Random rand = new Random();

    public static Coords generateFreeCoords(TableCells[][] table, TableCells coordsContentToAvoid){
        int coordsX;
        int coordsY;

        boolean coordsOccupied;

        do{
            coordsX = rand.nextInt(10);
            coordsY = rand.nextInt(10);
            coordsOccupied = Objects.equals(table[coordsX][coordsY], coordsContentToAvoid);
        }
        while (coordsOccupied);

        return new Coords(coordsX, coordsY);
    }

    public static Coords generateAttack(Player player){
        boolean alreadyAttackedHere = false;

        int coordsX;
        int coordsY;
        do{
            coordsX = rand.nextInt(10);
            coordsY = rand.nextInt(10);

            for (Coords attackCoords : player.attacks){
                if (Objects.isNull(attackCoords))
                    break;
                alreadyAttackedHere = attackCoords.X == coordsX && attackCoords.Y == coordsY;
            }
        }while (alreadyAttackedHere);

        return new Coords(coordsX, coordsY);
    }
}
