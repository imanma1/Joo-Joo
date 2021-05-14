package sample.listener;

import sample.apps.notifications.model.Request;

public interface RequestListener {
    void listen(Request request, String command);
}
