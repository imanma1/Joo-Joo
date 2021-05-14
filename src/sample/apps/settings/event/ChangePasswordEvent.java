package sample.apps.settings.event;

import java.util.EventObject;

public class ChangePasswordEvent extends EventObject {
    String oldPassword;
    String newPassword;

    public ChangePasswordEvent(Object source, String oldPassword, String newPassword) {
        super(source);
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
