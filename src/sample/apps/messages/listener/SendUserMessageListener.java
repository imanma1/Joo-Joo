package sample.apps.messages.listener;

import sample.apps.messages.controller.MessagesController;
import sample.apps.messages.model.Messages;
import sample.listener.MessagesListener;

public class SendUserMessageListener {
    private final MessagesController controller;

    public SendUserMessageListener(){
        controller = new MessagesController();
    }

    public void listen(Messages messages, String message, String imagePath) {
        controller.sendUserMessage(messages, message, imagePath);
    }
}
