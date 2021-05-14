package sample.apps.authentication.exception;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(){
        super("User already exist, try with another username!");
    }
}
