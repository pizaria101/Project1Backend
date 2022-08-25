package dev.schulte.exceptions;

public class PasswordShortException extends RuntimeException{

    public PasswordShortException(String message){
        super(message);
    }
}
