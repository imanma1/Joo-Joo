package sample.apps.settings.listener;

import sample.apps.settings.conroller.SettingsController;
import sample.listener.StringListener;

public class AccountPrivacySettingListener implements StringListener {
    private SettingsController settingsController;

    public AccountPrivacySettingListener() {
        this.settingsController = new SettingsController();
    }

    @Override
    public void listen(String command) {
        if (command.equals("Public"))
            settingsController.changePrivacySettings(false);
        else if (command.equals("Private"))
            settingsController.changePrivacySettings(true);
    }
}
