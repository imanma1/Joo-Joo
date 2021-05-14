package sample.apps.settings.listener;

import sample.apps.authentication.exception.WrongPasswordException;
import sample.apps.settings.conroller.SettingsController;
import sample.apps.settings.event.ChangePasswordEvent;
import sample.listener.PasswordListener;

public class ChangePasswordListener implements PasswordListener {
    private SettingsController settingsController;

    public ChangePasswordListener(){
        settingsController = new SettingsController();
    }

    @Override
    public void eventOccurred(ChangePasswordEvent changePasswordEvent) throws WrongPasswordException {
        settingsController.changePassword(changePasswordEvent);
    }
}
