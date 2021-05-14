package sample.apps.settings.listener;

import sample.apps.settings.conroller.SettingsController;
import sample.listener.StringListener;

public class DeleteAccountListener implements StringListener {
    SettingsController settingsController;

    public DeleteAccountListener(){
        settingsController = new SettingsController();
    }

    @Override
    public void listen(String command) {
        if (command.equals("Delete")) settingsController.deleteAccount();
    }
}
