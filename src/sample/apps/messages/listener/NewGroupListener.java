package sample.apps.messages.listener;

import sample.apps.messages.controller.MessagesController;
import sample.listener.StringListener;

public class NewGroupListener implements StringListener {
    private final MessagesController controller;

    public NewGroupListener(){
        controller = new MessagesController();
    }

    @Override /// command = groupName
    public void listen(String command) {
        controller.newGroup(command);
    }
}
