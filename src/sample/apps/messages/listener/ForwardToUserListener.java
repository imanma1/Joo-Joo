package sample.apps.messages.listener;

import sample.apps.messages.controller.MessagesController;
import sample.apps.messages.model.Message;
import sample.listener.MessageListener;

public class ForwardToUserListener implements MessageListener {
    private final MessagesController controller;

    public ForwardToUserListener(){
        controller = new MessagesController();
    }

    @Override  /// command = username
    public void listen(Message message, String command) {
        controller.forwardToUser(command, message);
    }
}
