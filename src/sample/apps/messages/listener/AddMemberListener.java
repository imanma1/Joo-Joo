package sample.apps.messages.listener;

import sample.apps.messages.controller.MessagesController;
import sample.apps.messages.model.Group;

public class AddMemberListener {
    private final MessagesController controller;

    public AddMemberListener(){
        controller = new MessagesController();
    }

    public void addMember(Group group, String username){
        controller.addMember(group, username);
    }
}
