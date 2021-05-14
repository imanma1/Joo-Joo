package sample.listener;

import sample.apps.messages.model.Message;

public interface MessageListener {
    void listen(Message message, String command);
}
