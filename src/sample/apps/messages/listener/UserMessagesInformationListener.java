package sample.apps.messages.listener;

import sample.apps.messages.controller.MessagesController;
import sample.apps.messages.event.MessagesInformationEvent;
import sample.apps.messages.model.Messages;
import sample.listener.CollectUserMessagesInformationListener;

public class UserMessagesInformationListener implements CollectUserMessagesInformationListener {
    private final MessagesController controller;

    public UserMessagesInformationListener(){
        controller = new MessagesController();
    }

    @Override
    public MessagesInformationEvent collectInformation(Messages messages) {
        return controller.collectMessagesInformation(messages);
    }
}
