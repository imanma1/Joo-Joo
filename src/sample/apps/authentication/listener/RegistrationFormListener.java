package sample.apps.authentication.listener;

import sample.apps.authentication.controller.AuthController;
import sample.apps.authentication.event.AccessFormEvent;
import sample.apps.authentication.exception.UserAlreadyExistException;
import sample.listener.FormListener;

public class RegistrationFormListener implements FormListener {
    AuthController controller = new AuthController();



    @Override
    public void eventOccurred(AccessFormEvent formEvent) throws UserAlreadyExistException {
        controller.register(formEvent);
    }
}
