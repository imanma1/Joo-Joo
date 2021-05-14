package sample.apps.authentication.listener;

import sample.apps.authentication.controller.AuthController;
import sample.apps.authentication.event.AccessFormEvent;
import sample.apps.authentication.exception.UserDoesNotExistException;
import sample.apps.authentication.exception.WrongPasswordException;
import sample.listener.FormListener;

public class LoginFormListener implements FormListener {
    AuthController controller = new AuthController();

    @Override
    public void eventOccurred(AccessFormEvent formEvent) throws UserDoesNotExistException, WrongPasswordException {
        controller.login(formEvent);
    }
}
