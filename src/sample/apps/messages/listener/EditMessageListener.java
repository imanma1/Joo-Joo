package sample.apps.messages.listener;

import sample.apps.messages.controller.MessagesController;
import sample.apps.messages.model.Message;
import sample.listener.MessageListener;

public class EditMessageListener implements MessageListener {
    private final MessagesController controller;

    public EditMessageListener(){
        controller = new MessagesController();
    }

    @Override /// command = new content
    public void listen(Message message, String command) {
        controller.editMessage(message, command);
    }
}
