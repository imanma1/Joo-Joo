package sample.apps.messages.listener;

import sample.apps.messages.controller.MessagesController;
import sample.apps.messages.event.GroupInformationEvent;
import sample.apps.messages.model.Group;
import sample.listener.CollectGroupInformationListener;

public class GroupInformationListener implements CollectGroupInformationListener {
    private final MessagesController controller;

    public GroupInformationListener(){
        controller = new MessagesController();
    }

    @Override
    public GroupInformationEvent collectInformation(Group group) {
        return controller.collectGroupInformation(group);
    }
}
