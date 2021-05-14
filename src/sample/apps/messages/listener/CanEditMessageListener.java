package sample.apps.messages.listener;

import sample.apps.messages.controller.MessagesController;
import sample.apps.messages.model.Message;

public class CanEditMessageListener {

    public boolean canEdit(Message message){
        return new MessagesController().canEdit(message);
    }
}
