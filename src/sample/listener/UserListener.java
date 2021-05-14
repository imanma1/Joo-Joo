package sample.listener;

import sample.apps.authentication.model.User;

public interface UserListener {
    void listen(User user, String command);
}
