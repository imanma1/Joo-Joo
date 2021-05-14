package sample.apps.authentication.event;

import java.util.EventObject;

public class AccessFormEvent extends EventObject {

    String username;
    String password;

    public AccessFormEvent(Object source, String username, String password) {
        super(source);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
