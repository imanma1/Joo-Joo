package sample.apps.messages.listener;

import sample.apps.messages.controller.MessagesController;
import sample.apps.messages.model.Group;
import sample.listener.GroupListener;

public class SendGroupMessageListener {
    private final MessagesController controller;

    public SendGroupMessageListener(){
        controller = new MessagesController();
    }

    public void listen(Group group, String message, String imagePath) {
        controller.sendGroupMessage(group, message, imagePath);
    }
}
