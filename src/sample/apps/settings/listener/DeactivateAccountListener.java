package sample.apps.settings.listener;

import sample.apps.settings.conroller.SettingsController;
import sample.listener.StringListener;

public class DeactivateAccountListener implements StringListener {
    SettingsController settingsController;

    public DeactivateAccountListener(){
        settingsController = new SettingsController();
    }

    @Override
    public void listen(String command) {
        if (command.equals("Deactivate")) settingsController.deactivateAccount();
    }
}
