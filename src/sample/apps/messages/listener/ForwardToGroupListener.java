package sample.apps.messages.listener;

import sample.apps.messages.controller.MessagesController;
import sample.apps.messages.model.Message;
import sample.listener.MessageListener;

public class ForwardToGroupListener implements MessageListener {
    private final MessagesController controller;

    public ForwardToGroupListener(){
        controller = new MessagesController();
    }

    @Override /// command = groupName
    public void listen(Message message, String command) {
        controller.forwardToGroup(command, message);
    }
}
