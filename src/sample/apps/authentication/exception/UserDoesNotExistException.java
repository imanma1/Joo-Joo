package sample.apps.authentication.exception;

public class UserDoesNotExistException extends Exception {
    public UserDoesNotExistException(){
        super("User does not exist. try with another username!");
    }
}
