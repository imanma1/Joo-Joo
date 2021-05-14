package sample.listener;

import sample.apps.messages.model.Messages;

public interface MessagesListener {
    void listen(Messages messages, String command);
}
