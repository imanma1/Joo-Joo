package sample.apps.messages.event;

import sample.apps.messages.model.Message;

import java.util.EventObject;
import java.util.LinkedList;

public class MessagesInformationEvent extends EventObject {
    private final String username;
    private final LinkedList<Message> messages;
    private final boolean isSeen;

    public MessagesInformationEvent(Object source, String username, LinkedList<Message> messages, boolean isSeen) {
        super(source);
        this.username = username;
        this.messages = messages;
        this.isSeen = isSeen;
    }

    public String getUsername() {
        return username;
    }

    public LinkedList<Message> getMessages() {
        return messages;
    }

    public boolean isSeen() {
        return isSeen;
    }
}
