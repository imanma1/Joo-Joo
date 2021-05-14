package sample.apps.messages.listener;

import sample.apps.messages.controller.MessagesController;
import sample.apps.messages.model.Group;

import java.util.LinkedList;

public class CollectUsernamesForAddingUserListener {
    private final MessagesController controller;

    public CollectUsernamesForAddingUserListener(){
        controller = new MessagesController();
    }

    public LinkedList<String> collectUsernames(Group group){
        return controller.collectUsernamesForAddingMembers(group);
    }
}
