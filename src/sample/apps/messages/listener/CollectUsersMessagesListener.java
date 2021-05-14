package sample.apps.messages.listener;

import sample.apps.messages.controller.MessagesController;
import sample.apps.messages.model.Messages;
import sample.listener.CollectMessagesListener;

import java.util.LinkedList;

public class CollectUsersMessagesListener implements CollectMessagesListener {
    private final MessagesController controller;

    public CollectUsersMessagesListener(){
        controller = new MessagesController();
    }

    @Override
    public LinkedList<Messages> collectMessages() {
        return controller.collectUsersMessages();
    }
}
