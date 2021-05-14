package sample.apps.messages.listener;

import sample.apps.messages.controller.MessagesController;
import sample.apps.messages.model.Group;
import sample.listener.CollectGroupsListener;

import java.util.LinkedList;

public class CollectGroupsMessagesListener implements CollectGroupsListener {
    private final MessagesController controller;

    public CollectGroupsMessagesListener(){
        controller = new MessagesController();
    }

    @Override
    public LinkedList<Group> collectGroups() {
        return controller.collectGroupsMessages();
    }
}
