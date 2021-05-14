package sample.apps.messages.listener;

import sample.apps.messages.controller.MessagesController;
import sample.apps.messages.model.Message;
import sample.listener.MessageListener;

public class MessageOptionsListener implements MessageListener {
    private final MessagesController controller;

    public MessageOptionsListener(){
        controller = new MessagesController();
    }

    @Override
    public void listen(Message message, String command) {
        if (command.equals("delete")){
            controller.deleteMessage(message);
        }
    }
}
