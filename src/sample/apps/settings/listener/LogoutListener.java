package sample.apps.settings.listener;

import sample.apps.settings.conroller.SettingsController;
import sample.listener.StringListener;

public class LogoutListener implements StringListener {
    SettingsController settingsController;

    public LogoutListener(){
        settingsController = new SettingsController();
    }

    @Override
    public void listen(String command) {
        if (command.equals("Logout")) settingsController.logout();
    }
}
