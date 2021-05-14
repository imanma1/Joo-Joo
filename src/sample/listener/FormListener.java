package sample.listener;

import sample.apps.authentication.event.AccessFormEvent;
import sample.apps.authentication.exception.UserAlreadyExistException;
import sample.apps.authentication.exception.UserDoesNotExistException;
import sample.apps.authentication.exception.WrongPasswordException;

public interface FormListener {
    void eventOccurred(AccessFormEvent formEvent) throws UserAlreadyExistException, UserDoesNotExistException, WrongPasswordException;
}
