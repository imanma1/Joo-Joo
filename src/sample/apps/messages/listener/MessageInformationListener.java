package sample.apps.messages.listener;

import sample.apps.messages.controller.MessagesController;
import sample.apps.messages.event.MessageInformationEvent;
import sample.apps.messages.model.Message;
import sample.listener.CollectMessageInformationListener;

public class MessageInformationListener implements CollectMessageInformationListener {
    private final MessagesController controller;

    public MessageInformationListener(){
        controller = new MessagesController();
    }

    @Override
    public MessageInformationEvent collectInformation(Message message) {
        return controller.collectMessageInformation(message);
    }
}
