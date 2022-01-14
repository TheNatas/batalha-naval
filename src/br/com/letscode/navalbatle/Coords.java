package br.com.letscode.navalbatle;

public class Coords {

    public int X;
    public int Y;

    public Coords(int X, int Y){
        this.X = X;
        this.Y = Y;
    }

    @Override
    public String toString(){
        return String.format("%d%d", this.X, this.Y);
    }
}
