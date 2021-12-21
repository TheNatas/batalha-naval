package br.com.letscode.navalbatle.exceptions;

public class InvalidCoordsException extends RuntimeException{

    public String message;

    public InvalidCoordsException(String message){
        super(message);
        this.message = message;
    }
}
