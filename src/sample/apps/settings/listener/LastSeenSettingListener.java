package sample.apps.settings.listener;

import sample.apps.authentication.model.LastSeenSetting;
import sample.apps.settings.conroller.SettingsController;
import sample.listener.StringListener;

public class LastSeenSettingListener implements StringListener {
    private SettingsController settingsController;

    public LastSeenSettingListener() {
        this.settingsController = new SettingsController();
    }

    @Override
    public void listen(String command) {
        if (command.equals("Public") ||
        command.equals("Private") ||
        command.equals("Followers"))
            settingsController.changeLastSeenSettings(LastSeenSetting.valueOf(command.toUpperCase()));
    }
}
