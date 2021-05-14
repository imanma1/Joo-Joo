package sample.listener;

import sample.apps.authentication.exception.WrongPasswordException;
import sample.apps.settings.event.ChangePasswordEvent;

public interface PasswordListener {
    void eventOccurred(ChangePasswordEvent changePasswordEvent) throws WrongPasswordException;
}
