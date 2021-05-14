package sample.apps.authentication.exception;

public class WrongPasswordException extends Exception{
    public WrongPasswordException(){
        super("Wrong password, try with another password!");
    }
}
